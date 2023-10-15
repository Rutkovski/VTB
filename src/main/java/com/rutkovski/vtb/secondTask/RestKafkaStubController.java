package com.rutkovski.vtb.secondTask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rutkovski.vtb.secondTask.model.pojo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestKafkaStubController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    Logger logger = LoggerFactory.getLogger(RestKafkaStubController.class);

    public RestKafkaStubController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping("/your_path")
    public String processRequest(@RequestBody Person person) {
        logger.info("Get new POST-request with object: " + person.toString());
        changeAndSendToKafka(person);
        return "{success : true}";
    }

    private void changeAndSendToKafka(Person person) {
        person.setAge(96);
        try {
            String msg = mapper.writeValueAsString(person);
            kafkaTemplate.send("my_topic", msg);
            logger.info("Success send message in Kafka with body: " + msg);
        } catch (JsonProcessingException e) {
            logger.error("Something was wrong. Error message: " + e.getMessage());
        }
    }

}
