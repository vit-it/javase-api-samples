package samples.beans$XMLEncoder$XMLDecoder;


import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import samples.java.beans$XMLEncoder$XMLDecoder.Bean;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.time.LocalDateTime;

/**
 * XMLEncoder$XMLDecoder test
 */
public class Test {

    @org.junit.Test
    public void test() {

        Bean bean;
        ByteOutputStream bos;
        ByteInputStream bis;

        bean = fill();
        bos = encode(bean);
        print(bos);

        bis = new ByteInputStream();
        bis.setBuf(bos.getBytes());
        bean = decode(bis);
        out("---");
        bos = encode(bean);
        print(bos);
    }

    private Bean fill() {
        Bean bean = new Bean();
        bean.setF1(true);
        bean.setF2(true);
        bean.setF3(Bean.E.F);
        bean.setF4(LocalDateTime.now());

        Bean.Inner inner = new Bean.Inner();
        inner.setI1(Math.PI);
        bean.setF5(inner);

        return bean;
    }

    private ByteOutputStream encode(Bean bean) {
        ByteOutputStream bos = new ByteOutputStream();
        XMLEncoder xmlEncoder = new XMLEncoder(bos);
        xmlEncoder.writeObject(bean);
        xmlEncoder.close();

        return bos;
    }

    private Bean decode(ByteInputStream bis) {
        XMLDecoder d = new XMLDecoder(bis);
        Bean bean = (Bean)d.readObject();
        d.close();

        return bean;
    }

    private void print(ByteOutputStream bos) {
        out(bos.toString());
    }

    private void out(Object o) {
        System.out.println(o);
    }
}
