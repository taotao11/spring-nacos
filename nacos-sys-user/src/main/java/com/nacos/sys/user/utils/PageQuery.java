package com.nacos.sys.user.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页查询条件
 * @param <T>
 */
@Data
public class PageQuery<T> implements Serializable {
    private T queryEntity;
    private int currentPage = 1;
    private int pageSize = 10;


}
