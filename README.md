# CMS REST API - 맑은기술 신입 백엔드 채용 과제 [김규빈]

본 프로젝트는 맑은기술 신입 백엔드 개발자 채용 과제로 진행된 **콘텐츠 관리 시스템(CMS) REST API**입니다. Spring Security 기반의 세션 인증과 JPA를 활용한 CRUD 기능을 포함하고 있습니다.

## 프로젝트 실행 방법

### 1. 사전 요구사항
*   **Java 25** (프로젝트 설정 기준)
*   **Gradle** (프로젝트에 포함된 `wrapper` 사용 권장)

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

---

## 테스트 계정 정보
| 권한 | Username | Password |
| :--- | :--- | :--- |
| **관리자** | `admin` | `admin` |
| **테스트 계정 1** | `dev_kim` | `qwer1234` |
| **테스트 계정 2** | `backend_kyubeen` | `qwer1234` |

---

## 기술 스택
*   **Language:** Java 25
*   **Framework:** Spring Boot 4
*   **Security:** Spring Security (Session-based)
*   **Database:** H2 Database (In-memory)
*   **ORM:** Spring Data JPA
*   **Validation:** Spring Boot Starter Validation
*   **Tooling:** Lombok, Gradle
*   **API Documentation:** Postman

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

### 1. 비즈니스 무결성 및 권한 제어
* **콘텐츠 소유권 검증**: 수정 및 삭제 요청 시 현재 세션 유저와 작성자의 일치 여부 확인 로직 구현. 타인 콘텐츠 접근 시 **403 Forbidden** 예외를 반환하여 데이터 보안 강화
* **리소스 유효성 체크**: 상세 조회, 수정, 삭제 시 존재하지 않는 ID 요청에 대해 **404 Not Found** 처리로 비즈니스 로직의 안정성 확보
* **중복 데이터 방지**: 회원가입 시 아이디 중복 여부를 사전에 검증하여 **409 Conflict** 반환 및 데이터베이스 무결성 보장

### 2. Spring Security 기반 인증 아키텍처
* **세션 기반 보안 관리**: Spring Security를 연동하여 인증되지 않은 사용자의 리소스 접근을 차단하고 세션을 통한 안정적인 사용자 상태 유지
* **데이터 이력 관리 자동화**: JPA Auditing을 적용하여 모든 엔티티의 생성/수정 시간 및 작성자 정보를 자동으로 기록, 데이터 추적성 확보

### 3. 실무 지향적 페이징 설계
* **사용자 친화적 페이징(1-based)**: 프론트엔드(1)와 백엔드(0) 간 인덱스 차이를 보정하여 API 호출 및 응답 시 모두 1페이지부터 시작하도록 구현
* **서버 부하 방지 및 보안**: Validation을 통해 비정상적으로 큰 페이지 번호나 과도한 페이지 크기(Size) 요청을 사전에 차단
* **데이터 정렬**: 최신 콘텐츠 우선 노출을 위해 생성일 및 고유 ID 역순(DESC)을 기본 정렬 기준으로 적용

### 4. 확장성 있는 시스템 구조
* **패키지 아키텍처 분리**: 비즈니스 로직(`domain`)과 시스템 전반의 공통 설정(`global`) 패키지를 엄격히 분리하여 유지보수성 및 확장성 극대화
* **Entity-DTO 분리**: 데이터 전달 시 엔티티 노출을 차단하고 목적에 최적화된 DTO를 사용하여 도메인 모델의 독립성 유지
* **예외 처리 규격화**: `BusinessException`과 `ErrorCode`(Enum)를 통한 커스텀 예외 관리 및 `ApiResponse` 클래스를 활용한 일관된 응답 구조 반환

---

## ERD

<img width="801" height="269" alt="image" src="https://github.com/user-attachments/assets/6e681b53-56bd-41a7-a44c-a080378369aa" />

**ERD 설계 참고사항**
* created_by, last_modified_by 컬럼은 JPA Auditing을 통해 관리되는 논리적 연관관계이다.
* 시스템 유연성과 성능을 위해 물리적 외래키(FK) 제약조건은 생성하지 않았으나, 어플리케이션 레이어에서 데이터 무결성을 보장하도록 설계하였다.

---

## API 명세서
상세한 API 사용법은 아래 Postman 문서를 확인해 주세요.

👉 [Postman API Documentation](https://documenter.getpostman.com/view/52990603/2sBXcLgHQT)

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

## 참고 자료
*   Spring Security 개념 및 로그인 튜토리얼 학습 (ChatGPT 활용)
*   [Spring Boot Validation 학습 자료](https://adjh54.tistory.com/77)
*   테스트 데이터 생성 (ChatGPT 활용)
