package io.github.sttanyanz.chesstc.view;

import io.github.sttanyanz.chesstc.controller.SetTimeController;
import io.github.sttanyanz.chesstc.model.Session;
import io.github.sttanyanz.chesstc.model.StudyObject;
import io.github.sttanyanz.chesstc.model.exceptions.GetPercentageWhenTotalTimeIsZeroException;
import io.github.sttanyanz.chesstc.model.exceptions.NegativeInputTimeException;
import io.github.sttanyanz.chesstc.model.exceptions.NegativeTimeSpentException;
import io.github.sttanyanz.chesstc.model.exceptions.StudyObjectIndexOutOfBoundsException;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {

    private final static int SPEND_TIME = 1;
    private final static int SET_TIME = 2;
    private final static int RESET = 3;
    private final static int EXIT = 0;

    private final static String RESET_COLOR = "\033[0m";
    private final static String GOAL_NOT_REACHED = "\033[0;31m";
    private final static String NECESSITY_REACHED = "\033[0;33m";
    private final static String SUFFICIENCY_REACHED = "\033[0;32m";

    public DecimalFormat df = new DecimalFormat("##0.0#");

    private final SetTimeController setTimeController =
            new SetTimeController();

    public void show(final Session session){

        for (int id = 0; id < session.getObjects().length; ++id) {
            printLine(session, id);
        }

    }

    private void printLine(final Session session, final int studyObjectID){

        final String objectName = getObjectName(studyObjectID);

        final String time = getTimeString(session, studyObjectID);

        float percentage;
        StudyObject studyObject = null;
        try {
            studyObject = session.getObject(studyObjectID);
            percentage = session.getPercentage(studyObjectID);
        } catch (GetPercentageWhenTotalTimeIsZeroException e) {
            percentage = 0;
        } catch (StudyObjectIndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }

        final String color = getColor(percentage,
                studyObject.getNecessityThreshold(),
                studyObject.getSufficiencyThreshold());

        System.out.println(color + objectName + ": " +
                df.format(percentage) + "%, " + time + RESET_COLOR);

    }

    private String getColor(final float percentage,
                            final int necessityThreshold,
                            final int sufficiencyThreshold) {

        if (percentage < necessityThreshold) return GOAL_NOT_REACHED;
        else if (percentage < sufficiencyThreshold) return NECESSITY_REACHED;
        else return SUFFICIENCY_REACHED;

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
            case Session.PLAYING -> objectName = "PLAYING";
            case Session.ANALYSIS -> objectName = "ANALYSING";
            case Session.TACTICS -> objectName = "TACTICS";
            case Session.THEORY -> objectName = "THEORY";
            default -> throw new RuntimeException();
        }

        return objectName;

    }

    public boolean consoleMenu(final Session session) {

        showMenuOptions();

        final int action = inputInteger();

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

    private int inputInteger() {
        final Scanner in = new Scanner(System.in);
        try {
            return in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Wrong Input");
            return inputInteger();
        }
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

    private void spendTime(final Session session) {

        showStudyAreasOptions();

        final StudyObject studyObject;

        try {
            studyObject = session.getObject(inputInteger() - 1);
        } catch (StudyObjectIndexOutOfBoundsException e) {
            return;
        }

        final int time = askTime();

        try {
            setTimeController.spendTime(studyObject, time);
        } catch (NegativeInputTimeException | NegativeTimeSpentException e) {
            System.out.println("Input time shouldn't be negative.");
        }

    }

    private int askTime() {
        System.out.println("Input time (in minutes)");
        return inputInteger();
    }

    private void showStudyAreasOptions() {
        System.out.println("Choose study area:");
        System.out.println();
        System.out.println();
        System.out.println("    1 - Intentional playing");
        System.out.println("    2 - Analysis");
        System.out.println("    3 - Tactics training");
        System.out.println("    4 - Theoretical study");
        System.out.println("    Input any other number to exit");
        System.out.println();
    }

    private void setTime(final Session session) {

        showStudyAreasOptions();

        final StudyObject studyObject;

        try {
            studyObject = session.getObject(inputInteger() - 1);
        } catch (StudyObjectIndexOutOfBoundsException e) {
            return;
        }

        final int time = askTime();

        try {
            setTimeController.setTime(studyObject, time);
        } catch (NegativeInputTimeException | NegativeTimeSpentException e) {
            System.out.println("Input time shouldn't be negative.");
        }

    }

    private void reset(final Session session) {
        try {
            setTimeController.reset(session);
        } catch (NegativeTimeSpentException e) {
            throw new RuntimeException(e);
        }
    }

}
