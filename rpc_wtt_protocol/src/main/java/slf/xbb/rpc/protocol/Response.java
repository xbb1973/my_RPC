package slf.xbb.rpc.protocol;

import lombok.Data;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 2:27 上午
 * @description：表示RPC的返回信息
 * @modifiedBy：
 * @version:
 */
@Data
public class Response {
    /**
     * 服务返回编码，0-成功，非0-失败
     */
    private int code;
    /**
     * 具体错误信息
     */
    private String message;
    /**
     * 返回的数据
     */
    private Object data;
}
