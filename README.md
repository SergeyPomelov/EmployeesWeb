## Employees page.
ExtJS + SpringMVC web application showcase.

![Repository Top Language](https://img.shields.io/github/languages/top/SergeyPomelov/EmployeesWeb)
![Languages](https://img.shields.io/github/languages/count/SergeyPomelov/EmployeesWeb)
![Github Repository Size](https://img.shields.io/github/repo-size/SergeyPomelov/EmployeesWeb)
![Github Open Issues](https://img.shields.io/github/issues/SergeyPomelov/EmployeesWeb)
![License](https://img.shields.io/badge/license-MIT-green)

### Running via Gradle:
Only [gradle 3.1][gradle], [Java 8][java] and net needed
for build & run. Repositories with libs for build tool and some ExtJS's stuff for runtime are online, they
could take a bit for downloading (even an app server will be downloaded for an easy start). In general, this
way should be a few click magic.

Simple instructions are going next.

RUNNING THE APPLICATION:

- Download and install
[JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).
If the app-get(or other installer) asks about PATH - "Yes, Ok."
 *If you already have installed JDK 8 and set the PATH, you can skip this step.*

- Download and install [Gradle 3.1](https://docs.gradle.org/current/userguide/installation.html) (follow
their simple instructions, there no universal installer for each OS.).
*If you have already installed Gradle 3.1 (and set GRADLE_HOME and its /bin is added to the PATH), you can
skip this step.*

- Open your console or other OS command line tool.

- Go to the root directory of project.
*The one which contains the gradle.build and this readme file.*

- Type and run the command **gradle clean appStart** (it may take a while, after a report about the server
being started and localhost:8080 address shown up you may go to the next point).
*gradle appStop - stops the app, waits before the handlers gracefully stop and free your ports. Output
should be in logs files, see logback.xml.*

- Start [Google Chrome][chrome] (scripts as JS, AJAX are on, no any plain html fall backs) and follow this
address: [localhost:8080][localhost]. First request may takes a bit (ExtJS .js and styles  MBytes are
downloading). *The port is cofigurable in gradle.build.*

RUNNING TESTS:

- You can run unit and integration tests by using this command: **gradle test**.
*You still should perform at least the stages 1-2 from the steps above.*

#### Running in IDEA
You need an IDEA 16 with gradle plugin (Eclipse should works the same way) and ethernet.
Import the gradle project (you will need gradle or chose the gradle wrapper option).
You'll get not only run, test, gradle gui with other enlisted tasks and dependencies but also a full easy
navigable code of the project. The full instruction have many depending nuances, if you are not sure,
follow the gradle unified way above.

#### Environment
- **Browser** - Google Chrome 54
- **JDK** - Oracle, 1.8.0_60
- **Build tool** - Gradle 3.1
- **Server** - Tomcat 8.5.6

#### Tech Stack
- **FrontEnd** - Ext JS 6.2
- **BackEnd** - SpringMVC 4.3
- **JPA** - Implementation by Spring-Data
- **DB** - HSQLDB 2.3
- **LOG** - Logback 1.1 via slf4j API
- **Testing** - JUnit 4, Mockito 2.2, Spring-test
- **JSON** - supported by Jackson 2.8, tested by Jsonpath 2.2

#### Miscellaneous
Project's Java style - GoogleSS, JS - IDEA default + HintJS & LintJS.
IDE - IDEA 16.

[gradle]: (https://docs.gradle.org/current/userguide/installation.html)
[java]: (http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
[chrome]: (https://docs.gradle.org/current/userguide/installation.html)
[localhost]: (http://localhost:8080)
