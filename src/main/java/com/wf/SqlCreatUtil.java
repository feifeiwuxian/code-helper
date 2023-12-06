package com.wf;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.List;
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
                String idJoinStr = collection.stream().map(Object::toString).collect(Collectors.joining("," ));
                sqlText = sqlText.replace("?" , idJoinStr);
                sqlBuilder.append(sqlText);
                return;
            }
        }
        // 其它
        sqlBuilder.append(sqlText);
        args.add(voObject);
    }

}
