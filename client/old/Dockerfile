FROM node:lts-alpine

# définit le dossier 'app' comme dossier de travail
WORKDIR /vuejs_client

# copie les fichiers et dossiers du projet dans le dossier de travail (par exemple : le dossier 'app')
COPY . .

EXPOSE 8080

# construit l'app pour la production en la minifiant
ENTRYPOINT ["npm", "run", "serve"]
