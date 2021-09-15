create database model_and_connection;

create table driver
(
    id serial primary key,
    name VARCHAR(255)
);

create table engine (
    id serial primary key,
    name VARCHAR(255)
);

create table car
(
    id  serial primary key,
    name VARCHAR(255),
    engine_id int not null unique references engine (id)
);

create table history_owner
(
    PRIMARY KEY(driver_id, car_id),
    driver_id int not null references driver(id),
    car_id    int not null references car(id)
);
