package com.danbro.common.utils;

import java.util.List;
import com.danbro.common.exceptions.GuliMallException;
import com.danbro.common.interfaces.ResultCode;

/**
 * @author Danrbo
 * @Classname CurdUtils
 * @Description TODO 数据库的crud工具，捕获异常。
 * @Date 2021/2/5 13:35
 */
public class MyCurdUtils {

    /**
     * 对到数据库查找数据后的处理
     *
     * @param t          查找的数据
     * @param resultCode 状态码
     * @param <T>        查找数据的泛型
     * @return 查找结果
     */
    public static <T> T selectOne(T t, ResultCode resultCode) {
        return selectOne(t, resultCode, true);
    }

    /**
     * @param t                  查找的对象
     * @param resultCode         状态码
     * @param needThrowException 是否需要抛出异常异常
     * @param <T>                查找数据的泛型
     * @return 结果
     */
    public static <T> T selectOne(T t, ResultCode resultCode, Boolean needThrowException) {
        if (MyObjectUtils.isNull(t)) {
            if (needThrowException) {
                throw new GuliMallException(resultCode);
            } else {
                return null;
            }
        }
        return t;
    }

    /**
     * 负责添加或者更新单个数据的处理
     *
     * @param t          添加的数据
     * @param bool       添加或者更新的结果
     * @param resultCode 状态码
     * @param <T>        添加或者更新的数据泛型
     * @return 添加或者更新的结果
     */
    public static <T> T insertOrUpdate(T t, Boolean bool, ResultCode resultCode) {
        if (bool) {
            return t;
        }
        throw new GuliMallException(resultCode);
    }

    /**
     * 负责删除单个数据的处理
     *
     * @param bool       删除结果
     * @param resultCode 状态码
     */
    public static void delete(Boolean bool, ResultCode resultCode) {
        delete(bool, resultCode, true);
    }

    /**
     * 负责删除单个数据的处理
     *
     * @param bool           删除结果
     * @param resultCode     状态码
     * @param throwException 是否需要抛出异常
     */
    public static void delete(Boolean bool, ResultCode resultCode, Boolean throwException) {
        if (!bool) {
            if (throwException) {
                throw new GuliMallException(resultCode);
            }
        }
    }


    /**
     * 负责添加或者更新批量数据的处理
     *
     * @param list       添加的数据集合
     * @param bool       添加或者更新的结果
     * @param resultCode 状态码
     * @return 添加或者更新的结果
     */
    public static List<?> batchInsertOrUpdate(List<?> list, Boolean bool, ResultCode resultCode) {
        return batchInsertOrUpdate(list, bool, resultCode, true);
    }


    /**
     * 负责添加或者更新批量数据的处理
     *
     * @param list           添加的数据集合
     * @param bool           添加或者更新的结果
     * @param resultCode     状态码
     * @param throwException 是否需要抛出异常
     * @return 添加或者更新的结果
     */
    public static List<?> batchInsertOrUpdate(List<?> list, Boolean bool, ResultCode resultCode, Boolean throwException) {
        if (bool) {
            return list;
        }
        if (throwException) {
            throw new GuliMallException(resultCode);
        }
        return null;
    }


    /**
     * 负责删除多个数据的处理
     *
     * @param bool       删除结果
     * @param resultCode 状态码
     */
    public static void batchDelete(Boolean bool, ResultCode resultCode) {
        batchDelete(bool, resultCode, true);
    }

    /**
     * 负责删除多个数据的处理
     *
     * @param bool           删除结果
     * @param resultCode     状态码
     * @param throwException 是否抛出异常
     */
    public static void batchDelete(Boolean bool, ResultCode resultCode, Boolean throwException) {
        if (!bool) {
            if (throwException) {
                throw new GuliMallException(resultCode);
            }
        }
    }
}
