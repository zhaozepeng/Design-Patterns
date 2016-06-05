package com.android.adapterpattern.classadapter;

/**
 * Description: #TODO
 *
 * @author zzp(zhao_zepeng@hotmail.com)
 * @since 2016-06-05
 */
public class OutletAdapter extends HKOutlet implements IChinaOutlet{
    @Override
    public String getChinaType() {
        String type = getHKType();
        type = type.replace("Chinese", "British");
        return type;
    }
}
