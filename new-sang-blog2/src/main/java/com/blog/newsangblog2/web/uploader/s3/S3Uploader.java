package com.blog.newsangblog2.web.uploader.s3;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class S3Uploader {
    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public String upload(MultipartFile file, String fileDir) throws IOException {
        String fileName = file.getOriginalFilename();

        s3Client.putObject(new PutObjectRequest(bucket, fileDir + fileName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead)
        );

        return s3Client.getUrl(bucket, fileName).toString();
    }

    public String getS3FileUrl(String fileSrc) {
        String url = s3Client.getUrl(bucket, fileSrc) + "";
        return url;
    }

    /*public String getS3Url() {
        String url = s3Client.getBucketLocation(bucket);
        S3Object fullObject = s3Client.getObject(new GetObjectRequest(bucket, secretKey));

        ListObjectsRequest request = new ListObjectsRequest().withBucketName(bucket);
        ObjectListing objectListing = s3Client.listObjects(request);
        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println("test: " + objectSummary.getKey());

        }

        return "url";
    }*/



}
