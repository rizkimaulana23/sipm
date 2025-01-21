package com.menyala.sipm.firebase.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import com.menyala.sipm.firebase.FirebaseConfig;
import com.menyala.sipm.firebase.FirebaseProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Service
public class FirebaseStorageService {

        @Autowired
        private FirebaseProperties firebaseProperties;

        @Autowired
        FirebaseConfig firebaseConfig;

        public String uploadFile(String fileName, byte[] fileData, String contentType) throws IOException {
            try {
                firebaseConfig.initializeFirebase();
                String bucketName = firebaseProperties.getStorageBucket();
                Bucket bucket = StorageClient.getInstance().bucket(bucketName);

                // Buat blob untuk file yang akan diunggah
                Blob blob = bucket.create(fileName, fileData, contentType);

                // URL publik file
                return blob.getMediaLink();
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("Error uploading file to Firebase Storage", e);
            }
        }
    }







