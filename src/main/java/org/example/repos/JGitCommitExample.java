package org.example.repos;

import org.eclipse.jgit.api.CommitCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

import java.io.File;
import java.io.IOException;

public class JGitCommitExample {
    public static void main(String[] args) {
        String repositoryPath = "/Users/cooper.zhao/project/allrepos/Marketplace-qualifications-service";
        String commitMessage = "Add api authorization checking config";

        try {
            // 打开 Git 仓库
            Repository repository = FileRepositoryBuilder.create(new File(repositoryPath));
            Git git = new Git(repository);

            // 添加文件到暂存区
            git.add().addFilepattern(".").call();

            // 创建 commit
            CommitCommand commitCommand = git.commit().setMessage(commitMessage);

            // 执行 commit
            commitCommand.call();

            // 关闭 Git 仓库
            repository.close();

            System.out.println("Changes committed successfully.");
        } catch (IOException | GitAPIException e) {
            e.printStackTrace();
        }
    }
}