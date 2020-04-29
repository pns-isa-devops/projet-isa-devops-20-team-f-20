<template>
    <v-container fluid>

        <v-app-bar app color="green darken-2" dark>
            <div @click="goToHome()" class="d-flex align-center">
                <v-img alt="Livrair'" class="shrink mr-2" contain src="../assets/drone_green.png"
                       transition="scale-transition"
                       width="40"/>
            </div>
            <v-toolbar-title @click="goToHome()" class="headline text-upper">
                <span class="font-weight-light">LIVRAIR</span>
            </v-toolbar-title>

            <v-spacer></v-spacer>

            <v-btn href="https://github.com/pns-isa-devops/projet-isa-devops-20-team-f-20" target="_blank" text>
                <span class="mr-2">Git Repo</span>
                <v-icon>mdi-open-in-new</v-icon>
            </v-btn>
        </v-app-bar>

        <v-row>
            <v-col cols="12">
                <v-row align="center" justify="center">
                    <v-card @click="goToClient()" class="pa-1 mx-auto" data-cy="client" max-width="250" outlined tile>

                        <v-img class="white--text align-end" height="250px" src="../assets/drone_cam.png"
                               transition="scale-transition"/>

                        <v-card-subtitle class="pb-0">Le service client</v-card-subtitle>

                        <v-card-text class="text--primary">
                            <div>Whitsunday Island, Whitsunday Islands</div>
                        </v-card-text>

                        <v-card-actions>
                            <v-btn color="primary" text>
                                Selectionner
                            </v-btn>
                        </v-card-actions>

                    </v-card>
                    <v-card @click="goToManu()" class="pa-1 mx-auto" data-cy="manu" max-width="250" outlined tile>

                        <v-img class="white--text align-end" height="250px" src="../assets/drone_package.png"
                               transition="scale-transition"/>

                        <v-card-subtitle class="pb-0">Le manutentionnaire</v-card-subtitle>

                        <v-card-text class="text--primary">
                            <div>Whitsunday Island, Whitsunday Islands</div>
                        </v-card-text>

                        <v-card-actions>
                            <v-btn color="primary" text>
                                Selectionner
                            </v-btn>
                        </v-card-actions>

                    </v-card>
                    <v-card class="pa-1 mx-auto" max-width="250" outlined tile>

                        <v-img class="white--text align-end" height="250px" src="../assets/drone_empty.png"
                               transition="scale-transition"/>

                        <v-card-subtitle class="pb-0">Le gestionnaire</v-card-subtitle>

                        <v-card-text class="text--primary">
                            <div>Whitsunday Island, Whitsunday Islands</div>
                        </v-card-text>

                        <v-card-actions>
                            <v-btn color="primary" text>
                                Selectionner
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                    <v-card class="pa-1 mx-auto" max-width="250" outlined tile>

                        <v-img class="white--text align-end" height="250px" src="../assets/drone_wireless.png"
                               transition="scale-transition"/>

                        <v-card-subtitle class="pb-0">Le chargeur</v-card-subtitle>

                        <v-card-text class="text--primary">
                            <div>Whitsunday Island, Whitsunday Islands</div>
                        </v-card-text>

                        <v-card-actions>
                            <v-btn color="primary" text>
                                Selectionner
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                    <v-card class="pa-1 mx-auto" max-width="250" outlined tile>

                        <v-img class="white--text align-end" height="250px" src="../assets/drone_fix.png"
                               transition="scale-transition"/>

                        <v-card-subtitle class="pb-0">Le mecanicien</v-card-subtitle>

                        <v-card-text class="text--primary">
                            <div>Whitsunday Island, Whitsunday Islands</div>
                        </v-card-text>

                        <v-card-actions>
                            <v-btn color="primary" text>
                                Selectionner
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-row>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>
    export default {
        name: 'Home',
        props: {
            msg: String
        },
        methods: {
            goToClient() {
                this.$router.push('/client')
            },
            goToManu() {
                this.$router.push('/manu')
            },
            goToHome() {
                this.$router.push('/')
            }
        }
    }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
