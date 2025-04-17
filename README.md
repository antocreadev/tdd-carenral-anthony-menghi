./mvnw clean verify sonar:sonar \ -Dsonar.projectKey=carental \
 -Dsonar.host.url=http://localhost:9000 \
 -Dsonar.login=TOKEN \
 -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
