package com.android.chainofresponsibilitypattern.chain;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-07-24
 */
public abstract class AbstractHandler {
    protected AbstractHandler nextHandler;

    public final void handleRequest(AbstractRequest request) {
        if (getHandlerLevel() == request.getLevel()) {
            handle(request);
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            } else {
                System.out.print("there is no handler that can handle this request");
            }
        }
    }

    protected abstract int getHandlerLevel();

    protected abstract void handle(AbstractRequest request);
}
