package slf.xbb.rpc.server;

import lombok.Data;
import slf.xbb.rpc.protocol.codec.Decoder;
import slf.xbb.rpc.protocol.codec.Encoder;
import slf.xbb.rpc.protocol.codec.JsonDecoder;
import slf.xbb.rpc.protocol.codec.JsonEncoder;
import slf.xbb.rpc.protocol.transport.HttptransportServer;
import slf.xbb.rpc.protocol.transport.TransportServer;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 2:16 下午
 * @description：server配置 1、代表使用哪个网络模块
 * 2、代表使用哪个序列化实现
 * 3、rpc server启动后监听什么端口
 * @modifiedBy：
 * @version:
 */
@Data
public class RpcServerConfig {
    private Class<? extends TransportServer> transportClass = HttptransportServer.class;
    private Class<? extends Encoder> encoderClass = JsonEncoder.class;
    private Class<? extends Decoder> decoderClass = JsonDecoder.class;
    private int port = 3000;

}
