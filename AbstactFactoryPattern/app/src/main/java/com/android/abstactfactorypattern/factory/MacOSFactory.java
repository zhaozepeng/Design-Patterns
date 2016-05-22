package com.android.abstactfactorypattern.factory;

import com.android.abstactfactorypattern.button.IButton;
import com.android.abstactfactorypattern.button.MacOSButton;
import com.android.abstactfactorypattern.text.IText;
import com.android.abstactfactorypattern.text.MacOSText;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-22
 */
public class MacOSFactory implements IFactory{
    @Override
    public IButton createButton() {
        return new MacOSButton();
    }

    @Override
    public IText createText() {
        return new MacOSText();
    }
}
