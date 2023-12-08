<h1 align="center">
  <img src="https://i.imgur.com/pcxav6F.png" alt="FiberTools Logo" width="200" height="200">
</h1>

<h1 align="center">
  <span style="color:#6E8FD9">FiberTools</span>

<span style="color:#D97B6E">Work In Progress</span>
</h1>

<h1 align="center">

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![SceneBuilder](https://img.shields.io/badge/SceneBuilder-007ACC.svg?style=for-the-badge&logo=Java&logoColor=white)

[![JavaFX](https://img.shields.io/badge/JavaFX-17%2B-green)](https://openjfx.io/)
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

<span style="color:#6E8FD9">FiberTools</span> is an open-source JavaFX application designed to assist with fiber optic
network data storage and calculations.

## <span style="color:#6E8FD9">Features</span>

- **CRUD** abilities for storage of Inventory, Employee, Splice Records, and Individual Fibers.
- **OTDR Style** trace viewer & table for splice records. **.SOR Files only**
- **Report Creator** for splice records. **.PDF Files only**
- **Conversion** between dBm, mW, and uW & Fiber to Color Code.
- **Calculations** for a loss budget. Allows for multiple different types of cable and units of measurement.

## <span style="color:#6E8FD9">Dependencies</span>

- **pyOTDR**
    - Version: 2.1.0
    - [pyOTDR](https://github.com/sid5432/pyOTDR)
- **MySQL Connector/J**
    - Version: 8.0.33
    - [MySQL Connector/J](https://mvnrepository.com/artifact/mysql/mysql-connector-java)
- **MaterialFx**
    - Version: 11.17.0
    - [MaterialFx](https://github.com/palexdev/MaterialFx)
- **ikonli**
    - Version: 12.3.1
    - [ikonli-javafx](https://mvnrepository.com/artifact/org.kordamp.ikonli/ikonli-javafx)
    - [ikonli-fontawesome5-pack](https://mvnrepository.com/artifact/org.kordamp.ikonli/ikonli-fontawesome5-pack)
- **JUnit Jupiter**
    - Version: 5.8.2
    - [JUnit Jupiter](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api)
    - [JUnit Jupiter Engine](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine)
- **JAXB API**
    - Version: 2.3.1
    - [jaxb-api](https://mvnrepository.com/artifact/javax.xml.bind/jaxb-api)
- **JAXB Runtime**
    - Version: 2.3.1
    - [jaxb-runtime](https://mvnrepository.com/artifact/org.glassfish.jaxb/jaxb-runtime)
- **VirtualizedFX**
    - Version: 11.9.3
    - [VirtualizedFX](https://github.com/palexdev/VirtualizedFX)

## <span style="color:#6E8FD9">Getting Started</span>

### <span style="color:#6E8FD9">Prerequisites</span>

- Java 17 or higher: [Java](https://www.oracle.com/java/technologies/javase-jdk14-downloads.html)
- JavaFX 17.0.2 or higher: [JavaFX SDK](https://openjfx.io/)
- MySQL 8.0.26 or higher: [MySQL](https://dev.mysql.com/downloads/mysql/)
- Python 3.6 or higher: [Python](https://www.python.org/downloads/)
- pyOTDR 2.1.0 or higher: [pyOTDR](https://github.com/sid5432/pyOTDR)

### <span style="color:#6E8FD9">Building & Running</span>

1. Ensure that you have python3 & pyOTDR installed:

   ```bash
    python3 --version
    pip3 install pyOTDR
   ```

1. Clone the repository:

   ```bash
   git clone https://github.com/jardo5/FiberTools.git
    ```
2. Navigate to the project directory:

   ```bash
   cd FiberTools
   ```
3. Build and Run the project:

   ```bash
   mvn clean javafx:run
   ```
   </h1>
