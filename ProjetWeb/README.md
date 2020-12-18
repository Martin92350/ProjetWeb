# Sondage & Vote 
 
## Présentation du Projet
Le site permet de :
* Se connecter ou de créer un utilisateur
* Lister, créer et supprimer des sondages
* Voter sur des propositions de dates et de lieux sur différents sondages


### Technologies
* IDE : Intelliji
* MysQL Workbench : Interface graphique
* MySQL Command Line : Invite de commande 
* Spring Boot : Backend
* Thymeleaf : Frontend
 
### Prérequis
1) Créer une base de donées :
CREATE TABLE bdd;

2) Se connecter à la base de donnée :
use bdd;

3) Générer les tables de la base de données avec le script.sql
source .../ProjetWeb/src/main/resources/templates/script.sql

4) Lier le site avec la base de donées en éditant le fichier ProjetWeb/src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:3306/[NOM_BDD]?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC
spring.datasource.username=[USERNAME_BDD]
spring.datasource.password=[MOT_DE_PASSE_BDD]

5) Sur un terminal lancer le backend:
./mvnw spring-boot:run

6) Sur un navigateur de recherche : 
localhost:8080/login
 
### Consignes respectées
* API LOGIN : Connexion et création d'un utilisateur
* API SONDAGE : Création, listage et suppression de sondages
* API VOTE : L'utilisateur peut voter sur les dates et lieux de chaque sondage
 
## Aperçu
