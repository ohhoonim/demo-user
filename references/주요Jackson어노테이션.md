# 주요 Jackson 어노테이션

- @JsonIgnoreProperties(ignoreUnknown = true)
    - JSON의 모든 필드가 클래스에 완벽하게 일치하지 않아도, 매핑 가능한 필드만 변환
- @JsonProperty
    
```java
public class User {
    @JsonProperty("user_id") // JSON의 "user_id"를 Java 객체의 userId에 매핑
    private String userId;
    private String name;
}
```
    
- @JsonInclude
    
```java
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    private String name;
    private Integer price;
    private String description; // null일 경우 JSON에 포함되지 않음
}
```
    
- @JsonFormat
    
```java
public class Order {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;
}
```
    
- @JsonIgnore
```java
public class User {
    private String username;
    @JsonIgnore // 비밀번호는 JSON에 포함시키지 않음
    private String password;
}
```