create table j_role (
                        id serial primary key,
                        name varchar(2000)
);

create table j_user (
                        id serial primary key,
                        name varchar(2000),
                        role_id int not null references j_role(id)
);

create table brand (
                       id serial primary key,
                       name varchar(2000)
);

create table model (
                       id serial primary key,
                       name varchar(2000),
                       brand_id int references brand(id)
);

create table author (
                        id serial primary key,
                        name varchar(2000),
                        book_id int references book(id)
);

create table book (
                      id serial primary key,
                      name varchar(2000)
);

create table tasks (
                      id serial primary key,
                      description varchar(2000),
                      category_id int references categories(id)
);

create table categories (
                       id serial primary key,
                       name varchar(2000)
);

create table brand (
                       id serial primary key,
                       name varchar(2000)
);

create table model (
                       id serial primary key,
                       name varchar(2000),
                       brand_id int references brand(id)
);

create table candidate (
                       id serial primary key,
                       name varchar(200),
                       experience varchar(200),
                       salary int
);

create table base (
                        id serial primary key,
                        name varchar(200)

);

create table vacancy (
                      id serial primary key,
                      name varchar(200)
);




