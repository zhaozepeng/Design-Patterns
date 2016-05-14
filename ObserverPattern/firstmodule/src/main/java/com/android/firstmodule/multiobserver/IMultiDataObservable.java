package com.android.firstmodule.multiobserver;

import java.util.ArrayList;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-14
 */
public interface IMultiDataObservable {
    /**
     * 增加观察者
     */
    void addObserver(Object observer);

    /**
     * 删除观察者
     */
    void deleteObserver(Object observer) throws IllegalArgumentException;

    /**
     * 查找观察者
     */
    <T>ArrayList<T> findObserver(Class<T> clazz);
}
