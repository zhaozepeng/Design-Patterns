package com.android.statepattern;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-09-04
 */
public class ConcreteStateA implements State {
    @Override
    public void doSomething() {
        System.out.print("this is ConcreteStateA's function\n");
    }
}
