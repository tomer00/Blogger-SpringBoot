package com.tomer.blogger.services;

import com.tomer.blogger.utils.Const;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;


@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImg(MultipartFile file) throws IOException {

        String fileName = Const.getRandomName();
        fileName += ".png";

        File folder = new File(Const.IMAGES_PATH);
        if (!folder.exists()) folder.mkdirs();
        File fileImg = new File(folder, fileName);

        Files.copy(file.getInputStream(), fileImg.toPath());

        return fileName.substring(0,fileName.length()-4);
    }
}
