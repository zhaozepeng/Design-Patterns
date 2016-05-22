package com.android.abstactfactorypattern.factory;

import com.android.abstactfactorypattern.button.IButton;
import com.android.abstactfactorypattern.text.IText;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-22
 */
public interface IFactory {
    /**
     * 生成对应按钮
     */
    IButton createButton();

    /**
     * 生成对应文字
     */
    IText createText();
}
