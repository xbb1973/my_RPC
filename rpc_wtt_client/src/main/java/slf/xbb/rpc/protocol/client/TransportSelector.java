package slf.xbb.rpc.protocol.client;

import slf.xbb.rpc.protocol.Peer;
import slf.xbb.rpc.protocol.transport.TransportClient;

import java.util.List;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 4:37 下午
 * @description：表示选择哪个server去连接
 * @modifiedBy：
 * @version:
 */
public interface TransportSelector {
    /**
     * 初始化slecteor，需要：
     * 1、Server网络端点Peer
     * 2、count记录client与server之间做了多少连接
     * 3、transport网络模块客户端transportClient的class信息
     * @param peerList
     * @param count
     * @param clazz
     */
    void init(List<Peer> peerList, int count, Class<? extends TransportClient> clazz);
    /**
     * 选择一个transport与server做交互
     * @return 网络client
     */
    TransportClient select();

    /**
     * 释放用完的client
     * @param client
     */
    void release(TransportClient client);

    /**
     * 关闭
     */
    void close();
}
