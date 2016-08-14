package com.android.commandpattern.command;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-08-14
 */
public class Client {
    public static void main(String[] args) {
        Light light = new Light();
        Door door = new Door();

        LightOnCommand lightOnCommand = new LightOnCommand(light);
        LightOffCommand lightOffCommand = new LightOffCommand(light);

        DoorOpenCommand doorOpenCommand = new DoorOpenCommand(door);
        DoorCloseCommand doorCloseCommand = new DoorCloseCommand(door);

        Controller controller = new Controller();
        controller.setCommand(0, lightOnCommand, lightOffCommand);
        controller.setCommand(1, doorOpenCommand, doorCloseCommand);

        controller.onCommand(0);
        controller.offCommand(0);
        controller.onCommand(1);
        controller.offCommand(1);
    }
}
