package slf.xbb.rpc.protocol.codec;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 3:05 上午
 * @description：反序列化
 * @modifiedBy：
 * @version:
 */
public interface Decoder {
    /**
     * 解码，将二进制转化为其他常见类型，这里使用范型节省后续强制转换的操作，节省时间操作
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T decode(byte[] bytes, Class<T> clazz);
}
