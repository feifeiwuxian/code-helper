package com.wf;

import java.io.*;

/**
 * CodeHelper
 *
 * @author 王飞
 */
public class CodeHelper {

    public static void convertFile(String sqlFileUrl, String outPutFileUrl) {

        // 使用ClassLoader来获取资源的URL
        ClassLoader classLoader = CodeHelper.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(sqlFileUrl);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        new FileReader(sqlFileUrl)
        String outputFile = "D:\\myproject\\ctojava\\" + outPutFileUrl;
        try (BufferedReader br = new BufferedReader(inputStreamReader);
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            // 先写入2行
            bw.write("    StringBuilder sql = new StringBuilder();");
            bw.newLine();
            bw.write("    List<Object> args = new ArrayList<>();");
            bw.newLine();
            String line;
            while ((line = br.readLine()) != null) {
                // 当前为变量行
                if (!line.isEmpty()) {
                    String variableLine = "    sql.append(\" " + line.trim() + " \");";
                    bw.write(variableLine);
//                    bw.write("\r\n");
                    bw.newLine();
                } else {
                    // 其它情况直接写入
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解析失败");
        }
    }

    public static void convertFileV2(String sqlFileUrl, String outPutFileUrl) {

        // 使用ClassLoader来获取资源的URL
        ClassLoader classLoader = CodeHelper.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(sqlFileUrl);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        new FileReader(sqlFileUrl)
        String outputFile = "D:\\myproject\\ctojava\\" + outPutFileUrl;
        try (BufferedReader br = new BufferedReader(inputStreamReader);
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            // 先写入2行
            bw.write("    StringBuilder sql = new StringBuilder();");
            bw.newLine();
            bw.write("    List<Object> args = new ArrayList<>();");
            bw.newLine();
            String line;
            while ((line = br.readLine()) != null) {
                // 当前为变量行
                if (!line.isEmpty()) {
                    // 如果是动态包含？
                    String variableLine;
                    String trimLine = line.trim();
                    if (trimLine.contains("?") && (trimLine.startsWith("and") || trimLine.startsWith("AND")) ) {
                        String voGetStr = WFTools.parseAndGenerateGetter(trimLine);
                        variableLine = "    SqlCreatUtil.append(\" " + trimLine + " \", "+ voGetStr +", sql, args);";
                    } else {
                        variableLine = "    sql.append(\" " + trimLine + " \");";
                    }
                    bw.write(variableLine);
//                    bw.write("\r\n");
                    bw.newLine();
                } else {
                    // 其它情况直接写入
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解析失败");
        }
    }

    public static void getSqlFile(String sqlFileUrl, String outPutFileUrl) {

        // 使用ClassLoader来获取资源的URL
        ClassLoader classLoader = CodeHelper.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(sqlFileUrl);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//        new FileReader(sqlFileUrl)
        String outputFile = "D:\\myproject\\ctojava\\" + outPutFileUrl;
        try (BufferedReader br = new BufferedReader(inputStreamReader);
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 当前为变量行
                String content = line.trim();
                if (content.contains(".append(") && content.startsWith("sql")) {
                    // 在Type后在加上    private String
                    // 删除Type及之前的内容
                    int typeIndex = content.indexOf(".append(");
                    String variableLine = content.substring(typeIndex + 9, content.length() - 3);
                    bw.write(variableLine);
//                    bw.write("\r\n");
                    bw.newLine();
                } else if (content.contains(".append(") && content.startsWith("SqlCreatUtil")) {
                    // 找到end位置
                    int endIndex = content.indexOf("\", vo.");
                    // 删除Type及之前的内容
                    int typeIndex = content.indexOf(".append(");
                    String variableLine = content.substring(typeIndex + 9, endIndex);
                    bw.write(variableLine);
                    bw.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("解析失败");
        }
    }
}
