FROM eclipse-temurin:21-jre

EXPOSE 8080

ADD target/coupon-0.0.1-SNAPSHOT.jar /coupon.jar

ENTRYPOINT ["java", "-jar", "/coupon.jar"]