package com.example.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wangweichun
 * @create 2020-05-09 17:46
 **/
public class DateUtils {

    /**
     * 格式化时间
     * @return 时间格式：yyyyMMddHHmmss
     */
    public static String getTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }

}
