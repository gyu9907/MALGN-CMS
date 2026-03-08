insert into contents (
    title,
    description,
    view_count,
    created_date,
    created_by,
    last_modified_date,
    last_modified_by
) values
      (
          'Spring Boot REST API 시작하기',
          'Spring Boot를 이용하여 간단한 REST API를 만드는 방법을 단계별로 설명하는 튜토리얼입니다.',
          1523, DATEADD('DAY', -10, NOW()), 'admin', DATEADD('DAY', -9, NOW()), 'admin'
      ),
      (
          'Docker로 개발환경 구성하기',
          'Docker와 Docker Compose를 사용해 로컬 개발 환경을 빠르게 구축하는 방법을 소개합니다.',
          2310, DATEADD('DAY', -9, NOW()), 'dev_kim', DATEADD('DAY', -7, NOW()), 'admin'
      ),
      (
          'JPA N+1 문제 해결 방법',
          'Spring Data JPA 사용 시 자주 발생하는 N+1 문제의 원인과 Fetch Join을 통한 해결 방법을 설명합니다.',
          964, DATEADD('DAY', -8, NOW()), 'backend_kyubeen', DATEADD('DAY', -7, NOW()), 'backend_kyubeen'
      ),
      (
          'Spring Security 로그인 구현',
          '세션 기반 인증을 사용하여 Spring Security 로그인 기능을 구현하는 방법을 설명합니다.',
          1880, DATEADD('DAY', -7, NOW()), 'admin', DATEADD('DAY', -6, NOW()), 'admin'
      ),
      (
          'JWT 인증 방식 이해하기',
          'JWT 토큰 기반 인증 방식의 구조와 동작 원리를 설명합니다.',
          2103, DATEADD('DAY', -7, NOW()), 'dev_kim', DATEADD('DAY', -5, NOW()), 'dev_kim'
      ),
      (
          'REST API 설계 베스트 프랙티스',
          'RESTful API를 설계할 때 고려해야 할 규칙과 좋은 설계 방법을 소개합니다.',
          1750, DATEADD('DAY', -6, NOW()), 'admin', NULL, NULL
      ),
      (
          'Spring Boot 프로젝트 구조 잡기',
          'Spring Boot 프로젝트를 효율적으로 구성하는 패키지 구조를 설명합니다.',
          1321, DATEADD('DAY', -6, NOW()), 'backend_kyubeen', DATEADD('DAY', -5, NOW()), 'admin'
      ),
      (
          'JPA Auditing 활용하기',
          'JPA Auditing을 사용하여 생성일과 수정일을 자동으로 관리하는 방법을 설명합니다.',
          890, DATEADD('DAY', -5, NOW()), 'dev_kim', NULL, NULL
      ),
      (
          'Spring Boot 예외 처리 전략',
          'ControllerAdvice를 이용한 전역 예외 처리 방법을 설명합니다.',
          1402, DATEADD('DAY', -5, NOW()), 'admin', DATEADD('DAY', -4, NOW()), 'admin'
      ),
      (
          'MySQL 인덱스 최적화',
          'MySQL에서 인덱스를 사용하여 조회 성능을 개선하는 방법을 설명합니다.',
          1765, DATEADD('DAY', -5, NOW()), 'backend_kyubeen', DATEADD('DAY', -3, NOW()), 'backend_kyubeen'
      ),
      (
          'Spring Data JPA 페이징 처리',
          'Pageable을 사용하여 데이터 목록을 효율적으로 페이징하는 방법을 설명합니다.',
          2011, DATEADD('DAY', -4, NOW()), 'admin', DATEADD('DAY', -3, NOW()), 'admin'
      ),
      (
          'Spring Boot 로깅 설정',
          'Logback을 이용한 Spring Boot 로깅 설정 방법을 소개합니다.',
          875, DATEADD('DAY', -4, NOW()), 'dev_kim', NULL, NULL
      ),
      (
          'DTO와 Entity 분리 이유',
          'DTO와 Entity를 분리해야 하는 이유와 장점을 설명합니다.',
          1422, DATEADD('DAY', -4, NOW()), 'backend_kyubeen', DATEADD('DAY', -2, NOW()), 'admin'
      ),
      (
          'Spring Boot 테스트 작성',
          'JUnit과 Mockito를 이용한 Spring Boot 테스트 코드 작성 방법을 설명합니다.',
          1670, DATEADD('DAY', -3, NOW()), 'admin', DATEADD('DAY', -2, NOW()), 'admin'
      ),
      (
          'Gradle 빌드 속도 개선',
          'Gradle 빌드 속도를 향상시키는 여러 가지 방법을 소개합니다.',
          760, DATEADD('DAY', -3, NOW()), 'dev_kim', DATEADD('DAY', -1, NOW()), 'admin'
      ),
      (
          'JPA Fetch 전략 이해',
          'Lazy Loading과 Eager Loading의 차이와 사용 방법을 설명합니다.',
          1540, DATEADD('DAY', -3, NOW()), 'backend_kyubeen', NULL, NULL
      ),
      (
          'Spring Boot 캐시 적용',
          'Spring Cache를 이용하여 성능을 개선하는 방법을 설명합니다.',
          1120, DATEADD('DAY', -2, NOW()), 'admin', DATEADD('DAY', -1, NOW()), 'admin'
      ),
      (
          'Redis를 이용한 캐싱 전략',
          'Redis를 활용하여 API 응답 속도를 개선하는 방법을 소개합니다.',
          1345, DATEADD('DAY', -2, NOW()), 'dev_kim', NULL, NULL
      ),
      (
          'Spring Boot 파일 업로드',
          'MultipartFile을 이용한 파일 업로드 기능 구현 방법을 설명합니다.',
          980, DATEADD('DAY', -2, NOW()), 'backend_kyubeen', DATEADD('DAY', -1, NOW()), 'backend_kyubeen'
      ),
      (
          'Swagger API 문서화',
          'Swagger를 사용하여 REST API 문서를 자동 생성하는 방법을 설명합니다.',
          1870, DATEADD('DAY', -2, NOW()), 'admin', DATEADD('DAY', -1, NOW()), 'admin'
      ),
      (
          'Spring Boot 배포 전략',
          'Spring Boot 애플리케이션을 서버에 배포하는 다양한 방법을 소개합니다.',
          1650, DATEADD('DAY', -1, NOW()), 'dev_kim', NULL, NULL
      ),
      (
          'CI/CD 파이프라인 구축',
          'GitHub Actions를 이용한 CI/CD 파이프라인 구축 방법을 설명합니다.',
          1480, DATEADD('DAY', -1, NOW()), 'admin', DATEADD('HOUR', -10, NOW()), 'admin'
      ),
      (
          'Spring Boot 환경 변수 관리',
          'application.yml과 환경 변수를 이용한 설정 관리 방법을 설명합니다.',
          920, DATEADD('HOUR', -20, NOW()), 'backend_kyubeen', NULL, NULL
      ),
      (
          'JPA 벌크 연산 사용법',
          '대량 데이터 업데이트 시 벌크 연산을 사용하는 방법을 설명합니다.',
          860, DATEADD('HOUR', -18, NOW()), 'dev_kim', DATEADD('HOUR', -12, NOW()), 'dev_kim'
      ),
      (
          'Spring Boot 스케줄링',
          'Spring Scheduler를 이용한 정기 작업 구현 방법을 설명합니다.',
          1100, DATEADD('HOUR', -16, NOW()), 'admin', NULL, NULL
      ),
      (
          'API 응답 표준화',
          'REST API 응답 구조를 통일하여 관리하는 방법을 설명합니다.',
          970, DATEADD('HOUR', -12, NOW()), 'backend_kyubeen', DATEADD('HOUR', -6, NOW()), 'admin'
      ),
      (
          'Spring Boot 트랜잭션 관리',
          'Spring의 @Transactional을 이용한 트랜잭션 관리 방법을 설명합니다.',
          1430, DATEADD('HOUR', -8, NOW()), 'dev_kim', NULL, NULL
      ),
      (
          'Spring Boot 성능 튜닝',
          '애플리케이션 성능을 향상시키기 위한 다양한 튜닝 방법을 소개합니다.',
          1580, DATEADD('HOUR', -5, NOW()), 'admin', DATEADD('HOUR', -2, NOW()), 'admin'
      );