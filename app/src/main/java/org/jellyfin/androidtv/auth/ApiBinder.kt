package org.jellyfin.androidtv.auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jellyfin.androidtv.JellyfinApplication
import org.jellyfin.androidtv.util.apiclient.callApi
import org.jellyfin.androidtv.util.apiclient.callApiEmpty
import org.jellyfin.apiclient.interaction.ApiClient
import org.jellyfin.apiclient.interaction.device.IDevice
import org.jellyfin.apiclient.model.apiclient.ServerInfo
import org.jellyfin.apiclient.model.dto.UserDto
import org.jellyfin.apiclient.model.entities.MediaType
import org.jellyfin.apiclient.model.session.ClientCapabilities
import org.jellyfin.apiclient.model.session.GeneralCommandType
import timber.log.Timber

class ApiBinder(
	private val application: JellyfinApplication,
	private val api: ApiClient,
	private val device: IDevice,
	private val authenticationStore: AuthenticationStore,
) {
	fun updateSession(session: Session?, resultCallback: (Boolean) -> Unit) {
		GlobalScope.launch(Dispatchers.IO) {
			@Suppress("TooGenericExceptionCaught")
			try {
				val success = updateSessionInternal(session)
				resultCallback(success)
			} catch (throwable: Throwable) {
				Timber.e(throwable, "Unable to update legacy API session.")
				resultCallback(false)
			}
		}
	}

	private suspend fun updateSessionInternal(session: Session?): Boolean {
		if (session == null) {
			application.currentUser = null
			return true
		}

		val server = authenticationStore.getServer(session.serverId)
		if (server == null) {
			Timber.e("Could not bind API because server ${session.serverId} was not found in the store.")
			return false
		}

		api.setDevice(AuthenticationDevice(device, session.userId.toString()))
		api.SetAuthenticationInfo(session.accessToken, session.userId.toString())
		api.EnableAutomaticNetworking(ServerInfo().apply {
			id = session.serverId.toString()
			name = server.name
			address = server.address
			userId = session.userId.toString()
			accessToken = session.accessToken
		})
		api.ensureWebSocket()

		// Update currentUser DTO
		val user = callApi<UserDto> { callback -> api.GetUserAsync(session.userId.toString(), callback) }
		application.currentUser = user

		callApiEmpty { callback ->
			api.ReportCapabilities(ClientCapabilities().apply {
				setPlayableMediaTypes(arrayListOf(MediaType.Video.toString(), MediaType.Audio.toString()));
				setSupportsMediaControl(true);
				setSupportedCommands(arrayListOf(
					GeneralCommandType.DisplayContent.toString(),
					GeneralCommandType.Mute.toString(),
					GeneralCommandType.Unmute.toString(),
					GeneralCommandType.ToggleMute.toString(),
					GeneralCommandType.DisplayContent.toString(),
				));
			}, callback)
		}

		return true
	}
}
