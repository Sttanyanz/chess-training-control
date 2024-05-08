package io.github.sttanyanz.chesstc.model;

import io.github.sttanyanz.chesstc.model.exceptions.GetPercentageWhenTotalTimeIsZeroException;
import io.github.sttanyanz.chesstc.model.exceptions.NegativeTimeSpentException;
import io.github.sttanyanz.chesstc.model.exceptions.StudyObjectIndexOutOfBoundsException;

public class Session {
    public static final int PLAYING = 0;
    public static final int ANALYSIS = 1;
    public static final int TACTICS = 2;
    public static final int THEORY = 3;

    private static final int OBJECT_COUNT = 4;

    private final StudyObject[] studyObjects;

    public Session() {

        studyObjects = new StudyObject[OBJECT_COUNT];

        studyObjects[PLAYING] = new StudyObject(25, 70);
        studyObjects[ANALYSIS] = new StudyObject(10, 25);
        studyObjects[TACTICS] = new StudyObject(10, 35);
        studyObjects[THEORY] = new StudyObject(10, 25);

    }

    public StudyObject[] getObjects() {
        return studyObjects;
    }

    public StudyObject getObject(int studyObjectID)
            throws StudyObjectIndexOutOfBoundsException {

        if (indexOutOfBounds(studyObjectID))
            throw new StudyObjectIndexOutOfBoundsException();

        return studyObjects[studyObjectID];

    }

    public int getTotalTimeSpent() {
        int totalTimeSpent = 0;

        for (StudyObject studyObject : studyObjects) {

            totalTimeSpent += studyObject.getTimeSpent();

        }

        return totalTimeSpent;
    }

    public float getPercentage(int studyObjectID)
            throws GetPercentageWhenTotalTimeIsZeroException,
            StudyObjectIndexOutOfBoundsException {

        if (totalTimeSpendIsZero())
            throw new GetPercentageWhenTotalTimeIsZeroException();

        final StudyObject studyObject = getObject(studyObjectID);

        float studyObjectTimeSpent =
                (float) studyObject.getTimeSpent();

        return studyObjectTimeSpent / getTotalTimeSpent() * 100;

    }

    public void reset() throws NegativeTimeSpentException {

        for (StudyObject studyObject : studyObjects) {

            studyObject.setTime(0);

        }

    }

    private boolean totalTimeSpendIsZero() {
        return getTotalTimeSpent() == 0;
    }

    private boolean indexOutOfBounds(int index) {
        return ( (index >= OBJECT_COUNT) || (index < 0) );
    }
}
