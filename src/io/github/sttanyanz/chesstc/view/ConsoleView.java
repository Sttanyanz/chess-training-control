package io.github.sttanyanz.chesstc.view;

import io.github.sttanyanz.chesstc.model.Session;
import io.github.sttanyanz.chesstc.model.StudyObject;
import io.github.sttanyanz.chesstc.model.exceptions.StudyObjectIndexOutOfBoundsException;

public class ConsoleView {

    public void show(final Session session){

        for (int id = 0; id < session.getObjects().length; ++id) {
            printLine(session, id);
        }

    }

    private void printLine(final Session session, final int studyObjectID){

        final String objectName = getObjectName(studyObjectID);

        final String time = getTimeString(session, studyObjectID);

        System.out.println("You have been " + objectName + " for " + time);

    }

    private String getTimeString(final Session session,
                                 final int studyObjectID){

        final StudyObject studyObject;

        try {
            studyObject = session.getObject(studyObjectID);
        } catch (StudyObjectIndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }

        final int time = studyObject.getTimeSpent();
        final int hours = time / 60;
        final int minutes = time % 60;

        String timeString;

        if (minutes == 1) timeString = minutes + " minute";
        else timeString = minutes + " minutes";

        if (hours == 1) timeString = hours + " hour and " + timeString;
        else if (hours > 0) timeString = hours + " hours and " + timeString;

        return timeString;
    }

    private String getObjectName(final int studyObjectID){

        final String objectName;


        switch (studyObjectID) {
            case Session.PLAYING -> objectName = "playing games";
            case Session.ANALYSIS -> objectName = "analysing games";
            case Session.TACTICS -> objectName = "training tactics";
            case Session.THEORY -> objectName = "studying theory";
            default -> throw new RuntimeException();
        }

        return objectName;

    }

    public void spendTime(final Session session) {

    }

    public void setTime(final Session session) {

    }

    public void reset(final Session session) {

    }

}
