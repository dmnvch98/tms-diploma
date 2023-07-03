#!/bin/bash

set -ex

####### S3
# Avatars storage
awslocal s3 mb s3://avatars --region us-east-1
awslocal s3api put-bucket-acl --bucket avatars --acl public-read-write

# Tutors Video presentations storage
awslocal s3 mb s3://video-presentations-tutors --region us-east-1
awslocal s3api put-bucket-acl --bucket video-presentations-tutors --acl public-read-write

# Students Video presentations storage
awslocal s3 mb s3://video-presentations-students --region us-east-1
awslocal s3api put-bucket-acl --bucket video-presentations-students --acl public-read-write

####### Other setup commands
awslocal kms create-key
awslocal --endpoint-url=http://localhost:4566 sqs create-queue --queue-name status-update

echo "INFRA SETUP FINISHED"
set +x
