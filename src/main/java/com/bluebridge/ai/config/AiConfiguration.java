package com.bluebridge.ai.config;


import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
//import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class AiConfiguration {
    @Autowired
    JdbcChatMemoryRepository chatMemoryRepository;
    @Bean
    public ChatMemory chatMemory(){
        return MessageWindowChatMemory.builder()
                .chatMemoryRepository(chatMemoryRepository)
                .maxMessages(10)
                .build();
    }


    @Bean
    public ChatClient chatClient(OllamaChatModel ollamaChatModel,ChatMemory chatMemory){
        return ChatClient.builder(ollamaChatModel)
                .defaultSystem("你是许博的助手，请以许博助手的身份用温柔的语气回答问题")
                .defaultAdvisors(new SimpleLoggerAdvisor(),
                         MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }
}
