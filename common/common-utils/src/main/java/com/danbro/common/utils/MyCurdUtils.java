package com.danbro.common.utils;

import com.danbro.common.exceptions.GuliMallException;
import com.danbro.common.interfaces.ResultCode;

import java.util.List;
import java.util.Optional;

/**
 * @author Danrbo
 * @Classname CurdUtils
 * @Description TODO 数据库的crud工具，捕获异常。
 * @Date 2021/2/5 13:35
 */
public class MyCurdUtils {

    public static <T> T selectOne(T t, ResultCode resultCode) {
        return Optional
                .ofNullable(t)
                .orElseThrow(() -> new GuliMallException(resultCode));
    }

    public static <T> T insertOrUpdate(T t, Boolean bool, ResultCode resultCode) {
        if (bool) {
            return t;
        }
        throw new GuliMallException(resultCode);
    }

    public static void delete(Boolean bool, ResultCode resultCode) {
        if (!bool) {
            throw new GuliMallException(resultCode);
        }
    }

    public static List<?> batchInserOrUpdate(List<?> list, Boolean bool, ResultCode resultCode) {
        if (bool) {
            return list;
        }
        throw new GuliMallException(resultCode);
    }


    public static void batchDelete(Boolean bool, ResultCode resultCode) {
        if (!bool) {
            throw new GuliMallException(resultCode);
        }
    }
}
