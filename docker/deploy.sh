#! /bin/bash

eval "docker cp ../juggler/target/juggler.war lotter-juggler:/opt/jboss/wildfly/standalone/deployments/"
