package com.android.abstactfactorypattern.factory;

import com.android.abstactfactorypattern.button.IButton;
import com.android.abstactfactorypattern.button.UnixButton;
import com.android.abstactfactorypattern.text.IText;
import com.android.abstactfactorypattern.text.UnixText;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-05-22
 */
public class UnixFactory implements IFactory{
    @Override
    public IButton createButton() {
        return new UnixButton();
    }

    @Override
    public IText createText() {
        return new UnixText();
    }
}
