FROM jboss/wildfly:16.0.0.Final

MAINTAINER Jakub Barski

RUN /opt/jboss/wildfly/bin/add-user.sh admin init5# --silent

USER root
RUN rm /etc/localtime
RUN ln -s /usr/share/zoneinfo/Europe/Warsaw /etc/localtime
USER jboss

RUN mkdir $JBOSS_HOME/database
COPY --chown=jboss:0 ./standalone.xml $JBOSS_HOME/standalone/configuration/
COPY --chown=jboss:0 ./database $JBOSS_HOME/database/
COPY --chown=jboss:0 ./modules $JBOSS_HOME/modules/system/layers/base

EXPOSE 8080
EXPOSE 8787
EXPOSE 9990

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0", "--debug", "*:8787"]