package slf.xbb.rpc.protocol.server;

import lombok.extern.slf4j.Slf4j;
import slf.xbb.rpc.protocol.Request;
import slf.xbb.rpc.protocol.Response;
import slf.xbb.rpc.protocol.codec.Decoder;
import slf.xbb.rpc.protocol.codec.Encoder;
import slf.xbb.rpc.protocol.transport.RequestHandler;
import slf.xbb.rpc.protocol.transport.TransportServer;
import slf.xbb.rpc.protocol.utils.ReflectionUtils;
import sun.misc.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 4:04 下午
 * @description：rpc服务对象
 * @modifiedBy：
 * @version:
 */
@Slf4j
public class RpcServer {
    /**
     * 配置信息
     */
    private RpcServerConfig rpcServerConfig;
    /**
     * 网络模块
     */
    private TransportServer transportServer;
    /**
     * 序列化 、反序列化
     */
    private Encoder encoder;
    private Decoder decoder;
    /**
     * 管理服务
     */
    private ServiceManager serviceManager;
    /**
     * 调用服务
     */
    private ServiceInvoker serviceInvoker;
    /**
     * 处理网络请求的handler
     */
    private RequestHandler handler = new RequestHandler() {
        @Override
        public void onRequest(InputStream recive, OutputStream toResp) {
            Response response = new Response();
            //从recive读取request数据体(request请求被序列化之后的二进制数据)
            //通过service invoke调用服务
            //最后写会toResp
            try {
                byte[] bytes = IOUtils.readFully(recive, recive.available(), true);
                Request request = decoder.decode(bytes, Request.class);
                log.info("get request: {}", request);
                ServiceInstance serviceInstance = serviceManager.lookup(request);
                Object ret = serviceInvoker.invoke(serviceInstance, request);
                response.setData(ret);

            } catch (IOException e) {
                log.warn(e.getMessage(), e);
                // 0成功，1失败
                response.setCode(1);
                response.setMessage("RpcServer got error : " + e.getClass().getName() + ":" + e.getMessage());
            } finally {
                try {
                    byte[] outBytes = encoder.encode(response);
                    toResp.write(outBytes);
                    log.info("responsed client");
                } catch (IOException e) {
                    log.warn(e.getMessage(), e);
                }
            }
        }
    };

    public RpcServer() {
        this(new RpcServerConfig());
    }

    public RpcServer(RpcServerConfig config) {
        //config
        this.rpcServerConfig = config;
        //net
        this.transportServer = ReflectionUtils.newInstance(config.getTransportClass());
        this.transportServer.init(config.getPort(), this.handler);
        //codec
        this.encoder = ReflectionUtils.newInstance(config.getEncoderClass());
        this.decoder = ReflectionUtils.newInstance(config.getDecoderClass());
        //service
        this.serviceManager = new ServiceManager();
        this.serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        serviceManager.register(interfaceClass, bean);
    }

    public void start() {
        this.transportServer.start();
    }

    public void stop() {
        this.transportServer.stop();
    }
}
