FROM eclipse-temurin:21-jre

WORKDIR /app

COPY coupon-0.0.1-SNAPSHOT.jar /app/coupon.jar

ENTRYPOINT ["java", "-jar", "/app/coupon.jar"]