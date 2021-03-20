CREATE TABLE roles
(
    authority    VARCHAR(50) NOT NULL PRIMARY KEY,
    description  VARCHAR(100) NOT NULL
);

create table users(
    username VARCHAR(50) not null primary key,
    password VARCHAR(100) not null,
    enabled boolean not null,
    first_name varchar(50),
    last_name varchar(50)
);

create table authorities (
    username VARCHAR(50) not null,
    authority VARCHAR(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
);
create unique index ix_auth_username on authorities (username,authority);

create table groups (
    id bigserial primary key,
    group_name VARCHAR(50) not null
);

create table group_authorities (
    group_id bigint not null,
    authority varchar(50) not null,
    constraint fk_group_authorities_group foreign key(group_id) references groups(id)
);

create table group_members (
    id bigserial primary key,
    username varchar(50) not null,
    group_id bigint not null,
    constraint fk_group_members_group foreign key(group_id) references groups(id)
);

---

create table persistent_logins (
	username varchar(64) not null,
	series varchar(64) primary key,
	token varchar(64) not null,
	last_used timestamp not null
);


create table acl_sid(
    id bigserial not null primary key,
    principal boolean not null,
    sid varchar(100) not null,
    constraint unique_uk_1 unique(sid,principal)
);

create table acl_class(
    id bigserial not null primary key,
    class varchar(100) not null,
    constraint unique_uk_2 unique(class)
);

create table acl_object_identity(
    id bigserial primary key,
    object_id_class bigint not null,
    object_id_identity varchar(36) not null,
    parent_object bigint,
    owner_sid bigint,
    entries_inheriting boolean not null,
    constraint unique_uk_3 unique(object_id_class,object_id_identity),
    constraint foreign_fk_1 foreign key(parent_object)references acl_object_identity(id),
    constraint foreign_fk_2 foreign key(object_id_class)references acl_class(id),
    constraint foreign_fk_3 foreign key(owner_sid)references acl_sid(id)
);

create table acl_entry(
    id bigserial primary key,
    acl_object_identity bigint not null,
    ace_order int not null,
    sid bigint not null,
    mask integer not null,
    granting boolean not null,
    audit_success boolean not null,
    audit_failure boolean not null,
    constraint unique_uk_4 unique(acl_object_identity,ace_order),
    constraint foreign_fk_4 foreign key(acl_object_identity) references acl_object_identity(id),
    constraint foreign_fk_5 foreign key(sid) references acl_sid(id)
);


-- spring session
create table spring_session (
	primary_id char(36) not null,
	session_id char(36) not null,
	creation_time bigint not null,
	last_access_time bigint not null,
	max_inactive_interval int not null,
	expiry_time bigint not null,
	principal_name varchar(100),
	constraint spring_session_pk primary key (primary_id)
);

create unique index spring_session_ix1 on spring_session (session_id);
create index spring_session_ix2 on spring_session (expiry_time);
create index spring_session_ix3 on spring_session (principal_name);

create table spring_session_attributes (
	session_primary_id char(36) not null,
	attribute_name varchar(200) not null,
	attribute_bytes bytea not null,
	constraint spring_session_attributes_pk primary key (session_primary_id, attribute_name),
	constraint spring_session_attributes_fk foreign key (session_primary_id) references spring_session(primary_id) on delete cascade
);
---

create table revinfo (
    rev       BIGINT(50) NOT NULL PRIMARY KEY AUTO_INCREMENT,
    revtstmp  BIGINT(50),
    username  VARCHAR(50)
);

---

INSERT INTO roles
(authority, description)
VALUES
('ROLE_ADMIN'  ,'Administrator'),
('ROLE_USER'  ,'User')
;

INSERT INTO users
(enabled,username              ,password     , first_name,last_name)
VALUES
(true  , 'admin@ramdan.com'   ,'{noop}rahasia', 'Agus'    ,'Ramdan'),
(true  , 'user@ramdan.com'    ,'{noop}rahasia', 'Agus'    ,'Ramdan')
;


INSERT INTO authorities
(authority     ,username)
VALUES
('ROLE_ADMIN'  ,'admin@ramdan.com'),
('ROLE_USER'  ,'user@ramdan.com')

;


