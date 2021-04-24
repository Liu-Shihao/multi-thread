package com.lsh.rule;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/4/24 3:07 下午
 * @desc ： 规则抽象
 */
public interface BaseRule {

    boolean execute(RuleDto dto);
}
