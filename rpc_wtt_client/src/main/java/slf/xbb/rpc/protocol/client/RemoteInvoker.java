package slf.xbb.rpc.protocol.client;

import lombok.extern.slf4j.Slf4j;
import slf.xbb.rpc.protocol.Request;
import slf.xbb.rpc.protocol.Response;
import slf.xbb.rpc.protocol.ServiceDescriptor;
import slf.xbb.rpc.protocol.codec.Decoder;
import slf.xbb.rpc.protocol.codec.Encoder;
import slf.xbb.rpc.protocol.transport.TransportClient;
import sun.misc.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Base64;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 9:36 下午
 * @description：
 * @modifiedBy：
 * @version:
 */
@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector transportSelector;

    public RemoteInvoker(Class clazz, Encoder encoder, Decoder decoder, TransportSelector transportSelector) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.transportSelector = transportSelector;
    }

    /**
     * client调用远程服务流程：
     * client构造请求，发送给server，等待server响应，client从响应拿到返回数据，调用结束。
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);
        Response resp = invokeRemote(request);
        if (resp == null || resp.getCode() != 0) {
            throw new IllegalStateException("fail to invoke remote: " + request);
        }
        return resp.getData();
    }

    private Response invokeRemote(Request request) {
        TransportClient transportClient = null;
        Response response = null;
        try {
            transportClient = transportSelector.select();
            byte[] outBytes = encoder.encode(request);
            InputStream revice = transportClient.write(new ByteArrayInputStream(outBytes));
            byte[] inBytes = IOUtils.readFully(revice, revice.available(), true);
            response = decoder.decode(inBytes, Response.class);
        } catch (IOException e) {
            log.warn(e.getMessage(), e);
            response = new Response();
            response.setCode(1);
            response.setMessage("RpcClient got error: "
                    + e.getClass()
                    + " : " + e.getMessage());
        } finally {
            if (transportClient != null) {
                transportSelector.release(transportClient);
            }
        }
        return response;
    }

}
