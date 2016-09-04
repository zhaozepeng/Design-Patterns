package com.android.statepattern;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-09-04
 */
public class Context {
    private State state = new NullState();

    void setState(State state) {
        this.state = state;
    }

    void doSomething() {
        state.doSomething();
    }

    public static void main(String[] args) {
        Context context = new Context();
        context.setState(new ConcreteStateA());
        context.doSomething();
        context.setState(new ConcreteStateB());
        context.doSomething();
    }
}
