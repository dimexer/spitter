/home/dhubenov/dev/apache-tomcat-7.0.47/bin/catalina.sh stop
mvn clean install
rm -rf /home/dhubenov/dev/apache-tomcat-7.0.47/webapps/spitter-web
rm -rf /home/dhubenov/dev/apache-tomcat-7.0.47/webapps/spitter-web.war
cp target/spitter-web.war /home/dhubenov/dev/apache-tomcat-7.0.47/webapps/
/home/dhubenov/dev/apache-tomcat-7.0.47/bin/catalina.sh start
