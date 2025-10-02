package com.bluebridge.ai.entity.vo;


import lombok.Data;
import org.springframework.ai.chat.messages.Message;

@Data
public class MessageVO {
    private String type;
    private String content;

    public MessageVO(Message message) {
        switch(message.getMessageType()){
            case USER:
                this.type = "user";

                break;
            case ASSISTANT:
                this.type = "assistant";

                break;
            case SYSTEM:
                this.type = "system";

                break;
            default:
                this.type = "unknown";

        }
        this.content = message.getText();
    }
}
