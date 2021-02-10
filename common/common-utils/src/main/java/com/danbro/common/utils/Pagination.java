package com.danbro.common.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * 分页器
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class Pagination<V, E> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 总记录数
     */
    private int totalCount;
    /**
     * 每页记录数
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 当前页数
     */
    private int currPage;
    /**
     * 列表数据
     */
    private List<V> list;
    /**
     * Page里的数据类型
     */
    @JsonIgnore
    private Class<V> clazz;

    /**
     * 分页
     *
     * @param eList      类型为Entity的列表数据
     * @param totalCount 总记录数
     * @param pageSize   每页记录数
     * @param currPage   当前页数
     */
    public Pagination(List<E> eList, int totalCount, int pageSize, int currPage, Class<V> clazz) {
        this.clazz = clazz;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
        this.list = copyList(eList);
    }

    /**
     * 分页
     */
    public Pagination(IPage<E> page, Class<V> clazz) {
        this.clazz = clazz;
        this.totalCount = (int) page.getTotal();
        this.pageSize = (int) page.getSize();
        this.currPage = (int) page.getCurrent();
        this.totalPage = (int) page.getPages();
        this.list = copyList(page.getRecords());
    }

    public Pagination(IPage<V> page) {
        this.totalCount = (int) page.getTotal();
        this.pageSize = (int) page.getSize();
        this.currPage = (int) page.getCurrent();
        this.totalPage = (int) page.getPages();
        this.list = page.getRecords();
    }


    /**
     * 复制List
     *
     * @param eList Entity的List
     * @return Vo的List
     */
    private List<V> copyList(List<E> eList) {
        List<V> voList = new ArrayList<>();
        eList.forEach(e -> {
            V v = MyReflectUtils.getNewInstance(this.clazz);
            MyBeanUtils.copyProperties(e, v);
            voList.add(v);
        });
        return voList;
    }

}
