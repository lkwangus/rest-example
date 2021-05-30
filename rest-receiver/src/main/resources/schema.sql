create table message
(
    id              int,
    message_content varchar,
    created_on      datetime default current_date not null,
    constraint MESSAGE_PK primary key (id)
);

