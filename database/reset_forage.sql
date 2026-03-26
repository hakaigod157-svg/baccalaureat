BEGIN;

SET session_replication_role = 'replica';

TRUNCATE TABLE
    "client",
    "Lieu",
    "Demande",
    "TypeDevis",
    "Statut",
    "Devis",
    "DetailDevis",
    "DemandeStatut",
    "PrixTotalDevis"
RESTART IDENTITY CASCADE;

-- Réactiver les contraintes FK
SET session_replication_role = 'origin';

COMMIT;

SELECT '=== Toutes les tables ont été vidées ===' AS statut;
