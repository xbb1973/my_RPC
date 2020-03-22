package slf.xbb.rpc.protocol.transport;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 1:06 下午
 * @description：处理网络请求的handler
 * 1、Server服务端接受的请求是InputSteam=byte数据流
 * 2、抽象一个handler实现，让用网络模块的人来实现
 * @modifiedBy：
 * @version:
 */
public interface RequestHandler {
    void onRequest(InputStream recive, OutputStream to);
}
