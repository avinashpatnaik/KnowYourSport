# KnowYourSport

KnowYourSport is an Android application that allows users to check the result results, upcoming matches and also to know about their favourite teams.
The application is developed using the [TheSportsDB](https://www.thesportsdb.com/) API.


## Installation

Clone this repository using,

```
https://github.com/avinashpatnaik/KnowYourSport.git
```

## Features

1. Know previous match results, upcoming match schedules of famous sports.
2. Check information about your favourite team by typing the team's name and follow them on instagram or twitter.
3. Material design UI with eye pleasing colours. 
4. Tracks the user's engagement across the application with the help of Firebase analytics.
5. Remotely controls the visibility of instagram and twitter buttons using Remote Config.
6. Uses crashlytics to quickly understand where things might have posibly gone wrong.
7. Enables Ads to monetise the application using Google's AdMob.


## Technologies Used
1. [Android] - Native android application developed using Java and Kotlin to demonstrate interoperability across the two languages.
2. [Lottie Animation by Airbnb] - To display animations 
3. [ViewModel] - To manage UI-related data in the app lifecycle
4. [Retrofit 2] - To retrieve the data from API
5. [Glide] - To render images
6. [Material Design] - For components such as CardView, Bottom Navbar, Chips, and so on
7. [Firebase Analytics] - To keep a track of all the user-engagement
8. [Google Admob] - To display banner ads in the app
9. [Firebase Crashlytics] - To track the app's crash rate in real time.
10.[Firebase RemoteConfig] - To remotely control the visibility of instagram and twitter buttons insidde the application.

## Application target platform
* `minSdkVersion` - 16
* `targetSdkVersion` - 30
* `compileSdkVersion` - 30
* `buildToolsVersion` - 30.0.2

## Screenshots

<div>
  <img src="https://github.com/avinashpatnaik/KnowYourSport/blob/master/InstagramPage_google-pixel4xl-ohsoorange-portrait.png" width="220">

  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/img2.jpg" width="220">

  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/img3.jpg" width="220">
</div>

<div>
  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/img4.jpg" width="220">

  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/img5.jpg" width="220">

  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/img6.jpg" width="220">
</div>

<div>
  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/img7.jpg" width="220">

  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/img8.jpg" width="220">

  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/img9.jpg" width="220">
</div>

<div>
  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/img10.jpg" width="220">

  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/img11.jpg" width="220">

  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/img12.jpg" width="220">
</div>

<div>
  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/img13.jpg" width="220">

  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/img14.jpg" width="220">
</div>

## App Working Video
<a href="https://youtu.be/ow2-rHQwBzY" target="_blank"><img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/video.png" 
alt="Video Working" width="220" /></a>

## Analytics Screenshots
<div>
  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/firebase_stats.PNG" width="350">

  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/firebase_stats2.PNG" width="350">

  <img src="https://github.com/Abhishekds94/Sports-Star/blob/master/screenshots/flurry_stats.PNG" width="350">
</div>

## Future Scope
Below are a few of the ideas that I could think of to enhance the application,
* Add notifications to notify user on match days of selected teams
* Add offline compatibility using Room and LiveData
* Adding [Interstitial ads](https://developers.google.com/ad-manager/mobile-ads-sdk/android/interstitial) to monetize the app
* Add Live scores feature with premium API
* Monetization using various techniques of in-app purchases
