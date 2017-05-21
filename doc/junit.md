# Setup unit tests with JUnit

## Maven
 
### Dependencies

You have to add the following Maven dependencies:

```
 <dependency>
  <groupId>info.cukes</groupId>
  <artifactId>cucumber-java</artifactId>
  <version>1.2.5</version>
  <scope>test</scope>
</dependency>
        
<dependency>
  <groupId>info.cukes</groupId>
  <artifactId>cucumber-junit</artifactId>
  <version>1.2.5</version>
  <scope>test</scope>
</dependency>
        
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.12</version>
  <scope>test</scope>
</dependency>
```

If you want to product reports which is described in the section **Report** you have to add the following dependency, too:

```
<dependency>
  <groupId>net.masterthought</groupId>
  <artifactId>cucumber-reporting</artifactId>
  <version>3.5.1</version>
  <scope>provided</scope>
</dependency>
```

### Plugin configuration

The Maven plugin *Failsave* is used for processing the tests, therefore the Surefire plugin skips the tests
```
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
      <skipTests>true</skipTests>
    </configuration>
</plugin>
```

The Failsave plugin is executed in the phase *verifiy*. All tests which ends with the suffix *AcceptanceTests* will
be executed.

```
<plugin>
  <groupId>org.apache.maven.plugins</groupId>
  <artifactId>maven-failsafe-plugin</artifactId>
  <version>2.20</version>
  <configuration>
    <includes>
      <include>**/*AcceptanceTest.java</include>
    </includes>
  </configuration>
  <executions>
    <execution>
      <id>integration-test</id>
      <phase>integration-test</phase>
      <goals>
        <goal>integration-test</goal>
      </goals>
    </execution>
    <execution>
      <id>verify</id>
      <phase>verify</phase>
      <goals>
        <goal>verify</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```

## Unit test

If you want to create an unit test you need the features file, the file with the step
definitions and the test runner file.

### Feature

The features file has the extension `.feature` and represents the feature which can consits
of serveral scenarios.


### Steps

The Java class with the steps contains the steps of the scenarios. If you want to execute
methods (hooks) before and after a scenario you can create methods with the annotations
`@Before` and `@After`.

### Test runner

You have to add the annotations `@RunWith` and `@CucumberOptions` to the class.

Options:
* The option *features* specifies the folder in which the features files are stored.
* The option *glue* specifies the Java package in which the step files are stored.
* The option *plugin* defines the format of the result file.

```
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/features/math",
        glue = "de.blackned.training.cucumber",
        plugin = "json:target/cucumber-report.json")
public class ArithmeticAcceptanceTest {
}
```

## Report

The Cucumber reporting Maven plugin can be used to create HTML reports.

You can used it by adding the plugin to the build tag in the `pom.xml` file.

```
<plugin>
  <groupId>net.masterthought</groupId>
  <artifactId>maven-cucumber-reporting</artifactId>
  <version>3.5.0</version>
  <executions>
    <execution>
      <id>execution</id>
      <phase>verify</phase>
      <goals>
        <goal>generate</goal>
      </goals>
      <configuration>
        <projectName>cucumber</projectName>
        <outputDirectory>${project.build.directory}/cucumber-html-reports</outputDirectory>
        <cucumberOutput>${project.build.directory}/cucumber-report.json</cucumberOutput>
      </configuration>
    </execution>
  </executions>
</plugin>
```

The next image shows an example of the report of all features:

![Example of a report of all features](cucumber_report_features.png)

The next image shows the details of the feature *Basic Arithmetic* with its steps and hooks:

![Example of a feature report](cucumber_report_feature_detail.png)