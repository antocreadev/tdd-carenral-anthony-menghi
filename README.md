<div style="display: flex; justify-content: center; gap: 10px;">


[![Coverage](http://localhost:9000/api/project_badges/measure?project=carental&metric=coverage&token=sqb_a3d8d9ded14fbd1d05f282b959fffd02231cf099)](http://localhost:9000/dashboard?id=carental)

[![Duplicated Lines (%)](http://localhost:9000/api/project_badges/measure?project=carental&metric=duplicated_lines_density&token=sqb_a3d8d9ded14fbd1d05f282b959fffd02231cf099)](http://localhost:9000/dashboard?id=carental)

[![Lines of Code](http://localhost:9000/api/project_badges/measure?project=carental&metric=ncloc&token=sqb_a3d8d9ded14fbd1d05f282b959fffd02231cf099)](http://localhost:9000/dashboard?id=carental)

[![Quality Gate Status](http://localhost:9000/api/project_badges/measure?project=carental&metric=alert_status&token=sqb_a3d8d9ded14fbd1d05f282b959fffd02231cf099)](http://localhost:9000/dashboard?id=carental)

[![Security Hotspots](http://localhost:9000/api/project_badges/measure?project=carental&metric=security_hotspots&token=sqb_a3d8d9ded14fbd1d05f282b959fffd02231cf099)](http://localhost:9000/dashboard?id=carental)
</div>
# Anthony Menghi - 20212098 - M1DWM - Univeristé de Corse

### Les tests sont lancer avec l'IDE (VScode)

![capture-tests](/capture-tests.png "capture-tests").

### Le rapport Cucumber

Le rapport est dans `./cucumber-report.pdf` ou `./cucumber-report.html`

### Le rapport SonarQube

Le rapport est dans `./sonar-qube-report.pdf`

#### Le lancer

```
docker compose up -d
```

#### Lancer l'analyse

```bash
./mvnw clean verify sonar:sonar \ -Dsonar.projectKey=carental \
 -Dsonar.host.url=http://localhost:9000 \
 -Dsonar.login=TOKEN \
 -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
```

### Jmeter

Le rapport est dans `./jmeter-report.pdf`

#### Lancer le test

```bash
jmeter -n -t  jmeter.jmx -l results.jtl -e -o jm-report
```

---

Fait sur macbook air ARM M1
