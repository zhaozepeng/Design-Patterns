package com.android.abstactfactorypattern.factory;

import com.android.abstactfactorypattern.button.IButton;
import com.android.abstactfactorypattern.button.WindowsButton;
import com.android.abstactfactorypattern.text.IText;
import com.android.abstactfactorypattern.text.WindowsText;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-22
 */
public class WindowsFactory implements IFactory {
    @Override
    public IButton createButton() {
        return new WindowsButton();
    }

    @Override
    public IText createText() {
        return new WindowsText();
    }
}
