drop table if exists customers CASCADE ;
-- for h2 database
create table customers (
                           id UUID NOT null,
                           name varchar(255) not null,
                           address varchar(255) not null,
                           phoneNumber varchar(255) not null,
                           createdAt date not null,
                           version int8 not null,
                           primary key (id)
);

insert into customers (id, name, address, phoneNumber, createdAt, version) values (random_uuid(), 'John', '123 Main St', '555-1212', '2019-01-01', 0);
insert into customers (id, name, address, phoneNumber, createdAt, version) values (random_uuid(), 'Jane', '123 Main St', '555-1212', '2019-01-01', 2);
insert into customers (id, name, address, phoneNumber, createdAt, version) values (random_uuid(), 'Bob', '123 Main St', '555-1212', '2019-01-01', 1);
insert into customers (id, name, address, phoneNumber, createdAt, version) values (random_uuid(), 'Sue', '123 Main St', '555-1212', '2019-01-01', 1);
insert into customers (id, name, address, phoneNumber, createdAt, version) values (random_uuid(), 'Tom', '123 Main St', '555-1212', '2019-01-01', 1);
insert into customers (id, name, address, phoneNumber, createdAt, version) values (random_uuid(), 'Bill', '123 Main St', '555-1212', '2019-01-01', 1);