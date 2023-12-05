package com.wf;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * SqlCreatUtil
 *
 * @author 王飞
 */
public class SqlCreatUtil {

    public static void append(String sqlText, Object voObject, StringBuilder sqlBuilder, List<Object> args) {
        if (ObjectUtils.isEmpty(voObject)) {
            return;
        }
        // 如果是String
        if (voObject instanceof String) {
            if (StringUtils.hasText((String) voObject)) {
                sqlBuilder.append(sqlText);
                args.add(voObject);
                return;
            }
        }
        // 如果是集合
        if (voObject instanceof Collection) {
            Collection<?> collection = (Collection<?>) voObject;
            if (!CollectionUtils.isEmpty(collection)) {
                String idJoinStr = collection.stream().map(Object::toString).collect(Collectors.joining(","));
                sqlText = sqlText.replace("?", idJoinStr);
                sqlBuilder.append(sqlText);
                return;
            }
        }
        // 其它
        sqlBuilder.append(sqlText);
        args.add(voObject);
    }

    /**
     * 获取 vo.getXxx()方法
     * @param input AND delivery_pay_id = ?
     * @return
     */
    public static String parseAndGenerateGetter(String input) {
        // 正则表达式用于匹配字段名
//        Pattern pattern = Pattern.compile("AND\\s+([a-zA-Z_]+)\\s*=");
        Pattern pattern = Pattern.compile("(?i)and\\s+([a-zA-Z_]+)\\s*=");
        Matcher matcher = pattern.matcher(input);

        // 查找匹配的字段名
        if (matcher.find()) {
            String fieldName = matcher.group(1);

            // 转换字段名为驼峰形式
            String camelCaseFieldName = underscoreToCamelCase(fieldName);

            // 生成对应的getter方法名
            String getterMethodName = "get" + capitalizeFirstLetter(camelCaseFieldName);

            // 返回生成的getter方法名
            return "vo." + getterMethodName + "()";
        } else {
            // 如果没有匹配的字段名，返回空字符串或者其他错误处理方式
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

    public static void main(String[] args) {
        String input = "AND delivery_pay_id = ?";
        String output = parseAndGenerateGetter(input);
        System.out.println(output);
    }
}
