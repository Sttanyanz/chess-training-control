package io.github.sttanyanz.chesstc.model;

import io.github.sttanyanz.chesstc.model.exceptions.NegativeTimeSpentException;

public enum StudyObject {
    playing, analysis, tactics, theory;

    private int timeSpent;

    StudyObject() {
        this.timeSpent = 0;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void addTime(int time) throws NegativeTimeSpentException {
        timeSpent += time;
        if (isTimeSpentNegative()) {
            throw new NegativeTimeSpentException();
        }
    }

    public void setTime(int time) throws NegativeTimeSpentException {
        timeSpent = time;
        if (isTimeSpentNegative()) {
            throw new NegativeTimeSpentException();
        }
    }

    private boolean isTimeSpentNegative() {
        return timeSpent >= 0;
    }
}
