package io.github.sttanyanz.chesstc.view;

import io.github.sttanyanz.chesstc.model.Session;
import io.github.sttanyanz.chesstc.model.StudyObject;
import io.github.sttanyanz.chesstc.model.exceptions.StudyObjectIndexOutOfBoundsException;

import java.util.Scanner;

public class ConsoleView {

    private final static int SPEND_TIME = 1;
    private final static int SET_TIME = 2;
    private final static int RESET = 3;
    private final static int EXIT = 0;

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

    public boolean consoleMenu(final Session session) {

        showMenuOptions();

        final int action = askAction();

        switch (action) {
            case SPEND_TIME -> spendTime(session);
            case SET_TIME -> setTime(session);
            case RESET -> reset(session);
            case EXIT -> {
                return false;
            }
            default -> System.out.println("Invalid action");
        }

        return true;

    }

    private int askAction() {
        final Scanner in = new Scanner(System.in);
        return in.nextInt();
    }

    private void showMenuOptions() {

        System.out.println("Chess Training Control Menu");
        System.out.println();
        System.out.println();
        System.out.println("    1 - Spend time on chosen study area");
        System.out.println("    2 - Set time for chosen study area");
        System.out.println("    3 - Reset study time");
        System.out.println("    0 - Exit");
        System.out.println();

    }

    public void spendTime(final Session session) {

    }

    public void setTime(final Session session) {

    }

    public void reset(final Session session) {

    }

}
