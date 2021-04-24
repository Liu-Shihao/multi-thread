package com.lsh.rule;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/4/24 3:34 下午
 * @desc ：
 */
public class AgeRule extends AbstractRule {
    @Override
    public boolean execute(RuleDto dto) {
        System.out.println("AddressRule invoke!");
        //大于18
        if (dto.getAge() >=(RuleConstant.MATCH_AGE_MIN)) {
            return true;
        }
        return false;
    }
}
