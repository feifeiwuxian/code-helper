package com.wf;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * WFTools
 *
 * @author 王飞
 * @date 2023-12-06 16:33:41
 */
public class WFTools {
    /**
     * 获取 vo.getXxx()方法
     *
     * @param input AND delivery_pay_id = ?
     * @return
     */
    public static String parseAndGenerateGetter(String input) {
        // 正则表达式用于匹配字段名
//        Pattern pattern = Pattern.compile("AND\\s+([a-zA-Z_]+)\\s*=");
//        Pattern pattern = Pattern.compile("(?i)and\\s+([a-zA-Z_]+)\\s*=");
//        Pattern pattern = Pattern.compile("(?i)and\\s+([a-zA-Z_.]+)\\s*([<>]=?|=)\\s*\\?");
//        Pattern pattern = Pattern.compile("(?i)and\\s+([a-zA-Z_.]+)\\s+like\\s+\\?");
        Pattern pattern = Pattern.compile("(?i)and\\s+([a-zA-Z_.]+)\\s*(([<>]=?|!=|=)\\s*\\?|(like)\\s+.*?\\?)");
        Matcher matcher = pattern.matcher(input);

        // 查找匹配的字段名和比较符号
        if (matcher.find()) {
            String fullFieldName = matcher.group(1);
            String operator = matcher.group();
            String operator3 = matcher.group(3);
            String operator4 = matcher.group(4);

            // 分离字段名中的类名和属性名
            int lastDotIndex = fullFieldName.lastIndexOf('.');
            String className = fullFieldName.substring(0, lastDotIndex);
            String fieldName = fullFieldName.substring(lastDotIndex + 1);

            // 转换属性名为驼峰形式
            String camelCaseFieldName = underscoreToCamelCase(fieldName);

            // 生成对应的getter方法名
            String getterMethodName = "get" + capitalizeFirstLetter(camelCaseFieldName);

            // 生成带比较符号的条件表达式
            String condition = "vo." + getterMethodName + "()";

            return condition;
        } else {
            // 如果没有匹配的字段名和比较符号，返回空字符串或者其他错误处理方式
            return "";
        }
    }
    private static String underscoreToCamelCase(String input) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = false;

        for (char ch : input.toCharArray()) {
            if (ch == '_') {
                capitalizeNext = true;
            } else {
                result.append(capitalizeNext ? Character.toUpperCase(ch) : ch);
                capitalizeNext = false;
            }
        }

        return result.toString();
    }

    private static String capitalizeFirstLetter(String input) {
        return Character.toUpperCase(input.charAt(0)) + input.substring(1);
    }

    public static void main (String[]args){
//        and a.despatch_actual_datetime >= ?
//                and a.despatch_actual_datetime <= ?
//                and a.goods_receipt_datetime >= ?
//                and a.goods_receipt_datetime <= ?
//                and a.wccyr_send_date >= ?
//                and a.wccyr_send_date <= ?
//                and v.upload_time >= ?
//                and v.upload_time <= ?
//                and d.upload_time >= ?
//                and d.upload_time <= ?
//                and a.wccyr_flag = ?
        String input = "and d.upload_name like concat('%', ?, '%')";
//            String input = "and d.upload_name like ?";
//            String input = "and d.upload_name >= ?";
        String output = parseAndGenerateGetter(input);
        System.out.println(output);
//              test2();

    }

    private static void test2() {
        String input = "abc 123";
        Pattern pattern1 = Pattern.compile(".+");
        Pattern pattern2 = Pattern.compile("\\s+");

        Matcher matcher1 = pattern1.matcher(input);
        Matcher matcher2 = pattern2.matcher(input);

        while (matcher1.find()) {
            System.out.println("Pattern 1: " + matcher1.group());
        }

        while (matcher2.find()) {
            System.out.println("Pattern 2: " + matcher2.group());
        }
    }
}
