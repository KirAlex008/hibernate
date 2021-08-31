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

create table author_book (
                             id serial primary key,
                             author_id int references author(id),
                             book_id int references book(id)
)
