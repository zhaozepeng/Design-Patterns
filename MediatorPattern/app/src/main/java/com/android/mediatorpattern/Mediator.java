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
public abstract class Mediator {

    protected Colleague colleagueA;
    protected Colleague colleagueB;

    public Mediator(Colleague colleagueA, Colleague colleagueB) {
        this.colleagueA = colleagueA;
        this.colleagueB = colleagueB;
    }

    public abstract void notifyColleagueA();
    public abstract void notifyColleagueB();
}
