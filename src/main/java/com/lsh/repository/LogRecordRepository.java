package com.lsh.repository;

import com.lsh.entity.LogRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author ：LiuShihao
 * @date ：Created in 2021/4/15 9:48 上午
 * @desc ：
 */
@Repository
public interface LogRecordRepository extends JpaRepository<LogRecord,Integer> {
}
