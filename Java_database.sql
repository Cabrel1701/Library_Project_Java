-- creation de la base de données 

create database if not exists Library;
create database Library;

use Library;

CREATE TABLE Livre (
    idLivre INT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(255) NOT NULL,
    auteur VARCHAR(255) NOT NULL,
    anneePublication INT NOT NULL,
    stock INT NOT NULL CHECK (stock >= 0)
);

CREATE TABLE Membre (
    idMembre INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    date_inscription DATE DEFAULT (current_date)
);


CREATE TABLE Emprunt (
    idEmprunt INT PRIMARY KEY AUTO_INCREMENT,
    idLivre INT NOT NULL,
    idMembre INT NOT NULL,
    dateEmprunt DATE DEFAULT (current_date),
    dateRetourPrevue DATE,
    dateRetourEffective DATE,
    FOREIGN KEY (idLivre) REFERENCES Livre(idLivre),
    FOREIGN KEY (idMembre) REFERENCES Membre(idMembre)
);


-- Un Membre peut effectuer plusieurs Emprunts
-- Chaque Emprunt concerne un seul Livre
-- Un Livre peut être emprunté plusieurs fois
-- Donc, la table Emprunt doit contenir deux clés étrangères :
-- idMembre → Membre
-- idLivre → Livre






































































