create table vehicle
(
    vehicle_id  int auto_increment
        primary key,
    create_date datetime     null,
    plate       varchar(255) null,
    status      varchar(255) null,
    update_date datetime     null,
    constraint UKbx1ojwn4cep83pyro5bic8mdw
        unique (plate)
);

create index PACKAGE_PLATE_INDEX
    on vehicle (plate);


INSERT INTO FLEET_MANAGEMENT.vehicle (vehicle_id, create_date, plate, update_date, status)
VALUES (1, '2022-12-12 15:45:45', '34TL34', '2022-12-12 15:45:45', 'USABLE');


create table delivery_point
(
    delivery_point_id int auto_increment
        primary key,
    create_date       datetime     null,
    location          varchar(255) null,
    name              varchar(255) null,
    type              varchar(255) null,
    update_date       datetime     null,
    constraint UKo59hms514rkc22uak40ekrqyi
        unique (name)
);

create index DELIVERY_POINT_TYPE_INDEX
    on delivery_point (type);

INSERT INTO FLEET_MANAGEMENT.delivery_point (delivery_point_id, create_date, location, name, type, update_date)
VALUES (1, '2022-12-12 15:44:26', 'string', 'Branch', 'BRANCH', '2022-12-12 15:44:26');
INSERT INTO FLEET_MANAGEMENT.delivery_point (delivery_point_id, create_date, location, name, type, update_date)
VALUES (2, '2022-12-12 15:44:49', 'string', 'Distribution Centre', 'DISTRIBUTION_CENTRE', '2022-12-12 15:44:49');
INSERT INTO FLEET_MANAGEMENT.delivery_point (delivery_point_id, create_date, location, name, type, update_date)
VALUES (3, '2022-12-12 15:45:06', 'string', 'Transfer Centre', 'TRANSFER_CENTRE', '2022-12-12 15:45:06');



create table sack
(
    sack_id           bigint auto_increment
        primary key,
    barcode           varchar(255) null,
    create_date       datetime     null,
    state             varchar(255) null,
    update_date       datetime     null,
    delivery_point_id int          not null,
    vehicle_id        int          null,
    constraint UKi00v9f4y9v14si1suihoir9ka
        unique (barcode),
    constraint FK7f6hq0o4kf9htts09ddwyl0p6
        foreign key (delivery_point_id) references delivery_point (delivery_point_id),
    constraint FKd9bsefpgbr5kh6eylldnsxg4l
        foreign key (vehicle_id) references vehicle (vehicle_id)
);

create index PACKAGE_BARCODE_INDEX
    on sack (barcode);

INSERT INTO FLEET_MANAGEMENT.sack (sack_id, barcode, create_date, state, update_date, delivery_point_id, vehicle_id)
VALUES (1, 'C725799', '2022-12-12 15:45:45', 'CREATED', '2022-12-12 15:45:45', 2, null);
INSERT INTO FLEET_MANAGEMENT.sack (sack_id, barcode, create_date, state, update_date, delivery_point_id, vehicle_id)
VALUES (2, 'C725800', '2022-12-12 15:45:53', 'CREATED', '2022-12-12 15:45:53', 3, null);



create table package
(
    package_id        bigint auto_increment
        primary key,
    barcode           varchar(255) null,
    create_date       datetime     null,
    desi              int          null,
    state             varchar(255) null,
    update_date       datetime     null,
    delivery_point_id int          not null,
    sack_id           bigint       null,
    vehicle_id        int          null,
    constraint UKwujqrr5ome3986fi2nnwdst8
        unique (barcode),
    constraint FKa3kjteabbnpq21yn0d7r7uqqm
        foreign key (delivery_point_id) references delivery_point (delivery_point_id),
    constraint FKklqge4gvhgeja5ofislq408u4
        foreign key (vehicle_id) references vehicle (vehicle_id),
    constraint FKp0m0okw9aqxjsm3e48gwo9w1t
        foreign key (sack_id) references sack (sack_id)
);

create index PACKAGE_BARCODE_INDEX
    on package (barcode);

INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (1, 'P7988000121', '2022-12-12 15:46:20', 5, 'CREATED', '2022-12-12 15:46:20', 1, null, null);
INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (2, 'P7988000122', '2022-12-12 15:46:27', 5, 'CREATED', '2022-12-12 15:46:27', 1, null, null);
INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (3, 'P7988000123', '2022-12-12 15:46:45', 9, 'CREATED', '2022-12-12 15:46:45', 1, null, null);
INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (4, 'P8988000120', '2022-12-12 15:47:01', 33, 'CREATED', '2022-12-12 15:47:01', 2, null, null);
INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (5, 'P8988000121', '2022-12-12 15:47:13', 17, 'CREATED', '2022-12-12 15:47:13', 2, null, null);
INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (6, 'P8988000122', '2022-12-12 15:47:24', 26, 'LOADED_INTO_SACK', '2022-12-12 15:49:50', 2, 1, null);
INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (7, 'P8988000123', '2022-12-12 15:47:34', 35, 'CREATED', '2022-12-12 15:47:34', 2, null, null);
INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (8, 'P8988000124', '2022-12-12 15:47:44', 1, 'CREATED', '2022-12-12 15:47:44', 2, null, null);
INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (9, 'P8988000125', '2022-12-12 15:47:53', 200, 'CREATED', '2022-12-12 15:47:53', 2, null, null);
INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (10, 'P8988000126', '2022-12-12 15:48:04', 50, 'LOADED_INTO_SACK', '2022-12-12 15:49:50', 2, 1, null);
INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (11, 'P9988000126', '2022-12-12 15:48:17', 15, 'CREATED', '2022-12-12 15:48:17', 3, null, null);
INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (12, 'P9988000127', '2022-12-12 15:48:28', 16, 'CREATED', '2022-12-12 15:48:28', 3, null, null);
INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (13, 'P9988000128', '2022-12-12 15:48:38', 55, 'LOADED_INTO_SACK', '2022-12-12 15:50:12', 3, 2, null);
INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (14, 'P9988000129', '2022-12-12 15:48:56', 28, 'LOADED_INTO_SACK', '2022-12-12 15:50:12', 3, 2, null);
INSERT INTO FLEET_MANAGEMENT.package (package_id, barcode, create_date, desi, state, update_date, delivery_point_id,
                                      sack_id, vehicle_id)
VALUES (15, 'P9988000130', '2022-12-12 15:49:04', 17, 'CREATED', '2022-12-12 15:49:04', 3, null, null);



