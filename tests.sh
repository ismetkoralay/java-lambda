#!/bin/bash
set -eo pipefail
FUNCTION=$(aws cloudformation describe-stack-resource --stack-name java-lambda --logical-resource-id function --query 'StackResourceDetail.PhysicalResourceId' --output text)
PAYLOAD='{"input": [1,2,[3,4,[5,6,[7,8],9],[10,11],12],[13,14,15]]}'
aws lambda invoke --function-name "$FUNCTION" --payload "$PAYLOAD" out1.json
PAYLOAD=''
aws lambda invoke --function-name "$FUNCTION" --payload "$PAYLOAD" out2.json
PAYLOAD='{"wrongInputName": [1,2,[3,4,[5,6,[7,8],9],[10,11],12],[13,14,15]]}'
aws lambda invoke --function-name "$FUNCTION" --payload "$PAYLOAD" out3.json
PAYLOAD='{"input": [1,2,[3,4,[5,6,[7,dummy],9],[10,11],12],[13,14,15]]}'
aws lambda invoke --function-name "$FUNCTION" --payload "$PAYLOAD" out4.json