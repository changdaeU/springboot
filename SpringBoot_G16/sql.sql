create table spmember(
	id varchar2(30),
	pw varchar2(30),
	name varchar2(30),
	phone1 varchar2(15),
	phone2 varchar2(15),
	phone3 varchar2(15),
	email varchar2(30)
);

insert into spmember(id, pw, name, phone1, phone2, phone3, email)
values('scott', '1234', '박지성', '010', '1234', '5678', 'park@naver.com');

insert into spmember(id, pw, name, phone1, phone2, phone3, email)
values('hong1', '1234', '홍길동', '010', '1111', '2222', 'hong1@naver.com');
insert into spmember(id, pw, name, phone1, phone2, phone3, email)
values('hong2', '1234', '홍길서', '010', '2222', '3333', 'hong2@naver.com');
insert into spmember(id, pw, name, phone1, phone2, phone3, email)
values('hong3', '1234', '홍길남', '010', '3333', '4444', 'hong3@naver.com');
insert into spmember(id, pw, name, phone1, phone2, phone3, email)
values('hong4', '1234', '홍길북', '010', '4444', '5555', 'hong4@naver.com');

select * from BOARD
select * from reply


