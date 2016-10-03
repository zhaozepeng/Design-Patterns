package com.android.templatepattern;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-10-02
 */
public class ConcreteTemplateB extends AbstractTemplate{
    @Override
    protected void abstractMethod() {
        System.out.print("ConcreteTemplateB abstractMethod\n");
    }

    @Override
    protected void hookMethod() {
        System.out.print("ConcreteTemplateB hookMethod\n");
    }
}
