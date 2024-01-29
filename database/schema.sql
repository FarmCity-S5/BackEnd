-- create database city;

create table UserFarm(
    id serial primary key ,
    name_user varchar(30),
    pass_word varchar(30)
);

-- niova(+description)
create table Terrain(
    id serial primary key ,
    longitude float,
    latitude float,
    nombre_parcelle integer,
    etat integer,
    descriptionTerrain text
);


create table UserTerrain(
    id_user integer references UserFarm(id),
    id_terrain int references Terrain(id)
);


create table Parcelle(
    id serial primary key ,
    id_terrain int references Terrain(id),
    rendement float,
    superficie integer
);

create table PhotosTerrain(
    id_terrain int references Terrain(id),
    photo_terrain varchar(60)
);

-- niova(+rendement)
create table Culture(
    id serial primary key ,
    name_culture varchar(30),
    prix integer,
    rendementM2 float,
    duration time
);

create table CultureParcelle(
    id serial primary key,
    id_parcelle int references Parcelle(id),
    id_culture int references Culture(id),
    date_plantation timestamp
);

create table CultureValableParcelle(
    id_parcelle int references Parcelle(id),
    id_culture int references Culture(id)
);
select * from parcelle;
create table HistoriqueTerrain(
    id serial primary key ,
    id_vendeur int references UserFarm(id),
    id_acheteur int references UserFarm(id),
    date_achat date
);


insert into UserFarm(name_user,pass_word) values ('Mitia','mitiaRak04');
insert into UserFarm(name_user,pass_word) values ('Fanilo','FalconX');
insert into UserFarm(name_user,pass_word) values ('Nekena','dimbyNekena');


insert into Terrain(longitude,latitude,nombre_parcelle,etat,descriptionTerrain) values ('-18.777192499999998','46.85432800000001',3,0,'Terrain au centre de la ville');

insert into UserTerrain(id_user,id_terrain) values (2,1);

INSERT INTO Parcelle (id_terrain, rendement, superficie)
VALUES
    (1, '10.6', 500),
    (1, '9.6', 300),
    (1, '5.7', 200);

insert into Culture(name_culture,prix,rendementM2,duration) 
VALUES
    ('Laitue',50,6,CAST('00:00:10' AS TIME)),
    ('Salade',20,9,CAST('00:00:06' AS TIME)),
    ('Tomate',10,12,CAST('00:00:08' AS TIME)),
    ('Pomme de terre',40,20,CAST('00:00:20' AS TIME));
