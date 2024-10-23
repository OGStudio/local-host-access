
## Java host

Build stand-alone JAR file:

$ `./gradlew jar`

Run stand-alone JAR file:

$ `java -jar app/build/libs/app.jar`

## macOS host

Build native desktop binary:

$ `./gradlew nativeBinaries`

Run native desktop binary:

$ `./nt/build/bin/native/releaseExecutable/nt.kexe`

## Notes

* In case of build problems try to leave only one subproject included in settings.gradle.kts
