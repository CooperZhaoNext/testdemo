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

public class RemoveBranchMain {


    public static void main(String[] args) throws Exception {

        List<String> exclude = Lists.newArrayList("BackendService");

        String targetDirectory = "/Users/cooper.zhao/project/allrepos/%s";

        String path = "/Users/cooper.zhao/project/allrepos/ErosService";

        String result = FileUtils.readFileToString(new File("repos.json"), "UTF-8");

        Map<String, String> reposMap = JSONObject.parseObject(result, new TypeReference<Map<String, String>>() {
        });

        for (Map.Entry<String, String> entry : reposMap.entrySet()) {
            if (exclude.contains(entry.getKey())) {
                continue;
            }
            removeBranch(String.format(targetDirectory, entry.getKey()));
        }
    }



    public static void removeBranch(String repositoryPath) {
        try {

            String branchName = "api-authorization-check";

            String[] checkoutCommand = {"git","-C",repositoryPath,"checkout","master"};
            Process checkoutProcess = Runtime.getRuntime().exec(checkoutCommand);
            waitForProcess(checkoutProcess);

//            String[] deleteCommand = {"git", "-C", repositoryPath, "branch", "-D", branchName};
//            // 删除本地分支
//            Process deleteProcess = Runtime.getRuntime().exec(deleteCommand);
//            waitForProcess(deleteProcess);

            // 推送删除的分支到远程仓库
            String[] pushBranchCommand = {"git", "-C", repositoryPath,"push", "origin", "--delete", branchName};
            Process pushProcess = Runtime.getRuntime().exec(pushBranchCommand);
            waitForProcess(pushProcess);

            System.out.println("分支删除成功，并已推送到远程仓库。"+ repositoryPath);
        } catch (Exception e) {
            System.out.println("failed repositoryPath "+ repositoryPath);
            e.printStackTrace();
        }
    }

    private static void waitForProcess(Process process) throws IOException, InterruptedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("命令执行失败。");
        }
    }

}
