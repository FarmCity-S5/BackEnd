-- create database city;

create table UserFarm(
                         id serial primary key ,
                         name_user varchar(30),
                         pass_word varchar(30)
);

create table Terrain(
                        id serial primary key ,
                        longitude float,
                        latitude float,
                        nombre_parcelle integer,
                        etat integer
);


create table UserTerrain(
                            id_user integer references UserFarm(id),
                            id_terrain int references Terrain(id)
);

create table HistoriqueTerrain(
                                  id serial primary key ,
                                  id_vendeur int references UserFarm(id),
                                  id_acheteur int references UserFarm(id),
                                  date_achat date
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

create table Culture(
                        id serial primary key ,
                        name_culture varchar(30),
                        prix integer,
                        duration time
);

create table CultureParcelle(
                                id_parcelle int references Parcelle(id),
                                id_culture int references Culture(id),
                                date_plantation timestamp
);

create table CultureValableParcelle(
                                       id_parcelle int references Parcelle(id),
                                       id_culture int references Culture(id)
);

Select Parcelle.id,
       Parcelle.id_terrain,
       Parcelle.rendement,
       Culture.name_culture
from Parcelle
        join Terrain on Parcelle.id_terrain = Terrain.id
        join Culture on Terrain.id = Culture.id;

SELECT * FROM UserFarm ;
SELECT C.duration, CP.date_plantation FROM CultureParcelle CP
                       JOIN Culture C ON CP.id_culture = C.id
                       WHERE CP.id_parcelle = ?;

select P.id_terrain, C.id id_culture, C.name_culture nom_culture, count(C.id) nombre_culture
from cultureparcelle
join Culture C on cultureparcelle.id_culture = C.id
join Parcelle P on cultureparcelle.id_parcelle = P.id

group by id_terrain, C.id;

select * from cultureparcelle
join Culture C on C.id = cultureparcelle.id_culture;

update Terrain set_etat = 1 where id_terrain = ?;
UPDATE Terrain
SET etat = 1
               WHERE id= ?;