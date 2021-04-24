package com.lsh.rule;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/4/24 3:09 下午
 * @desc ：具体规则- 例子1
 */
public class AddressRule extends AbstractRule {

    @Override
    public boolean execute(RuleDto dto) {
        System.out.println("AddressRule invoke!");
        //北京
        if (dto.getAddress().startsWith(RuleConstant.MATCH_ADDRESS_START_BJ)) {
            return true;
        }
        return false;
    }
}
