package org.example;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

public class TestUrlPatternMain {

    public static void main(String[] args) {
        FileSystem fileSystem = FileSystems.getDefault();
        PathMatcher pathMatcher = fileSystem.getPathMatcher("glob:" + "/api/frontweb/cache/jobStatus/*");
        Path urlPath = Paths.get("/api/frontweb/cache/jobStatus/");

        System.out.println(pathMatcher.matches(urlPath));
    }

}
