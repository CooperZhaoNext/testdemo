package org.example.repos;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CreateNewPullRequestMain {


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

    public static void main(String[] args) {

        String targetDirectory = "/Users/cooper.zhao/project/allrepos/trips";
        String commend = "gh pr create -repo git@github.com:NextDeveloperTeam/carrier-service.git  -t \"Add api authorization checking config\" -b \"Scan all the apis, find the apis that have no permission protection. https://nexttrucking.atlassian.net/browse/APD-29847\" -B master -H api-authorization-check";







    }


}
