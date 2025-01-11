package com.menyala.sipm.firebase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FirebaseProperties {

    @Value("${firebase.apiKey}")
    public String apiKey;

    @Value("${firebase.authDomain}")
    private String authDomain;

    @Value("${firebase.projectId}")
    private String projectId;

    @Value("${firebase.storageBucket}")
    private String storageBucket;

    @Value("${firebase.messagingSenderId}")
    private String messagingSenderId;

    @Value("${firebase.appId}")
    private String appId;

    @Value("${firebase.measurementId}")
    private String measurementId;


    public String getApiKey() {
        return apiKey;
    }

    public String getAuthDomain() {
        return authDomain;
    }

    public String getProjectId() {
        return projectId;
    }

    public String getStorageBucket() {
        return storageBucket;
    }

    public String getMessagingSenderId() {
        return messagingSenderId;
    }

    public String getAppId() {
        return appId;
    }

    public String getMeasurementId() {
        return measurementId;
    }
}
