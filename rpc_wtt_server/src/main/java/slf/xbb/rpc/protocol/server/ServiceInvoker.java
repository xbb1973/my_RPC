package slf.xbb.rpc.protocol.server;

import slf.xbb.rpc.protocol.Request;
import slf.xbb.rpc.protocol.utils.ReflectionUtils;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 3:48 下午
 * @description：调用ServiceInstance 实例的辅助类,用来调用服务
 * @modifiedBy：
 * @version:
 */
public class ServiceInvoker {
    /**
     *
     * @param serviceInstance
     * @param request
     * @return Object 调用服务的返回类型
     */
    public Object invoke(ServiceInstance serviceInstance, Request request) {
        return ReflectionUtils.invoke(serviceInstance.getTarget(),
                serviceInstance.getMethod(),
                request.getParameters());
    }
}
