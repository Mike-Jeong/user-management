# 회원 관리 API 개발

#### 회원 관리 API를 활용하여 다른시스템에서도 회원을 관리할 수 있는 API를 개발해 보았습니다.
#### 1차 개발 : REST API 구현
#### 2차 개발 : REST API 를 바탕으로 Thymeleaf 기반 SSR 어플리케이션으로 리펙토링 

## 기술 스택
- Spring Boot (2.7.9)
- Spring Security
- JPA
- Querydsl
- MariaDB

간단한 API 용청/응답 명세서 (API Specification)
- https://github.com/Mike-Jeong/user-management/commit/66d9b7ef40fb54cf11228bc5ee8c8d6d050f0b4a
- 해당 커밋을 기준으로 작성된 API 명세서 입니다. 해당 커밋 시점까지는 프로젝트를 @RestController 를 사용하여 REST API 로 개발하였습니다.

## GET /users

### Description

유저 리스트를 조회합니다. 페이지 번호를 파라미터로 전달하여 페이징 처리할 수 있으며, 유저 ID와 유저 이름으로 검색할 수 있습니다.

### Request Parameters

| Parameter | Type   | Required | Description         |
| --------- | ------ | -------- |---------------------|
| page      | Number | No       | 페이지 번호(default : 1) |
| userId    | String | No       | 검색할 유저 ID           |
| userName  | String | No       | 검색할 유저 이름           |

### Response(리스트 별 항목)

| Field        | Type    | Description |
| ------------ |---------|-------------|
| userIndex       | Integer | 유저 번호       |
| userId     | String  | 유저 아이디      |
| name        | String  | 유저 이름       |
| auth | String  | 유저 권한       |

## POST /users

### Description

새로운 유저를 생성합니다.

### Request Body

| Field       | Type             | Required | Description     |
| ----------- | ---------------- | -------- | --------------- |
| userId      | String           | Yes      | 유저 ID         |
| userName    | String           | Yes      | 유저 이름       |
| password    | String           | Yes      | 비밀번호        |
| role        | String           | Yes      | 유저 권한          |


### Response

없음 ("200 OK", "성공")

## PUT /users

### Description

기존 유저의 이름을 업데이트합니다.

### Request Body

| Field    | Type   | Required | Description     |
| -------- | ------ | -------- | --------------- |
| userId   | String | Yes      | 유저 ID         |
| newUserName | String | Yes      | 새로운 유저 이름 |

### Response

없음 ("200 OK", "성공")

## DELETE /users

### Description

유저를 삭제합니다.

### Request Body

| Field  | Type   | Required | Description |
| ------ | ------ | -------- | ----------- |
| userId | String | Yes      | 유저 ID     |

### Response

없음 ("200 OK", "성공")
