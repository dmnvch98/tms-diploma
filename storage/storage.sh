#!/bin/bash

set -ex

####### S3
# File storage
awslocal s3 mb s3://avatars --region us-east-1
awslocal s3api put-bucket-acl --bucket avatars --acl public-read-write
awslocal kms create-key

echo "INFRA SETUP FINISHED"
set +x