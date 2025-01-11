package com.menyala.sipm.firebase.controller;

import com.menyala.sipm.firebase.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/fileupload")
public class FirebaseController {
    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @RequestMapping(value = "/file", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        try {
            String fileName = file.getOriginalFilename();
            byte[] fileData = file.getBytes();
            String contentType = file.getContentType();

            String fileUrl = firebaseStorageService.uploadFile(fileName, fileData, contentType);
            return "File uploaded successfully: " + fileUrl;
        } catch (Exception e) {
            return "File upload failed: " + e.getMessage();
        }
    }
}
