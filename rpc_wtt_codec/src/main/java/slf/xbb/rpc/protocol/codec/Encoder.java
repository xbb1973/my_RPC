package slf.xbb.rpc.protocol.codec;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 3:04 上午
 * @description：序列化
 * @modifiedBy：
 * @version:
 */
public interface Encoder {
    /**
     *
     * @param object
     * @return
     */
    byte[] encode(Object object);
}
