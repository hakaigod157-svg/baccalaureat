CREATE DATABASE baccalaureat_db;

\c baccalaureat_db;


CREATE TABLE professeurs(
   id_prof SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    matricule VARCHAR(50)
);

CREATE TABLE serie(
    idSerie SERIAL PRIMARY KEY,
    libelle VARCHAR(50)
);

CREATE TABLE Matieres(
    idMatiere SERIAL PRIMARY KEY,
    nom VARCHAR(50),
    coeff DECIMAL(10,2),
    idSerie INTEGER NOT NULL REFERENCES  serie(idSerie)
);

CREATE TABLE Ecole(
    idEcole SERIAL PRIMARY KEY,
    nomEcole VARCHAR (50),
    adresse VARCHAR(50)
);

CREATE TABLE Candidat(
    idCandidat SERIAL PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    matricule VARCHAR(50),
    idEcole INTEGER NOT NULL REFERENCES Ecole(idEcole),
    idSerie INTEGER NOT NULL REFERENCES serie(idSerie)
);

CREATE TABLE IF NOT EXISTS Notes(
    idNote SERIAL PRIMARY KEY,
    idCandidat INTEGER NOT NULL REFERENCES Candidat(idCandidat),
    idMatiere INTEGER NOT NULL REFERENCES Matieres(idMatiere),
    idProf INTEGER NOT NULL REFERENCES professeurs(id_prof),
    note DECIMAL(10,2),
    dateNote timestamp default now()
);

CREATE TABLE IF NOT EXISTS Resolution(
    idResolution SERIAL PRIMARY KEY,
    operation VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Operateur(
    idOperateur SERIAL PRIMARY KEY,
    operation VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS Parametres(
    idParametre SERIAL PRIMARY KEY,
    diffNotes VARCHAR(50),
    idMatiere INTEGER NOT NULL REFERENCES Matieres(idMatiere),
    idOperateur INTEGER NOT NULL REFERENCES Operateur(idOperateur),
    idResolution INTEGER NOT NULL REFERENCES Resolution(idResolution),
    dateAjout TIMESTAMP DEFAULT NOW()
);
