package com.example.convservice.shceduler;

import com.example.convservice.services.CommandService;
import com.example.convservice.services.ConversationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CommandsScheduler {

    private final CommandService commandService;
    private final ConversationService conversationService;

    @Scheduled(fixedDelay = 10000)
    public void processMessage() {
        commandService.getCommand()
            .ifPresent(
                m -> {
                    log.info("Received ID to update conversation status: {} ", m.getContent());
                    conversationService.updateConversationStatusToFinish(Long.parseLong(m.getContent()));
                    commandService.deleteMessage(m.getReceipt());
                });
    }

}
