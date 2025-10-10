create table user_liked_cars
(
    id         uuid primary key default uuid_generate_v4(),
    created_at timestamp        default current_timestamp not null,
    updated_at timestamp        default current_timestamp not null,
    car_id   uuid                                       not null,
    user_id    uuid                                       not null,

    constraint fk_user_liked_houses_house
        foreign key (car_id) references houses (id) on delete cascade,
    constraint fk_user_liked_houses_user
        foreign key (user_id) references users (id) on delete cascade,
    constraint uq_user_liked_houses unique (car_id, user_id)
);