# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table aircraft (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  economy                   integer,
  first                     integer,
  constraint pk_aircraft primary key (id))
;

create table airline (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  constraint pk_airline primary key (id))
;

create table airport (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  city                      varchar(255),
  constraint uq_airport_name unique (name),
  constraint pk_airport primary key (id))
;

create table booking (
  id                        bigint auto_increment not null,
  customer_email            varchar(255),
  status                    integer,
  constraint ck_booking_status check (status in (0,1)),
  constraint pk_booking primary key (id))
;

create table flight (
  id                        bigint auto_increment not null,
  route_id                  bigint,
  airline_id                bigint,
  aircraft_id               bigint,
  date                      datetime,
  first_price               integer,
  economy_price             integer,
  constraint pk_flight primary key (id))
;

create table passenger (
  identify                  varchar(255) not null,
  name                      varchar(255),
  constraint pk_passenger primary key (identify))
;

create table route (
  id                        bigint auto_increment not null,
  depart_airport_id         bigint,
  arrive_airport_id         bigint,
  depart_time               datetime,
  arrive_time               datetime,
  constraint pk_route primary key (id))
;

create table ticket (
  id                        bigint auto_increment not null,
  booking_id                bigint,
  flight_id                 bigint,
  passenger_identify        varchar(255),
  first_class               tinyint(1) default 0,
  constraint pk_ticket primary key (id))
;

create table user (
  email                     varchar(255) not null,
  password_hash             varchar(255),
  role                      integer,
  constraint ck_user_role check (role in (0,1)),
  constraint pk_user primary key (email))
;

alter table booking add constraint fk_booking_customer_1 foreign key (customer_email) references user (email) on delete restrict on update restrict;
create index ix_booking_customer_1 on booking (customer_email);
alter table flight add constraint fk_flight_route_2 foreign key (route_id) references route (id) on delete restrict on update restrict;
create index ix_flight_route_2 on flight (route_id);
alter table flight add constraint fk_flight_airline_3 foreign key (airline_id) references airline (id) on delete restrict on update restrict;
create index ix_flight_airline_3 on flight (airline_id);
alter table flight add constraint fk_flight_aircraft_4 foreign key (aircraft_id) references aircraft (id) on delete restrict on update restrict;
create index ix_flight_aircraft_4 on flight (aircraft_id);
alter table route add constraint fk_route_departAirport_5 foreign key (depart_airport_id) references airport (id) on delete restrict on update restrict;
create index ix_route_departAirport_5 on route (depart_airport_id);
alter table route add constraint fk_route_arriveAirport_6 foreign key (arrive_airport_id) references airport (id) on delete restrict on update restrict;
create index ix_route_arriveAirport_6 on route (arrive_airport_id);
alter table ticket add constraint fk_ticket_booking_7 foreign key (booking_id) references booking (id) on delete restrict on update restrict;
create index ix_ticket_booking_7 on ticket (booking_id);
alter table ticket add constraint fk_ticket_flight_8 foreign key (flight_id) references flight (id) on delete restrict on update restrict;
create index ix_ticket_flight_8 on ticket (flight_id);
alter table ticket add constraint fk_ticket_passenger_9 foreign key (passenger_identify) references passenger (identify) on delete restrict on update restrict;
create index ix_ticket_passenger_9 on ticket (passenger_identify);



# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table aircraft;

drop table airline;

drop table airport;

drop table booking;

drop table flight;

drop table passenger;

drop table route;

drop table ticket;

drop table user;

SET FOREIGN_KEY_CHECKS=1;

