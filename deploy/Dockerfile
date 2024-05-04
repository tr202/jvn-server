FROM jetty:9.4.50-jdk8
#COPY bin/server.war /var/lib/jetty/webapps/server.war
#RUN cd ~
#RUN apt add openssl
#RUN openssl s_client -connect <rusgardserver> < /dev/null | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > public.crt
#RUN $JAVA_HOME/bin/keytool -import -alias Alias -keystore $JAVA_HOME/lib/security/cacerts -file RUSGARDSERVER -storepass 9244Lassk -noprompt
RUN chmod -R 777 /home

