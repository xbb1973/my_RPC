package slf.xbb.rpc.protocol.client;

import slf.xbb.rpc.protocol.codec.Decoder;
import slf.xbb.rpc.protocol.codec.Encoder;
import slf.xbb.rpc.protocol.utils.ReflectionUtils;

import java.lang.reflect.Proxy;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 9:28 下午
 * @description：客户端
 * @modifiedBy：
 * @version:
 */
public class RpcClient {
    private RpcClientConfig rpcClientConfig;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector transportSelector;

    public RpcClient() {
        this(new RpcClientConfig());
    }

    public RpcClient(RpcClientConfig rpcClientConfig) {
        this.rpcClientConfig = rpcClientConfig;
        this.encoder = ReflectionUtils.newInstance(this.rpcClientConfig.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(this.rpcClientConfig.getDecoderClass());
        this.transportSelector = ReflectionUtils.newInstance(this.rpcClientConfig.getSelectorClass());
        this.transportSelector.init(this.rpcClientConfig.getServers(),
                this.rpcClientConfig.getConnectCount(),
                this.rpcClientConfig.getTransportClass());
    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[] {clazz},
                new RemoteInvoker(clazz, encoder, decoder, transportSelector));
    }
}
