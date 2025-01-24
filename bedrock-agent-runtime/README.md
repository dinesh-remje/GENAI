# BedrockAgent

This project demonstrates how to use the AWS SDK to interact with a Bedrock agent via asynchronous communication. The code enables invoking an agent using `BedrockAgentRuntimeAsyncClient` and handles responses in a non-blocking manner.

## Features

- Connect to an AWS Bedrock Agent using the specified `agentId` and `agentAliasId`.
- Process the agent's response asynchronously.
- Enable detailed trace for debugging.
- Handles streaming responses from the agent in real time.

## Requirements

- Java 8 or higher
- AWS SDK for Java v2
- AWS credentials configured in your environment

## How to Use

1. **Set Up AWS Credentials**  
   Ensure your AWS credentials are set up in one of the following locations:
   - Environment variables
   - AWS credentials file (`~/.aws/credentials`)
   - IAM roles for Amazon EC2

2. **Replace Placeholders**  
   Update the following placeholders in the `BedrockAgent` class:
   - `Region.US_EAST_1` to the desired AWS region.
   - `AGENT_ID` with your Bedrock agent's ID.
   - `ALIAS_ID` with your agent alias ID.
   - `PROMPT_TEXT` with the input prompt text you wish to send to the agent.

3. **Run the Program**  
   Compile and run the program using a compatible Java compiler. 
   
   For example:
   javac BedrockAgent.java
   java BedrockAgent

Process the Output
The agent's responses will be printed to the console in chunks.

Example Output
Response Received from Agent: [Initial Agent Response]
Response: [Processed Data Chunk 1]
Response: [Processed Data Chunk 2]
...

Error messages, if any, will also be displayed in the console.
Error occurred: [Error Details]

Notes
This example uses an asynchronous API to improve performance and avoid blocking the main thread.
It demonstrates processing streamed responses using onEventStream with chunked data handling.
The enableTrace(true) option can be enabled for detailed debugging information.


Dependencies
Make sure to include the following dependencies in your pom.xml (if using Maven):

<dependencies>
    <dependency>
		<groupId>software.amazon.awssdk</groupId>
		<artifactId>bedrockagentruntime</artifactId>
		<version>[AWS_SDK_VERSION]</version>
	</dependency>
</dependencies>
Replace [AWS_SDK_VERSION] with the version of the AWS SDK for Java v2 you are using.
