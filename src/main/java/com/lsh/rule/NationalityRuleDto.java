package com.lsh.rule;

import lombok.Data;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/4/24 3:15 下午
 * @desc ：国籍规则
 */
@Data
public class NationalityRuleDto extends RuleDto{
    /**
     * 国籍
     */
    private String nationality;
}
