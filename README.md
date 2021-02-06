# KnowYourSport

KnowYourSport is an Android application that allows users to check the result results, upcoming matches and also to know about their favourite teams.
The application is developed using the [TheSportsDB](https://www.thesportsdb.com/) API. The apk is attached with a file named app-debug.apk.


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
6. [Material Design] - For components such as CardView, Bottom Navbar and so on.
7. [Firebase Analytics] - To keep a track of all the user-engagement
8. [Google Admob] - To display banner ads in the app
9. [Firebase Crashlytics] - To track the app's crash rate in real time
10. [Firebase RemoteConfig] - To remotely control the visibility of instagram and twitter buttons insidde the application
11. [MVVM] - The architecture of the code follows MVVM pattern.

## Application target platform
* `minSdkVersion` - 16
* `targetSdkVersion` - 30
* `compileSdkVersion` - 30
* `buildToolsVersion` - 30.0.2

## Screenshots

<div>
  <img src="https://github.com/avinashpatnaik/KnowYourSport/blob/master/SplashScreen_google-pixel4xl-ohsoorange-portrait.png" width="220">
  
  <img src="https://github.com/avinashpatnaik/KnowYourSport/blob/master/ResultsPage_google-pixel4xl-ohsoorange-portrait.png" width="220">
  
  <img src="https://github.com/avinashpatnaik/KnowYourSport/blob/master/NoInternet_google-pixel4xl-ohsoorange-portrait.png" width="220">

</div>

<div>

 <img src="https://github.com/avinashpatnaik/KnowYourSport/blob/master/SearchAnim_google-pixel4xl-ohsoorange-portrait.png" width="220">
  
 <img src="https://github.com/avinashpatnaik/KnowYourSport/blob/master/SearchPage_google-pixel4xl-ohsoorange-portrait.png" width="220">
  
 <img src="https://github.com/avinashpatnaik/KnowYourSport/blob/master/InstagramPage_google-pixel4xl-ohsoorange-portrait.png" width="220">
  
</div>

<div>

 <img src="https://github.com/avinashpatnaik/KnowYourSport/blob/master/TwitterPage_google-pixel4xl-ohsoorange-portrait.png" width="220">
  
 <img src="https://github.com/avinashpatnaik/KnowYourSport/blob/master/UpcomingAnim_google-pixel4xl-ohsoorange-portrait.png" width="220">
  
 <img src="https://github.com/avinashpatnaik/KnowYourSport/blob/master/Upcoming_google-pixel4xl-ohsoorange-portrait.png" width="220">
 
</div>

## Analytics Screenshots

<div>
  
<img src="https://github.com/avinashpatnaik/KnowYourSport/blob/master/AnalyticsOverview.jpeg" width="550" heigh="550">
  
</div>
  
<div>
  
<b> A detailed report of the analytics with the name reach_mobi_coding_task_report.csv is attached. </b>

</div>

## Remote Config Report

<div>
  
<img src="https://github.com/avinashpatnaik/KnowYourSport/blob/master/RemoteConfigReport.PNG" width="550" heigh="550">
  
</div>

## Future Scope

Following are the ideas which could be implemented in the future

* Add more personlisation to the app where the user can potentially store his/her favourite team's information and get alerts for the same.
* Add offline compatibility using Room and Realm.
* Leverage the power of remote config to control the color palette of the application.
* The AdMob feature can be extended by introducing in-app purchases.
* Inject the power of analytics deep into the application by tracking the kind of sports the users are most interested in and push ads related to that.
* Use the power of GraphQL to get only desired response.
* Get feedback from the user.
* Include search player by name feature.
  



