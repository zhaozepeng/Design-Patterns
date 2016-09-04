package com.android.statepattern;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-09-04
 */
public class ConcreteStateB implements State{
    @Override
    public void doSomething() {
        System.out.print("this is ConcreteStateB's function\n");
    }
}
