package slf.xbb.rpc.protocol;

import lombok.Data;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 2:25 上午
 * @description：表示RPC的一个请求
 * @modifiedBy：
 * @version:
 */
@Data
public class Request {
    //描述请求的服务
    private ServiceDescriptor serviceDescriptor;
    //请求的相关参数
    private Object[] parameters;
}
