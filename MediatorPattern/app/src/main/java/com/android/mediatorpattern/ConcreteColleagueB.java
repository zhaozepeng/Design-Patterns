/*
 * Copyright (C) 2016 Facishare Technology Co., Ltd. All Rights Reserved.
 */
package com.android.mediatorpattern;

/**
 * Description:
 *
* @author zhaozp
        * @since 2016-08-31
        */
public class ConcreteColleagueB extends Colleague{

    public void notifyColleagueA() {
        mediator.notifyColleagueA();
    }

    @Override
    public void operation() {
        System.out.print("this is ConcreteColleagueB's operation\n");
    }
}
