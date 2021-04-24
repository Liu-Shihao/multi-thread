package com.lsh.rule;



import lombok.Data;


/**
 * @author ：LiuShihao
 * @date ：Created in 2021/4/24 3:05 下午
 * @desc ：对于规则的抽象并实现规则
 */

@Data
public class RuleDto {
    private String address;
    private String name;
    private String subject;
    private int age;
}

