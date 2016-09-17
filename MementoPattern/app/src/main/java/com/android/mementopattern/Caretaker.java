package com.android.mementopattern;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-09-17
 */
public class Caretaker {
    private Memento memento;

    public Memento restoreMemento() {
        return memento;
    }

    public void storeMemento(Memento memento) {
        this.memento = memento;
    }
}
