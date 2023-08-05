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

public class NewBranchMain {


    public static void main(String[] args) throws Exception {
        List<String> exclude = Lists.newArrayList("BackendService");

        String targetDirectory = "/Users/cooper.zhao/project/allrepos/%s";

        String result = FileUtils.readFileToString(new File("repos.json"), "UTF-8");

        Map<String, String> reposMap = JSONObject.parseObject(result, new TypeReference<Map<String, String>>() {
        });

        for (Map.Entry<String, String> entry : reposMap.entrySet()) {
            if (exclude.contains(entry.getKey())) {
                continue;
            }
            commitChange(String.format(targetDirectory, entry.getKey()));
        }


    }


    public static void newBranch(String repositoryPath) {
        String branchName = "api-authorization-check";
        String commitMessage = "Add api authorization checking config";
        String remote = "origin";

        try {
            // 创建分支
            String createBranchCommand = "git -C " + repositoryPath + " branch " + branchName;
            executeCommand(createBranchCommand);

            // 切换到新分支
            String switchBranchCommand = "git -C " + repositoryPath + " checkout " + branchName;
            executeCommand(switchBranchCommand);

            String pushCommand = "git -C " + repositoryPath + " push " + remote + " " + branchName;
            executeCommand(pushCommand);

            System.out.println("Branch created and changes committed. "+repositoryPath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void commitChange(String repositoryPath) {
        String branchName = "api-authorization-check";
        String commitMessage = "Add api authorization checking config";
        String remote = "origin";

        try {
            // 添加文件到暂存区
            String addCommand = "git -C " + repositoryPath + " add .";
            executeCommand(addCommand);

            // 提交 commit
            executeCommit(repositoryPath);

            String pushCommand = "git -C " + repositoryPath + " push " + remote + " " + branchName;
            executeCommand(pushCommand);

            System.out.println("Committed and Pushed."+repositoryPath);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void executeCommit(String repositoryPath) {
        String commitMessage = "\"Add api authorization checking config\"";
        String[] command = {"git", "-C", repositoryPath, "commit", "-m", commitMessage};
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeCommand(String command) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime().exec(command);

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new IOException("Command execution failed: " + command);
        }

        reader.close();
    }

}
