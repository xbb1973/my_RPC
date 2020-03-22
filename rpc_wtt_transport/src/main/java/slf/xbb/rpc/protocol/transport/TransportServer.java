package slf.xbb.rpc.protocol.transport;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 1:04 下午
 * @description：传输服务端
 * 1、启动、监听
 * 2、接受请求
 * 3、关闭监听
 * @modifiedBy：
 * @version:
 */

public interface TransportServer {
    /**
     * handler什么时候传给server呢？
     * 应该是初始化的时候，因此创建init函数
     * @param port
     * @param handler
     */
    void init(int port, RequestHandler handler);
    /**
     * 启动
     */
    void start();

    /**
     * 停止
     */
    void stop();
}
