# Sử dụng hình ảnh chính thức của OpenJDK làm hình ảnh gốc
FROM openjdk:16

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép file jar từ máy host vào container
COPY target/fresher-management-0.0.1-SNAPSHOT.jar app.jar

# Đặt cổng mà ứng dụng sẽ chạy
EXPOSE 8080

# Lệnh để chạy ứng dụng khi container khởi động
ENTRYPOINT ["java", "-jar", "app.jar"]
