package org.example.repos;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import java.io.File;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;

public class ReposMain {

    public static void main(String[] args) throws Exception {
        List<String> exclude = Lists.newArrayList("DashboardService", "trips", "BackendService");

        String result = FileUtils.readFileToString(new File("repos.json"), "UTF-8");

        Map<String,String> reposMap = JSONObject.parseObject(result,new TypeReference<Map<String, String>>(){});

        for(Map.Entry<String,String> entry:reposMap.entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue());
        }

    }

}
