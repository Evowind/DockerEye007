# DockerEye007

Une plateforme moderne de gestion et d'analyse de speedruns pour GoldenEye 007 et Perfect Dark, construite avec une architecture microservices.

## À propos du projet

DockerEye_007 est une application cloud-native qui permet aux speedrunners de soumettre, vérifier et analyser leurs performances, Inspiré par TheElite.

## Technologies

- **Architecture**: Microservices (REST/gRPC)
- **Backend**: Spring Boot/Node.js
- **Containerisation**: Docker
- **Orchestration**: Kubernetes
- **Base de données**: PostgreSQL, MongoDB
- **Frontend** (optionnel): React
- **CI/CD**: GitHub Actions
- **Service Mesh**: Istio

## Fonctionnalités

- Authentification et gestion des utilisateurs
- Soumission et vérification des speedruns
- Leaderboards par niveau et catégorie
- Analyse statistique des performances
- API Gateway pour l'accès unifié
- Stockage sécurisé des données

## Microservices

- **auth-service**: Gestion de l'authentification et des utilisateurs
- **run-service**: Soumission et récupération des speedruns
- **stats-service**: Analyse et génération des statistiques
- **verification-service**: Validation des soumissions
- **api-gateway**: Point d'entrée unifié pour tous les services

## Contributeurs

[Evowind]
[Kemory]
