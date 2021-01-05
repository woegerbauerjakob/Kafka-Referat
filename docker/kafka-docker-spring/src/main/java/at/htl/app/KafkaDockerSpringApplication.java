package at.htl.app;

import at.htl.configuration.ProducerConfiguration;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

@SpringBootApplication
public class KafkaDockerSpringApplication implements CommandLineRunner {

    private static ProducerConfiguration producerConfiguration = new ProducerConfiguration();
    public static void main(String[] args) throws IOException {
        SpringApplication.run(KafkaDockerSpringApplication.class,args).close();
    }

    @Override
    public void run(String... args){

        producerConfiguration.kafkaTemplate()
                .send("words-to-count", "This is the Initial Message of every startup");

        var kafkaTemplate =producerConfiguration.kafkaTemplate();
        String input = System.console().readLine();
        while (input != "exit") {
            kafkaTemplate.send("words-to-count","key", input);
            input = System.console().readLine();
        }
    }


    @KafkaListener(topics = "words-to-count")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        System.out.println("Counting Words");
        String value = (String) cr.value();
        String counted = value + "\nhas " + value.split(" ").length + " Words";
        producerConfiguration.kafkaTemplate().send("words-counted",counted);
    }

}
