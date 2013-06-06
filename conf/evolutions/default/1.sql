# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table aircraft (
  id                        bigint auto_increment not null,
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
  constraint pk_airport primary key (id))
;

create table booking (
  id                        bigint auto_increment not null,
  constraint pk_booking primary key (id))
;

create table flight (
  id                        bigint auto_increment not null,
  route_id                  bigint,
  airline_id                bigint,
  aircraft_id               bigint,
  constraint pk_flight primary key (id))
;

create table passenger (
  identify                  varchar(255) not null,
  booking_id                bigint not null,
  name                      varchar(255),
  constraint pk_passenger primary key (identify))
;

create table route (
  id                        bigint auto_increment not null,
  depart_airport_id         bigint,
  arrive_airport_id         bigint,
  constraint pk_route primary key (id))
;

create table ticket (
  id                        bigint auto_increment not null,
  flight_id                 bigint,
  passenger_identify        varchar(255),
  constraint pk_ticket primary key (id))
;

create table user (
  id                        bigint auto_increment not null,
  email                     varchar(255),
  password                  varchar(255),
  name                      varchar(255),
  role                      integer,
  constraint ck_user_role check (role in (0,1)),
  constraint pk_user primary key (id))
;

alter table flight add constraint fk_flight_route_1 foreign key (route_id) references route (id) on delete restrict on update restrict;
create index ix_flight_route_1 on flight (route_id);
alter table flight add constraint fk_flight_airline_2 foreign key (airline_id) references airline (id) on delete restrict on update restrict;
create index ix_flight_airline_2 on flight (airline_id);
alter table flight add constraint fk_flight_aircraft_3 foreign key (aircraft_id) references aircraft (id) on delete restrict on update restrict;
create index ix_flight_aircraft_3 on flight (aircraft_id);
alter table passenger add constraint fk_passenger_booking_4 foreign key (booking_id) references booking (id) on delete restrict on update restrict;
create index ix_passenger_booking_4 on passenger (booking_id);
alter table route add constraint fk_route_departAirport_5 foreign key (depart_airport_id) references airport (id) on delete restrict on update restrict;
create index ix_route_departAirport_5 on route (depart_airport_id);
alter table route add constraint fk_route_arriveAirport_6 foreign key (arrive_airport_id) references airport (id) on delete restrict on update restrict;
create index ix_route_arriveAirport_6 on route (arrive_airport_id);
alter table ticket add constraint fk_ticket_flight_7 foreign key (flight_id) references flight (id) on delete restrict on update restrict;
create index ix_ticket_flight_7 on ticket (flight_id);
alter table ticket add constraint fk_ticket_passenger_8 foreign key (passenger_identify) references passenger (identify) on delete restrict on update restrict;
create index ix_ticket_passenger_8 on ticket (passenger_identify);



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

