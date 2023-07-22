package com.example.fileloader.config;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.DeleteObjectsRequest;
import com.amazonaws.services.s3.model.DeleteObjectsResult;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter
@Slf4j
public class TestBucketInitializer {
    private final AmazonS3 amazonS3;
    @Value("${aws.avatar_storage_name}")
    private final String avatarTestBucketName;
    @Value("${aws.tutors_video_presentation_storage_name}")
    private final String tutorsVideoPresentationTestBucketName;
    @Value("${aws.students_video_presentation_storage_name}")
    private final String studentsVideoPresentationTestBucketName;

    private Bucket avatarTestBucket;
    private Bucket tutorsVideoPresentationTestBucket;
    private Bucket studentsVideoPresentationTestBucket;

    @Autowired
    public TestBucketInitializer(AmazonS3 amazonS3,
                                 @Value("${aws.avatar_storage_name}") String avatarTestBucketName,
                                 @Value("${aws.tutors_video_presentation_storage_name}") String tutorsVideoPresentationTestBucketName,
                                 @Value("${aws.students_video_presentation_storage_name}") String studentsVideoPresentationTestBucketName) {
        this.amazonS3 = amazonS3;
        this.avatarTestBucketName = avatarTestBucketName;
        this.tutorsVideoPresentationTestBucketName = tutorsVideoPresentationTestBucketName;
        this.studentsVideoPresentationTestBucketName = studentsVideoPresentationTestBucketName;
    }

    public void initializeTestBuckets() {
        log.info("Creating test buckets");
        avatarTestBucket = initializeTestBucket(avatarTestBucketName);
        tutorsVideoPresentationTestBucket = initializeTestBucket(tutorsVideoPresentationTestBucketName);
        studentsVideoPresentationTestBucket = initializeTestBucket(studentsVideoPresentationTestBucketName);
        log.info("Test buckets were created successfully");
    }

    private Bucket initializeTestBucket(String bucketName) {
        if (!amazonS3.doesBucketExistV2(bucketName)) {
            return amazonS3.createBucket(bucketName);
        } else {
            return amazonS3.listBuckets().stream()
                .filter(bucket -> bucket.getName().equals(bucketName))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Test bucket not found: " + bucketName));
        }
    }

    public void cleanupTestBuckets() {
        deleteBucketIfExists(avatarTestBucketName);
        deleteBucketIfExists(tutorsVideoPresentationTestBucketName);
        deleteBucketIfExists(studentsVideoPresentationTestBucketName);
    }

    private void deleteBucketIfExists(String bucketName) {
        log.info("Deleting test bucket: {}", bucketName);
        if (amazonS3.doesBucketExistV2(bucketName)) {
            log.info("Deleting objects of the test bucket: {}", bucketName);
            List<String> objectKeys = amazonS3.listObjectsV2(bucketName).getObjectSummaries().stream()
                .map(S3ObjectSummary::getKey)
                .toList();

            if (!objectKeys.isEmpty()) {
                DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(bucketName).withKeys(objectKeys.toArray(new String[0]));
                DeleteObjectsResult deleteObjectsResult = amazonS3.deleteObjects(deleteObjectsRequest);
                List<String> deletedObjectKeys = deleteObjectsResult.getDeletedObjects().stream()
                    .map(DeleteObjectsResult.DeletedObject::getKey)
                    .toList();
                if (deletedObjectKeys.size() > 0) {
                    log.info("Objects were deleted successfully. Bucket: {}, objects: {}", bucketName, deletedObjectKeys);
                }
            }
            amazonS3.deleteBucket(bucketName);
            log.info("Test bucket was deleted successfully: {}", bucketName);
        }
    }
}
