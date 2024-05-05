package io.github.sttanyanz.chesstc.controller;

import io.github.sttanyanz.chesstc.model.StudyObject;
import io.github.sttanyanz.chesstc.model.exceptions.NegativeInputTimeException;
import io.github.sttanyanz.chesstc.model.exceptions.NegativeTimeSpentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpendTimeControllerTest {

    @Test
    void spendTime()
            throws NegativeTimeSpentException, NegativeInputTimeException {

        final int inputTimeSpent = 120;
        final int expectedTimeSpent = inputTimeSpent;
        final SpendTimeController inputController = new SpendTimeController();

        inputController.spendTime(StudyObject.playing, inputTimeSpent);
        final int actualTimeSpent = StudyObject.playing.getTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);

    }

    @Test
    void testInputNegativeTime()
            throws NegativeInputTimeException, NegativeTimeSpentException {

        final int inputTimeSpent = -120;
        final SpendTimeController inputController = new SpendTimeController();

        try {
            inputController.spendTime(StudyObject.theory, inputTimeSpent);
            fail();
        } catch (final NegativeInputTimeException e) {}

    }

}