package com.lsh.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * (LogRecord)实体类
 *
 * @author LiuShihao
 * @since 2021-04-15 09:15:34
 */
@Data
@Entity
@Table(name = "log_record")
public class LogRecord implements Serializable {
    private static final long serialVersionUID = 278820648583560379L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer flag;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 操作人
     */
    private String operator;

    private String editContent;

    private String ipAddress;

    private String username;
    private String url;


}
