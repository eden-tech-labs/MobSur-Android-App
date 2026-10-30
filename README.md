# MobSur-Android-App
Android sample application which integrates the MobSur Android SDK.

Uses SDK **1.4.0** (`app/libs/mobsur.aar`, from [MobSur-Android-SDK](https://github.com/eden-tech-labs/MobSur-Android-SDK)). Needs JDK 17.

To run the project:

1. Clone the repository.
2. Open the project in Android Studio.
3. Build the project and run it.

## Testing surveys (debug builds)

- **New test user** switches the SDK to a new random user, so surveys this device already answered can be
  answered again. The SDK shows at most one survey per app launch, so tap **Restart** on the message that
  appears (or restart the app yourself) before sending the event again.
- The SDK state is excluded from Android backup, so uninstalling and reinstalling also starts with a new user.
  `adb shell pm clear com.example.mobsurandroiddemo` resets everything too.
- Point a debug build at another MobSur app or backend with Gradle properties (no code changes):

  ```sh
  ./gradlew installDebug -Pmobsur.appId=<app-id> -Pmobsur.baseUrl=http://10.0.2.2:8000/
  ```

  `10.0.2.2` is the host computer as seen from the emulator; from a phone, use the computer's LAN IP.
  Debug builds allow plain HTTP for this. You can also put `mobsur.appId` / `mobsur.baseUrl` in
  `~/.gradle/gradle.properties`. Release builds always use the default app and `https://api.mobsur.com/`.
