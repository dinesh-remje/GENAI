package com.bedrock.dinesh;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.bedrockruntime.BedrockRuntimeClient;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelRequest;
import software.amazon.awssdk.services.bedrockruntime.model.InvokeModelResponse;

import org.json.JSONPointer;
import org.json.JSONObject;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;

public class Bedrock {

	public static void main(String[] args) {

        Region region = Region.US_EAST_1; // Replace with your desired region

        BedrockRuntimeClient bedrockClient = BedrockRuntimeClient.builder()
                .region(region)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();

        String modelId = "amazon.titan-text-premier-v1:0";
        String nativeRequestTemplate = "{ \"inputText\": \"{{prompt}}\",\"textGenerationConfig\":{\"maxTokenCount\":3072,\"temperature\":0.7,\"topP\":1}}";
        String prompt = ""; //ENTER A PROMPT THAT YOU WANT TO PASS TO BEDROCK
        String nativeRequest = nativeRequestTemplate.replace("{{prompt}}", prompt);
        
        InvokeModelRequest request = InvokeModelRequest.builder()
                .modelId(modelId)
                .body(SdkBytes.fromUtf8String(nativeRequest))
                .build();
        InvokeModelResponse response = bedrockClient.invokeModel(request);

        JSONObject output = new JSONObject(response.body().asUtf8String());
        
        var text = new JSONPointer("/results/0/outputText").queryFrom(output).toString();
     
        System.out.println(text);
    } 
	
}
