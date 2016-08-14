package com.android.commandpattern.command;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-08-14
 */
public class DoorOpenCommand implements Command{

    public Door door;

    public DoorOpenCommand(Door door) {
        this.door = door;
    }

    @Override
    public void execute() {
        door.doorOpen();
    }
}
