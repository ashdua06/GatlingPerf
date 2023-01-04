FROM core.harbor.cloudapps.okdcloud.india.airtel.itm/library/openjdk:8-jdk-alpine
FROM maven:3.5-jdk-8-alpine
WORKDIR /AIRTEL_PERF
COPY src /AIRTEL_PERF/src
COPY profiles /AIRTEL_PERF/profiles
COPY pom.xml /AIRTEL_PERF
#ENTRYPOINT ["mvn","-Dhttp.proxyHost=appproxy.airtel.com -Dhttp.proxyPort=4145 -Dhttp.nonProxyHosts=nexus.airtelworld.in -Dhttps.proxyHost=appproxy.airtel.com -Dhttps.proxyPort=4145 -Dhttps.nonProxyHosts=nexus.airtelworld.in clean gatling:test -Dgatling.simulationClass=com.airtel.performanceTests.shop.shopTestSimulation"]
ENTRYPOINT ["mvn","-Dhttp.proxyHost=appproxy.airtel.com","-Dhttp.proxyPort=4145","-Dhttp.nonProxyHosts=nexus.airtelworld.in","-Dhttps.proxyHost=appproxy.airtel.com","-Dhttps.proxyPort=4145","-Dhttps.nonProxyHosts=nexus.airtelworld.in","clean","gatling:test","-Dgatling.simulationClass=com.airtel.performanceTests.shop.shopTestSimulation"]