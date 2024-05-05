package io.github.sttanyanz.chesstc.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudyObjectTest {

    @Test
    void addTime() {
        final int inputTimeSpent = 130;
        final int expectedTimeSpent = inputTimeSpent;

        StudyObject.theory.addTime(inputTimeSpent);
        final int actualTimeSpent = StudyObject.theory.getTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);
    }

    @Test
    void reset() {
        final int inputTimeSpent = 255;
        final int expectedTimeSpent = 0;

        StudyObject.theory.addTime(inputTimeSpent);
        StudyObject.theory.reset();
        final int actualTimeSpent = StudyObject.theory.getTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);
    }
}