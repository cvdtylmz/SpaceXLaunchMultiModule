# SpaceXLaunchDemoApp

SpaceXLaunchDemoApp for Android. Developed with Kotlin.
This application had 2 screens:
  1) LastLaunch List screen; shows recently last launches spacex name and date.
  ![](/ScreenShots/Screenshot_20220629-180805_SpaceXLaunchApp.jpg)
  ![](/ScreenShots/Screenshot_20220629-180817_SpaceXLaunchApp.jpg)
  2) Satellite Detail screen; shows selected satellite detail informations.
  ![](/ScreenShots/Screenshot_20220629-180824_SpaceXLaunchApp.jpg)
  ![](/ScreenShots/Screenshot_20220629-180829_SpaceXLaunchApp.jpg)

Developed by MVVM design pattern with Multi Module Clean Architecture . Thus, as can be seen in the picture below, a cyclic flow was achieved.
![](/mvvm-clean-arch.png)

## Libraries used

- Apollo Client
- Dagger-Hilt
- Grahpql
- Viewmodel
- StateFlow, Flow
- Coroutines
- Material Library
- Navigation Components
- Unit Test (mocck, google-truth, coroutine-test)
