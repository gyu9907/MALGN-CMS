# CMS REST API - 맑은기술 신입 백엔드 채용 과제 [김규빈]

본 프로젝트는 맑은기술 신입 백엔드 개발자 채용 과제로 진행된 **콘텐츠 관리 시스템(CMS) REST API**입니다. Spring Security 기반의 세션 인증과 JPA를 활용한 CRUD 기능을 포함하고 있습니다.

## 프로젝트 실행 방법

### 1. 사전 요구사항
*   **Java 25**
*   **Gradle** (프로젝트에 포함된 `gradlew` 사용 권장)

### 2. 프로젝트 빌드
```bash
./gradlew build
```

### 3. 애플리케이션 실행
```bash
./gradlew bootRun
```
또는 빌드된 JAR 파일을 직접 실행:
```bash
java -jar build/libs/simple-cms-api-0.0.1-SNAPSHOT.jar
```

자세한 기능은 아래 [API 명세서](#api-명세서)를 참조

---

## 테스트 계정 정보
| 권한 | Username | Password |
| :--- | :--- | :--- |
| **관리자** | `admin` | `admin` |
| **테스트 계정 1** | `dev_kim` | `qwer1234` |
| **테스트 계정 2** | `backend_kyubeen` | `qwer1234` |

---

## 기술 스택
*   Java 25
*   Spring Boot 4
*   Spring Security (Session 기반)
*   H2 Database (In-memory 방식)
*   Spring Data JPA
*   Spring Boot Starter Validation
*   Lombok, Gradle
*   Postman (테스트 및 문서화)

---

## 주요 기능
본 프로젝트는 도메인 중심의 설계를 바탕으로 다음과 같이 기능을 분류하여 구현했습니다.

### 1. User (회원 관리)
* **회원 가입**: 신규 사용자 등록 기능

### 2. Auth (인증 관리)
* **로그인**: Spring Security 세션 기반 인증
* **로그아웃**: 세션 무효화를 통한 로그아웃 처리

### 3. Content (콘텐츠 관리)
* **생성**: 새로운 콘텐츠 등록
* **수정**: 기존 콘텐츠 내용 업데이트
* **삭제**: 콘텐츠 삭제
* **상세 조회**: 고유 ID를 통한 단일 콘텐츠 조회
* **목록 조회**: 페이징 및 정렬 조건이 적용된 콘텐츠 리스트 조회

---

## 구현 상세 및 설계 원칙

### 1. 프로젝트 구조
* **패키지 분리**: `domain`(비즈니스 로직)과 `global`(공통 설정)을 분리하여 구조를 단순하게 유지
* **Entity / DTO 분리**: 엔티티를 직접 노출하지 않고 DTO를 사용하여 API 응답 구조와 도메인 모델을 분리
* **공통 응답 구조**: `ApiResponse` 클래스를 사용하여 성공/실패 시 일관된 JSON 형태로 응답하도록 구현
* **JPA Auditing 적용**: 엔티티의 생성자, 생성일, 수정자, 수정일을 자동으로 기록하도록 설정

### 2. 예외 처리 및 유효성 검사
* **Global Exception Handling**: `BusinessException`과 `ErrorCode`를 정의하여 일관된 에러 응답을 제공
* **요청 데이터 검증**: `@Valid`, `@NotBlank`, `@Size`, `@Min`, `@Max` 등을 활용해 요청 데이터의 유효성을 검증
* **비즈니스 로직 검증**: 콘텐츠 소유자 검증(403), 리소스 존재 여부(404), 중복 데이터(409) 등의 예외 처리를 구현 ([API 명세서](#api-명세서) 참조)

### 3. 페이징 처리
* **1-based 페이지 처리**: API 요청과 응답 모두 1페이지부터 시작하도록 구현
* **요청 값 검증**: 비정상적으로 큰 page, size 요청을 Validation으로 제한
* **정렬 기준**: 최신 콘텐츠가 먼저 노출되도록 `createdDate DESC`, `id DESC` 기준으로 정렬

---

## 로그인 구현 방식

### Spring Security 세션 기반 로그인
* Spring Security의 기본 인증 흐름을 그대로 활용할 수 있어 구현 난이도가 낮고 안정적으로 동작하기 때문에 선택했습니다.
* JWT 방식은 확장성과 무상태(stateless) 구조라는 장점이 있지만, 토큰 재발급, 만료 처리, 로그아웃 처리 등 추가적인 설계가 필요해 과제 범위 대비 복잡도가 높다고 판단했습니다.
* 따라서 인증 방식 구현에 과도한 시간을 사용하기보다, 안정적인 인증 구성을 바탕으로 API 기능 구현에 집중하기 위해 세션 기반 방식을 선택했습니다.

---

## ERD

<img width="737" height="282" alt="image" src="https://github.com/user-attachments/assets/d469673d-bc54-4349-9314-2d8a349171bc" />


### ERD 설계 참고사항
* created_by, last_modified_by 컬럼은 JPA Auditing을 통해 관리되는 논리적 연관관계입니다.
* 시스템 유연성과 성능을 위해 물리적 외래키(FK) 제약조건은 생성하지 않았으나, 어플리케이션 레이어에서 데이터 무결성을 보장하도록 설계하였습니다.

---

## API 명세서
상세한 API 사용법 및 예외처리는 아래 Postman 문서에서 확인 가능합니다.

👉 [MALGN CMS REST API 문서](https://documenter.getpostman.com/view/52990603/2sBXcLgHQT)

### HTTP 상태 코드 정의
| 코드 | 상태 | 설명 |
| :--- | :--- | :--- |
| **200** | OK | 요청 성공 |
| **400** | Bad Request | 요청 값 오류 또는 제약 조건 위반 |
| **401** | Unauthorized | 인증 실패 (로그인 필요) |
| **403** | Forbidden | 권한 부족 |
| **404** | Not Found | 리소스를 찾을 수 없음 |
| **409** | Conflict | 데이터 중복 (예: 아이디 중복) |
| **500** | Internal Server Error | 서버 내부 오류 |

---

## 활용 도구 및 참고 자료

### 활용 도구
*   ChatGPT : Spring Security 개념 및 로그인 튜토리얼 학습, 개발 방향성 검토, 테스트 데이터 생성에 사용했습니다.

### 참고 자료
*   [Spring Boot Validation 학습 자료](https://docs.oracle.com/javaee/7/tutorial/bean-validation001.htm)
