package com.android.factorymethodpattern.multiFactory;

import com.android.factorymethodpattern.toy.IToy;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-21
 */
public interface IToyCreator {
    /**
     * 生产玩具
     */
    IToy createToy();
}
