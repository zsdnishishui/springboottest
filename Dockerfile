# 基础镜像使用java
FROM openjdk:8

# VOLUME 指定了临时文件目录为/tmp。
# 其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为app.jar
ARG JAR_FILE
ADD ${JAR_FILE} app.jar
# 运行jar包
# 容器启动时运行java -Djava.security.egd=file:/dev/./urandom -jar /app.jar $PARAMS
# 其中，$JAVA_OPTS为java启动参数，$PARAMS为jar包运行参数
ENV JAVA_OPTS=""

ENV PARAMS=""
EXPOSE 8080
CMD java -Djava.security.egd=file:/dev/./urandom -jar $JAVA_OPTS /app.jar $PARAMS
