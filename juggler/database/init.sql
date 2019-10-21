-- noinspection SqlDialectInspectionForFile
-- noinspection SqlNoDataSourceInspectionForFile

create database lotter_juggler;

create table draw (
	"id" int not null primary key,
	"date" date not null
);

create table number (
	id int not null,
	draw_id int not null,
	value smallint not null,
	primary key (id),
	foreign key (draw_id) references draw (id)
);

CREATE TABLE DRAW (
	"ID" INT NOT NULL PRIMARY KEY,
	"DRAW_DATE" DATE NOT NULL
);


CREATE SEQUENCE NUMBER_ID_SEQ;
CREATE TABLE NUMBER (
	"ID" INT NOT NULL DEFAULT NUMBER_ID_SEQ.nextval PRIMARY KEY,
	"DRAW_ID" INT NOT NULL,
	"VALUE" SMALLINT NOT NULL,
	FOREIGN KEY ("DRAW_ID") REFERENCES DRAW ("ID")
);