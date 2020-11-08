package cn.y3h2.blog.web.common.utils;

/**
 * @ClassName NumberUtil
 * @Author tubingyi
 * @Date 2020-06-03 15:43
 * @Description 数字工具类
 **/
public class NumberUtil {
    /*除数10*/
    public static final int DIVISOR_10 = 10;
    /*除数100*/
    public static final int DIVISOR_100 = 100;
    /*除数1000*/
    public static final int DIVISOR_1000 = 1000;
    /**
     * 转换成保留两位小数的字符串
     * number 被转换侧数字
     * divisor 除数
     * exp:number 12345,divisor 100 返回值 123.45
     * @param number
     * @return
     */
    public static String get2DecimalStr(Integer number,int divisor){
        if(number == null || number.intValue() == 0 || divisor == 0){
            return "";
        }
        double v = number.doubleValue()/(double)divisor;
        String retV =String.format("%.2f", v);
        return retV;
    }
}
