package com.xxx.test.core.sql;

import org.apache.ibatis.jdbc.SQL;

import javax.persistence.criteria.Predicate;

/**
 * @author: shdwu
 * @date: 18-10-10 16:09
 * @description:
 */
public class CustomSql {

    /**
     * 添加where条件
     * @return
     */
    public CustomSql where(Predicate... restrictions) {
        new SQL(){
            {
                SELECT()
            }
        };
        return this;
    }
}
