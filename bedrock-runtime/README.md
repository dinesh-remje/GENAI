Bedrock Runtime Java Example

This repository contains a sample Java program that demonstrates how to interact with the Amazon Bedrock Runtime service using the AWS SDK for Java v2. The program sends a prompt to a Bedrock model, retrieves the generated output, and prints it to the console.

Prerequisites

AWS SDK for Java v2: Ensure the AWS SDK for Java v2 is added to your project.

You can add the following dependency to your pom.xml if using Maven:

<dependency>
    <groupId>software.amazon.awssdk</groupId>
    <artifactId>bedrockruntime</artifactId>
    <version>2.x.x</version>
</dependency>

AWS Credentials: Set up your AWS credentials using one of the following methods:

Configure the ~/.aws/credentials file.

Use environment variables (AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY).

Use an AWS IAM role when running on an EC2 instance.

Java Environment: Ensure you have Java 11 or later installed.

Amazon Bedrock Access: Ensure that your AWS account has access to Amazon Bedrock and the required permissions.

Code Explanation

Overview

The program demonstrates the following key steps:

Setting up the Bedrock Runtime client.

Building a request to invoke a Bedrock model.

Parsing the response to extract the generated text.

Code Walkthrough

Setting the AWS Region:

Region region = Region.US_EAST_1; // Replace with your desired region

This specifies the AWS region where the Bedrock service is hosted. Update the region as needed.

Creating the BedrockRuntimeClient:

BedrockRuntimeClient bedrockClient = BedrockRuntimeClient.builder()
        .region(region)
        .credentialsProvider(DefaultCredentialsProvider.create())
        .build();

The BedrockRuntimeClient is created using the region and default credentials provider.

Model ID and Request Template:

String modelId = "amazon.titan-text-premier-v1:0";
String nativeRequestTemplate = "{ \"inputText\": \"{{prompt}}\",\"textGenerationConfig\":{\"maxTokenCount\":3072,\"temperature\":0.7,\"topP\":1}}";

modelId: Specifies the Bedrock model to invoke. Update this to the appropriate model available in your AWS account.

nativeRequestTemplate: Template for the JSON payload sent to the model. Replace {{prompt}} with the actual input prompt.

Preparing the Request:

String prompt = ""; // Enter your desired prompt
String nativeRequest = nativeRequestTemplate.replace("{{prompt}}", prompt);

InvokeModelRequest request = InvokeModelRequest.builder()
        .modelId(modelId)
        .body(SdkBytes.fromUtf8String(nativeRequest))
        .build();

Replace prompt with the desired text input. The InvokeModelRequest object is built using the modelId and the request payload.

Invoking the Model:

InvokeModelResponse response = bedrockClient.invokeModel(request);

The invokeModel method sends the request to the Bedrock model and retrieves the response.

Parsing the Response:

JSONObject output = new JSONObject(response.body().asUtf8String());

var text = new JSONPointer("/results/0/outputText").queryFrom(output).toString();
System.out.println(text);

The JSON response is parsed to extract the generated text from the model's output. The result is printed to the console.

Running the Program

Clone this repository and navigate to the project directory.

Update the prompt variable in the code with the input text you want to send to the Bedrock model.

Build and run the program:

javac Bedrock.java
java com.bedrock.dinesh.Bedrock

The generated output from the model will be printed to the console.

Example Output

For a prompt like "What is the capital of France?", the output might be:

"The capital of France is Paris."

Notes

Ensure that the modelId corresponds to a valid Bedrock model available in your AWS account.

Update the textGenerationConfig in the nativeRequestTemplate as needed to customize the model's behavior.
