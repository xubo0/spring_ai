package com.bluebridge.ai.repository;

import java.util.List;

public interface ChatHistoryRepository {

    public void save(String type,String chatId);

    public List<String> getChatIds(String type);

}
