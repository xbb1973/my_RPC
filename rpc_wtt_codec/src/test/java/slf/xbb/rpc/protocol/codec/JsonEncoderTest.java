package slf.xbb.rpc.protocol.codec;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 11:17 上午
 * @description：
 * @modifiedBy：
 * @version:
 */
public class JsonEncoderTest {

    @Test
    public void encode() {
        Encoder encoder = new JsonEncoder();
        TestBean bean = new TestBean();
        bean.setName("洪义霖");
        bean.setAge(18);
        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);
    }
}