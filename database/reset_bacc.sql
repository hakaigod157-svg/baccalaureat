-- ================================================================
-- SCRIPT DE RÉINITIALISATION COMPLÈTE — Application Baccalauréat
-- Base: baccalaureat_db | PostgreSQL
-- ================================================================
-- USAGE:
--   Via pgAdmin: ouvrez ce fichier et exécutez-le (F5)
-- ================================================================

BEGIN;

-- Désactiver les contraintes FK
SET session_replication_role = 'replica';

-- Vider toutes les tables avec reset des séquences
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
