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
public class ConcreteColleagueA extends Colleague{

    public void notifyColleagueB() {
        mediator.notifyColleagueB();
    }

    @Override
    public void operation() {
        System.out.print("this is ConcreteColleagueA's operation\n");
    }
}
