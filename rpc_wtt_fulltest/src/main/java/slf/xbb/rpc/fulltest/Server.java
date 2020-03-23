package slf.xbb.rpc.fulltest;

import slf.xbb.rpc.server.RpcServer;


/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 10:10 下午
 * @description：服务端
 * @modifiedBy：
 * @version:
 */
public class Server {
    public static void main(String[] args) {
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(CalcService.class, new CalcServiceImpl());
        rpcServer.start();
    }
}
