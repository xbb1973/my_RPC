package slf.xbb.rpc.protocol.server;

import org.junit.Before;
import org.junit.Test;
import slf.xbb.rpc.protocol.Request;
import slf.xbb.rpc.protocol.ServiceDescriptor;
import slf.xbb.rpc.protocol.utils.ReflectionUtils;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 3:22 下午
 * @description：
 * @modifiedBy：
 * @version:
 */
public class ServiceManagerTest {

    ServiceManager sm;
    @Before
    public void init() {
        sm = new ServiceManager();
        sm.register(TestInterface.class, new TestClass());
    }

    @Test
    public void register() {
        TestInterface bean = new TestClass();
        sm.register(TestInterface.class, bean);
    }

    @Test
    public void lookup() {
        Method method = ReflectionUtils.getPublicMethods(TestInterface.class)[0];
        ServiceDescriptor sdp = ServiceDescriptor.from(TestInterface.class, method);
        Request request = new Request();
        request.setServiceDescriptor(sdp);
        ServiceInstance serviceInstance = sm.lookup(request);
        assertNotNull(serviceInstance);
    }
}