# Gacha Tracker App Firebase

A simple gacha tracker for keeping track of your pulls and pull history in your favourite Gacha games.

Minimum supported Android version is Android 8 

## Supported Games
- Genshin Impact
- Honkai: Star Rail
- Zenless Zone Zero

## Features
- Pull Counter: also tracks pulls to pity and 50/50
- History of Pulls
- Statistics: both for the individual user and global averages across all users
- Firebase integration: the app uses Firebase for authentication and database

## Technology Stack
- Frontend: Android Studio(Java)
- Backend: Firebase

## Project Setup
- Create a project in the Firebase console and connect it with the Android Studio project.
- Create a file named **```keystore.properties```** in the root directory of your project. This file should contain the following information:
    ```
    store_file=my_store_file_location
    store_password=my_store_password
    key_alias=my_key_alias
    key_password=my_key_password
    ```

- Add your app signing keys to the Firebase project

### Google OAUTH
  - Create a **```credentials.xml```** file in app/src/main/res/values/credentials.xml
  - create web_client_id resource in credentials.xml
  - add your OAuth 2.0 Client IDs Web application Id from <a href="https://console.cloud.google.com/">your google cloud console</a>
  - example:
    ```
    <resources>
        <string name="web_client_id" translatable="false">[my web client id]</string>
    </resources>
    ```

## Download
Head to the <a href="https://github.com/sesvete/gacha-tracker-firebase/releases">releases</a> page for the newest release

Permission to install unknown apps is required for the installation of the app
