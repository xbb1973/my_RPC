package slf.xbb.rpc.fulltest;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ：xbb
 * @date ：Created in 2020/3/22 10:11 下午
 * @description：
 * @modifiedBy：
 * @version:
 */
@NoArgsConstructor
public class CalcServiceImpl implements CalcService {
    @Override
    public int add(int a, int b) {
        return a+b;
    }

    @Override
    public int minus(int a, int b) {
        return Math.min(a, b);
    }
}
