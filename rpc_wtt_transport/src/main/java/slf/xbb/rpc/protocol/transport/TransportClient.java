package slf.xbb.rpc.protocol.transport;

import slf.xbb.rpc.protocol.Peer;

import java.io.InputStream;
/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 1:01 下午
 * @description：连接客户端
 * 1、创建链接
 * 2、发送数据，并且等待响应
 * 3、关闭连接
 * @modifiedBy：
 * @version:
 */
public interface TransportClient {
    void connect(Peer peer);
    /**
     *
     * @param inputStream是服务端返回的数据，也就是执行结果。
     * @return
     */
    InputStream write(InputStream inputStream);
    void close();
}
