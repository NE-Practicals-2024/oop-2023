package com.mugishap.rca.springboot.v1.utils;


import com.mugishap.rca.springboot.v1.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.security.SecureRandom;
import java.util.Random;

public class Utility {

    public static String getConstraintViolationMessage(DataIntegrityViolationException ex, User user) {
        String message = ex.getMostSpecificCause().getMessage();
        if (message.contains("email")) {
            return String.format("User with email '%s' already exists", user.getEmail());
        } else if (message.contains("telephone")) {
            return String.format("User with phone number '%s' already exists", user.getTelephone());
        }
        // Add more checks for other unique constraints if necessary
        return "A unique constraint violation occurred";
    }

    public static boolean isImageFile(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && (contentType.equals(MediaType.IMAGE_JPEG_VALUE) || contentType.equals(MediaType.IMAGE_PNG_VALUE) || contentType.equals(MediaType.IMAGE_GIF_VALUE));
    }


}
