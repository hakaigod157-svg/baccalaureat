CREATE DATABASE forage_db;

\c forage_db;

CREATE TABLE client(
    id_client SERIAL PRIMARY KEY,
    Nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    contact INTEGER NOT NULL 
);

CREATE TABLE Lieu(
    idLieu SERIAL PRIMARY KEY,
    localisation VARCHAR (50),
    district VARCHAR(50)
);

CREATE TABLE Demande(
    idDemande SERIAL PRIMARY KEY,
    idClient INTEGER NOT NULL REFERENCES client(id_client),
    idLieu INTEGER NOT NULL REFERENCES Lieu(idLieu),
    dateDemande TIMESTAMP DEFAULT NOW()
);


CREATE TABLE TypeDevis(
    idTypeDevis SERIAL PRIMARY KEY,
    libelle VARCHAR(50)
);

CREATE TABLE Statut(
    idStatut SERIAL PRIMARY KEY,
    libelle VARCHAR(50)
);

CREATE TABLE Devis(
    idDevis SERIAL PRIMARY KEY,
    dateDevis TIMESTAMP DEFAULT NOW(),
    idDemande INTEGER NOT NULL REFERENCES Demande(idDemande),
    idTypeDevis INTEGER NOT NULL REFERENCES TypeDevis(idTypeDevis),
    idStatut INTEGER NOT NULL REFERENCES Statut(idStatut)
);

CREATE TABLE DetailDevis(
    idDetailDevis SERIAL PRIMARY KEY,
    idDevis INTEGER NOT NULL REFERENCES Devis(idDevis),
    libelle VARCHAR(50),
    prixUnitaire DECIMAL(10,2),
    quantite INTEGER
);

CREATE TABLE PrixTotalDevis(
    idPrixTotalDevis SERIAL PRIMARY KEY,
    idDevis INTEGER NOT NULL REFERENCES Devis(idDevis),
    montantTotal DECIMAL(10,2)
);

CREATE TABLE DemandeStatut(
    idDemandeStatut SERIAL PRIMARY KEY ,
    idDemande INTEGER NOT NULL REFERENCES Demande(idDemande),
    idDevis INTEGER REFERENCES Devis(idDevis),
    idStatut INTEGER NOT NULL REFERENCES Statut(idStatut),
    dateDemandeStatut TIMESTAMP DEFAULT NOW()
);