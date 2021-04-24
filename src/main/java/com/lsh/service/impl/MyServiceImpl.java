package com.lsh.service.impl;

import com.lsh.annotation.MyLog;
import com.lsh.entity.LogRecord;
import com.lsh.service.MyService;
import org.springframework.stereotype.Service;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/4/15 10:04 上午
 * @desc ：
 */
@Service
public class MyServiceImpl implements MyService {
    @MyLog("修改策略")
    @Override
    public void test1(LogRecord logRecord) {
        System.out.println("业务层执行业务代码");
    }
}
