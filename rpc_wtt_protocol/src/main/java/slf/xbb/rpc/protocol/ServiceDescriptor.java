package slf.xbb.rpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 2:23 上午
 * @description：表示服务
 * @modifiedBy：
 * @version:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor {
    /**
     * 类名
     */
    private String clazz;
    /**
     * 方法
     */
    private String method;
    /**
     * 返回类型
     */
    private String returnType;
    /**
     * 参数
     */
    private String[] parameter;

    /**
     *
     */
    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor sdp = new ServiceDescriptor();
        sdp.setClazz(clazz.getName());
        sdp.setMethod(method.getName());
        sdp.setReturnType(method.getReturnType().getName());
        Class[] parameterClasses = method.getParameterTypes();
        String[] parameterTypes = new String[parameterClasses.length];
        for (int i = 0; i < parameterClasses.length; i++) {
            parameterTypes[i] = parameterClasses[i].getSimpleName();
        }
        sdp.setParameter(parameterTypes);
        return sdp;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        } else if (obj == null || getClass() != obj.getClass()){
            return false;
        }
        ServiceDescriptor that = (ServiceDescriptor) obj;
        return this.toString().equals(that.toString());
    }

    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", returnType='" + returnType + '\'' +
                ", parameter=" + Arrays.toString(parameter) +
                '}';
    }
}
