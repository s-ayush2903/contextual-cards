# Contextual-Cards
|GH Actions CI Status|
|--|
|[![application-ci](https://github.com/s-ayush2903/contextual-cards/actions/workflows/ci.yml/badge.svg?branch=master)](https://github.com/s-ayush2903/contextual-cards/actions/workflows/ci.yml)|

Hi more than happy to see you here! Contextual Cards is an application that
renders the data fetched from the server on the screen. The cards fetched are
dynamic, meaning the changes will be reflected on the application as soon as the
response being received from the server gets changed. These cards come with
various properties like image, background color, text, formatting options, deep
Links etc, the application is completely capable of rendering and handling all
the entities/components being received from the backend. It is an example of a
_Plug & Play_ service which can work in multiple scenarios like in activities,
fragments, custom layouts / viewGroups, etc. It is built as an assignment by the
Fampay Team.

## Getting the APK
* Directly through this link: [master/apk](https://github.com/s-ayush2903/contextual-cards/blob/master/apk/ContextualCards.apk)
* Obtaining from the Releases section: [contextual-cards/releases](https://github.com/s-ayush2903/contextual-cards/releases/latest)
* From the latest Job act GH-Actions(as a CI Artifact): [here](https://github.com/s-ayush2903/contextual-cards/actions?query=branch%3Amaster+is%3Asuccess)

(for getting artifacts from CI, read the documentation [here](https://docs.github.com/en/actions/managing-workflow-runs/downloading-workflow-artifacts), or simply open the latest workflow from above link and scroll down a bit, you'll be able to find the `Contextual Cards APK` artifact.)
## Screenshots
|Application Icon|Splash Screen|
|--|--|
|<img src="https://files.gitter.im/5e46e1d4d73408ce4fd9acef/4yaj/icon.jpg" height=550>|<img src="https://files.gitter.im/5e46e1d4d73408ce4fd9acef/7tdM/splash_screen.jpg" height=550>|
---

|Home Screen Overview|Big Card|
|--|--|
<img src="https://files.gitter.im/5e46e1d4d73408ce4fd9acef/WsLS/overview.jpg" height=550> |<img src="https://files.gitter.im/5e46e1d4d73408ce4fd9acef/5CLD/big_card.jpg" height=550>|
---


|Big Card Menu|Relevant Cards Scrolled|
|--|--|
|<img src="https://files.gitter.im/5e46e1d4d73408ce4fd9acef/Jo0L/big_card_menu.jpg" height=550>|<img src="https://files.gitter.im/5e46e1d4d73408ce4fd9acef/gEn8/relevant_cards_scrolled.jpg" height=550>|


## Key Components of Codebase
* The application is built following the [MVVM](https://developer.android.com/jetpack/guide) Architecture and the [Android Jetpack](https://developer.android.com/jetpack)
components in consideration. 
* Uses Kotlin Extension functions & Scoping functions to keep the code legible,
  clean, null safe and make codebase follow _separation of concerns_.
* Uses SVGs only, no manually added PNG or any other image file, to handle the
  scaling automatically.
* Uses [Retrofit](https://square.github.io/retrofit/) for Making Calls to backend server
* Uses [RxJava](https://github.com/ReactiveX/RxJava) to observe the required components/variables
* Uses [Glide](https://bumptech.github.io/glide/) to fetch and display images
