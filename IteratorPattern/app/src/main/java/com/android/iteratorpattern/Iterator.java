package com.android.iteratorpattern;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-09-11
 */
public interface Iterator<T> {
    boolean hasNext();

    T next();
}
