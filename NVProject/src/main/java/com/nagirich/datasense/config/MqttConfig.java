package com.nagirich.datasense.config;

import com.nagirich.datasense.services.MqttService;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqttConfig {

  @Autowired
  private MqttService mqttService;

  @Value("${mqtt.broker.url}")
  private String brokerUrl;

  @Value("${mqtt.client.id}")
  private String clientId;

  @Value("${mqtt.username}")
  private String username;

  @Value("${mqtt.password}")
  private String password;

  @Value("${mqtt.topic}")
  private String topic;

  @Bean
  public MqttAsyncClient mqttAsyncClient() throws MqttException {
    MqttConnectOptions connOpts = new MqttConnectOptions();
    connOpts.setUserName(username);
    connOpts.setPassword(password.toCharArray());

    MqttAsyncClient mqttClient = new MqttAsyncClient(brokerUrl, clientId);
    mqttClient.setCallback(new MqttCallback() {
      @Override
      public void connectionLost(Throwable cause) {
        System.out.println("Connection lost!");
        // Переподключение или другая обработка потери соединения
      }

      @Override
      public void messageArrived(String topic, MqttMessage message) throws Exception {
        String payload = new String(message.getPayload());
//        System.out.println("Received message:");
//        System.out.println("Topic: " + topic);
//        System.out.println("Message: " + payload);
        mqttService.datta(payload);

//        ObjectMapper objectMapper = new ObjectMapper();
//        DataObject dataObject = objectMapper.readValue(payload, DataObject.class);
//        System.out.println(dataObject);

      }

      @Override
      public void deliveryComplete(IMqttDeliveryToken token) {
        // Метод вызывается, когда сообщение доставлено
      }
    });

    // Асинхронное подключение к брокеру MQTT
    mqttClient.connect(connOpts, null, new IMqttActionListener() {
      @Override
      public void onSuccess(IMqttToken asyncActionToken) {
        System.out.println("Connected to MQTT broker.");
        try {
          // После успешного подключения, подписываемся на топик
          mqttClient.subscribe(topic, 0);
        } catch (MqttException e) {
          System.out.println("Error subscribing to topic: " + e.getMessage());
        }
      }

      @Override
      public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
        System.out.println("Failed to connect to MQTT broker: " + exception.getMessage());
      }
    });

    return mqttClient;
  }

}
