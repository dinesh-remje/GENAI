package com.bedrock.dinesh;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrockagentruntime.BedrockAgentRuntimeAsyncClient;
import software.amazon.awssdk.services.bedrockagentruntime.model.InvokeAgentRequest;
import software.amazon.awssdk.services.bedrockagentruntime.model.InvokeAgentResponseHandler;
import software.amazon.awssdk.services.bedrockagentruntime.model.PayloadPart;

public class BedrockAgent {
	
	public static void main(String args[]) {
		
        Region region = Region.US_EAST_1; // Replace with your desired region

		String agentId = "AGENT_ID";// Replace with your agent ID
		String agentAliasId = "ALIAS_ID"; // Replace with your agent alias ID

		BedrockAgentRuntimeAsyncClient bedrockClient = BedrockAgentRuntimeAsyncClient.builder()
				.region(region)
				.credentialsProvider(DefaultCredentialsProvider.create())
				.build();
		
		String inputText = "PROMPT_TEXT";

		String sessionId = UUID.randomUUID().toString();

		InvokeAgentRequest request = InvokeAgentRequest.builder()
				.agentId(agentId)
				.agentAliasId(agentAliasId)
				.sessionId(sessionId)
				.inputText(inputText)
				.enableTrace(true).build();

		//Handle Responses**
		//The program uses an **asynchronous handler** to process responses from the Bedrock agent.	
		//The **`onResponse`** callback processes the initial response from the agent.
		//**Event Stream:** Handles streaming responses, which may arrive in chunks.
		//**`PayloadPart`:** Represents a piece of data from the stream.
		//**Processing the Stream:** Converts streamed byte data into a string for display.
		InvokeAgentResponseHandler handler = InvokeAgentResponseHandler.builder().onResponse(response -> {
			System.out.println("Response Received from Agent: "+response);
			// Process the response here
		}).onEventStream(publisher -> {
			publisher.subscribe(event -> {
				if (event instanceof PayloadPart) { 
					PayloadPart payloadPart = (PayloadPart) event;
					SdkBytes sdkBytes = payloadPart.bytes();
					byte[] data = sdkBytes.asByteArray();

		            // Process chunks of data (Example: convert to String)
		            String result = new String(data, StandardCharsets.UTF_8);
		            System.out.println("Response: " + result);
				}
			});
		}).onError(error -> {
			System.out.println("Error occurred: "+error);
		}).build();

		//Invoke the Agent
		CompletableFuture<Void> future = bedrockClient.invokeAgent(request, handler);

		//Handle the completion 
		//**Purpose:** Waits for the agent's response to complete within 30 seconds.
		// **Exception Handling:** Captures timeouts or other issues.
		try {
		    future.get(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			System.out.println("Error invoking Bedrock agent: "+e);
		} 
		
		//Closes the Bedrock client to free up resources.
		bedrockClient.close();
	} 	
}
