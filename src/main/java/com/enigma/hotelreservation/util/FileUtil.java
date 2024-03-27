package com.enigma.hotelreservation.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    public static void createUploadDirectory() {
        String uploadPath = "payment";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdir();
            if (!created) {
                throw new IllegalStateException("Unable to create directory");
            }
        }
    }

    public static String generateUniqueFilename(Integer resvId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = format.format(new Date());
        return "payment_" + resvId  + "_" + timestamp;
    }
}

