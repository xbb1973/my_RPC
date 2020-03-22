package slf.xbb.rpc.protocol.transport;

import org.apache.commons.io.IOUtils;
import slf.xbb.rpc.protocol.Peer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 1:12 下午
 * @description：基于http的client实现 1、因为是基于HTTP的，这里使用HTTP短连接，因此无需在连接和关闭方法进行处理
 * 2、需要url
 * 3、写数据时需要打开connection
 * @modifiedBy：
 * @version:
 */
public class HttpTransportClient implements TransportClient {
    private String url;

    @Override
    public void connect(Peer peer) {
        this.url = "http://" + peer.getHost() + ":"
                + peer.getPort();
    }

    @Override
    public InputStream write(InputStream inputStream) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(url).openConnection();
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.connect();
            //data是服务端返回的数据，也就是执行结果。
            IOUtils.copy(inputStream, httpURLConnection.getOutputStream());
            int resultCode = httpURLConnection.getResponseCode();
            if (resultCode == HttpURLConnection.HTTP_OK) {
                return httpURLConnection.getInputStream();
            } else {
                return httpURLConnection.getErrorStream();
            }
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
