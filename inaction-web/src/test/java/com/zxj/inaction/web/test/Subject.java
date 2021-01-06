package com.zxj.inaction.web.test;

import com.jd.fastjson.JSON;
import lombok.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhaoxiujie
 * @Date 2020/12/8 21:50
 * @Description: TODO
 */
@Data
public class Subject {
    private String subjectName;
    private String subjectScore;

    public Subject(String subjectName, String subjectScore) {
        this.subjectName = subjectName;
        this.subjectScore = subjectScore;
    }

    private static String params = "张三 语文 100\n" +
            "李四 数学 90\n" +
            "王二 手工 80.5\n" +
            "张三 数学 89\n" +
            "张三 语文 88\n";

    public static void main(String[] args) {
        String[] items = params.split("\n");
        if (items == null) {
            return;
        }

        Map<String, List<Subject>> users = new HashMap<>();
        for (String item : items) {

            // 拆分每行数据
            String[] data = item.split(" ");
            if (data == null) {
                continue;
            }

            // 数据不合法
            if (data.length < 3) {
                continue;
            }

            String name = data[0];
            String subjectName = data[1];
            String subjectScore = data[2];

            if (users.containsKey(name)) {
                users.get(name).add(new Subject(subjectName, subjectScore));
            } else {
                List<Subject> subjects = new ArrayList<>();
                subjects.add(new Subject(subjectName, subjectScore));
                users.put(name, subjects);
            }
        }
        System.out.println(JSON.toJSONString(users) );

    }

    public static String readFile (String fileName){
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            reader=new BufferedReader(new FileReader(file));
            String tempStr=reader.readLine();
            while (tempStr!=null){
                sb.append(tempStr);
            }
            reader.close();
            return sb.toString();
        }catch (IOException ex){
            ex.printStackTrace();
        }finally {
            if (reader!=null){
                try {
                    reader.close();
                }catch (IOException ex){
                    ex.printStackTrace();
                }
            }
        }
        return sb.toString();
    }


}
