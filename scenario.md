# Scénario complet bout en bout
Équipe F

## Description du scénario

Il s'agit d'un scénario typique de Drone Delivery qui utilise (quasi) toutes nos fonctrionnalités implémentées pour ce rendu du MVP 2.0.
Il passe de ce fait entre tous les composants du backend (sauf Biller, pas implémenter dans le front) et utilise également le service externe de récupération de packages.

Il consiste à :

- 1) Récupérer la liste des packages prévues aujourd'hui auprès du service externe
- 2) "Scanner" un package afin de le rendre disponible à la livraison
- 3) Ajouter un drone au garage
- 4) Créé une delivery avec le package précédemment scanné
- 5) Lancer l'étape de livraison de la delivery

## Étapes à reproduire

- 1.a) Ouvrir le frontend : [http://localhost:8844/home]
- 1.b) Aller sur la page 'Service Client' du frontend (via l'url ou le bouton dédié sur la page) : [http://localhost:8844/client]
- 1.c) Constater qu'il n'y a aucun package enregistrer ('Pacakge List' à droite), même en actualisant
- 1.d) Cliquer sur le bouton 'Récupérer les pacakges prévus' (sticky bouton rond en bas à gauche)
- 1.e) Actualiser la liste des packages et constater qu'il y a 10 packages prévus
- 2.a) Aller sur la page Manutentionnaire [http://localhost:8844/manu]
- 2.b) Entrer l'id du package voulu (par exemple 0) dans 'Change package status' et séléectionné l'état 'WAITING'
- 2.c) Une popup de validation doit se lancer et le package doit avoir changer de statut une fois la liste actualiser (boutons en forme de flèches dans 'Package List')
- 3.a) Aller sur la page Garage [http://localhost:8844/garage]
- 3.b) Constater qu'il n'y a pas de drone, même en actualisant
- 3.c) Ajouter un drone avec un ID (par exemple 1) en remplissant le formulaire 'Add Drone' sur la droite
- 3.d) Une popup de validation doit se lancer et le package doit avoir changer de statut une fois la liste actualiser (boutons en forme de flèches dans 'Drone List')
- 4.a) Retourner sur la page 'Service Client' : [http://localhost:8844/client]
- 4.b) Remplir le formulaire 'Add Delivery' en haut à droite avec l'id du précédent package à WAITING en rentrant une date souhaité (ajourd'hui par défaut) et une heure comprise entre 8h01 et 19h59 (11h05 par exemple)
- 4.c) Actualisé la liste de delivery et de packages et constaté que la delivery est presente et le statut du package a changé

## Alternatives

Il est également possible de déroulé ce scénario via le tests cypress dédié en lancant la commande suivante :
(Nécessite cypress d'installé, et une BD vierge/wipé avec ```./wipe_hsqldb.sh localhost```, à lancé à la racine du projet)
```npm run --prefix client/ cy:run -- --spec "tests/e2e/specs/full_test.js"```

Enfin il est également possible de joué ce même scénario en 'loadant' une DB déjà préremplie de packages et drones en utilisant le script ```./load_hsqldb.sh localhost``` à la racine du projet.
