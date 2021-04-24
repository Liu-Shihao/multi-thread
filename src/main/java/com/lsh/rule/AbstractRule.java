package com.lsh.rule;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/4/24 3:38 下午
 * @desc ：
 */
public abstract class AbstractRule implements BaseRule {

    protected <T> T convert(RuleDto dto) {
        return (T) dto;
    }

    @Override
    public boolean execute(RuleDto dto) {
        return executeRule(convert(dto));
    }

    protected <T> boolean executeRule(T t) {
        return true;
    }
}