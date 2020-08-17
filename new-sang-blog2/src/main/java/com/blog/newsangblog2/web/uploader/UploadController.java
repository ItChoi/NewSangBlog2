package com.blog.newsangblog2.web.uploader;


import com.blog.newsangblog2.web.uploader.s3.S3Uploader;
import com.blog.newsangblog2.web.uploader.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class UploadController {

    private final S3Uploader s3Uploader;

    private final UploadService uploadService;

    @ResponseBody
    @PostMapping("/uploader/editor-image")
    public ResponseEntity<Map<Object, Object>> uploadTest(@RequestParam("file") MultipartFile file,
                                                          HttpServletRequest request, HttpServletResponse response) {
        Map<Object, Object> responseData = null;
        try {
            responseData = uploadService.settingFroalaImageResponse(file, response, request);
        } catch (IOException | ServletException e) {
            log.error("EEROR: {}", e);
        }


        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
