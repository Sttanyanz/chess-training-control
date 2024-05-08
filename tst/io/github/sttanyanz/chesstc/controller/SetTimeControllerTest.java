package io.github.sttanyanz.chesstc.controller;

import io.github.sttanyanz.chesstc.model.Session;
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
        
        final Session session = new Session();
        final StudyObject playing = session.getObject(Session.PLAYING);

        inputController.spendTime(playing, inputTimeSpent);
        inputController.spendTime(playing, inputTimeSpent);

        final int actualTimeSpent = playing.getTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);

    }

    @Test
    void testSpendNegativeTime()
            throws NegativeTimeSpentException {

        final int inputTimeSpent = -120;
        
        final SetTimeController inputController = new SetTimeController();

        final Session session = new Session();
        final StudyObject theory = session.getObject(Session.THEORY);

        try {
            inputController.spendTime(theory, inputTimeSpent);
            fail();
        } catch (final NegativeInputTimeException e) {}

    }

    @Test
    void setTime()
            throws NegativeTimeSpentException, NegativeInputTimeException {

        final int inputTimeSpent = 120;
        final int expectedTimeSpent = inputTimeSpent;
        
        final SetTimeController inputController = new SetTimeController();

        final Session session = new Session();
        final StudyObject tactics = session.getObject(Session.TACTICS);

        inputController.setTime(tactics, inputTimeSpent);
        inputController.setTime(tactics, inputTimeSpent);

        final int actualTimeSpent = tactics.getTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);

    }

    @Test
    void testSetNegativeTime()
            throws NegativeTimeSpentException {

        final int inputTimeSpent = -120;
        
        final SetTimeController inputController = new SetTimeController();

        final Session session = new Session();
        final StudyObject theory = session.getObject(Session.THEORY);

        try {
            inputController.setTime(theory, inputTimeSpent);
            fail();
        } catch (final NegativeInputTimeException e) {}

    }

    @Test
    void reset()
            throws NegativeTimeSpentException, NegativeInputTimeException {

        final int inputTimeSpent = 120;
        final int expectedTimeSpent = StudyObject.DEFAULT_TIME;
        
        final SetTimeController inputController = new SetTimeController();

        final Session session = new Session();
        final StudyObject analysis = session.getObject(Session.ANALYSIS);

        inputController.setTime(analysis, inputTimeSpent);
        inputController.reset(session);

        final int actualTimeSpent = analysis.getTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);

    }

}