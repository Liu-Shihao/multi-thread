package com.lsh;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/2/22 10:32 上午
 * @desc ：
 * tenant是代表租户的标识，一般一个服务或者一个业务下的多个服务都写死一个 tenant 就可以
 */
@EnableLogRecord(tenant = "com.lsh.test")
@SpringBootApplication
public class MultiThreadApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultiThreadApplication.class);
    }
}
