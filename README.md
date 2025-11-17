# ED-Egg-Blocker

A small Paper/PaperMC plugin that prevents players from placing the Ender Dragon Egg into an Ender Chest. If a player attempts to move the egg into an Ender Chest, the action is cancelled and the player receives an ActionBar message plus a short BossBar warning.

## Features
- Prevents placing the Ender Dragon Egg into Ender Chests (click, drag, shift-click, hotbar moves and automatic transfers like hoppers).
- Notifies the player with an ActionBar message and a temporary BossBar.

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

## GitHub Actions: automatic build & release
A workflow is included that builds the project and creates a GitHub Release with the produced JAR when a tag like `v1.0.0` is pushed. You can also trigger the workflow manually with `workflow_dispatch`.

## Notes
- The ActionBar API used in the plugin may be marked as deprecated in some server API versions; it still works on common Paper/Spigot servers. If you prefer, this can be adjusted to use an alternative ActionBar method or Adventure API.
