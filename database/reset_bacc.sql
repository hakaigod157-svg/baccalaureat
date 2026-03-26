BEGIN;

SET session_replication_role = 'replica';

TRUNCATE TABLE
    "Notes",
    "Parametres",
    "Matieres",
    "Candidat",
    "Ecole",
    "serie",
    "professeurs",
    "Operateur",
    "Resolution"
RESTART IDENTITY CASCADE;

-- Réactiver les contraintes FK
SET session_replication_role = 'origin';

COMMIT;

SELECT '=== Toutes les tables ont été vidées ===' AS statut;
