package org.example.repos;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.FileUtils;

public class ConfigYamlMain {



    public static String kongbai(String line){
        String input = line;

        // 定义正则表达式匹配非字符部分
        Pattern pattern = Pattern.compile("^\\s*"); // 匹配开头的空白字符

        // 进行匹配
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String nonCharacterPrefix = matcher.group();
            return nonCharacterPrefix;
        } else {
            System.out.println("没有找到非字符前缀");
            return null;
        }
    }


    public static void updateShConfigYml(String path,String repos) throws Exception{

        List<String> stringList = FileUtils.readLines(new File(path),"UtF-8");

        boolean existed = stringList.stream().anyMatch(s -> s.contains("build/apiauthorizationcheck/api_authorization_check.sh api_authorization_check.sh"));

        if(existed){
            return;
        }

        for(int i =0;i<stringList.size();i++){
            String line =stringList.get(i);
            if(line.contains("docker cp nextdata:/home/circleci/repo/. ~/repo")){
                String blank = kongbai(line);

                stringList.add(i+1,blank+"bash api_authorization_check.sh");
                stringList.add(i+1,blank+"aws s3api get-object --bucket next-infrastructure --key build/apiauthorizationcheck/api_authorization_check.sh api_authorization_check.sh");

                stringList.add(i+1,"\n");


                break;
            }
        }

        FileUtils.writeLines(new File(path),stringList);





    }

    public static void updateAuthConfigYml(String path,String repos) throws Exception{


        List<String> stringList = FileUtils.readLines(new File(path),"UtF-8");

        boolean existed = stringList.stream().anyMatch(s -> s.contains("echo $BACKEND_PMD_REPORT_CREDENTIALS >"));
        if(existed){
            return;
        }

        for(int i =0;i<stringList.size();i++){
            String line =stringList.get(i);
            if(line.contains("docker create -v /home/circleci --name nextdata alpine:3.4 /bin/true")){
                String blank = kongbai(line);
                stringList.add(i+1,blank+"echo $BACKEND_PMD_REPORT_CREDENTIALS > ~/repo/.circleci/creds.json");
            }
        }
        FileUtils.writeLines(new File(path),stringList);
    }


    public static void main(String[] args) throws Exception {

        String result = FileUtils.readFileToString(new File("repos.json"), "UTF-8");

        Map<String,String> reposMap = JSONObject.parseObject(result,new TypeReference<Map<String, String>>(){});

        String targetDirectory = "/Users/cooper.zhao/project/allrepos/%s/.circleci/config.yml";

        String path = "/Users/cooper.zhao/project/allrepos/ErosService/.circleci/config.yml";
        List<String> exclude = Lists.newArrayList("BackendService");

//
//
//        updateShConfigYml(path,path);
//        updateAuthConfigYml(path,path);


        for(Map.Entry<String,String> entry:reposMap.entrySet()){
            if (exclude.contains(entry.getKey())) {
                continue;
            }
            updateAuthConfigYml(String.format(targetDirectory,entry.getKey()),entry.getKey());
            updateShConfigYml(String.format(targetDirectory,entry.getKey()),entry.getKey());
        }










    }

}
