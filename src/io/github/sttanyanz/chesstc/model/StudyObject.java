package io.github.sttanyanz.chesstc.model;

public enum StudyObject {
    playing, analysis, tactics, theory;

    private int timeSpent;

    StudyObject() {
        this.timeSpent = 0;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void addTime(int time) {
        timeSpent += time;
    }

    public void reset() {
        timeSpent = 0;
    }
}
