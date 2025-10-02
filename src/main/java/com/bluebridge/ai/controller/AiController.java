package com.bluebridge.ai.controller;


import com.bluebridge.ai.repository.ChatHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

@AllArgsConstructor
@RestController
@RequestMapping("/ai")
public class AiController {

    private final ChatClient chatClient;

    private final ChatHistoryRepository chatHistoryRepository;

    @RequestMapping(value = "/chat",produces = "text/html;charset=utf-8")
    public Flux<String> chat(String prompt, String conversationId){
        ///记录会话类型与Id
        chatHistoryRepository.save("chat", conversationId);
        return chatClient.prompt()
                .user( prompt)
                .advisors(a->a.param(CONVERSATION_ID,conversationId))
                .stream()
                .content();
    }
}
