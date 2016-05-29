package com.android.objectpoolpattern;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-29
 */
public interface IReusablePool {
    Reusable requireReusable();
    void releaseReusable(Reusable reusable);
    void setMaxPoolSize(int size);
}
