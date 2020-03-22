package slf.xbb.rpc.protocol.client;

import lombok.extern.slf4j.Slf4j;
import slf.xbb.rpc.protocol.Peer;
import slf.xbb.rpc.protocol.utils.ReflectionUtils;
import slf.xbb.rpc.protocol.transport.TransportClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 8:54 下午
 * @description：随机选择
 * @modifiedBy：
 * @version:
 */
@Slf4j
public class RandomTransportSelector implements TransportSelector {

    private List<TransportClient> clients;

    public RandomTransportSelector() {
        clients = new ArrayList<>();
    }
    public RandomTransportSelector(List<TransportClient> clients) {
        this.clients = clients;
    }

    /**
     * 初始化slecteor，需要：
     * 1、Server网络端点Peer
     * 2、count记录client与server之间做了多少连接
     * 3、transport网络模块客户端transportClient的class信息
     *
     * @param peerList
     * @param count
     * @param clazz
     */
    @Override
    public synchronized void init(List<Peer> peerList, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);
        for (Peer peer : peerList) {
            for (int i = 0; i < count; i++) {
                //刚创建的client和server之间建立好了连接
                TransportClient client = ReflectionUtils.newInstance(clazz);
                client.connect(peer);
                clients.add(client);
            }
            log.info("connect server: {}", peer);
        }
    }

    /**
     * 选择一个transport与server做交互
     *
     * @return 网络client
     */
    @Override
    public TransportClient select() {
        int clientIndex = new Random().nextInt(clients.size());
        return clients.remove(clientIndex);
    }

    /**
     * 释放用完的client
     *
     * @param client
     */
    @Override
    public synchronized void release(TransportClient client) {
        clients.add(client);
    }

    /**
     * 关闭
     */
    @Override
    public synchronized void close() {
        for (TransportClient client : clients) {
            client.close();
        }
        clients.clear();
    }
}
