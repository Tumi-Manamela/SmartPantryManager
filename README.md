# Smart Pantry Manager

## Mobile App Development 700

### Project Overview
Smart Pantry Manager is an Android application developed in Java using Android Studio. It helps users manage ingredients in their kitchen pantry and discover recipes they can prepare using the ingredients they have available.

### Features
- **Pantry Management:** Add, view, edit, and delete ingredients.
- **Ingredient Details:** Record ingredient names, quantities, units, and optional expiry dates.
- **Local Database:** Store pantry ingredients persistently using SQLite.
- **Recipe Suggestions:** Display recipes only when the pantry contains all required ingredients in sufficient quantities.
- **Recipe Details:** View recipe ingredients and preparation instructions.
- **Settings:** Access the application's settings screen.
- **Input Validation:** Validate ingredient information before saving.
- **Empty-State Feedback:** Display a helpful message when no recipes match the available pantry ingredients.

### Technologies Used
- Java
- Android Studio
- XML for user interface layouts
- SQLite for local data storage
- Git and GitHub for version control
- Android Emulator for application testing

### Application Screens
1. Home
2. Pantry List
3. Add/Edit Ingredient
4. Suggested Recipes
5. Recipe Details
6. Settings

### Installation and Setup
1. Clone or download this repository.
2. Open Android Studio.
3. Select **Open** and choose the `SmartPantryManager` project folder.
4. Allow Gradle to sync and download any required dependencies.
5. Start an Android emulator or connect a compatible Android device.
6. Run the application from Android Studio.

### Database
The application uses a local SQLite database to store pantry ingredients. Ingredient records include a name, quantity, unit, and optional expiry date.

### Testing
The application has been manually tested for adding, editing, and deleting ingredients; pantry count updates; persistent storage; recipe matching; recipe details; and feedback when no recipes match.

### Repository
GitHub: https://github.com/Tumi-Manamela/SmartPantryManager

### Author
Tumi Manamela

### Module
Mobile App Development 700
