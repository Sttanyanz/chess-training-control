package io.github.sttanyanz.chesstc.model;

import io.github.sttanyanz.chesstc.model.exceptions.NegativeTimeSpentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudyObjectTest {

    @Test
    void addTime() throws NegativeTimeSpentException {

        final int input1TimeSpent = 120;
        final int input2TimeSpent = 130;
        final int expectedTimeSpent = input1TimeSpent + input2TimeSpent;

        StudyObject.theory.addTime(input1TimeSpent);
        StudyObject.theory.addTime(input2TimeSpent);
        final int actualTimeSpent = StudyObject.theory.getTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);

    }

    @Test
    void setTime() throws NegativeTimeSpentException {

        final int input1TimeSpent = 120;
        final int input2TimeSpent = 130;
        final int expectedTimeSpent = input2TimeSpent;

        StudyObject.playing.setTime(input1TimeSpent);
        StudyObject.playing.setTime(input2TimeSpent);
        final int actualTimeSpent = StudyObject.playing.getTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);

    }

    @Test
    void testSetNegativeTime() throws NegativeTimeSpentException {

        final int inputTimeSpent = -120;

        try {
            StudyObject.tactics.setTime(inputTimeSpent);
            fail();
        } catch (final NegativeTimeSpentException e) {}

    }

    @Test
    void testReduceTimeToNegative() throws NegativeTimeSpentException {

        final int input1TimeSpent = 30;
        final int input2TimeSpent = -213;

        StudyObject.analysis.setTime(input1TimeSpent);

        try {
            StudyObject.tactics.addTime(input2TimeSpent);
            fail();
        } catch (final NegativeTimeSpentException e) {}
    }
}