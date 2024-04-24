import paho.mqtt.client as mqtt
import json
import time
import random

# Функция для генерации значения для raw_value
def generate_raw_value(previous_raw_value):
    # Увеличиваем предыдущее значение на случайную величину
    return round(previous_raw_value + random.uniform(1, 10))

# Функция для генерации текущего временной метки
def generate_timestamp():
    return int(time.time() * 1000)

# Функция для генерации сообщения
# def generate_message(previous_raw_value):
#     raw_value = generate_raw_value(previous_raw_value)
#     message = {
#         "deviceId": "ESP8266_1",
#         "timestamp": generate_timestamp(),
#         "water_meter_reading": {
#             "raw_value": raw_value,
#             "converted_value": raw_value / 1000,  # Пример конвертации в кубические метры
#             "units": "cubic_meters"
#         },
#         "photo_url": "http://example.com/photo123.jpg"
#     }
#     return json.dumps(message), message["water_meter_reading"]["raw_value"]

def generate_message2(previous_raw_value):
    raw_value = generate_raw_value(previous_raw_value)
    message = {
        "deviceId": "ESP8266_1",
        "userId": "123456",
        "accessToken": "your_access_token_here",
        "timestamp": generate_timestamp(),
        "comments": "These measurements were taken during peak hours.",
        "name": "Meter 1",
        "measurementType": "water",
        "rawValue": raw_value,
        "convertedValue": raw_value / 1000,
        "units": "cubic_meters"
    }
    return json.dumps(message), message["rawValue"]

    # message = {
    #     "deviceId": "ESP8266_1",
    #     "userId": "123456",
    #     "accessToken": "your_access_token_here",
    #     "timestamp": generate_timestamp(),
    #     "location": {
    #         "latitude": 51.5074,
    #         "longitude": -0.1278
    #     },
    #     "comments": "These measurements were taken during peak hours.",
    #     "payload": {
    #         "name": "Meter 1",
    #         "measurement_type": "water",
    #         "raw_value": raw_value,
    #         "converted_value": raw_value / 1000,
    #         "units": "cubic_meters"
    #     }
    # }
    # return json.dumps(message), message["payload"]["raw_value"]
    # message = {
    #     "device_id": "ESP8266_1",
    #     "user_id": "123456",
    #     "access_token": "your_access_token_here",
    #     "timestamp": generate_timestamp(),
    #     "meter_readings": [
    #         {
    #             "name": "Meter 1",
    #             "measurement_type": "water",
    #             "raw_value": raw_value,
    #             "converted_value": raw_value / 1000,
    #             "units": "cubic_meters"
    #         },
    #         {
    #             "name": "Meter 2",
    #             "measurement_type": "electricity",
    #             "raw_value": 23456,
    #             "converted_value": 23.456,
    #             "units": "kWh"
    #         }
    #     ],
    #     "location": {
    #         "latitude": 51.5074,
    #         "longitude": -0.1278
    #     },
    #     "photo_url": "http://example.com/photo123.jpg",
    #     "signal_strength": "good",
    #     "comments": "These measurements were taken during peak hours.",
    #     "custom_parameters": [
    #         { "parameter": "parameter1", "value": "value1" },
    #         { "parameter": "parameter2", "value": "value2" },
    #         { "parameter": "parameter3", "value": "value3" }
    #     ]
    # }
    # return json.dumps(message), message["meter_readings"][0]["raw_value"]

# Функция для отправки сообщения
def send_message(client, previous_raw_value):
    message, new_raw_value = generate_message2(previous_raw_value)
    client.publish("topic", message)
    print("Sent:", message)
    return new_raw_value

# Callback-функция, вызываемая при установлении соединения с MQTT-брокером
def on_connect(client, userdata, flags, rc):
    if rc == 0:
        print("Connected to MQTT broker")
    else:
        print("Connection failed")

def main():
    # Создание клиента MQTT
    client = mqtt.Client()

    # Настройка callback-функции для обработки события установки соединения
    client.on_connect = on_connect

    # Настройка логина и пароля
    username = "admin"
    password = "admin"
    client.username_pw_set(username, password)

    # Подключение к MQTT-брокеру
    client.connect("localhost", 1883)

    # Запуск цикла для работы клиента MQTT
    client.loop_start()

    # Инициализация предыдущего значения raw_value
    previous_raw_value = 0

    try:
        while True:
            previous_raw_value = send_message(client, previous_raw_value)
            time.sleep(2)  # Отправка каждую секунду
    except KeyboardInterrupt:
        print("Interrupted")
        client.disconnect()
        client.loop_stop()

if __name__ == "__main__":
    main()
