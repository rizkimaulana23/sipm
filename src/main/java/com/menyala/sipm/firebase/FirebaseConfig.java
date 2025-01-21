package com.menyala.sipm.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class FirebaseConfig {

    @Autowired
    private FirebaseProperties fp;

    @PostConstruct
    public void initializeFirebase() {
        try {
            // Periksa apakah Firebase Emulator sedang digunakan
            boolean useEmulator = Boolean.parseBoolean(System.getenv("USE_FIREBASE_EMULATOR"));

            if (FirebaseApp.getApps().isEmpty()) {
                if (useEmulator) {
                    // Konfigurasi untuk Firebase Emulator
                    System.setProperty("firebase.storage-emulator-host", "localhost:9199");
                    System.out.println("Firebase Emulator Storage configured at localhost:9199");

                    FirebaseOptions options = FirebaseOptions.builder()
                            .setStorageBucket(fp.getStorageBucket())
                            .build();

                    FirebaseApp.initializeApp(options);
                    System.out.println("Firebase Initialized with Bucket: " + options.getStorageBucket());
                    System.out.println("Firebase App Initialized with Emulator");
                } else {
                    // Konfigurasi untuk Firebase Production
                    FileInputStream serviceAccount =
                            new FileInputStream("src/main/resources/sipm-87d88-firebase-adminsdk-hc6lx-19f49e8840.json");

                    FirebaseOptions options = FirebaseOptions.builder()
                            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                            .setStorageBucket(fp.getStorageBucket())
                            .build();

                    FirebaseApp.initializeApp(options);
                    System.out.println("Firebase App Initialized for Production");
                }
            } else {
                System.out.println("FirebaseApp [DEFAULT] already initialized. Skipping initialization.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize Firebase", e);
        }
    }

}
