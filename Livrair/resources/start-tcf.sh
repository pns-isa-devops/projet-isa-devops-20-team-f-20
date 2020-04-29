#!/bin/bash

# step #1: configure the bank.propoerties file
mkdir -p ./WEB-INF/classes/
touch ./WEB-INF/classes/supplier.properties
echo "supplierHostName=$supplier_host" >> ./WEB-INF/classes/supplier.properties
echo "supplierPortNumber=$supplier_port" >> ./WEB-INF/classes/supplier.properties

# step #2: update the webapp to load the right properties
jar uvf ./webapps/logistic.war ./WEB-INF/classes/supplier.properties

# step #3: start the TomEE engine
catalina.sh run
