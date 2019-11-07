package org.jellyfin.androidtv.livetv;

import org.jellyfin.androidtv.ui.ProgramGridCell;

public interface ILiveTvGuide {
    void displayChannels(int start, int max);
    long getCurrentLocalStartDate();
    void showProgramOptions();
    void setSelectedProgram(ProgramGridCell programView);
}
