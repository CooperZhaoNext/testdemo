package org.example.repos;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;

public class AllReposMain {

    private static String targetDirectory = "/Users/cooper.zhao/project/allrepos";

    public static void main(String[] args) throws IOException {

        List<String> exclude = Lists.newArrayList("BackendService");

        String result = FileUtils.readFileToString(new File("repos.json"), "UTF-8");

        Map<String,String> reposMap = JSONObject.parseObject(result,new TypeReference<Map<String, String>>(){});


        for (String url : reposMap.values()) {
            new Thread(() -> cloneProject(url)).start();
        }
    }

    public static void cloneProject(String url) {
        try {
            System.out.println(url+" clone start");
            String[] command = {"git", "-C", targetDirectory, "clone", url};
            Process process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
            reader.close();
            System.out.println(url+" clone end");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

