-- insert into users (username, last_modified_date) values('bob', now());

insert into contents
(title, description, view_count, created_date, created_by, last_modified_date, last_modified_by)
values
    (
        'Spring Boot REST API 시작하기',
        'Spring Boot를 이용하여 간단한 REST API를 만드는 방법을 단계별로 설명하는 튜토리얼입니다.',
        1523,
        NOW(),
        'admin',
        NOW(),
        'admin'
    ),
    (
        'Docker로 개발환경 구성하기',
        'Docker와 Docker Compose를 사용해 로컬 개발 환경을 빠르게 구축하는 방법을 소개합니다.',
        2310,
        NOW(),
        'dev_kim',
        NOW(),
        'dev_kim'
    ),
    (
        'JPA N+1 문제 해결 방법',
        'Spring Data JPA 사용 시 자주 발생하는 N+1 문제의 원인과 Fetch Join을 통한 해결 방법을 설명합니다.',
        964,
        NOW(),
        'backend_kyubeen',
        NOW(),
        'backend_kyubeen'
    );