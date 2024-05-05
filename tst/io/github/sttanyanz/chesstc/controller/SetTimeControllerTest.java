package io.github.sttanyanz.chesstc.controller;

import io.github.sttanyanz.chesstc.model.StudyObject;
import io.github.sttanyanz.chesstc.model.exceptions.NegativeInputTimeException;
import io.github.sttanyanz.chesstc.model.exceptions.NegativeTimeSpentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SetTimeControllerTest {

    @Test
    void spendTime()
            throws NegativeTimeSpentException, NegativeInputTimeException {

        final int inputTimeSpent = 120;
        final int expectedTimeSpent = inputTimeSpent * 2;
        final SetTimeController inputController = new SetTimeController();

        inputController.spendTime(StudyObject.playing, inputTimeSpent);
        inputController.spendTime(StudyObject.playing, inputTimeSpent);
        final int actualTimeSpent = StudyObject.playing.getTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);

    }

    @Test
    void testSpendNegativeTime()
            throws NegativeTimeSpentException {

        final int inputTimeSpent = -120;
        final SetTimeController inputController = new SetTimeController();

        try {
            inputController.spendTime(StudyObject.theory, inputTimeSpent);
            fail();
        } catch (final NegativeInputTimeException e) {}

    }

    @Test
    void setTime()
            throws NegativeTimeSpentException, NegativeInputTimeException {

        final int inputTimeSpent = 120;
        final int expectedTimeSpent = inputTimeSpent;
        final SetTimeController inputController = new SetTimeController();

        inputController.setTime(StudyObject.tactics, inputTimeSpent);
        inputController.setTime(StudyObject.tactics, inputTimeSpent);
        final int actualTimeSpent = StudyObject.tactics.getTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);

    }

    @Test
    void testSetNegativeTime()
            throws NegativeTimeSpentException {

        final int inputTimeSpent = -120;
        final SetTimeController inputController = new SetTimeController();

        try {
            inputController.setTime(StudyObject.theory, inputTimeSpent);
            fail();
        } catch (final NegativeInputTimeException e) {}

    }

    @Test
    void reset()
            throws NegativeTimeSpentException, NegativeInputTimeException {

        final int inputTimeSpent = 120;
        final int expectedTimeSpent = StudyObject.DEFAULT_TIME;
        final SetTimeController inputController = new SetTimeController();

        inputController.setTime(StudyObject.analysis, inputTimeSpent);
        inputController.reset();
        final int actualTimeSpent = StudyObject.analysis.getTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);

    }

}