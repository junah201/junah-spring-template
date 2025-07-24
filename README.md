# Junah's Java Spring Template

이 레포지토리는 Java Spring을 기반으로 한 협업용 템플릿입니다.
공통 응답 구조, 인증/권한 처리, Swagger 문서 자동화, DTO 구성 등 실제 개발에 필요한 구성이 포함되어 있습니다.

## 사용 기술 스택

| 기술                          | 버전            |
| --------------------------- | ------------- |
| Java                        | 17+           |
| Spring Boot                 | 3.x           |
| Spring Security             | ✔️ 사용         |
| Lombok                      | ✔️ 사용         |
| Hibernate Validator         | ✔️ 사용         |
| Swagger / Springdoc OpenAPI | ✔️ API 문서 자동화 |

## 📑 공통 Dto 구조

### `Response<T>`

API 응답은 모두 다음과 같은 통일된 구조를 따릅니다:
```json
{
  "code": 0,
  "message": "요청이 성공적으로 처리되었습니다.",
  "data": { ... }
}
```
에러 응답 예시:
```json
{
  "code": 10401,
  "message": "로그인이 필요합니다.",
  "data": null
}
```

> 응답 형식이 일관되어 프론트엔드 처리 및 Swagger 문서화가 쉬워집니다.

### `Pagination<T>`

페이징이 필요한 목록 응답 시 다음 구조를 따릅니다:
```json
{
  "page": 1,
  "size": 10,
  "total": 78,
  "nodes": [ ... ]
}
```

> 프론트엔드에서 페이지네이션 처리에 필요한 데이터가 전부 포함됩니다.

### 요청/응답 DTO 설명 및 Swagger 자동 등록

- 각 API의 요청(Request)와 응답(Response)을 담당하는 DTO들은 `@Schema` 어노테이션으로 상세한 설명과 예시를 붙여 Swagger 문서에 자동으로 반영됩니다.
- 예를 들어, 아래와 같이 로그인 관련 DTO를 정의하면, Swagger UI의 API 문서에 자동으로 필드 설명과 유효성 제약조건, 데이터 형식(예: email, password) 등이 노출됩니다.

```java
// dto/auth/LoginReqDto.java

@Schema(description = "로그인 요청 DTO")
public class LoginReqDto {
    @Schema(description = "이메일", format = "email")
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식으로 입력해주세요.")
    private String email;

    @Schema(description = "비밀번호", format = "password")
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private String password;
}
```

```java
// dto/auth/LoginResDto.java

@Schema(description = "로그인 응답 DTO")
public class LoginResDto {
    @Schema(description = "액세스 토큰")
    private String accessToken;

    @Schema(description = "리프레시 토큰")
    private String refreshToken;

    @Schema(description = "유저 ID", example = "1")
    private String userId;
}
```

- Controller 메서드에서 이 DTO들을 요청과 응답 타입으로 사용하면, Swagger가 자동으로 API 요청/응답 스펙을 생성해 줍니다.

```java
// controller/AuthController.java

@PostMapping("/login")
@Operation(summary = "로그인", description = "사용자가 로그인하여 토큰을 발급받습니다.")
public Response<LoginResDto> login(@RequestBody LoginReqDto loginReqDto) {
    ...
}
```
<p align="center">
  <img width="1432" height="924" alt="image" src="https://github.com/user-attachments/assets/04cf13d4-c4e9-4581-943e-08167fc697ad" />
</p>

> 따라서, 별도의 Swagger 문서 작업 없이도 API 스펙이 최신 상태로 유지되고, 클라이언트 개발자도 직관적으로 요청/응답 데이터를 이해할 수 있습니다.

필요하면 DTO 내 필드마다 예시(example), 형식(format), 설명(description) 등을 꼼꼼히 작성해두면 Swagger 문서 완성도가 훨씬 높아집니다.

## 🚨 예외 처리 정책

### ✅ 핵심 개념

모든 커스텀 예외는 `BaseCustomException`을 상속받아 정의하며, `GlobalExceptionHandler`에서 이 예외들을 한 곳에서 처리합니다.
즉, 서비스 로직에서 예외만 던지면, 공통 Response 포맷으로 자동으로 응답이 내려갑니다.

> 비즈니스 로직에 `if-else`로 직접 응답을 만드는 번거로움 없이, 핵심 로직에만 집중할 수 있습니다.

### ✅ 흐름 예시

1. 예외 클래스 정의
```java
// exception/auth/EmailAlreadyExistsException.java

public class EmailAlreadyExistsException extends BaseCustomException {
    public EmailAlreadyExistsException() {
        super(10003, "이미 존재하는 이메일입니다.");
    }
}
```

2. 서비스 로직에서 예외 발생
```java
// service/authService.java

if (userRepository.existsByEmail(email)) {
    throw new EmailAlreadyExistsException();
}
```

3. 예외 처리 (GlobalExceptionHandler)
```java
// exception/GlobalExceptionHandler.java

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(BaseCustomException.class)
    public Response<?> handleBaseCustomException(BaseCustomException e) {
        return Response.builder()
                .code(e.getErrorCode())
                .message(e.getErrorMessage())
                .build();
    }
}
```

4. 실제 응답 형태
```json
{
  "code": 10003,
  "message": "이미 존재하는 이메일입니다.",
  "data": null
}
```

## 🔐 인증 및 권한 처리

### 비트 필드를 통한 권한 관리

이 템플릿은 사용자의 권한을 int 타입의 비트 필드로 관리합니다. 비트필드는 하나의 정수형 변수의 각 비트(bit)를 하나의 플래그(상태)처럼 사용하여 여러 개의 Boolean 값을 효율적으로 저장하는 방식입니다.
예를 들어 int 타입(32비트)를 사용하면 최대 32개의 권한(또는 상태)를 하나의 변수로 관리할 수 있습니다.

```java
// security/Permission.java

public enum Permission {
    USER(1), ADMIN(8);

    private final int bit;

    Permission(int bit) {
        this.bit = bit;
    }

    public int getBit() {
        return bit;
    }

    public static boolean hasPermission(int target, Permission required) {
        return (target & required.getBit()) == required.getBit();
    }
}
```

### ✅ 커스텀 어노테이션

#### `@LoginRequired`

- **용도**: 로그인(인증)이 필요한 API에 사용합니다.
- **기반**: `@PreAuthorize("isAuthenticated()")`
- **동작**: 로그인하지 않은 사용자가 요청할 경우, 아래와 같은 **401 UNAUTHORIZED** 응답이 반환됩니다.
```json
{
  "code": 10401,
  "message": "로그인이 필요합니다.",
  "data": null
}
```

#### `@AdminOnly`

- **용도**: 관리자 권한이 필요한 API에 사용합니다.
- **기반**: 권한 비트 필드를 통한 관리자 체크 (ex. @PreAuthorize("hasRole('ADMIN')") 또는 비트 연산)
- **동작**: 관리자 권한이 없는 사용자가 요청할 경우, 아래와 같은 **403 FORBIDDEN** 응답이 반환됩니다.
```json
{
  "code": 10403,
  "message": "접근이 거부되었습니다.",
  "data": null
}
```

> 위 응답의 경우 `CustomAccessDeniedHandler.java`와 `CustomAuthenticationEntryPointHandler.java`에서 수정 가능합니다.

#### 🧩 사용 예시

클래스 또는 메서드 단위에 사용 가능합니다:

- **클래스에 적용**
```java
@RestController
@LoginRequired
public class UserController() {}
```

- **메서드에 적용**
```java
@RestController
public class UserController() {
    @GetMapping("me")
    @LoginRequired
    public Response<UserResDto> getMyInfo(@AuthenticationPrincipal Long userId) {}
}
```

#### 📘 Swagger 문서화 연동

각 어노테이션에는 Swagger 문서에 자동으로 보안 응답 스펙을 포함하는 기능이 내장되어 있습니다:
- `@LoginRequired`: `401 Unauthorized` 응답 문서 자동 추가
- `@AdminOnly`: `401 Unauthorized`, `403 Forbidden` 응답 문서 자동 추가

> Swagger UI 상에서도 실제 인증이 필요한 API임을 명확히 보여주므로 프론트엔드 개발자가 테스트/사용 시 혼동 없이 접근할 수 있습니다.

<p align="center">
  <img width="991" height="1346" alt="image" src="https://github.com/user-attachments/assets/2acd5c19-701a-4e03-bf44-9739e2169a07" />
</p>


