package org.example.repos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class TestCommitMain {

    public static void main(String[] args) throws Exception {
        String commitMessage = "\"Add api authorization checking config\"";
        String repositoryPath = "/Users/cooper.zhao/project/allrepos/Marketplace-qualifications-service";
        String[] command = {"git", "-C", repositoryPath, "commit", "-m",commitMessage};
        Process process = Runtime.getRuntime().exec(command);

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        process.waitFor();
        reader.close();
    }

}
