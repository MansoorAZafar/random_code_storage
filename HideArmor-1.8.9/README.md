# FullBright Mod Minecraft 1.8.9 Mod
## Instructions

** Setup **
```bash
# Change your terminal session to java 8
# the example below uses powershell
# replace with your java 8 path
$env:JAVA_HOME="C:\Program Files\Eclipse Adoptium\jdk-8.0.502.7-hotspot" # EXAMPLE PATH
$env:Path="$env:JAVA_HOME\bin;$env:Path"

.\gradlew setupDecompWorkspace eclipse
```

** Building **
```bash
.\gradlew clean build
```

** Testing **
```bash
.\gradlew clean runClient
```

## In Game Use
```bash
/gamma <amount>
```

## Details
Uses Mixins to inject right atfer options.txt is loaded and load ONCE the gamma from the internal file. This apporach is better than using onClientTick or onGuiLoaded as it is only ran ONCE at the start and never again. 