package com.lsh.annotation;

import lombok.Data;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/26 12:00 下午
 * @desc ：
 */
@Data
public class Banana {
    @FruitProvider(id = 2, name = "海南香蕉集团", address = "海南省三亚市")
    private String appleProvider;
}
