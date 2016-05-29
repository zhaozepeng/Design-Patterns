package com.android.objectpoolpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-29
 */
public class ReusablePool implements IReusablePool {
    private static final String TAG = "ReusablePool";

    private static volatile ReusablePool instance = null;

    private static List<Reusable> available = new ArrayList<>();
    private static List<Reusable> inUse = new ArrayList<>();

    private static final byte[] lock = new byte[]{};
    private static int maxSize = 5;
    private int currentSize = 0;

    private ReusablePool() {
        available = new ArrayList<>();
        inUse = new ArrayList<>();
    }

    public static ReusablePool getInstance() {
        if (instance == null) {
            synchronized (ReusablePool.class) {
                if (instance == null) {
                    instance = new ReusablePool();
                }
            }
        }
        return instance;
    }

    @Override
    public Reusable requireReusable() {
        synchronized (lock) {
            if (currentSize >= maxSize) {
                throw new RuntimeException("pool has gotten its maximum size");
            }

            if (available.size() > 0) {
                Reusable reusable = available.get(0);
                available.remove(0);
                currentSize++;
                inUse.add(reusable);
                return reusable;
            } else {
                Reusable reusable = new Reusable();
                inUse.add(reusable);
                currentSize++;
                return reusable;
            }
        }
    }

    @Override
    public void releaseReusable(Reusable reusable) {
        if (reusable != null) {
            reusable.a = null;
            reusable.b = null;
            reusable.c = null;
            reusable.d = null;
            reusable.e = null;
            reusable.f = null;
            reusable.g = null;
            reusable.h.clear();
            reusable.i.clear();
            reusable.j.clear();
            reusable.k.clear();
            reusable.l.clear();
        }

        synchronized (lock) {
            inUse.remove(reusable);
            available.add(reusable);
            currentSize--;
        }
    }

    @Override
    public void setMaxPoolSize(int size) {
        maxSize = size;
    }
}
