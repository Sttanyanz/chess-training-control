package io.github.sttanyanz.chesstc.model;

import io.github.sttanyanz.chesstc.model.exceptions.NegativeTimeSpentException;

public class Session {
    public static final int PLAYING = 0;
    public static final int ANALYSIS = 1;
    public static final int TACTICS = 2;
    public static final int THEORY = 3;

    private static final int OBJECT_COUNT = 4;

    private final StudyObject[] studyObjects;

    public Session() {

        studyObjects = new StudyObject[OBJECT_COUNT];

        studyObjects[PLAYING] = new StudyObject(30, 75);
        studyObjects[ANALYSIS] = new StudyObject(5, 15);
        studyObjects[TACTICS] = new StudyObject(10, 40);
        studyObjects[THEORY] = new StudyObject(10, 25);

    }

    public StudyObject[] getObjects() {
        return studyObjects;
    }

    public StudyObject getObject(int studyObjectID) {
        return studyObjects[studyObjectID];
    }

    public int getTotalTimeSpent() {
        int totalTimeSpent = 0;

        for (StudyObject studyObject : studyObjects) {

            totalTimeSpent += studyObject.getTimeSpent();

        }

        return totalTimeSpent;
    }

    public float getPercentage(int studyObjectID) {

        float studyObjectTimeSpent =
                (float) studyObjects[studyObjectID].getTimeSpent();

        return studyObjectTimeSpent / getTotalTimeSpent() * 100;

    }

    public void reset() throws NegativeTimeSpentException {

        for (StudyObject studyObject : studyObjects) {

            studyObject.setTime(0);

        }

    }
}
