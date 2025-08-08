-- Script pour vérifier les utilisateurs et leurs rôles

-- Vérifier les rôles
SELECT * FROM roles;

-- Vérifier les utilisateurs
SELECT * FROM utilisateurs;

-- Vérifier les associations utilisateur-rôles
SELECT u.username, r.nom as role_name 
FROM utilisateurs u 
JOIN utilisateur_roles ur ON u.id = ur.utilisateur_id 
JOIN roles r ON ur.role_id = r.id;

-- Vérifier les utilisateurs sans rôles
SELECT u.username 
FROM utilisateurs u 
LEFT JOIN utilisateur_roles ur ON u.id = ur.utilisateur_id 
WHERE ur.utilisateur_id IS NULL; 