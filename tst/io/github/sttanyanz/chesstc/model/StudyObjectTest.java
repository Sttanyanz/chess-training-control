package io.github.sttanyanz.chesstc.model;

import io.github.sttanyanz.chesstc.model.exceptions.NegativeTimeSpentException;
import io.github.sttanyanz.chesstc.model.exceptions.StudyObjectIndexOutOfBoundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudyObjectTest {

    @Test
    void addTime() throws NegativeTimeSpentException,
            StudyObjectIndexOutOfBoundsException {

        final int input1TimeSpent = 120;
        final int input2TimeSpent = 130;
        final int expectedTimeSpent = input1TimeSpent + input2TimeSpent;

        final Session session = new Session();
        final StudyObject theory = session.getObject(Session.THEORY);

        theory.addTime(input1TimeSpent);
        theory.addTime(input2TimeSpent);

        final int actualTimeSpent = theory.getTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);

    }

    @Test
    void setTime() throws NegativeTimeSpentException,
            StudyObjectIndexOutOfBoundsException {

        final int input1TimeSpent = 120;
        final int input2TimeSpent = 130;
        final int expectedTimeSpent = input2TimeSpent;

        final Session session = new Session();
        final StudyObject playing = session.getObject(Session.PLAYING);

        playing.setTime(input1TimeSpent);
        playing.setTime(input2TimeSpent);

        final int actualTimeSpent = playing.getTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);

    }

    @Test
    void testSetNegativeTime() throws NegativeTimeSpentException,
            StudyObjectIndexOutOfBoundsException {

        final int inputTimeSpent = -120;

        final Session session = new Session();
        final StudyObject tactics = session.getObject(Session.TACTICS);

        try {
            tactics.setTime(inputTimeSpent);
            fail();
        } catch (final NegativeTimeSpentException e) {}

    }

    @Test
    void testReduceTimeToNegative() throws NegativeTimeSpentException,
            StudyObjectIndexOutOfBoundsException {

        final int input1TimeSpent = 30;
        final int input2TimeSpent = -213;

        final Session session = new Session();
        final StudyObject analysis = session.getObject(Session.ANALYSIS);

        analysis.setTime(input1TimeSpent);

        try {
            analysis.addTime(input2TimeSpent);
            fail();
        } catch (final NegativeTimeSpentException e) {}
    }

    @Test
    void getNecessityThreshold() throws StudyObjectIndexOutOfBoundsException {
        final int expectedThreshold = 10;

        final Session session = new Session();
        final int actualThreshold =
                session.getObject(Session.TACTICS).getNecessityThreshold();

        assertEquals(expectedThreshold, actualThreshold);

    }

    @Test
    void getSufficiencyThreshold() throws StudyObjectIndexOutOfBoundsException {
        final int expectedThreshold = 75;

        final Session session = new Session();
        final int actualThreshold =
                session.getObject(Session.PLAYING).getSufficiencyThreshold();

        assertEquals(expectedThreshold, actualThreshold);

    }

}