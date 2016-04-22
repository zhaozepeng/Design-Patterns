 　　很早之前就像开始总结设计模式了，平时看代码会遇到很多设计模式，《Head First》这本书也看过了，但是总是觉得自己不会用，而且对于常用的设计模式理解都不深刻，所以以此为基础，开始自己的设计模式之旅吧。
　　前段时间公司一些同事在讨论单例模式，这个模式应该基本很多设计模式的书都会作为前几章来讲，使用的频率很高，也可能是很多初级工程师唯一会使用的设计模式，当然单例模式也算是最简单的设计模式之一吧，简单归简单，在实际使用的时候会有一些坑，在此总结一下。
#特点#
　　单例模式的使用很广泛，比如：线程池（threadpool）、缓存（cache）、对话框、处理偏好设置、和注册表（registry）的对象、日志对象，充当打印机、显卡等设备的驱动程序的对象等，这些类的对象只能有一个实例，如果制造出多个实例，就会导致很多问题的产生，程序的行为异常，资源使用过量，或者不一致的结果等，所以单例模式最主要的特点：<ol><li>构造函数不对外开放，一般为private；</li><li>通过一个静态方法或者枚举返回单例类对象；</li><li>确保单例类的对象有且只有一个，尤其是在多线程的环境下；</li><li>确保单例类对象在反序列化时不会重新构建对象。</li></ol>通过将单例类构造函数私有化，使得客户端不能通过 new 的形式手动构造单例类的对象。单例类会暴露一个共有静态方法，客户端需要条用这个静态方法获取到单例类的唯一对象，在获取到这个单例对象的过程中需要确保线程安全，即在多线程环境下构造单例类的对象也是有且只有一个，这是单利模式较关键的一个地方。
#UML类图#
![这里写图片描述](http://img.blog.csdn.net/20160417220420021)
　　类图很简单，Singleton类有一个 static 的 instance对象，类型为Singleton，构造函数为private，提供一个getInstance的静态函数，返回刚才的instance对象，在该函数中进行初始化操作。
#示例与源码#
　　单例模式的写法很多，总结一下：
##lazy initialization, thread-unsafety（懒汉法，线程不安全）##
　　延迟初始化，一般很多人称为懒汉法，写法一目了然，在需要使用的时候去调用getInstance()函数去获取Singleton的唯一静态对象，如果为空，就会去做一个额外的初始化操作。
```
public class Singleton {
    private static Singleton instance = null;
    private Singleton(){}
    public static Singleton getInstance() {
        if(instance == null) 
			instance = new Singleton();
        return instance;
    }
}
```
　　需要注意的是这种写法在多线程操作中是不安全的，后果是可能会产生多个Singleton对象，比如两个线程同时执行getInstance()函数时，然后同时执行到 new 操作时，最后很有可能会创建两个不同的对象。
##lazy initialization, thread-safety, double-checked(懒汉法，线程安全)##
　　需要做到线程安全，就需要确保任意时刻只能有且仅有一个线程能够执行new Singleton对象的操作，所以可以在getInstance()函数上加上 synchronized 关键字，类似于：
```
public static synchronized Singleton getInstance() {
        if(singleton == null) 
			instance = new Singleton();
        return instance;
    }
```
但是套用《Head First》上的一句话，synchronized 会让函数性能糟糕一百倍以上（Since synchronizing a method could in some extreme cases decrease performance by a factor of 100 or higher），所以就有了double-checked（双重检测）的方法：
```
public class Singleton {
    private volatile static Singleton instance = null;
    private Singleton(){}
    public static Singleton getInstance() {
        if (instance == null){
            synchronized (Singleton.class){
                if (instance == null){
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```
　　为什么要这么写呢？我们假设两个线程A,B同时执行到了getInstance()这个方法，第一个if判断，两个线程同时为true，进入if语句，里面有个 synchronized 同步，所以之后有且仅有一个线程A会执行到 synchronized 语句内部，接着再次判断instance是否为空，为空就去new Singleton对象并且赋值给instance，A线程退出 synchronized 语句，交出同步锁，B线程进入 synchronized 语句内部，if判断instance是否为空，防止创建不同的instance对象，这也是第二个if判断的作用，B线程发现不为空，所以直接退出，所以最终A和B线程可以获取到同一个Singleton对象，之后的线程调用getInstance()函数，都会因为Instance不为空而直接返回，不会受到 synchronized 的性能影响。
　　volatile关键字的作用是什么呢？[Why is volatile used in this example of double checked locking](http://stackoverflow.com/a/7855774)，这个答案写的很清楚了，线程 A 在完全构造完 instance 对象之前就会给 instance 分配内存，线程B在看到 instance 已经分配了内存不为空就回去使用它，所以这就造成了B线程使用了部分初始化的 instance 对象而出现相关问题了。但是这个也是有一个小问题，[Double-checked locking](https://en.wikipedia.org/wiki/Double-checked_locking#Usage_in_Java)里面有一句话
`As of J2SE 5.0, this problem has been fixed. The volatile keyword now ensures that multiple threads handle the singleton instance correctly. This new idiom is described in [2] and [3].`，所以说5.0之后volatile关键字才会起作用，java volatile关键字：
```
Java也支持volatile关键字，但它被用于其他不同的用途。当volatile用于一个作用域时，Java保证如下：
　　１．（适用于Java所有版本）读和写一个volatile变量有全局的排序。也就是说每个线程访问一个volatile作用域时会在继续执行之前读取它的当前值，而不是（可能）使用一个缓存的值。(但是并不保证经常读写volatile作用域时读和写的相对顺序，也就是说通常这并不是有用的线程构建)。
　　２.　（适用于Java5及其之后的版本）volatile的读和写建立了一个happens-before关系，类似于申请和释放一个互斥锁[8]。
使用volatile会比使用锁更快，但是在一些情况下它不能工作。volatile使用范围在Java5中得到了扩展，特别是双重检查锁定现在能够正确工作[9]。
```
所以说在JDK1.5之后使用volatile关键字是可以使用的，也就是说上面的 double-checked 在java 5之后才能够不出问题。
###volatile关键字介绍###
　　上面提到了volatile关键字，总结一下volatile的相关知识<ol><li>C/C++中的volatile关键字作用</li><ul><li>易变性</li>所谓的易变性，在汇编层面反映出来，就是两条语句，下一条语句不会直接使用上一条语句对应的volatile变量的寄存器内容，而是重新从内存中读取<li>不可优化性</li></ul><li>java中volatile关键字作用</li></ol>
#源码下载#
#引用#
> http://www.tekbroaden.com/singleton-java.html?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io
> http://hedengcheng.com/?p=725
> http://www.cnblogs.com/hxsyl/archive/2013/03/19/2969489.html
> http://www.blogjava.net/kenzhh/archive/2013/03/15/357824.html
> http://blog.csdn.net/jason0539/article/details/23297037
> https://sourcemaking.com/design_patterns/singleton/java/1
> http://stackoverflow.com/questions/70689/what-is-an-efficient-way-to-implement-a-singleton-pattern-in-java
> http://stackoverflow.com/questions/15792186/singleton-pattern-with-combination-of-lazy-loading-and-thread-safety
> https://en.wikipedia.org/wiki/Double-checked_locking#Usage_in_Java
> https://zh.wikipedia.org/wiki/Volatile%E5%8F%98%E9%87%8F
> http://jeremymanson.blogspot.com/2008/11/what-volatile-means-in-java.html
> http://www.jianshu.com/p/d8bf5d08a147
> http://preshing.com/20130702/the-happens-before-relation/
> http://blog.csdn.net/imzoer/article/details/8620801