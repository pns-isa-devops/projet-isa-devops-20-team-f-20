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
- 2.b) 

## Alternatives

Il est également possible de déroulé ce scénario via le tests cypress dédié en lancant la commande suivante :
(Nécessite cypress d'installé, et une BD vierge/wipé avec ```./wipe_hsqldb.sh localhost```, à lancé à la racine du projet)
```npm run --prefix client/ cy:run -- --spec "tests/e2e/specs/full_test.js"```

Enfin il est également possible de joué ce même scénario en 'loadant' une DB déjà préremplie de packages et drones en utilisant le script ```./load_hsqldb.sh localhost``` à la racine du projet.
