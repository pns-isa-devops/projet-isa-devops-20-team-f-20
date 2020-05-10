FROM node:lts-alpine

# définit le dossier 'app' comme dossier de travail
# WORKDIR /vuejs_client # Doesnt work with jenkins :/
 
# copie les fichiers et dossiers du projet dans le dossier de travail (par exemple : le dossier 'app')
COPY . .

#RUN npm i

EXPOSE 8844

# construit l'app pour la production en la minifiant
ENTRYPOINT ["npm", "run", "serve"]

#HEALTHCHECK --interval=1m --timeout=30s --retries=5 \
#  CMD curl -f http://localhost:8844/ || exit 1
