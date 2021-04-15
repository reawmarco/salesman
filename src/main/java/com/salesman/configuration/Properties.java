package com.salesman.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Properties {
    @Value("${separator}")
    private String separator;
    @Value("${poller.timerate}")
    private Long pollerTimerate;
    @Value("${poller.maxmessages}")
    private int pollerMaxmessages;
    @Value("${file.in.type}")
    private String fileInType;
    @Value("${file.in.folder}")
    private String fileInFolder;
    @Value("${file.out.folder}")
    private String fileOutFolder;
    @Value("${file.out.regex}")
    private String fileOutRegex;
    @Value("${file.out.replace}")
    private String fileOutReplace;

    public String getBasePath() {
        return System.getProperty("user.home");
    }

    public String getSeparator() {
        return separator;
    }

    public Long getPollerTimerate() {
        return pollerTimerate;
    }

    public int getPollerMaxmessages() {
        return pollerMaxmessages;
    }

    public String getFileInType() {
        return fileInType;
    }

    public String getFileInFolder() {
        return fileInFolder;
    }

    public String getFileOutFolder() {
        return fileOutFolder;
    }

    public String getFileOutRegex() {
        return fileOutRegex;
    }

    public String getFileOutReplace() {
        return fileOutReplace;
    }
}
