package io.github.sttanyanz.chesstc.model;

import io.github.sttanyanz.chesstc.model.exceptions.NegativeTimeSpentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @Test
    void getTotalTimeSpent() throws NegativeTimeSpentException {

        final int input1TimeSpent = 137;
        final int input2TimeSpent = 24;
        final int input3TimeSpent = 345;
        final int expectedTimeSpent =
                input1TimeSpent + input2TimeSpent + input3TimeSpent;

        final Session session = new Session();

        session.getObject(Session.ANALYSIS).setTime(input1TimeSpent);
        session.getObject(Session.PLAYING).setTime(input2TimeSpent);
        session.getObject(Session.TACTICS).setTime(input3TimeSpent);

        final int actualTimeSpent = session.getTotalTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);

    }

    @Test
    void getPercentage() throws NegativeTimeSpentException {

        final int input1TimeSpent = 34;
        final int input2TimeSpent = 2;
        final int input3TimeSpent = 65;
        final float expectedPercentage = (float) input1TimeSpent /
                (input1TimeSpent + input2TimeSpent + input3TimeSpent) * 100;

        final Session session = new Session();

        session.getObject(Session.TACTICS).setTime(input1TimeSpent);
        session.getObject(Session.PLAYING).setTime(input2TimeSpent);
        session.getObject(Session.THEORY).setTime(input3TimeSpent);

        final float actualPercentage = session.getPercentage(Session.TACTICS);

        assertEquals(expectedPercentage, actualPercentage);

    }

    @Test
    void reset() throws NegativeTimeSpentException {

        final int input1TimeSpent = 137;
        final int input2TimeSpent = 24;
        final int input3TimeSpent = 345;
        final int expectedTimeSpent = 0;

        final Session session = new Session();

        session.getObject(Session.ANALYSIS).setTime(input1TimeSpent);
        session.getObject(Session.THEORY).setTime(input2TimeSpent);
        session.getObject(Session.TACTICS).setTime(input3TimeSpent);

        session.reset();

        final int actualTimeSpent = session.getTotalTimeSpent();

        assertEquals(expectedTimeSpent, actualTimeSpent);

    }
}