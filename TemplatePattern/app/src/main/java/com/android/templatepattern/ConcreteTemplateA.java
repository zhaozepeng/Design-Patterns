package com.android.templatepattern;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-10-02
 */
public class ConcreteTemplateA extends AbstractTemplate{
    @Override
    protected void abstractMethod() {
        System.out.print("ConcreteTemplateA abstractMethod\n");
    }
}
