package com.lsh.annotation;

import lombok.Data;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/26 10:57 上午
 * @desc ：
 */
@Data
public class Apple {
    @FruitProvider(id = 1, name = "陕西红富士集团", address = "陕西省西安市延安路")
    private String appleProvider;

}
