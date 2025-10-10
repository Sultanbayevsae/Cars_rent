create table car_photos
(
    id           uuid primary key default uuid_generate_v4(),
    created_at   timestamp        default current_timestamp not null,
    updated_at   timestamp        default current_timestamp not null,
    car_id     uuid                                       not null,
    bytes        bytea                                      not null,
    content_type varchar(255)                               not null,

    constraint fk_house_photos_car
        foreign key (car_id) references cars (id) on delete cascade
);
