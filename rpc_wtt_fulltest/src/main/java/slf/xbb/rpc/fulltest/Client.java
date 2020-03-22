package slf.xbb.rpc.fulltest;

import slf.xbb.rpc.protocol.client.RpcClient;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 10:09 下午
 * @description：客户端
 * @modifiedBy：
 * @version:
 */
public class Client {
    public static void main(String[] args) {
        RpcClient client = new RpcClient();
        CalcService service = client.getProxy(CalcService.class);
        int r1 = service.add(1, 2);
        int r2 = service.minus(3, 4);
        System.out.println(r1);
        System.out.println(r2);
    }
}
