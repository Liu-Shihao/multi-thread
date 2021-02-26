package com.lsh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/26 2:02 下午
 * @desc ：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order extends Token{
    private Long orderId;
    private String orderNo;
    private String purchaseName;
    private String productName;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
