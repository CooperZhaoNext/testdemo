package org.example.repos;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;

public class CheckConfigMain {

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
            System.out.println(entry.getKey()+" start");
            List<String> lines = FileUtils.readLines(new File(String.format(targetDirectory,entry.getKey())),"UTF-8");

            for(String l:lines){
                if(l.contains("mvn ") && !l.contains("mvn_revision")) {
                    System.out.println(entry.getKey()+":"+l);
                }
            }


        }
    }

}
