#!/bin/bash
echo "------------------------------------------"
echo "Jenkins external server"
echo "URL : http://dev.richard-peres.com:8181/"
echo "Username : admin"
echo "Password : Drone_Delivery69"
echo "Commentaire : Nous avons créé une image Jenkins spéciale pour ce projet étant donné que nous devions installer plusieurs outils (comme Mono, NPM et Cypress) sur l'host pour pouvoir faire fonctionner nos builds plans correctement."
echo "------------------------------------------"
echo ""
echo "------------------------------------------"
echo "Artifactory external server"
echo "URL : http://dev.richard-peres.com:8082"
echo "Username : admin"
echo "Password : Drone_Delivery69"
echo "Commentaire : Les artéfacts sont déployés dans 'libs-snapshot-local' lors d'un mvn deploy (effectué quasi exclusivement par le build plan Jenkins 'Livrair_backend_test'"
echo "------------------------------------------"
echo ""
echo "------------------------------------------"
echo "Portainer external server"
echo "URL : http://dev.richard-peres.com:9000"
echo "Username : TeamF"
echo "Password : Drone_Delivery69"
echo "Commentaire : Nous avons ajouter Portainer afin de pouvoir facilement avoir accés aux containers (log et console principalement) depuis l'extérieur (impossible de SSH sur le serveur étant donné qu'il s'agit du PC 'personnel' de Richard)"
echo "------------------------------------------"
echo ""
echo "------------------------------------------"
echo "SonarQube external server"
echo "URL : http://dev.richard-peres.com:9001"
echo "Username : admin"
echo "Password : admin"
echo "Commentaire : Nous avons créer un server SonarQube qui expose le résultat issu de l'analyse de SonarScanner dans le build plan Jenkins 'SonarQube_Analysis' afin d'avoir un retour sur la qulité de notre code en temps réel."
echo "A noter que le coverage des tests arquillian n'est pas disponible car il necéssite de configurer l'extension Jacoco."
echo "Nous av(i)ons prévu de configurer le coverage et de modifier le flow Jenkins pour effectuer une analyse Sonar AVANT de deploy et de ne deploy que si un certains seuil (Quality Gate) est respecté."
echo "------------------------------------------"