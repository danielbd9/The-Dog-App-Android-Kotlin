## The Dog App

![Splash](https://github.com/danielbd9/s-health/assets/16392300/4fef1a6c-a44a-4c6a-8125-ff8abbe449ea)  ![Breed list](https://github.com/danielbd9/s-health/assets/16392300/f3868dd8-ba08-40e3-870b-bd81951c6bc0) ![Breed Grid](https://github.com/danielbd9/s-health/assets/16392300/c482e49c-a82b-40bc-9cc2-1a359da6c9b0)



Mobile Application - Dog Breeds Viewer
This is a mobile application developed in Kotlin that allows users to view information about different dog breeds, including their images, names, breed categories, origins, and temperaments. The application follows the MVVM (Model-View-ViewModel) architecture and utilizes some of the most popular libraries and technologies in the Android ecosystem.

Technologies Used
Language: Kotlin
Architecture: MVVM (Model-View-ViewModel)
View: Activities and Layouts
Dependency Manager: Gradle
Modular Architecture
Libraries:
Glide (for image loading)
Retrofit (for communication with the dog breeds API)
LiveData (for observing and reacting to data changes)
ViewModel (part of Android's Architecture Components)
Hilt (for Dependency Injection)
Material Design (for UI/UX)
MockK (for unit testing)
Kotlin Coroutines (for asynchronous programming)
Features
Dog Breeds Listing:

Users can view a list or grid of different dog breeds, including a representative image, the breed name, breed category, origin, and temperament.
Alphabetical sorting is available through pagination, avoiding displaying an excessively long list on the screen.
Dog Breed Details:

When clicking on a specific breed, the user is redirected to a details screen, which displays in-depth information about the selected breed.
The displayed information includes the breed name, breed category, origin, and temperament.
Dog Breed Search:

The application allows users to search for a specific dog breed.
The search results are displayed in a list or grid, containing information about the breed name, group, origin, and temperament.
Users have the option to view the results in list or grid format and can also sort alphabetically with pagination support.
Navigation between Screens:

Users can navigate between the listing, details, and search result screens using native navigation buttons.
Modular Architecture and Separation of Concerns
The application was developed following a modular architecture, which allows logical separation of different components, reducing coupling and facilitating maintenance and scalability. The main modules are:

App Module: Contains the presentation layer, including Activities, Fragments, and ViewModels related to displaying dog breeds and their details, as well as the search result screen.
Domain Module: Handles business logic, including use cases, repositories, and entities.
Data Module: Responsible for data access, such as communication with the dog breeds API and cache management.
How to Run the Project
Clone the application's repository to your local machine.
Import the project into your Android IDE (Android Studio recommended).
Wait for the Gradle synchronization process to complete and ensure that all dependencies have been downloaded correctly.
Connect an Android device or use an emulator to run the application.
Unit Testing
Unit tests have been conducted using the MockK and JUnit frameworks. These tests ensure that the application's key functionalities are working correctly and help avoid potential regressions.

API Reference
The data for dog breeds, including images, is obtained from the Dog API. For more information, please refer to the official Dog API website at: https://www.dogapi.com/

Graphics Resources Reference
The icons and animations used in the application were obtained from various sources, including:

LottieFiles
SVG Repo
FreeSVG
Flaticon
Future Improvements
As the development of the application continues, some potential areas for future improvements and enhancements include:

Offline Functionality: Implementing offline capabilities, allowing users to access previously loaded dog breed information even without an active internet connection. This could be achieved by implementing local caching of data and using a database to store breed details.

RXJava Integration: Introducing RXJava to handle asynchronous operations, such as API calls and data stream processing, could lead to more efficient and scalable code. RXJava's observables and reactive programming can simplify data handling and enhance the application's responsiveness.

Enhanced UI/UX: Continuously improving the user interface and user experience by incorporating more interactive elements, smooth animations, and intuitive gestures to make navigating through the app more enjoyable and engaging.

Pagination Optimization: Fine-tuning the pagination mechanism to optimize the loading and display of dog breeds, ensuring a seamless user experience when browsing through large lists of breeds.

Error Handling and User Feedback: Implementing a more comprehensive error handling system to provide informative and user-friendly error messages, helping users understand and resolve issues when they occur.

Accessibility Features: Enhancing the application's accessibility by incorporating features such as support for TalkBack (screen reader) and adjusting the UI for users with disabilities.

These improvements would further elevate the usability and functionality of the Dog Breeds Viewer application, enhancing the user experience and ensuring a robust and reliable performance.
