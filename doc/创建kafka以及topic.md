# 启动 kafka
```cmd
docker-compose -f kafka-plain.yml up -d # 不需鉴权的
docker-compose -f kafka-sasl-oqs.yml up -d # 需鉴权的，SASL
docker-compose -f kafka-sasl-ots.yml up -d #
```
注意：如果拉取镜像报错，可以单独先拉取

# topic 操作
```cmd
docker exec -it kafka-sasl-ots /bin/bash
# 不需鉴权的topic创建
kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:9092 --partitions 3 --replication-factor 1
# 需鉴权的topic创建
kafka-topics.sh --create --topic my-oqs-topic.DLT --bootstrap-server localhost:6092 --partitions 3 --replication-factor 1 --command-config <(printf "security.protocol=SASL_PLAINTEXT\nsasl.mechanism=PLAIN\nsasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username=\"root\" password=\"root\";")
# 需鉴权的topic创建
kafka-topics.sh --create --topic my-ots-topic --bootstrap-server localhost:7092 --partitions 3 --replication-factor 1 --command-config <(printf "security.protocol=SASL_PLAINTEXT\nsasl.mechanism=PLAIN\nsasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username=\"root1\" password=\"root1\";")
# 发送topic
kafka-console-producer.sh \
  --bootstrap-server localhost:7092 \
  --topic my-ots-topic \
  --producer.config /opt/bitnami/kafka/config/client-sasl.properties
# 查看topic
kafka-console-consumer.sh \
  --bootstrap-server localhost:7092 \
  --topic your-topic \
  --from-beginning \
  --consumer.config /opt/bitnami/kafka/config/client-sasl.properties
# 创建 client-sasl.properties
cd /opt/bitnami/kafka/config
touch client-sasl.properties
echo "# client-sasl.properties
security.protocol=SASL_PLAINTEXT
sasl.mechanism=PLAIN
sasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required \
  username="root" \
  password="root";
" > client-sasl.properties
```