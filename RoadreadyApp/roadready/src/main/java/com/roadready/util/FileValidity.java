package com.roadready.util;

import com.roadready.exceptions.FileInvalidExtensionException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.util.List;

@Component
public class FileValidity {


    public static void validateFile(MultipartFile file) throws FileNotFoundException {

        // check whether the file is empty or not
        if (file.isEmpty())
            throw new FileNotFoundException("Please select file to upload");

        // make a list of extension that are allowed
        List<String> allowedExtensions = List.of("docx", "pages", "jpeg", "pdf", "jpg", "png");

        // get the file name
        String filename = file.getOriginalFilename();

        // get the extension
        String ext = filename.split("\\.")[1];

        if (!allowedExtensions.contains(ext))
            throw new FileInvalidExtensionException(ext + " not allowed");

    }
}
