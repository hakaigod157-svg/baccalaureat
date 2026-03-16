-- ==================================================================================
-- SCRIPT DE RÉINITIALISATION RAPIDE (VIDER + RÉINSTALLER SANS SUPPRIMER LA BDD)
-- Base: bacc_db | PostgreSQL
-- ==================================================================================

\c bacc_db;

-- 1. VIDER LA BASE (DROP TABLES)
DROP TABLE IF EXISTS "Notes" CASCADE;
DROP TABLE IF EXISTS "Parametres" CASCADE;
DROP TABLE IF EXISTS "Matieres" CASCADE;
DROP TABLE IF EXISTS "Candidat" CASCADE;
DROP TABLE IF EXISTS "Ecole" CASCADE;
DROP TABLE IF EXISTS "serie" CASCADE;
DROP TABLE IF EXISTS "professeurs" CASCADE;
DROP TABLE IF EXISTS "Operateur" CASCADE;
DROP TABLE IF EXISTS "Resolution" CASCADE;

-- ======================================
-- 2. CREATION DES TABLES
-- ======================================

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
    dateAjout TIMESTAMP
);

-- ============================================================
-- 3. DONNÉES DE TEST 
-- ============================================================

-- ---- SÉRIES ----
INSERT INTO serie ("idSerie", libelle) OVERRIDING SYSTEM VALUE VALUES
  (1, 'S1 — Sciences Exactes'),
  (2, 'S2 — Sciences de la Vie'),
  (3, 'L — Lettres et Humanities');
SELECT setval(pg_get_serial_sequence('serie', 'idSerie'), 3);

-- ---- ÉCOLES ----
INSERT INTO "Ecole" ("idEcole", "nomEcole", adresse) OVERRIDING SYSTEM VALUE VALUES
  (1, 'Ecole Bird', 'ByPass'),
  (2, 'Lycée Blaise Diagne', 'Dakar'),
  (3, 'Lycée Cheikh Anta Diop', 'Thiès');
SELECT setval(pg_get_serial_sequence('"Ecole"', 'idEcole'), 3);

-- ---- PROFESSEURS ----
INSERT INTO professeurs (id_prof, nom, prenom, matricule) OVERRIDING SYSTEM VALUE VALUES
  (1, 'Diallo',  'Mamadou',  'PROF-001'),
  (2, 'Sarr',    'Fatou',    'PROF-002'),
  (3, 'Ndoye',   'Ibrahima', 'PROF-003'),
  (4, 'Fall',    'Aminata',  'PROF-004');
SELECT setval(pg_get_serial_sequence('professeurs', 'id_prof'), 4);

-- ---- MATIÈRES S1 ----
INSERT INTO "Matieres" ("idMatiere", nom, coeff, "idSerie") OVERRIDING SYSTEM VALUE VALUES
  (1, 'Mathématiques',          5, 1),
  (2, 'Physique-Chimie',        4, 1),
  (3, 'Histoire-Géographie',    2, 1),
  (4, 'Français',               3, 1);

-- MATIÈRES L
INSERT INTO "Matieres" ("idMatiere", nom, coeff, "idSerie") OVERRIDING SYSTEM VALUE VALUES
  (5, 'Français (L)',     5, 3),
  (6, 'Philosophie',      3, 3);
SELECT setval(pg_get_serial_sequence('"Matieres"', 'idMatiere'), 6);

-- ---- OPÉRATEURS ----
INSERT INTO "Operateur" ("idOperateur", operation) OVERRIDING SYSTEM VALUE VALUES
  (1, '>'),
  (2, '>='),
  (3, '<'),
  (4, '<='),
  (5, '=');
SELECT setval(pg_get_serial_sequence('"Operateur"', 'idOperateur'), 5);

-- ---- RÉSOLUTIONS ----
INSERT INTO "Resolution" ("idResolution", operation) OVERRIDING SYSTEM VALUE VALUES
  (1, 'moyenne'),
  (2, 'note la plus basse'),
  (3, 'note la plus haute');
SELECT setval(pg_get_serial_sequence('"Resolution"', 'idResolution'), 3);

-- ---- PARAMÈTRES D'ÉCART ----
INSERT INTO "Parametres" ("idParametre", "idMatiere", "diffNotes", "idOperateur", "idResolution") OVERRIDING SYSTEM VALUE VALUES
  (1, 1, '5',  1, 1),   -- Maths:      écart > 5 → moyenne
  (2, 2, '4',  1, 2),   -- Physique:   écart > 4 → note la plus basse
  (3, 3, '3',  1, 1),   -- Histoire:   écart > 3 → moyenne
  (4, 4, '4',  1, 1);   -- Français:   écart > 4 → moyenne
SELECT setval(pg_get_serial_sequence('"Parametres"', 'idParametre'), 4);

-- ---- CANDIDATS ----
INSERT INTO "Candidat" ("idCandidat", nom, prenom, matricule, "idEcole", "idSerie") OVERRIDING SYSTEM VALUE VALUES
  (1, 'Ndiaye', 'Abou',    'BAC-001', 1, 1),
  (2, 'Badji',  'Mariama', 'BAC-002', 1, 1),
  (3, 'Cissé',  'Oumar',   'BAC-003', 2, 1),
  (4, 'Diop',   'Khady',   'BAC-004', 3, 3);
SELECT setval(pg_get_serial_sequence('"Candidat"', 'idCandidat'), 4);

-- ---- NOTES ----
-- Candidat 1: Ndiaye Abou
INSERT INTO "Notes" ("idNote", "idCandidat", "idMatiere", "idProf", note) OVERRIDING SYSTEM VALUE VALUES
  (1,  1, 1, 1, 14.0),
  (2,  1, 1, 2, 16.0),
  (3,  1, 2, 2, 12.0),
  (4,  1, 2, 1, 17.0),
  (5,  1, 3, 3, 11.0),
  (6,  1, 4, 4,  9.5);

-- Candidat 2: Badji Mariama
INSERT INTO "Notes" ("idNote", "idCandidat", "idMatiere", "idProf", note) OVERRIDING SYSTEM VALUE VALUES
  (7,  2, 1, 1,  8.0),
  (8,  2, 1, 2, 15.0),
  (9,  2, 2, 2, 13.0),
  (10, 2, 3, 3, 10.0),
  (11, 2, 4, 4, 14.0);

-- Candidat 3: Cissé Oumar
INSERT INTO "Notes" ("idNote", "idCandidat", "idMatiere", "idProf", note) OVERRIDING SYSTEM VALUE VALUES
  (12, 3, 1, 1, 18.0),
  (13, 3, 2, 2, 16.0),
  (14, 3, 2, 1,  9.0),
  (15, 3, 3, 3, 12.0),
  (16, 3, 4, 4, 15.0);

-- Candidat 4: Diop Khady
INSERT INTO "Notes" ("idNote", "idCandidat", "idMatiere", "idProf", note) OVERRIDING SYSTEM VALUE VALUES
  (17, 4, 5, 4, 16.0),
  (18, 4, 6, 3, 13.0);
SELECT setval(pg_get_serial_sequence('"Notes"', 'idNote'), 18);
