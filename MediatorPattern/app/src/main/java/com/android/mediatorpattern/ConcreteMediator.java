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
public class ConcreteMediator extends Mediator{

    public ConcreteMediator(Colleague colleagueA, Colleague colleagueB) {
        super(colleagueA, colleagueB);
    }

    @Override
    public void notifyColleagueA() {
        if (colleagueA != null) {
            colleagueA.operation();
        }
    }

    @Override
    public void notifyColleagueB() {
        if (colleagueB != null) {
            colleagueB.operation();
        }
    }
}
