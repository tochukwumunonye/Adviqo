# Adviqo


Greetings 👋🏼👋🏼👋🏼
Thanks so much for checking out my project. 
## Table of content

- [Prerequisite](#prerequisite)
- [Preview](#preview)
- [Feature](#feature)
- [Design](#design)
- [Architecture](#architecture)
- [Testing](#testing)


## Prerequisite
- Android Studio Arctic Fox | 2020.3.1
- Gradle version 7.0.2
- MinSdk 21
- TargetSdk 32


## Preview
<img src="https://user-images.githubusercontent.com/61085272/177013368-a94c171f-56fb-49b0-a01d-bea052ab32ff.png" width="33%" /> <img src="https://user-images.githubusercontent.com/61085272/177013374-1be12c6e-ce3a-4c7b-adb1-fdc6693fd73e.png" width="33%" /> 


<img src="https://user-images.githubusercontent.com/61085272/177013379-c9959909-ebd6-455f-bd36-f7e7266d9da5.png" width="33%" /> 

![Screenshot_20220702-200148](https://user-images.githubusercontent.com/61085272/177013385-46cb6f87-a00e-436a-8c6c-9d575ccb6d47.png)


## Design
- Two screens with functionality to navigate between themselves.
- The First Screen(Search Screen) used to search for items.
- The Second Screen Displays the details about the searched item
- The third screen Screen displays the last five opened items.


## Architecture
Here are some architecture and technical decisions I made:
- I leveraged on constraint Layout to avoid nested layouts which can cause UI jank
- I used Room Database which serves as the single source of truth to always persist selected dates
- I followed clean architecture(Data, Domain, Presentation) guidelines to ensure separation of concerns whichmakes the application is scalable, testable and mainainable
- I used coroutines to prevent memory leaks and threading issues.



## Testing
Testing is done with Junit4 testing framework for assertions and Mockito for mocking classes and Expresso for Instrumented Test. Each  layer has its own test. 
Viewmodel tests verify that each call to repository produces the correct view state.
Repository Test verify each interaction with database returns the expected result.
UI  was  tested with Expresso

## - Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [StateFlows](https://developer.android.com/kotlin/flow) -  Flow APIs that enable flows to optimally emit state updates and emit values to multiple consumers.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes. 
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - SQLite object mapping library.
- [Mockito](http://site.mockito.org/) - Most popular mocking framework for Java/kotlin.

