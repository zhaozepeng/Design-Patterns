package com.android.iteratorpattern;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-09-11
 */
public interface Aggregation<T> {

    void add(T obj);

    void remove(T obj);

    Iterator<T> iterator();
}
