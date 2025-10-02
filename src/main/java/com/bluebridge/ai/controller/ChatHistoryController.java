package com.bluebridge.ai.controller;


import com.bluebridge.ai.entity.vo.MessageVO;
import com.bluebridge.ai.repository.ChatHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.messages.Message;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/chatHistory")
public class ChatHistoryController {

    private final ChatHistoryRepository chatHistoryRepository;
    private final ChatMemory chatMemory;

    @RequestMapping("/{type}")
    public List<String> getChatHistory(@PathVariable("type") String type){
        return chatHistoryRepository.getChatIds(type);
    }

    @RequestMapping("/{type}/{chatId}")
    public List<MessageVO> getChatHistory(@PathVariable("type") String type, @PathVariable("chatId") String chatId){
        List<Message> messages = chatMemory.get(chatId);
        if(messages == null) return List.of();
        return messages.stream().map(MessageVO::new).toList();
    }
}
