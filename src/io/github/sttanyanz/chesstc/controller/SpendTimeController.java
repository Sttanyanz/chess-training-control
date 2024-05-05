package io.github.sttanyanz.chesstc.controller;

import io.github.sttanyanz.chesstc.model.StudyObject;
import io.github.sttanyanz.chesstc.model.exceptions.NegativeInputTimeException;
import io.github.sttanyanz.chesstc.model.exceptions.NegativeTimeSpentException;

public class SpendTimeController {

    public void spendTime (StudyObject studyObject, int time)
            throws NegativeInputTimeException, NegativeTimeSpentException {

        if (isInputTimeNegative(time)) {
            throw new NegativeInputTimeException();
        }

        studyObject.addTime(time);

    }

    private boolean isInputTimeNegative(int time) {
        return time < 0;
    }

}
