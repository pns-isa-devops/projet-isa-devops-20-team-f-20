<template>
    <v-row class="fill-height">
        <v-col class="mb-4 controls" lg3 md8 sm12>
            <v-sheet height="64">

                <v-toolbar color="white" flat>

                    <v-btn @click="setToday" class="mr-4" color="grey darken-2" outlined>
                        Today
                    </v-btn>

                    <v-spacer></v-spacer>

                    <v-btn @click="prev" color="grey darken-2" fab small text>
                        <v-icon small>mdi-chevron-left</v-icon>
                    </v-btn>

                    <v-spacer></v-spacer>

                    <v-toolbar-title>{{ title }}</v-toolbar-title>

                    <v-spacer></v-spacer>

                    <v-btn @click="next" color="grey darken-2" fab small text>
                        <v-icon small>mdi-chevron-right</v-icon>
                    </v-btn>

                    <v-spacer></v-spacer>

                    <v-menu bottom right>
                        <template v-slot:activator="{ on }">
                            <v-btn color="grey darken-2" outlined v-on="on">
                                <span>{{ typeToLabel[type] }}</span>
                                <v-icon right>mdi-menu-down</v-icon>
                            </v-btn>
                        </template>
                        <v-list>
                            <v-list-item @click="type = 'day'">
                                <v-list-item-title>Day</v-list-item-title>
                            </v-list-item>
                            <v-list-item @click="type = 'week'">
                                <v-list-item-title>Week</v-list-item-title>
                            </v-list-item>
                            <v-list-item @click="type = 'month'">
                                <v-list-item-title>Month</v-list-item-title>
                            </v-list-item>
                            <v-list-item @click="type = '4day'">
                                <v-list-item-title>4 days</v-list-item-title>
                            </v-list-item>
                        </v-list>
                    </v-menu>

                </v-toolbar>
            </v-sheet>
            <v-sheet height="500">
                <v-calendar :event-color="getEventColor" :events="events" :now="today" :type="type" @change="updateRange"
                            @click:date="viewDay" @click:event="showEvent" @click:more="viewDay" color="primary"
                            ref="calendar"
                            v-model="focus">
                </v-calendar>
                <v-menu :activator="selectedElement" :close-on-content-click="false" offset-x v-model="selectedOpen">
                    <v-card color="grey lighten-4" flat min-width="350px">
                        <v-toolbar :color="selectedEvent.color" dark>
                            <v-btn icon>
                                <v-icon>mdi-pencil</v-icon>
                            </v-btn>
                            <v-toolbar-title v-html="selectedEvent.name"></v-toolbar-title>
                            <v-spacer></v-spacer>
                            <v-btn icon>
                                <v-icon>mdi-heart</v-icon>
                            </v-btn>
                            <v-btn icon>
                                <v-icon>mdi-dots-vertical</v-icon>
                            </v-btn>
                        </v-toolbar>
                        <v-card-text>
                            <span v-html="selectedEvent.details"></span>
                        </v-card-text>
                        <v-card-actions>
                            <v-btn @click="selectedOpen = false" color="secondary" text>
                                Cancel
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </v-menu>
            </v-sheet>
        </v-col>
    </v-row>

</template>

<script>
    export default {
        data: () => ({
            xmlhttp: new XMLHttpRequest(),
            focus: '',
            type: 'month',
            typeToLabel: {
                month: 'Month',
                week: 'Week',
                day: 'Day',
                '4day': '4 Days',
            },
            start: null,
            end: null,
            selectedEvent: {},
            selectedElement: null,
            selectedOpen: false,
            events: [],
            colors: ['blue', 'indigo', 'deep-purple', 'cyan', 'green', 'orange', 'grey darken-1'],
            names: ['Meeting', 'Holiday', 'PTO', 'Travel', 'Event', 'Birthday', 'Conference', 'Party'],
        }),
        computed: {
            today() {
                var currentDate = new Date();

                var date = currentDate.getDate();
                date = (date < 10) ? '0' + date : date
                var month = currentDate.getMonth() + 1; //Be careful! January is 0 not 1
                month = (month < 10) ? '0' + month : month
                var year = currentDate.getFullYear();

                return year + "-" + month + "-" + date;
            },
            title() {
                const {
                    start,
                    end
                } = this
                if (!start || !end) {
                    return ''
                }

                const startMonth = this.monthFormatter(start)
                const endMonth = this.monthFormatter(end)
                const suffixMonth = startMonth === endMonth ? '' : endMonth

                const startYear = start.year
                const endYear = end.year
                const suffixYear = startYear === endYear ? '' : endYear

                const startDay = start.day + this.nth(start.day)
                const endDay = end.day + this.nth(end.day)

                switch (this.type) {
                    case 'month':
                        return `${startMonth} ${startYear}`
                    case 'week':
                    case '4day':
                        return `${startDay} ${startMonth} ${startYear} - ${suffixMonth} ${endDay} ${suffixYear}`
                    case 'day':
                        return `${startDay} ${startMonth} ${startYear}`
                }
                return ''
            },
            monthFormatter() {
                return this.$refs.calendar.getFormatter({
                    timeZone: 'UTC',
                    month: 'long',
                })
            },
        },
        mounted() {
            this.$refs.calendar.checkChange()
        },
        methods: {
            viewDay({
                        date
                    }) {
                this.focus = date
                this.type = 'day'
            },
            getEventColor(event) {
                return event.color
            },
            setToday() {
                this.focus = this.today
            },
            prev() {
                this.$refs.calendar.prev()
            },
            next() {
                this.$refs.calendar.next()
            },
            showEvent({
                          nativeEvent,
                          event
                      }) {
                const open = () => {
                    this.selectedEvent = event
                    this.selectedElement = nativeEvent.target
                    setTimeout(() => this.selectedOpen = true, 10)
                }

                if (this.selectedOpen) {
                    this.selectedOpen = false
                    setTimeout(open, 10)
                } else {
                    open()
                }

                nativeEvent.stopPropagation()
            },
            delay(ms) {
                return new Promise(resolve => setTimeout(resolve, ms));
            },
            updateRange({
                            start,
                            end
                        }) {

                (async () => {
                    await this.delay(1000);
                    this.xmlhttp.open('POST', 'http://' + process.env.VUE_APP_BACKEND + ':8080/logistic/webservices/LogisticWS?wsdl', true);

                    // build SOAP request
                    var sr =
                        `<Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
              <Body>
                  <getPlanning xmlns="http://www.polytech.unice.fr/si/4a/isa/drone-delivery/logistic"/>
              </Body>
          </Envelope>`

                    let context = this

                    this.xmlhttp.onreadystatechange = function () {
                        if (context.xmlhttp.readyState == 4) {
                            if (context.xmlhttp.status == 200) {

                                let respXML = context.xmlhttp.responseXML;
                                // let respJSON = require('xml-js').xml2json(context.xmlhttp.response, {
                                //   compact: true,
                                //   spaces: 4
                                // });
                                //console.log(respXML)

                                let slots = respXML.getElementsByTagName('slots')

                                context.events = []
                                for (let slot of slots) {
                                    console.log('slot')
                                    console.log(slot)
                                    let isAvailable = respXML.getElementsByTagName('isAvailable')[0]
                                    if (isAvailable == 'false') {
                                        context.events.push({
                                            name: 'Premier test',
                                            start: context.formatDate(new Date(2020, 3, 1, 12, 0), true),
                                            end: context.formatDate(new Date(2020, 3, 1, 15, 0), true),
                                            color: context.colors[context.rnd(0, context.colors.length - 1)],
                                        })
                                    }
                                }

                                context.start = start
                                context.end = end

                            }
                        }
                    }
                    // Send the POST request
                    this.xmlhttp.setRequestHeader('Content-Type', 'text/xml');
                    console.log(sr)
                    //this.xmlhttp.send(sr);
                })();
            },
            nth(d) {
                return d > 3 && d < 21 ?
                    'th' : ['th', 'st', 'nd', 'rd', 'th', 'th', 'th', 'th', 'th', 'th'][d % 10]
            },
            rnd(a, b) {
                return Math.floor((b - a + 1) * Math.random()) + a
            },
            formatDate(a, withTime) {
                return withTime ?
                    `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()} ${a.getHours()}:${a.getMinutes()}` :
                    `${a.getFullYear()}-${a.getMonth() + 1}-${a.getDate()}`
            },
        },
    }
</script>

<style scoped>

</style>
