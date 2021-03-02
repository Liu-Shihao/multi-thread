package com.lsh.service.impl;

import com.mzt.logapi.beans.Operator;
import com.mzt.logapi.service.IOperatorGetService;
import org.springframework.stereotype.Service;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/26 2:55 下午
 * @desc ：
 */
@Service
public class DefaultOperatorGetServiceImpl implements IOperatorGetService {
    @Override
    public Operator getUser() {
        Operator operator = new Operator();
        operator.setOperatorId("SYSTEM");
        return operator;
    }
}
