package com.android.compositepattern.composite;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-06-26
 */
public interface Component {
    void operation();

    void add(Component child);

    void remove(Component child);

    Component getChild(int position);
}
