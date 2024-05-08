package io.github.sttanyanz.chesstc;

import io.github.sttanyanz.chesstc.model.Session;
import io.github.sttanyanz.chesstc.view.ConsoleView;

public class CTCLI {

    public static void main(final String[] args) {

        final Session session = new Session();

        final ConsoleView consoleView = new ConsoleView();

        consoleView.show(session);

    }

}
