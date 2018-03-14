package com.formation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.connect.json.JsonSerializer;

import java.util.Properties;
import java.util.Scanner;

/**
 * Created by sunilpatil on 12/25/16.
 */
public class ProducerJson {
    private static Scanner in;
    public static void main(String[] argv)throws Exception {
        /*if (argv.length != 1) {
            System.err.println("Please specify 1 parameters ");
            System.exit(-1);
        }

        in = new Scanner(System.in);
        System.out.println("Enter message(type exit to quit)");*/

        String topicName = "topic-Pr-Cons-Json";
        //Configure the Producer
        Properties configProperties = new Properties();
        configProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"34.251.183.197:6667");
        configProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.ByteArraySerializer");
        configProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.connect.json.JsonSerializer");

        org.apache.kafka.clients.producer.Producer producer = new KafkaProducer(configProperties);

        ObjectMapper objectMapper = new ObjectMapper();

//        String line = in.nextLine();
        try {
            while (true) {
                Contact contact = new Contact();
                contact.setName("karim" + Math.random());
                JsonNode jsonNode = objectMapper.valueToTree(contact);
                ProducerRecord<String, JsonNode> rec = new ProducerRecord<String, JsonNode>(topicName, jsonNode);
                producer.send(rec);
//            line = in.nextLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //in.close();
        //producer.close();
    }
}