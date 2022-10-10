create sequence hibernate_sequence start 1 increment 1;
create table auth_token (id int8 not null, created_at timestamp, expires_at timestamp, token varchar(255), user_id int8, primary key (id));
create table user (id int8 not null, email varchar(255), nickname varchar(255), password varchar(255), registration_date date, primary key (id));
alter table if exists auth_token add constraint FKptnc32s7h74npe9p918cm4emk foreign key (user_id) references user;
