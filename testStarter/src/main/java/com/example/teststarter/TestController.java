package com.example.teststarter;

import com.example.demostarter.service.KafkaMessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sendMessage")
public class TestController {

    public TestController(KafkaMessageSender sender) {
        this.sender = sender;
    }

    @Autowired
    private final KafkaMessageSender sender;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        sender.sendMessage(message);

        return ResponseEntity.ok("Сообщение успешно отправлено в Kafka. topic:" + sender.getTopic());
    }

}
