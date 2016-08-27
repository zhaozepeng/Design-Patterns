package com.android.strategypattern;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-08-27
 */
public class Context {

    private Stragety stragety;

    public Context() {
        stragety = new ConcreteStragetyDefault();
    }

    public void algorithm() {
        stragety.algorithm();
    }

    public void setStragety(Stragety stragety) {
        if (stragety == null) {
            throw new IllegalArgumentException("argument must not be null!!!");
        }
        this.stragety = stragety;
    }

    public static void main(String args[]) {
        Context context = new Context();
        context.setStragety(new ConcreteStragetyA());
        context.algorithm();
        context.setStragety(new ConcreteStragetyB());
        context.algorithm();
    }
}
