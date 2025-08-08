-- Script d'initialisation des données pour la base de données produit

-- Insérer les rôles
INSERT INTO roles (nom) VALUES ('CLIENT') ON DUPLICATE KEY UPDATE nom = nom;
INSERT INTO roles (nom) VALUES ('MANAGER') ON DUPLICATE KEY UPDATE nom = nom;

-- Insérer les utilisateurs (mot de passe encodé avec BCrypt pour "password")
INSERT INTO utilisateurs (username, password, nom, prenom, email) VALUES 
('client1', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Dupont', 'Jean', 'client1@email.com'),
('client2', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Martin', 'Marie', 'client2@email.com'),
('client3', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Bernard', 'Pierre', 'client3@email.com'),
('client4', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Petit', 'Sophie', 'client4@email.com'),
('manager', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Durand', 'Paul', 'manager@email.com')
ON DUPLICATE KEY UPDATE username = username;

-- Récupérer les IDs des rôles
SET @role_client_id = (SELECT id FROM roles WHERE nom = 'CLIENT');
SET @role_manager_id = (SELECT id FROM roles WHERE nom = 'MANAGER');

-- Récupérer les IDs des utilisateurs
SET @client1_id = (SELECT id FROM utilisateurs WHERE username = 'client1');
SET @client2_id = (SELECT id FROM utilisateurs WHERE username = 'client2');
SET @client3_id = (SELECT id FROM utilisateurs WHERE username = 'client3');
SET @client4_id = (SELECT id FROM utilisateurs WHERE username = 'client4');
SET @manager_id = (SELECT id FROM utilisateurs WHERE username = 'manager');

-- Assigner les rôles aux utilisateurs
INSERT INTO utilisateur_roles (utilisateur_id, role_id) VALUES 
(@client1_id, @role_client_id),
(@client2_id, @role_client_id),
(@client3_id, @role_client_id),
(@client4_id, @role_client_id),
(@manager_id, @role_manager_id)
ON DUPLICATE KEY UPDATE utilisateur_id = utilisateur_id; 