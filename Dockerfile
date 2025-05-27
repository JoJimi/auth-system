FROM eclipse-temurin:17-alpine
WORKDIR /app

# 1) 호스트에서 gradlew로 빌드된 JAR을 복사
COPY build/libs/*.jar app.jar

# 2) 외부에서 설정할 포트 노출
EXPOSE 8080

# 3) 자바 프로세스가 메인 프로세스로 계속 실행되도록 ENTRYPOINT만 정의
ENTRYPOINT ["java", "-jar", "app.jar"]