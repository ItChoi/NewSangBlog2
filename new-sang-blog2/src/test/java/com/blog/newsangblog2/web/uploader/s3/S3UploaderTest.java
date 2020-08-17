package com.blog.newsangblog2.web.uploader.s3;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

class S3UploaderTest {
    private static final Regions REGION = Regions.AP_NORTHEAST_2;

    /*@Test
    public void S3_파일_경로_가져오기() {
        AmazonS3 s3Uploader = Mockito.mock(AmazonS3.class);
        String bucketName = "sang-blog-image";
        s3Uploader.setRegion(Region.getRegion(REGION));

        ListObjectsRequest request = new ListObjectsRequest().withBucketName(bucketName);

        ObjectListing objectListing = s3Uploader.listObjects(request);

        for (S3ObjectSummary objectSummary : objectListing.getObjectSummaries()) {
            System.out.println("test: " + objectSummary.getKey());

        }

    }*/

    @Test
    public void S3_디폴트_경로_가져오기() {

    }

}