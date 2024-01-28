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


create table HistoriqueTerrain(
    id serial primary key ,
    id_vendeur int references UserFarm(id),
    id_acheteur int references UserFarm(id),
    date_achat date
);


select * from Parcelle where id_parcelle='?' and id_culture='?';