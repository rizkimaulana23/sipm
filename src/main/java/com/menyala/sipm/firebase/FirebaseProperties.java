package com.menyala.sipm.firebase;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
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

}
