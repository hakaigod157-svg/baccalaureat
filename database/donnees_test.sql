-- ============================================================
-- DONNÉES DE TEST — Application Baccalauréat
-- COHÉRENT AVEC LES ENTITÉS JPA (noms de table Hibernate)
-- ============================================================
--
--   Table JPA        | @Table(name=...)  | Colonnes PK
--   Serie            | serie             | "idSerie"
--   Ecole            | "Ecole"           | "idEcole"
--   Prof             | professeurs       | id_prof
--   Matiere          | "Matieres"        | "idMatiere"
--   Candidat         | "Candidat"        | "idCandidat"
--   Note             | "Notes"           | "idNote"
--   Operateur        | "Operateur"       | "idOperateur"
--   Resolution       | "Resolution"      | "idResolution"
--   Parametre        | "Parametres"      | "idParametre"
--
-- PostgreSQL convertit les noms sans guillemets en minuscules.
-- Hibernate écrit les noms @Table TELS QUELS dans le DDL.
-- ============================================================

-- ---- 1. SÉRIES (table: serie) ----
INSERT INTO serie ("idSerie", libelle) OVERRIDING SYSTEM VALUE VALUES
  (1, 'S1 — Sciences Exactes'),
  (2, 'S2 — Sciences de la Vie'),
  (3, 'L — Lettres et Humanities');
SELECT setval(pg_get_serial_sequence('serie', 'idSerie'), 3);

-- ---- 2. ÉCOLES (table: "Ecole") ----
INSERT INTO "Ecole" ("idEcole", "nomEcole", adresse) OVERRIDING SYSTEM VALUE VALUES
  (1, 'Lycée National de Dakar', 'Dakar'),
  (2, 'Lycée Blaise Diagne', 'Dakar'),
  (3, 'Lycée Cheikh Anta Diop', 'Thiès');
SELECT setval(pg_get_serial_sequence('"Ecole"', 'idEcole'), 3);

-- ---- 3. PROFESSEURS (table: professeurs) ----
INSERT INTO professeurs (id_prof, nom, prenom, matricule) OVERRIDING SYSTEM VALUE VALUES
  (1, 'Diallo',  'Mamadou',  'PROF-001'),
  (2, 'Sarr',    'Fatou',    'PROF-002'),
  (3, 'Ndoye',   'Ibrahima', 'PROF-003'),
  (4, 'Fall',    'Aminata',  'PROF-004');
SELECT setval(pg_get_serial_sequence('professeurs', 'id_prof'), 4);

-- ---- 4. MATIÈRES S1 (table: "Matieres") ----
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

-- ---- 5. OPÉRATEURS (table: "Operateur") ----
INSERT INTO "Operateur" ("idOperateur", operation) OVERRIDING SYSTEM VALUE VALUES
  (1, '>'),
  (2, '>='),
  (3, '<'),
  (4, '<='),
  (5, '=');
SELECT setval(pg_get_serial_sequence('"Operateur"', 'idOperateur'), 5);

-- ---- 6. RÉSOLUTIONS (table: "Resolution") ----
INSERT INTO "Resolution" ("idResolution", operation) OVERRIDING SYSTEM VALUE VALUES
  (1, 'moyenne'),
  (2, 'note la plus basse'),
  (3, 'note la plus haute');
SELECT setval(pg_get_serial_sequence('"Resolution"', 'idResolution'), 3);

-- ---- 7. PARAMÈTRES D'ÉCART (table: "Parametres") ----
-- Règle : si écart > X pour cette matière → résolution
INSERT INTO "Parametres" ("idParametre", "idMatiere", "diffNotes", "idOperateur", "idResolution") OVERRIDING SYSTEM VALUE VALUES
  (1, 1, '5',  1, 1),   -- Maths:      écart > 5 → moyenne
  (2, 2, '4',  1, 2),   -- Physique:   écart > 4 → note la plus basse
  (3, 3, '3',  1, 1),   -- Histoire:   écart > 3 → moyenne
  (4, 4, '4',  1, 1);   -- Français:   écart > 4 → moyenne
SELECT setval(pg_get_serial_sequence('"Parametres"', 'idParametre'), 4);

-- ---- 8. CANDIDATS (table: "Candidat") ----
INSERT INTO "Candidat" ("idCandidat", nom, prenom, matricule, "idEcole", "idSerie") OVERRIDING SYSTEM VALUE VALUES
  (1, 'Ndiaye', 'Abou',    'BAC-001', 1, 1),
  (2, 'Badji',  'Mariama', 'BAC-002', 1, 1),
  (3, 'Cissé',  'Oumar',   'BAC-003', 2, 1),
  (4, 'Diop',   'Khady',   'BAC-004', 3, 3);
SELECT setval(pg_get_serial_sequence('"Candidat"', 'idCandidat'), 4);

-- ---- 9. NOTES (table: "Notes") ----
-- Candidat 1: Ndiaye Abou
INSERT INTO "Notes" ("idNote", "idCandidat", "idMatiere", "idProf", note) OVERRIDING SYSTEM VALUE VALUES
  (1,  1, 1, 1, 14.0),   -- Maths — Diallo     \
  (2,  1, 1, 2, 16.0),   -- Maths — Sarr        → écart=|14-16|=2 (<5) → moyenne=15
  (3,  1, 2, 2, 12.0),   -- Physique — Sarr     \
  (4,  1, 2, 1, 17.0),   -- Physique — Diallo    → écart=|12-17|=5 (=5, pas >5) → moyenne=14.5
  (5,  1, 3, 3, 11.0),   -- Histoire (unique)    → 11
  (6,  1, 4, 4,  9.5);   -- Français (unique)    → 9.5

-- Candidat 2: Badji Mariama
INSERT INTO "Notes" ("idNote", "idCandidat", "idMatiere", "idProf", note) OVERRIDING SYSTEM VALUE VALUES
  (7,  2, 1, 1,  8.0),   -- Maths — Diallo      \
  (8,  2, 1, 2, 15.0),   -- Maths — Sarr         → écart=|8-15|=7 (>5) → moyenne=11.5
  (9,  2, 2, 2, 13.0),   -- Physique (unique)     → 13
  (10, 2, 3, 3, 10.0),   -- Histoire (unique)     → 10
  (11, 2, 4, 4, 14.0);   -- Français (unique)     → 14

-- Candidat 3: Cissé Oumar
INSERT INTO "Notes" ("idNote", "idCandidat", "idMatiere", "idProf", note) OVERRIDING SYSTEM VALUE VALUES
  (12, 3, 1, 1, 18.0),   -- Maths (unique)        → 18
  (13, 3, 2, 2, 16.0),   -- Physique — Sarr       \
  (14, 3, 2, 1,  9.0),   -- Physique — Diallo      → écart=|16-9|=7 (>4) → NOTE LA PLUS BASSE=9
  (15, 3, 3, 3, 12.0),   -- Histoire (unique)      → 12
  (16, 3, 4, 4, 15.0);   -- Français (unique)      → 15

-- Candidat 4: Diop Khady (Lettres — pas de maths/physique)
INSERT INTO "Notes" ("idNote", "idCandidat", "idMatiere", "idProf", note) OVERRIDING SYSTEM VALUE VALUES
  (17, 4, 5, 4, 16.0),   -- Français L (unique)   → 16
  (18, 4, 6, 3, 13.0);   -- Philo (unique)         → 13
SELECT setval(pg_get_serial_sequence('"Notes"', 'idNote'), 18);


-- ============================================================
-- RÉSULTATS ATTENDUS (Notes Finales)
-- ============================================================
--
--   Candidat       | Matière    | Notes      | Écart | Paramètre          | Note Finale
--   -------------- | ---------- | ---------- | ----- | ------------------ | -----------
--   Ndiaye Abou    | Maths      | 14, 16     | 2     | écart>5 → non      | 15.0 (moy)
--   Ndiaye Abou    | Physique   | 12, 17     | 5     | écart>4 → non (=5) | 14.5 (moy)
--   Ndiaye Abou    | Histoire   | 11         | —     | pas d'écart        | 11.0
--   Ndiaye Abou    | Français   | 9.5        | —     | pas d'écart        | 9.5
--   Badji Mariama  | Maths      | 8, 15      | 7     | écart>5 → OUI      | 11.5 (moy)
--   Badji Mariama  | Physique   | 13         | —     | pas d'écart        | 13.0
--   Cissé Oumar    | Maths      | 18         | —     | pas d'écart        | 18.0
--   Cissé Oumar    | Physique   | 16, 9      | 7     | écart>4 → OUI      | 9.0 (min!)
--   Diop Khady     | Français L | 16         | —     | pas d'écart        | 16.0
--   Diop Khady     | Philo      | 13         | —     | pas d'écart        | 13.0
-- ============================================================
