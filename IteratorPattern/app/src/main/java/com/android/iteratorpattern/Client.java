package com.android.iteratorpattern;

/**
 * Description: #TODO
 *
 * @author Shawn(zhao_zepeng@hotmail.com)
 * @since 2016-09-11
 */
public class Client {
    public static void main(String args[]) {
        Aggregation<String> a = new ConcreteAggregation<>();
        a.add("a");
        a.add("b");
        a.add("c");
        Iterator<String> iterator = a.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next());
        }
    }
}
