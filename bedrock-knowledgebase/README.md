RAG - Invoke Knowledge Base and Model

Overview

This Java application demonstrates how to use Amazon Bedrock Agent to retrieve and generate responses from a knowledge base using AWS SDK for Java. It leverages the BedrockAgentRuntimeClient to send queries and retrieve structured responses from a specified model and knowledge base.

Prerequisites

Before running the application, ensure you have the following:

AWS SDK for Java installed.

AWS Credentials configured via DefaultCredentialsProvider.

Amazon Bedrock Agent Runtime set up with:

A Knowledge Base ID (knowledgeBaseId).

A Model ARN (modelArn).

Java 8 or later installed.

Setup

AWS Credentials:

Ensure your AWS credentials are configured via ~/.aws/credentials or an IAM role.

Modify Configuration:

Set the correct AWS Region in Region.US_EAST_1 (or update as needed).

Replace knowledgeBaseId and modelArn with valid values from your AWS account.

Code Explanation

The application performs the following steps:

Initializes an AWS Region and BedrockAgentRuntimeClient.

Configures a KnowledgeBaseRetrieveAndGenerateConfiguration object.

Constructs a RetrieveAndGenerateRequest with a sample query.

Calls retrieveAndGenerate() to fetch the response.

Prints the response and citation details to the console.

Running the Application

Compile and run the Java program:

javac YourJavaFile.java
java YourJavaFile

Expected Output

The program prints the retrieved response along with citation details, including content and S3 location references.

Example output:

RAG - Invoke Knowledge Base and Model
RAG - Response: <Response Details>
RAG - Output: <Generated Text>
RAG - Count of Citations: <Number>
RAG - Retrieval References: <Number>
RAG - Content: <Reference Content>
RAG - Location: <S3 URI>
Response: <Generated Text>

Notes

Ensure that your AWS account has the necessary permissions to access Amazon Bedrock Agent.

If you encounter permission issues, update your IAM policies accordingly