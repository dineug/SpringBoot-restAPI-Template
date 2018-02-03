CREATE SCHEMA `handcoding` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE TABLE `code` (
	`code`       CHAR(20)      NOT NULL COMMENT '코드값',
	`parentCode` CHAR(20)      NULL     DEFAULT '' COMMENT '상위 코드값',
	`descript`   VARCHAR(150)  NOT NULL COMMENT '코드에 대한 설명'
);

ALTER TABLE `code`
	ADD CONSTRAINT
		PRIMARY KEY (
			`code`,
            `parentCode`
		);

CREATE TABLE `user` (
	`id`                 VARCHAR(20)	NOT NULL COMMENT 'ID',
	`pw`       		 	 VARCHAR(100)	NOT NULL COMMENT '암호',
	`name`               VARCHAR(20)	NOT NULL COMMENT '이름',
	`email`              VARCHAR(256)	NOT NULL COMMENT '이메일 주소',
	`userTypeCode`       CHAR(20)		NOT NULL COMMENT '사용자타입code',    
	`statusCode`         CHAR(20)		NOT NULL COMMENT '상태code',
	`regDate`            DATETIME		NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'now()'
);

ALTER TABLE `user`
	ADD CONSTRAINT
		PRIMARY KEY (
			`id`
		);
        
CREATE TABLE `email_confirm` (
	`emailKey`		VARCHAR(255) NOT NULL COMMENT '키값',
	`id`			VARCHAR(20)  NOT NULL COMMENT 'ID',    
	`userTypeCode`	CHAR(20)      NOT NULL COMMENT '사용자타입code',
	`regDate`		DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'now()',
	`expiredDate`	DATETIME     NOT NULL COMMENT '유효하기까지의 날짜'
);

ALTER TABLE `email_confirm`
	ADD CONSTRAINT
		PRIMARY KEY (
			`emailKey`
		);

create table oauth_client_details (
  client_id VARCHAR(256) ,
  resource_ids VARCHAR(256),
  client_secret VARCHAR(256),
  scope VARCHAR(256),
  authorized_grant_types VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities VARCHAR(256),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(256),
  id VARCHAR(20) PRIMARY KEY
);

create table oauth_access_token (
  token_id VARCHAR(256),
  token BLOB,
  authentication_id VARCHAR(256) PRIMARY KEY,
  user_name VARCHAR(256),
  client_id VARCHAR(256),
  authentication BLOB,
  refresh_token VARCHAR(256)
);



-- API 정보테이블
CREATE TABLE `api` (
	`apiTypeCode` CHAR(20)     NOT NULL, -- API 타입
	`url`         VARCHAR(150) NOT NULL, -- API URL
	`method`      VARCHAR(20)  NOT NULL, -- API 메소드 타입
	`notes`       VARCHAR(100) NOT NULL, -- API 설명
	`scope`       VARCHAR(20)  NOT NULL  -- API 호출범위
);

-- API 정보테이블
ALTER TABLE `api`
	ADD CONSTRAINT `PK_api` -- API 정보테이블 기본키
		PRIMARY KEY (
			`apiTypeCode`, -- API 타입
			`url`,         -- API URL
			`method`       -- API 메소드 타입
		);

-- client API 권한정보
CREATE TABLE `api_client_auth` (
	`client_id`   VARCHAR(256) NOT NULL, -- client 아이디
	`apiTypeCode` CHAR(20)     NOT NULL, -- API 타입
	`url`         VARCHAR(150) NOT NULL, -- API URL
	`method`      VARCHAR(20)  NOT NULL, -- API 메소드 타입
	`scope`       VARCHAR(20)  NOT NULL  -- API 호출범위
);

-- client API 권한정보
ALTER TABLE `api_client_auth`
	ADD CONSTRAINT `PK_api_client_auth` -- client API 권한정보 기본키
		PRIMARY KEY (
			`client_id`,   -- client 아이디
			`apiTypeCode`, -- API 타입
			`url`,         -- API URL
			`method`       -- API 메소드 타입
		);



