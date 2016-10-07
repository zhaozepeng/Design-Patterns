package com.android.interpreterpattern;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-10-07
 */
public abstract class AbstractExpression {
    /**
     * 抽象的解析方法
     * @param context 上下文环境对象
     */
    public abstract void interpret(Context context);
}
