package slf.xbb.rpc.server;

import lombok.extern.slf4j.Slf4j;
import slf.xbb.rpc.protocol.Request;
import slf.xbb.rpc.protocol.ServiceDescriptor;
import slf.xbb.rpc.protocol.utils.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 2:44 下午
 * @description：管理rpc暴露的服务 1、注册暴露的服务
 * 2、查找管理服务
 * @modifiedBy：
 * @version:
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    /**
     * 注册，因为两个参数有关系，使用范型
     *
     * @param interfaceClass 接口class
     * @param bean           实现接口class的对象
     */
    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] methods = ReflectionUtils.getPublicMethods(interfaceClass);
        for (Method method : methods) {
            ServiceInstance sis = new ServiceInstance(bean, method);
            ServiceDescriptor sdp = ServiceDescriptor.from(interfaceClass, method);
            //这样实现了interface所有的方法封装成服务注册到service manager中
            services.put(sdp, sis);
            log.info("register service: {} {}", sdp.getClazz(), sdp.getMethod());
        }
    }

    /**
     * 查找服务
     * @param request 传进来的RPC request
     * @return ServiceInstance
     */
    public ServiceInstance lookup(Request request) {
        ServiceDescriptor sdp = request.getServiceDescriptor();
        //services这个map使用get方法时，是使用equal方法来实现判断语句的
        //所以我们需要重写sdp的equal方法
        //重写实现equal方法时还需要去重写它的hashcode方法
        return services.get(sdp);
    }

}
