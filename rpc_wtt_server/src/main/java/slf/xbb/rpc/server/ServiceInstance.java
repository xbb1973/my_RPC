package slf.xbb.rpc.server;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Method;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 2:43 下午
 * @description：表示一个具体服务实例 1、服务由哪个对象提供
 * 2、这个对象哪个方法暴露成为服务
 * @modifiedBy：
 * @version:
 */
@Data
@AllArgsConstructor
public class ServiceInstance {
    private Object target;
    private Method method;
}
