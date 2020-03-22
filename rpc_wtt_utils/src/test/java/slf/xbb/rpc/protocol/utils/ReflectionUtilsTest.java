package slf.xbb.rpc.protocol.utils;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 2:48 上午
 * @description：
 * @modifiedBy：
 * @version:
 */
public class ReflectionUtilsTest {

    @Test
    public void newInstance() {
        TestCase t = ReflectionUtils.newInstance(TestCase.class);
        assertNotNull(t);
    }

    @Test
    public void getPublicMethods() {
        Method[] m = ReflectionUtils.getPublicMethods(TestCase.class);
        assertEquals(1, m.length);
        String mName = m[0].getName();
        //获得方法名期望是'c'
        assertEquals("c", mName);
    }

    @Test
    public void invoke() {
        Method[] methods = ReflectionUtils.getPublicMethods(TestCase.class);
        //Method methods = (Method) ReflectionUtils.invoke(m[0],m);
        Object o = ReflectionUtils.invoke(new TestCase(), methods[0]);
        //返回值期望是'c'
        assertEquals("c", o);
    }
}