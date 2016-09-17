package com.android.mementopattern;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-09-17
 */
public class Originator {
    private int state = 0;

    public void setState(int state) {
        this.state = state;
    }

    public void print() {
        System.out.print("state = " + state + "\n");
    }

    public void restore(Memento memento) {
        setState(memento.getState());
    }

    public Memento createMemoto() {
        Memento memento = new Memento();
        memento.setState(state);
        return memento;
    }

    public static void main(String args[]) {
        Originator originator = new Originator();
        originator.setState(1);
        Caretaker caretaker = new Caretaker();
        caretaker.storeMemento(originator.createMemoto());
        System.out.print("before\n");
        originator.print();
        originator.setState(2);
        System.out.print("after\n");
        originator.print();
        originator.restore(caretaker.restoreMemento());
        System.out.print("restore to the original state\n");
        originator.print();
    }
}
