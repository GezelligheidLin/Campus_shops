package com.taotao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YuLong
 * Date: 2022/12/3 16:43
 * 身份证号码的格式：610821-20061222-612-X 由18位数字组成：
 * 前6位为地址码，第7至14位为出生日期码，第15至17位为顺序码，第18位为校验码。
 * 检验码分别是0-10共11个数字，当检验码为“10”时，为了保证公民身份证号码18位，所以用“X”表示。
 * 虽然校验码为“X”不能更换，但若需全用数字表示，
 * 只需将18位公民身份号码转换成15位居民身份证号码，去掉第7至8位和最后1位3个数码。
 * 当今的身份证号码有15位和18位之分。
 * 1985年我国实行居民身份证制度，当时签发的身份证号码是15位的，
 * 1999年签发的身份证由于年份的扩展（由两位变为四位）和末尾加了效验码，就成了18位。
 * （1）前1、2位数字表示：所在省份的代码；
 * （2）第3、4位数字表示：所在城市的代码；
 * （3）第5、6位数字表示：所在区县的代码；
 * （4）第7~14位数字表示：出生年、月、日；
 * （5）第15、16位数字表示：所在地的派出所的代码；
 * （6）第17位数字表示性别：奇数表示男性，偶数表示女性
 * （7）第18位数字是校检码：根据一定算法生成
 */
public class IdCardVerification {
    // 关于身份证验证消息常量区
    /** 身份证有效 */
    private static final String VALIDITY = "该身份证有效！";
    /** 位数不足 */
    private static final String LACK_DIGITS = "身份证号码长度应为15位或18位。";
    /** 最后一位应为数字 */
    private static final String LAST_OF_NUMBER = "身份证15位号码都应为数字，18位除最后一位外，都应为数字。";
    /** 出生日期无效 */
    private static final String INVALID_BIRTH = "身份证出生日期无效。";
    /** 生日不在有效范围 */
    private static final String INVALID_SCOPE = "身份证生日不在有效范围。";
    /** 月份无效 */
    private static final String INVALID_MONTH = "身份证月份无效";
    /** 日期无效 */
    private static final String INVALID_DAY = "身份证日期无效";
    /** 身份证地区编码错误 */
    private static final String CODING_ERROR = "身份证地区编码错误。";
    /** 身份证校验码无效 */
    private static final String INVALID_CALIBRATION = "身份证校验码无效，不是合法的身份证号码";

    // 字符及数字常量区（避免出现魔法值）

    private static final String RAIL = "-";
    private static final String YYYY_MM_DD = "yyyy-MM-dd";
    private static final Integer ZERO = 0;
    private static final Integer TWO = 2;
    private static final Integer SIX = 6;
    private static final Integer TEN = 10;
    private static final Integer TWELVE = 12;
    private static final Integer FOURTEEN = 14;
    private static final Integer FIFTEEN = 15;
    private static final Integer SEVENTEEN = 17;
    private static final Integer EIGHTEEN = 18;
    private static final Integer THIRTY_ONE = 31;
    private static final Integer HUNDRED_FIFTY = 150;

    /**
     * 身份证号验证主方法
     * @param idStr 身份证号
     * @return 身份证号验证结果消息
     */
    public static String idCardValidate(String idStr) {
        // 记录错误信息
        String tipInfo = VALIDITY;
        String ai;
        // 判断号码的长度 15位或18位
        if (idStr.length() != FIFTEEN && idStr.length() != EIGHTEEN) {
            tipInfo = LACK_DIGITS;
            return tipInfo;
        }

        // 18位身份证前 17位位数字，如果是 15位的身份证则所有号码都为数字
        // （前面已经做过判断，除长度为 15或 18位的字符串外，都是走不到这里的，因此无需再次判断 15位的）
        if (idStr.length() == EIGHTEEN) {
            ai = idStr.substring(ZERO, SEVENTEEN);
        } else {
            ai = idStr.substring(ZERO, SIX) + "19" + idStr.substring(SIX, FIFTEEN);
        }
        if (!isNumeric(ai)) {
            tipInfo = LAST_OF_NUMBER;
            return tipInfo;
        }

        // 判断出生年月是否有效
        // 年份
        String strYear = ai.substring(SIX, TEN);
        // 月份
        String strMonth = ai.substring(TEN, TWELVE);
        // 日期
        String strDay = ai.substring(TWELVE, FOURTEEN);
        if (!isDate(strYear + RAIL + strMonth + RAIL + strDay)) {
            tipInfo = INVALID_BIRTH;
            return tipInfo;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat(YYYY_MM_DD);
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > HUNDRED_FIFTY
                    || (gc.getTime().getTime() - s.parse(strYear + RAIL + strMonth + RAIL + strDay).getTime()) < ZERO) {
                tipInfo = INVALID_SCOPE;
                return tipInfo;
            }
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > TWELVE || Integer.parseInt(strMonth) == ZERO) {
            tipInfo = INVALID_MONTH;
            return tipInfo;
        }
        if (Integer.parseInt(strDay) > THIRTY_ONE || Integer.parseInt(strDay) == ZERO) {
            tipInfo = INVALID_DAY;
            return tipInfo;
        }

        // 判断地区码是否有效
        Hashtable<String, String> areaCode = getAreaCode();
        // 如果身份证前两位的地区码不在Hashtable，则地区码有误
        if (areaCode.get(ai.substring(ZERO, TWO)) == null) {
            tipInfo = CODING_ERROR;
            return tipInfo;
        }
        if (!isVerifyCode(ai, idStr)) {
            tipInfo = INVALID_CALIBRATION;
            return tipInfo;
        }
        return tipInfo;
    }

    /**
     * 判断第 18位校验码是否正确 第 18位校验码的计算方式：
     * 1. 对前 17位数字本体码加权求和 公式为：S = Sum(ai * wi),
     *       i = 0, ... , 16 其中 ai表示第 i个位置上的身份证号码数字值，wi表示第 i位置上的加权因子，
     *      其各位对应的值依次为： 7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
     * 2. 用 11对计算结果取模 Y = mod(S, 11)
     * 3. 根据模的值得到对应的校验码
     * 对应关系为： Y值： 0 1 2 3 4 5 6 7 8 9 10 校验码： 1 0 X 9 8 7 6 5 4 3 2
     * @param ai 第 i个位置上的身份证号码数字值
     * @param idStr id字符串
     * @return boolean
     */
    private static boolean isVerifyCode(String ai, String idStr) {
        String[] verifyCode = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
        String[] wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
        int sum = 0;
        for (int i = 0; i < SEVENTEEN; i++) {
            sum = sum + Integer.parseInt(String.valueOf(ai.charAt(i))) * Integer.parseInt(wi[i]);
        }
        int modValue = sum % 11;
        String strVerifyCode = verifyCode[modValue];
        ai = ai + strVerifyCode;
        if (idStr.length() == EIGHTEEN) {
            return ai.equals(idStr);
        }
        return true;
    }

    /**
     * 将所有地址编码保存在一个Hashtable中
     * @return Hashtable 对象
     */
    private static Hashtable<String, String> getAreaCode() {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    private static final String REGEX_DIGIT = "[0-9]*";
    private static final String REGEX_DATE = "^((\\d{2}(([02468][048])|([13579][26]))[\\-/\\s]?((((0?[13578])|(1[02]))[\\-/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-/\\s]?((((0?[13578])|(1[02]))[\\-/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))?$";

    /**
     * 判断字符串是否为数字,0-9重复0次或者多次
     * @param strNum 数字字符串
     * @return true, 符合; false, 不符合。
     */
    private static boolean isNumeric(String strNum) {
        Pattern pattern = Pattern.compile(REGEX_DIGIT);
        Matcher isNum = pattern.matcher(strNum);
        return isNum.matches();
    }

    /**
     * 功能：判断字符串出生日期是否符合正则表达式：包括年月日，闰年、平年和每月 31天、30天和闰月的 28天或者 29天
     * @param strDate 日期字符串
     * @return true, 符合; false, 不符合。
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile(REGEX_DATE);
        Matcher matcher = pattern.matcher(strDate);
        return matcher.matches();
    }

}