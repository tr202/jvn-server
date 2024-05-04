#!/bin/sh
# cacerts.sh
/usr/bin/openssl s_client -showcerts -connect rusgardserver:443 </dev/null 2>/dev/null | /usr/bin/openssl x509 -outform PEM > /tmp/rusgardserver.pem
keytool -import -trustcacerts -file /tmp/rusgardserver.pem -alias rusgardserver -keystore /etc/ssl/certs/java/cacerts
rm /tmp/rusgardserver.pem
