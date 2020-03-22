package slf.xbb.rpc.protocol.codec;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 11:18 上午
 * @description：
 * @modifiedBy：
 * @version:
 */
public class JsonDecoderTest {

    @Test
    public void decode() {
        Encoder encoder = new JsonEncoder();
        TestBean bean = new TestBean();
        bean.setName("洪义霖");
        bean.setAge(18);
        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);

        Decoder decoder = new JsonDecoder();
        TestBean obj = decoder.decode(bytes, TestBean.class);
        assertEquals(18, obj.getAge());
        assertEquals("洪义霖", obj.getName());
        assertEquals(bean, obj);
    }
}