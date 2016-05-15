package com.android.firstmodule.multiobserver;

import java.util.ArrayList;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-14
 */
public class MultiDataObserver implements IMultiDataObservable {

    private static volatile MultiDataObserver instance;
    private ArrayList<Object> observers;

    private MultiDataObserver() {
        observers = new ArrayList<>();
    }

    public static MultiDataObserver getInstance() {
        if (instance == null) {
            synchronized (MultiDataObserver.class) {
                if (instance == null) {
                    instance = new MultiDataObserver();
                }
            }
        }
        return instance;
    }

    @Override
    public void addObserver(Object observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(Object observer) throws IllegalArgumentException {
        if (observer == null) {
            throw new IllegalArgumentException("observer must not be null");
        }
        if (!observers.remove(observer)) {
            throw new IllegalArgumentException("observer not registered");
        }
    }

    @Override
    public <T> ArrayList<T> findObserver(Class<T> clazz) {
        ArrayList<T> lists = new ArrayList<>();
        for (Object observer : observers) {
            if (clazz.isInstance(observer)) {
                lists.add(clazz.cast(observer));
            }
        }
        return lists;
    }
}
