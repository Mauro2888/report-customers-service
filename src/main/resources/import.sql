drop table if exists customers CASCADE ;
-- for h2 database
create table customers (
                           id UUID NOT NULL DEFAULT random_uuid(),
                           name varchar(255) not null,
                           address varchar(255) not null,
                           phoneNumber varchar(255) not null,
                           createdAt date not null
);

insert into customers (address, createdAt, name, phoneNumber) values ('123 Main St', '2015-01-01', 'John Doe', '555-555-5555');
insert into customers (address, createdAt, name, phoneNumber) values ('456 Main St', '2016-01-01', 'Jane Doe', '555-555-5555');
insert into customers (address, createdAt, name, phoneNumber) values ('789 Main St', '2017-01-01', 'John Smith', '555-555-5555');
insert into customers (address, createdAt, name, phoneNumber) values ('101 Main St', '2020-01-01', 'Jane Smith', '555-555-5555');
insert into customers (address, createdAt, name, phoneNumber) values ('102 Main St', '2020-08-02', 'Lucas Check', '555-555-5555');