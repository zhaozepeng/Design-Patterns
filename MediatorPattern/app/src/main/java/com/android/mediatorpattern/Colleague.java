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
public abstract class Colleague {

    protected Mediator mediator;

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void operation();
}
