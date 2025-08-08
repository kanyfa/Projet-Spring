# API Gestion des Produits

Cette application Spring Boot permet de gérer des produits avec authentification JWT et autorisation basée sur les rôles.

## Fonctionnalités

### Part 1 - Spring Boot
- ✅ Projet Spring Boot avec Spring Security et Lombok
- ✅ Intégration de Swagger/OpenAPI
- ✅ Configuration des environnements dev et prod
- ✅ Configuration de la base de données MySQL
- ✅ Variable d'environnement BOUTIQUE_ENV
- ✅ Entité Produit avec les champs requis
- ✅ Contrôleur CRUD pour les produits

### Part 2 - Tests API
- ✅ Tests unitaires avec JUnit
- ✅ Tests d'intégration
- ✅ Tests avec authentification

### Part 3 - Spring Security
- ✅ Entités Utilisateur et Role
- ✅ Initialisation automatique des rôles et utilisateurs
- ✅ Configuration des autorisations par rôle
- ✅ Authentification JWT
- ✅ Tests avec authentification

## Configuration

### Variables d'environnement
- `BOUTIQUE_ENV`: Détermine l'environnement (dev/prod)

### Base de données
- **Développement**: MySQL sur localhost:3306/boutique_dev
- **Production**: MySQL sur localhost:3306/boutique_prod
- **Tests**: H2 en mémoire

## Utilisateurs créés automatiquement

### Clients (4 utilisateurs)
- `client1` / `password` - Dupont Jean
- `client2` / `password` - Martin Marie  
- `client3` / `password` - Bernard Pierre
- `client4` / `password` - Petit Sophie

### Manager (1 utilisateur)
- `manager` / `password` - Durand Paul

## Autorisations

### Accès public
- `GET /api/produits` - Liste des produits

### Accès Client et Manager
- `GET /api/produits/{id}` - Détails d'un produit
- `POST /api/produits` - Créer un produit

### Accès Manager uniquement
- `PUT /api/produits/{id}` - Modifier un produit
- `DELETE /api/produits/{id}` - Supprimer un produit

## API Endpoints

### Authentification
- `POST /api/auth/login` - Se connecter

### Produits
- `GET /api/produits` - Liste des produits
- `GET /api/produits/{id}` - Détails d'un produit
- `POST /api/produits` - Créer un produit
- `PUT /api/produits/{id}` - Modifier un produit
- `DELETE /api/produits/{id}` - Supprimer un produit

## Documentation Swagger

- **Développement**: http://localhost:8080/swagger-ui.html
- **Production**: Swagger désactivé

## Lancement

### Développement
```bash
# Avec variable d'environnement
export BOUTIQUE_ENV=dev
mvn spring-boot:run

# Ou directement
mvn spring-boot:run -Dspring.profiles.active=dev
```

### Production
```bash
export BOUTIQUE_ENV=prod
mvn spring-boot:run
```

### Tests
```bash
mvn test
```

## Exemple d'utilisation

### 1. Authentification
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"client1","password":"password"}'
```

### 2. Créer un produit (avec token)
```bash
curl -X POST http://localhost:8080/api/produits \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{"nom":"Laptop","description":"Ordinateur portable","prixUnitaire":999.99}'
```

### 3. Lister les produits
```bash
curl -X GET http://localhost:8080/api/produits
```

## Structure du projet

```
src/
├── main/
│   ├── java/sn/isep/edu/diamniadio/produit/Produit/
│   │   ├── config/          # Configurations
│   │   ├── controller/      # Contrôleurs REST
│   │   ├── dto/            # Objets de transfert
│   │   ├── entity/         # Entités JPA
│   │   ├── repository/     # Repositories
│   │   ├── security/       # Filtres de sécurité
│   │   └── service/        # Services métier
│   └── resources/
│       ├── application.properties
│       ├── application-dev.properties
│       └── application-prod.properties
└── test/
    └── java/sn/isep/edu/diamniadio/produit/Produit/
        ├── controller/      # Tests des contrôleurs
        └── integration/     # Tests d'intégration
``` 