package com.danbro.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * @Classname MyRandomUtils
 * @Description TODO
 * @Date 2021/3/3 20:57
 * @Created by Administrator
 */
public class MyRandomUtils {

    /**
     * 随机UUID
     *
     * @return UUID
     */
    public static String randomUUID() {
        return IdUtil.randomUUID();
    }

    /**
     * 通过雪花算法生成 ID
     *
     * @return ID
     */
    public static String snowFlakeId() {
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        return snowflake.nextIdStr();
    }
}
