package com.lsh.rule;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/4/24 3:10 下午
 * @desc ：具体规则- 例子2
 */
public class NationalityRule extends AbstractRule {

    @Override
    protected <T> T convert(RuleDto dto) {
        NationalityRuleDto nationalityRuleDto = new NationalityRuleDto();
        //北京
        if (dto.getAddress().startsWith(RuleConstant.MATCH_ADDRESS_START_BJ)) {
            //中国   如果地址是北京的学生，设置国籍为中国
            nationalityRuleDto.setNationality(RuleConstant.MATCH_NATIONALITY_START_CH);
        }else if (dto.getAddress().startsWith(RuleConstant.MATCH_ADDRESS_START_DJ)){
            nationalityRuleDto.setNationality(RuleConstant.MATCH_NATIONALITY_START_JA);
        }else {
            nationalityRuleDto.setNationality(RuleConstant.MATCH_NATIONALITY_START_UNKNOWN);
        }
        return (T) nationalityRuleDto;
    }


    @Override
    protected <T> boolean executeRule(T t) {
        System.out.println("NationalityRule invoke!");
        NationalityRuleDto nationalityRuleDto = (NationalityRuleDto) t;
        // 如果国籍是中国，返回true
        if (nationalityRuleDto.getNationality().startsWith(RuleConstant.MATCH_NATIONALITY_START_CH)) {
            return true;
        }
        return false;
    }
}

