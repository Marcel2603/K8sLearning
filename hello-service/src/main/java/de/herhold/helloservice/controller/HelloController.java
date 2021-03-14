package de.herhold.helloservice.controller;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.Bucket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {
    @Value("${app.access:foobar}")
    private String accessKey;
    @Value("${app.secret:foobar}")
    private String secret;
    @Value("${app.endpoint}")
    private String endpoint;

    @GetMapping(value = "/hello")
    public ResponseEntity<String> sayHallo() {
        return new ResponseEntity<>("Hello, World!", HttpStatus.OK);
    }

    @GetMapping(value = "/s3")
    public ResponseEntity<String> s3Test() {
        AWSCredentials credentials = new BasicAWSCredentials(
                accessKey,
                secret
        );
        AmazonS3 s3client = AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.EU_CENTRAL_1)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, "eu-central-1"))
                .build();
        String bucketName = "baeldung-bucket";

        if(s3client.doesBucketExist(bucketName)) {
            System.out.println("Bucket name is not available."
                    + " Try again with a different Bucket name.");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        s3client.createBucket(bucketName);

        List<com.amazonaws.services.s3.model.Bucket> buckets = s3client.listBuckets();
        for(Bucket bucket : buckets) {
            System.out.println(bucket.getName());
        }
        return new ResponseEntity<>("NICE",HttpStatus.OK);
    }
}
