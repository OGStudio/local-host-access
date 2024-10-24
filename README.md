
## Java host

Build stand-alone JAR file:

$ `./gradlew jar`

Run stand-alone JAR file:

$ `./run/run-java`

## macOS host

Build native desktop binary:

$ `./gradlew nativeBinaries`

Run native desktop binary:

$ `./run/run-native`

## Notes

* In case of build problems try to leave only one subproject included in `settings.gradle.kts` like this:

```
//include("app")
include("nt")
```
