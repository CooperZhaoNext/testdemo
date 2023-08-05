package org.example.repos;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;

public class PmdCommondMain {

    private static void showFiles(Collection<File> listFiles) {
        if (listFiles == null) {
            return;
        }
        for (File file : listFiles) {
            System.out.println(file.getName());
        }
    }

    public static void main(String[] args) throws Exception{
        String result = FileUtils.readFileToString(new File("repos.json"), "UTF-8");

        Map<String,String> reposMap = JSONObject.parseObject(result,new TypeReference<Map<String, String>>(){});

        String targetDirectory = "/Users/cooper.zhao/project/allrepos/%s/.circleci/config.yml";

        String path = "/Users/cooper.zhao/project/allrepos/ErosService/.circleci/config.yml";
        List<String> exclude = Lists.newArrayList("BackendService");


        for(Map.Entry<String,String> entry:reposMap.entrySet()){
            if (exclude.contains(entry.getKey())) {
                continue;
            }
            System.out.println(entry.getKey());
            dealPmdCom(entry.getKey());
        }

    }

    public static void dealPmdCom(String repos) throws Exception {

        String path = "/Users/cooper.zhao/project/allrepos/%s/.circleci";

        String targetDirectory = "/Users/cooper.zhao/project/allrepos/%s/.circleci/config.yml";

        path = String.format(path,repos);
        targetDirectory = String.format(targetDirectory,repos);

        Collection<File> listFiles = FileUtils.listFiles(new File(path), FileFilterUtils.suffixFileFilter("sh"), null);

        File target = null;
        for (String line : FileUtils.readLines(new File(targetDirectory), "UTF-8")) {

            for (File file : listFiles) {
                if (line.contains(".circleci/"+file.getName())) {
                    target = file;
                    break;
                }
            }
            if (target != null) {
                break;
            }
        }
        List<String> context = FileUtils.readLines(target, "UTF-8");
        for (int i = 0; i < context.size(); i++) {
            String line = context.get(i);
            if (line.contains("mvn -B ") && !line.contains("pmd:pmd")) {
                line = line.replace("mvn -B ", "mvn -B pmd:pmd ");
                context.set(i, line);
            }
        }
        FileUtils.writeLines(target, context);
    }

}
