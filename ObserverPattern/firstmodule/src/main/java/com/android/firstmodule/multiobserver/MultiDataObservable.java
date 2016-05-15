package com.android.firstmodule.multiobserver;

import java.util.ArrayList;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-14
 */
public class MultiDataObservable implements IMultiDataObservable {

    private static volatile MultiDataObservable instance;
    private ArrayList<Object> observers;

    private MultiDataObservable() {
        observers = new ArrayList<>();
    }

    public static MultiDataObservable getInstance() {
        if (instance == null) {
            synchronized (MultiDataObservable.class) {
                if (instance == null) {
                    instance = new MultiDataObservable();
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
