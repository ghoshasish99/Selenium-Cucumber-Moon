![Java Cucumber Test Execution](https://github.com/ghoshasish99/Selenium-Cucumber-Moon/workflows/Java%20Cucumber%20Test%20Execution/badge.svg)

### Get Started
```xml
<dependency>
     <groupId>io.cucumber</groupId>
     <artifactId>cucumber-java</artifactId>
     <version>4.2.6</version>
     <scope>compile</scope>
</dependency>
```


###### UI Tests
```
$ mvn clean test -Dcucumber.options="--tags @UITest" -DExecutionPlatform="GRID_CHROME"
```