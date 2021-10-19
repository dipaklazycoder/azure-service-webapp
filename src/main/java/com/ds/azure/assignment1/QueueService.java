package com.ds.azure.assignment1;

import com.azure.storage.queue.QueueClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueService {

    @Autowired
    private QueueClient queueClient;

    public void insert(String message) {
        queueClient.sendMessage(message);
    }
}
