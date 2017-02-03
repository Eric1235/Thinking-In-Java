package io.serializable;

import java.io.Serializable;

/**
 * Created by EricLi on 2017/1/6.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 执行反序列化的时候，并没有调用任何构造器，整个对象都是通过InputStream中取得数据恢复而来的
 */
public class Data implements Serializable{

    private static final long serialVersionUID = 1L;//指定序列号，可以减少反序列化失败的危机

    private int n;

    public Data(int n) {
        this.n = n;
    }

    @Override
    public String toString() {
        return Integer.toString(n);
    }
}
