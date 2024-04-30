Database tablolarını oluşturmak için aşağıdaki scriptleri kullanabilirsiniz:

```
create table hotel
(
    id         integer generated always as identity
        primary key,
    hotel_name varchar(255),
    address    varchar(255),
    email      varchar(255),
    phone      text,
    star       integer,
    pansiyon   text,
    tesis      text,
    donem      text
);

create table reservation
(
    id            integer generated always as identity
        primary key,
    city          varchar(255),
    season_choice varchar(50),
    room_type     varchar(50),
    name_surname  varchar(255),
    phone_num     varchar(15),
    mail          varchar(255),
    hotel_name    varchar(255),
    entry_date    varchar(255),
    exit_date     varchar(255),
    people_number integer,
    child_number  integer,
    price         varchar(255),
    room_id       integer
);

create table room
(
    id           integer generated always as identity
        primary key,
    hotel_id     integer,
    room_size    varchar(50),
    television   boolean,
    mini_bar     boolean,
    game_console boolean,
    safe         boolean,
    projection   boolean,
    bed_number   integer,
    room_type    varchar(50),
    price        varchar(50),
    availability boolean,
    stock        integer,
    room_id      integer
);

create table "user"
(
    id       integer generated always as identity
        constraint users_pkey
            primary key,
    username varchar(255),
    password varchar(255),
    role     varchar(50)
);
```