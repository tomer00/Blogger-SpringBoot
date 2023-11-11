package com.tomer.blogger.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface FileService {

    String uploadImg(MultipartFile file) throws IOException;
}
