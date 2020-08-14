package com.blog.newsangblog2.web.uploader;


import com.blog.newsangblog2.web.uploader.s3.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class UploadController {

    private final S3Uploader s3Uploader;

    @ResponseBody
    @PostMapping("/uploader/s3")
    public ResponseEntity<String> uploadTest(HttpServletResponse response, @RequestParam("file") MultipartFile file) {

        System.out.println("test1: " + s3Uploader.getS3Url());
        System.out.println("test2: " + s3Uploader.getS3UrlByFile("a.jpg"));


        return null;
    }
    @ResponseBody
    @GetMapping("/uploader/s3")
    public ResponseEntity<String> guetploadTest(HttpServletRequest request, @RequestParam("file") MultipartFile file) {
        System.out.println("test:::");
        System.out.println("test:::");
        System.out.println("test:::");
        System.out.println("test:::");
        System.out.println("test:::");
        return null;
    }

}
