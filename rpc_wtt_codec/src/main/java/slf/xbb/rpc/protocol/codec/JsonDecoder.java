package slf.xbb.rpc.protocol.codec;

import com.alibaba.fastjson.JSON;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 11:15 上午
 * @description：基于json的反序列化实现
 * @modifiedBy：
 * @version:
 */
public class JsonDecoder implements Decoder {
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        return JSON.parseObject(bytes, clazz);
    }
}
