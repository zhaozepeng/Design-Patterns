package com.android.decoratorpattern.decorator;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-06-09
 */
public abstract class WindowDecorator implements IWindow{

    private IWindow window;

    public WindowDecorator(IWindow window) {
        this.window = window;
    }

    @Override
    public void draw() {
        window.draw();
    }

    @Override
    public String getDescription() {
        return window.getDescription();
    }
}
