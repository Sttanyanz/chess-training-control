package io.github.sttanyanz.chesstc.model;

import io.github.sttanyanz.chesstc.model.exceptions.NegativeTimeSpentException;

public enum StudyObject {
    playing(30, 75), analysis(5, 15),
    tactics(10, 40), theory(10, 25);

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

    public static int getTotalTimeSpent() {
        return playing.getTimeSpent() + theory.getTimeSpent()
                + tactics.getTimeSpent() + analysis.getTimeSpent();
    }

    public float getPercentage() {
        return (float) getTimeSpent() / getTotalTimeSpent() * 100;
    }

    public GoalStatus getStatus() {
        float percentage = getPercentage();

        if (percentage > sufficiencyThreshold)
            return GoalStatus.sufficiencyReached;

        else if (percentage >= necessityThreshold)
            return GoalStatus.necessityReached;

        else
            return GoalStatus.notReached;
    }

}
