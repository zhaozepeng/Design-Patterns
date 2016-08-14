package com.android.commandpattern.command;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-08-14
 */
public class Controller {
    private Command[] onCommands;
    private Command[] offCommands;

    public Controller() {
        onCommands = new Command[2];
        offCommands = new Command[2];

        Command noCommand = new NoCommand();
        onCommands[0] = noCommand;
        onCommands[1] = noCommand;
        offCommands[0] = noCommand;
        offCommands[1] = noCommand;
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onCommand(int slot) {
        onCommands[slot].execute();
    }

    public void offCommand(int slot) {
        offCommands[slot].execute();
    }
}
