package slf.xbb.rpc.protocol.codec;

import com.alibaba.fastjson.JSON;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 3:08 上午
 * @description：基于json的序列化实现
 * @modifiedBy：
 * @version:
 */
public class JsonEncoder implements Encoder {
    @Override
    public byte[] encode(Object object) {
        return JSON.toJSONBytes(object);
    }
}

