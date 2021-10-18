# java-lambda

- First create an AWS account if you don't have and create an IAM user with necessary permissions.
And keep the secret and access keys.
- Then install aws cli from https://docs.aws.amazon.com/cli/latest/userguide/install-cliv2.html
- After installing aws cli run the command "aws configure" and provide necessary information(secret key, access key, region...)
- Then run the following command to give the permissions to script files: chmod +x ./bucket.sh ./deploy.sh ./tests.sh
- After giving permission first run bucket.sh to create the s3 bucket which hold the source code.
- Then run deploy.sh to create and update the lambda function.
- After deploying the lambda function you can run tests.sh for basic tests.

Note: The necessary input format is following:
{
    "input": [1,2,3]
}