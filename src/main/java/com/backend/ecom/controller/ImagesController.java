package com.backend.ecom.controller;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImagesController {

	@GetMapping("/api/images")
    public List<String> listImages() {
        String folderPath = "D:/Android_Projects/backendEcomAPI/uploads/images"; // Change if needed
        File folder = new File(folderPath);

        // Folder not found
        if (!folder.exists()) {
            return List.of("❌ Folder not found: " + folderPath);
        }
        // Not a directory
        if (!folder.isDirectory()) {
            return List.of("❌ Not a directory: " + folderPath);
        }

        String[] fileNames = folder.list();
        if (fileNames == null || fileNames.length == 0) {
            return List.of("⚠ No files found in: " + folderPath);
        }

        // Return URLs instead of just names
        return Arrays.stream(fileNames)
                .map(name -> "http://localhost:8080/api/images/" + name)
                .toList();
    }
}
