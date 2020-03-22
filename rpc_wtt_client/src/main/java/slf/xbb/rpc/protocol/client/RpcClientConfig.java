package slf.xbb.rpc.protocol.client;

import lombok.Data;
import slf.xbb.rpc.protocol.Peer;
import slf.xbb.rpc.protocol.codec.Decoder;
import slf.xbb.rpc.protocol.codec.Encoder;
import slf.xbb.rpc.protocol.codec.JsonDecoder;
import slf.xbb.rpc.protocol.codec.JsonEncoder;
import slf.xbb.rpc.protocol.transport.HttpTransportClient;
import slf.xbb.rpc.protocol.transport.HttptransportServer;
import slf.xbb.rpc.protocol.transport.TransportClient;
import slf.xbb.rpc.protocol.transport.TransportServer;

import java.util.Arrays;
import java.util.List;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 9:16 下午
 * @description：客户端配置
 * @modifiedBy：
 * @version:
 */
@Data
public class RpcClientConfig {
    private Class<? extends TransportClient> transportClass = HttpTransportClient.class;
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    private Class<? extends TransportSelector> selectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1", 3000)
    );

}
