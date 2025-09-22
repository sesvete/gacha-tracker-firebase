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
- Add your app signing keys to the Firebase project
- ### Google OAUTH
  - Create a credentials.xml file in app/src/main/res/values/credentials.xml
  - create web_client_id resource in credentials.xml
  - add your OAuth 2.0 Client IDs Web application Id from <a href="https://console.cloud.google.com/" title="google-cloud">your google cloud console</a>

## Download
Head to the <a href="https://github.com/sesvete/gacha-tracker-firebase/releases" title="Releases">releases</a> page for the newest release

Permission to install unknown apps is required for the installation of the app
