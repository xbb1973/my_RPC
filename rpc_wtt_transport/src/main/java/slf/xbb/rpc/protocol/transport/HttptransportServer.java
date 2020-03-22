package slf.xbb.rpc.protocol.transport;

import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 1:55 下午
 * @description：基于http、jetty的transport服务端
 * @modifiedBy：
 * @version:
 */
@Slf4j
public class HttptransportServer implements TransportServer {

    private RequestHandler handler;
    private Server server;

    /**
     * handler什么时候传给server呢？
     * 应该是初始化的时候，因此创建init函数
     *
     * @param port
     * @param handler
     */
    @Override
    public void init(int port, RequestHandler handler) {
        this.handler = handler;
        this.server = new Server(port);
        //servlet请求接收
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);
        //servlet holder是ctx在处理网络请求的抽象
        Servlet servlet = new RequestServlet();
        ServletHolder holder = new ServletHolder(servlet);
        ctx.addServlet(holder, "/*");
    }

    /**
     * 启动
     */
    @Override
    public void start() {
        try {
            //使用jetty的start方法
            server.start();
            //启动完后，有一个线程去监听，方法会马上返回
            //我们这个方法立马返回，使用join等server停止关闭
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 停止
     */
    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("client connect");
            InputStream in = req.getInputStream();
            OutputStream out = resp.getOutputStream();
            if (handler != null) {
                handler.onRequest(in, out);
            }
            out.flush();
        }
    }
}
