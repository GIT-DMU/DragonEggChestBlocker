# ED-Egg-Blocker

A small Paper/PaperMC plugin that prevents players from placing the Ender Dragon Egg into an Ender Chest. If a player attempts to move the egg into an Ender Chest, the action is cancelled and the player receives an ActionBar message.

## Features
- Prevents placing the Ender Dragon Egg into Ender Chests (click, drag, shift-click, hotbar moves and automatic transfers like hoppers).
- Notifies the player with an ActionBar message.

## Installation
1. Build the plugin (see below).
2. Copy the generated JAR (for example `build/libs/ED-Egg-Blocker-1.0-SNAPSHOT.jar`) into your Paper/Spigot server's `plugins/` folder.
3. Restart the server.

## Local build
Requirements: Java JDK (recommended 17+) and Gradle (or use the included wrapper).

Windows PowerShell:

```powershell
./gradlew.bat --no-daemon build
```

After the build, the JAR will be available in `build/libs/`.

## Testing in Minecraft
- Open an Ender Chest and try to shift-left-click an Ender Dragon Egg into it — this should be prevented.
- Test drag & drop, normal clicks and hopper transfers; only Ender Chests are blocked.
