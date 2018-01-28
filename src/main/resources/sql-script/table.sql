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
