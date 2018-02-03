insert into code (code, descript) values('USER', '사용자구분');
insert into code values('NORMAL', 'USER', '사용자');
insert into code values('ADMIN', 'USER', '관리자');

insert into code (code, descript) values('USER_STATUS', '사용자상태');
insert into code values('EMAIL_CONFIRM', 'USER_STATUS', '이메일인증대기');
insert into code values('USE', 'USER_STATUS', '사용가능');
insert into code values('LEAVE', 'USER_STATUS', '탈퇴');

insert into code (code, descript) values('API_TYPE', 'API 타입');
insert into code values('RESTAPI_01', 'API_TYPE', 'SpringBoot-restAPI-Template');


insert into user
(id, pw, name, email, userTypeCode, statusCode)
values('master', SHA2('1234', 256), '이승환', 'dlrmld@naver.com', 'ADMIN', 'USE');

insert into user
(id, pw, name, email, userTypeCode, statusCode)
values('dlrmld', SHA2('1234', 256), '이승환', 'dlrmld@naver.com', 'NORMAL', 'USE');


insert into oauth_client_details
(client_id, client_secret, resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove, id)
values('my_client_id', 'my_client_secret', null, 'public,private', 'client_credentials', null, 'ROLE_CLIENT', 36000, 2592000, null, true, 'master');
insert into oauth_client_details
(client_id, client_secret, resource_ids, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove, id)
values('your_client_id', 'your_client_secret', null, 'public', 'client_credentials', null, 'ROLE_CLIENT', 36000, 2592000, null, true, 'dlrmld');
