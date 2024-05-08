package io.github.sttanyanz.chesstc.model;

import io.github.sttanyanz.chesstc.model.exceptions.NegativeTimeSpentException;

public class StudyObject {

    public static final int DEFAULT_TIME = 0;

    private int timeSpent;
    private final int necessityThreshold;
    private final int sufficiencyThreshold;


    StudyObject(int necessityThreshold, int sufficiencyThreshold) {
        this.necessityThreshold = necessityThreshold;
        this.sufficiencyThreshold = sufficiencyThreshold;
        this.timeSpent = DEFAULT_TIME;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public void addTime(final int time) throws NegativeTimeSpentException {

        timeSpent += time;

        if (isTimeSpentNegative()) {
            throw new NegativeTimeSpentException();
        }

    }

    public void setTime(final int time) throws NegativeTimeSpentException {

        timeSpent = time;

        if (isTimeSpentNegative()) {
            throw new NegativeTimeSpentException();
        }

    }

    private boolean isTimeSpentNegative() {
        return timeSpent < 0;
    }


}
