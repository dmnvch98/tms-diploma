package com.example.convservice.services;

import com.example.convservice.client.QueueClient;
import com.example.convservice.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommandService {

    private final QueueClient queueClient;

    public void sendCommand(Long convId, Integer delay) {
        queueClient.sendMessage(convId, delay);
    }

    public Optional<Message> getCommand() {
        return queueClient.getMessage();
    }

    public void deleteMessage(final String receipt) {
        queueClient.deleteMessage(receipt);
    }

}
