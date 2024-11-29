Local Host Access (**LHA**) is a cross-platform HTTP server
for client side JS to access local host

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

## Current availability of functions

| № | Function      | JVM | Linux | macOS | Windows |
|---|---            |---  |---    |---    |---      |
| 1 | `GET /path`   | √   | X     | √     | √       |
| 2 | `POST /list`  | √   | X     | √     | X       |
| 3 | `POST /read`  | √   | X     | √     | X       |
| 4 | `POST /write` | X   | X     | X     | X       |

## Notes

* On POSIX hosts native LHA variant uses `stat -L` command to detect if symbolic link points to a regular file or directory
* In case of build problems try to leave only one subproject included in `settings.gradle.kts` like this:

```
//include("jvm")
include("nt")
```

## Test

1. Launch LHA with `test/test-dir` directory as `.`

![launch-lha][launch-lha]

2. Run `curl-test` or `web-test.html` to validate each function

![web-test][web-test]

[launch-lha]: img/01.launch-lha.jpg
[web-test]: img/02.web-test.jpg
