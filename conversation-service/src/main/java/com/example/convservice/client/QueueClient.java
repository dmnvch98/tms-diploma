package com.example.convservice.client;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.DeleteMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.example.convservice.model.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class QueueClient {

    private final AmazonSQS queue;
    @Value("${services.queue.url}")
    public String queueUrl;

    public Optional<Message> getMessage() {
        ReceiveMessageResult receiveMessageResult = queue.receiveMessage(queueUrl);
        return receiveMessageResult.getMessages().stream()
            .findFirst()
            .map(m -> Message.builder()
                .content(m.getBody())
                .receipt(m.getReceiptHandle())
                .build());
    }

    public void deleteMessage(final String receipt) {
        try {
            queue.deleteMessage(new DeleteMessageRequest(queueUrl, receipt));
            log.info("Message was deleted the from queue. Receipt : {} ", receipt);
        } catch (Exception e) {
            log.error("An error occurred during deleting message from the queue. Receipt : {}", receipt);
        }
    }

    public void sendMessage(Long convId, Integer delay) {
        try {
            log.info("Adding message to the queue to update the conversation status. Conversation Id: {}." +
                " Delay : {} sec", convId, delay);
            SendMessageRequest messageRequest = new SendMessageRequest(queueUrl, String.valueOf(convId));
            messageRequest.setDelaySeconds(delay);
            queue.sendMessage(messageRequest);
            log.info("Message successfully saved. Conversation Id: {}." +
                "Delay: {} sec", convId, delay);
        } catch (Exception e) {
            log.error("An error occurred during saving message to the queue. ConvId: {}. Delay: {} sec",
                convId, delay);
        }
    }

}
