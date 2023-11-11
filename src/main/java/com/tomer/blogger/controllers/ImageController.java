package com.tomer.blogger.controllers;

import com.tomer.blogger.payloads.FileResponse;
import com.tomer.blogger.services.FileService;
import com.tomer.blogger.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;

@RestController
@RequestMapping("file")
public class ImageController {

    @Autowired
    private FileService service;

    @GetMapping(value = "/img/{name}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<InputStreamResource> getImageDynamicType(
            @PathVariable String name
    ) throws Exception {
        InputStream in = new FileInputStream(Const.IMAGES_PATH + name + ".png");
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new InputStreamResource(in));
    }


    @PostMapping("/img")
    public ResponseEntity<FileResponse> uploadImg(
            @RequestParam(value = "image") MultipartFile file
    ) {

        try {
            var name = service.uploadImg(file);
            return ResponseEntity.ok(new FileResponse(name, "File Uploaded Successfully"));
        } catch (Exception e) {
            return new ResponseEntity<>(new FileResponse("null", e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
