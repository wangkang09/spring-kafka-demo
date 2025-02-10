# 启动 kafka
```cmd
docker-compose -f kafka-plain.yml up -d # 不需鉴权的
docker-compose -f kafka-sasl.yml up -d # 需鉴权的，SASL
```
注意：如果拉取镜像报错，可以单独先拉取

# 创建 topic
```cmd
docker exec -it kafka-sasl /bin/bash
# 不需鉴权的topic创建
kafka-topics.sh --create --topic my-topic --bootstrap-server localhost:6092 --partitions 3 --replication-factor 1
# 需鉴权的topic创建
kafka-topics.sh --create --topic my-secure-topic --bootstrap-server localhost:6092 --partitions 3 --replication-factor 1 --command-config <(printf "security.protocol=SASL_PLAINTEXT\nsasl.mechanism=PLAIN\nsasl.jaas.config=org.apache.kafka.common.security.plain.PlainLoginModule required username=\"root\" password=\"root\";")
```
