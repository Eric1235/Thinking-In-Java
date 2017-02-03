package io.externalizable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Created by EricLi on 2017/1/6.
 * Email me : EricLi1235@gmail.com.
 */

/**
 * 是不是觉得和Android的Parcel很像？
 */
public class Blip3 implements Externalizable {

    private int i;
    private String s;

    private transient String passwd;//经过transient修饰的字段，就不会被序列化进去

    public Blip3(int i, String s) {
        this.i = i;
        this.s = s;
    }

    public Blip3(){}

    @Override
    public String toString() {
        return s + i;
    }


    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(s);
        out.writeInt(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        s = (String)in.readObject();
        i = in.readInt();
    }
}
