package de.htw.berlin.student.vsys2.both.enums;

/**
 * Possible server commands enum.
 * <p/>
 * Created by matthias.drummer on 04.11.14.
 */
public enum ServerCommands {

    // @formatter:off
    FREE("free"),
    IN("in"),
    OUT("out"),
    QUIT("quit");
    // @formatter:on

    private final String command;

    private ServerCommands(String command) {
        this.command = command;
    }

    public static ServerCommands getFromCommand(String command) {
        for (ServerCommands serverCommand : ServerCommands.values()) {
            if (serverCommand.getCommand().equals(command)) {
                return serverCommand;
            }
        }

        return null;
    }

    public String getCommand() {
        return command;
    }
}
