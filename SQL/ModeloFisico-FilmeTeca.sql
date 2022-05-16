/* ModeloLogico-FilmeTeca v0.1: */

CREATE TABLE Film (
    id bigserial PRIMARY KEY
);

CREATE TABLE Customer (
    user_creation_date date,
    email varchar(512),
    birthday date,
    password varchar(50),
    username varchar(30),
    id serial PRIMARY KEY
);

CREATE TABLE Favorite (
    fk_Customer_id serial,
    fk_Film_id bigserial,
    id serial PRIMARY KEY,
    date_of date
);

CREATE TABLE Watched (
    fk_Customer_id serial,
    fk_Film_id bigserial,
    id serial PRIMARY KEY,
    watch_date date
);

CREATE TABLE Annotation (
    fk_Film_id bigserial,
    fk_Customer_id serial,
    text varchar(512),
    id bigserial PRIMARY KEY,
    time_of datetime
);
 
ALTER TABLE Favorite ADD CONSTRAINT FK_Favorite_2
    FOREIGN KEY (fk_Customer_id)
    REFERENCES Customer (id)
    ON DELETE SET NULL;
 
ALTER TABLE Favorite ADD CONSTRAINT FK_Favorite_3
    FOREIGN KEY (fk_Film_id)
    REFERENCES Film (id)
    ON DELETE SET NULL;
 
ALTER TABLE Watched ADD CONSTRAINT FK_Watched_2
    FOREIGN KEY (fk_Customer_id)
    REFERENCES Customer (id)
    ON DELETE SET NULL;
 
ALTER TABLE Watched ADD CONSTRAINT FK_Watched_3
    FOREIGN KEY (fk_Film_id)
    REFERENCES Film (id)
    ON DELETE SET NULL;
 
ALTER TABLE Annotation ADD CONSTRAINT FK_Annotation_2
    FOREIGN KEY (fk_Film_id)
    REFERENCES Film (id)
    ON DELETE SET NULL;
 
ALTER TABLE Annotation ADD CONSTRAINT FK_Annotation_3
    FOREIGN KEY (fk_Customer_id)
    REFERENCES Customer (id)
    ON DELETE SET NULL;