package slf.xbb.rpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 2:21 上午
 * @description：表示网络传输的一个端点
 * @modifiedBy：
 * @version:
 */

/**
 * lombok注解使用
 */
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}
