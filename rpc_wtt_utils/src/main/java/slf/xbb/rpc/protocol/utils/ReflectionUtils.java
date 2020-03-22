package slf.xbb.rpc.protocol.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 2:32 上午
 * @description：反射工具类
 * @modifiedBy：
 * @version:
 */
public class ReflectionUtils {
    /**
     * 创建出来的是Object，但是我们期望能够转换，因此使用范型。
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 获取某个class的公共方法
     *
     * @param clazz
     * @return 当前类声明的公共方法
     */
    public static Method[] getPublicMethods(Class clazz) {
        //返回当前类clazz的所有方法，不包括父类，包括private、protected、public
        Method[] methods = clazz.getDeclaredMethods();
        //因此需要过滤public的方法出来，再返回
        List<Method> pmethods = new ArrayList<>();
        for (Method m : methods) {
            if (Modifier.isPublic(m.getModifiers())){
                pmethods.add(m);
            }
        }
        return pmethods.toArray(new Method[0]);
    }

    /**
     * 调用指定对象的指定方法
     *
     * @param obj    被调用方法的对象
     * @param method 被调用的方法
     * @param args   方法的参数
     * @return 方法的返回结果
     */
    public static Object invoke(Object obj, Method method, Object... args) {
        try {
            //第一个参数是调每个对象的方法，第二个参数是可变参数
            //第一个参数需要注意，假设我们需要调用静态方法时，该对象需要写为null
            //正常情况对象和方法都是绑定的，但是静态方法并不属于某个对象
            return method.invoke(obj, args);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
