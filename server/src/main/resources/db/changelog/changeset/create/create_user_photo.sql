create table user_photos
(
    id           uuid primary key default uuid_generate_v4(),
    created_at   timestamp        default current_timestamp not null,
    updated_at   timestamp        default current_timestamp not null,
    user_id     uuid                                       not null,
    bytes        bytea                                      not null,
    content_type varchar(255)                               not null,

    constraint fk_house_photos_user
        foreign key (user_id) references users (id) on delete cascade
);
