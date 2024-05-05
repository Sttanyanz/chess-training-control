package io.github.sttanyanz.chesstc.controller;

import io.github.sttanyanz.chesstc.model.StudyObject;
import io.github.sttanyanz.chesstc.model.exceptions.NegativeInputTimeException;
import io.github.sttanyanz.chesstc.model.exceptions.NegativeTimeSpentException;

public class SetTimeController {

    public void spendTime (StudyObject studyObject, int time)
            throws NegativeInputTimeException, NegativeTimeSpentException {

        if (isInputTimeNegative(time)) {
            throw new NegativeInputTimeException();
        }

        studyObject.addTime(time);

    }

    public void setTime (StudyObject studyObject, int time)
            throws NegativeInputTimeException, NegativeTimeSpentException {

        if (isInputTimeNegative(time)) {
            throw new NegativeInputTimeException();
        }

        studyObject.setTime(time);

    }

    public void reset () throws NegativeTimeSpentException {

        for ( StudyObject studyObject : StudyObject.values() ) {
            studyObject.setTime( StudyObject.DEFAULT_TIME );
        }

    }

    private boolean isInputTimeNegative(int time) {
        return time < 0;
    }

}
