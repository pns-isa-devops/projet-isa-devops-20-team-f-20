# Minimal Viable Product

## Reception colis :
Nous ne recevons pour le moment que des colis d'un seul fournisseur (type UPS, etc..).
Le système connait donc un code unique permettant de faire une requete par jour pour recuperer les datas (JSON) des colis a receptionner, sur l'API du fournisseur.
Le systère possède egalement un Parser qui permet de creer les objets 'Package' dans le système.
Eventuellement une verification du manutentionnaire pour les colis au moment de l'arrivée, changement de statut ?

## Prise de commande client :
Fournir les plages horaires en fonction des drones disponibles, calculé sur le temps de vol d'un trajet + temps de charge (voir paragraphe associe)
A la validation d'un horaire, on crée un objet 'Delivery' avec le drone et le colis associé, et le statut 'ready'.
Le manutentionnaire sera alors notifier des livraisons prevus dans les 30 prochaines minutes

## Chargement des colis, lancement, livraison :
Le manutentionnaire possède un ecran avec les prochaines livraisons. On concidère qu'une livraion dure toujours 1h, on calcul donc l'heure de part et on retire 30min pour l'affichage de marcel. 
Quand marcel charge un drone, il est alors concidere comme 'en livraion', et est place sur la rampe de lancement (il decolera au bon moment).

## Retour, charge, revision:
A chaque retour de drone, si le colis a été livré, on passe le statut de la livraion a 'validé', et on notifie Gisèle de la commande effectué (voir paragraphe associe). Egalement, chaque drone qui revient est forcé a etre placé en rechargement. Comme une livraion dure 1h, tous les drones chargeront en le meme temps. Le drone peut également etre apporté en révision s'il a depassé les 20h de vols, et la revison durera 1h fixe.

## Facturation :
Chaque forunisseur a un objet 'Factures' qui lui ait associé, et qui recense les livraisons qui lui sont associés sur la journée. Quand un colis est livré, Gisèle recoit la livraison en question, et prend connaissance du fourniseur, elle va ensuite soit créé l'objet facture du fournisseur si c'est sa premiere livraison de la journée, soit ajouter la livraion en question sur sa facture. 
A la fin de la journée, elle va finalement renseigner les dernières informations de la facture, et l'envoyer au fournisseur.

## Retour client, statistiques : 
Un client peut appeler Clissandre pour donner un retour client. Ce dernier donne son numero de livraion, et une note/commentaire voulu. Le systeme stocke les 'Reviews'. 
Le boss peut acceder en temps reel à l'etat de sa flotte, et à l'integralité des retours clients.






