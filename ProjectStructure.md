Project Structure
neurix/
│
├── app/
│   ├── src/main/
│   │   ├── java/com/neurix/app/
│   │   │   ├── MainActivity.kt          # Activity entry point, splash, scaffold
│   │   │   └── NeurixApplication.kt     # Hilt application class
│   │   ├── res/
│   │   │   ├── values/
│   │   │   │   ├── strings.xml          # String resources
│   │   │   │   └── themes.xml           # Android theme
│   │   │   └── drawable/                # Drawable resources (future)
│   │   └── AndroidManifest.xml
│   ├── build.gradle.kts
│   └── proguard-rules.pro
│
├── core/
│   ├── src/main/java/com/neurix/core/
│   └── build.gradle.kts                 # Aggregator module
│
├── core-common/
│   ├── src/main/java/com/neurix/core/common/
│   │   ├── BaseViewModel.kt             # Abstract MVI ViewModel
│   │   ├── Constants.kt                 # App constants
│   │   ├── MviState.kt                  # State marker interface
│   │   ├── MviIntent.kt                 # Intent marker interface
│   │   ├── MviEffect.kt                 # Effect marker interface
│   │   └── Result.kt                    # Generic result wrapper
│   └── build.gradle.kts
│
├── core-design/
│   ├── src/main/java/com/neurix/core/design/
│   │   ├── NeurixColors.kt              # Color palette
│   │   ├── NeurixDimens.kt              # Dimensions
│   │   ├── NeurixShapes.kt              # Shape definitions
│   │   ├── NeurixSystemUi.kt            # Status/nav bar configuration
│   │   ├── NeurixTheme.kt               # Material 3 theme composable
│   │   └── NeurixTypography.kt          # Typography scale
│   └── build.gradle.kts
│
├── core-ui/
│   ├── src/main/java/com/neurix/core/ui/composables/
│   │   └── NeurixComponents.kt          # Reusable UI components
│   └── build.gradle.kts
│
├── core-navigation/
│   ├── src/main/java/com/neurix/core/navigation/
│   │   ├── NeurixNavHost.kt             # Navigation graph with transitions
│   │   └── Screen.kt                    # Route definitions
│   └── build.gradle.kts
│
├── feature-home/
│   ├── src/main/java/com/neurix/feature/home/
│   │   ├── presentation/
│   │   │   ├── HomeContract.kt          # State, Intent, Effect
│   │   │   ├── HomeScreen.kt            # Home screen UI
│   │   │   └── HomeViewModel.kt         # MVI ViewModel
│   │   ├── domain/                      # Use cases (future)
│   │   └── data/                        # Repositories (future)
│   └── build.gradle.kts
│
├── feature-chat/
│   ├── src/main/java/com/neurix/feature/chat/
│   │   ├── presentation/
│   │   │   ├── ChatContract.kt          # State, Intent, mock data
│   │   │   ├── ChatScreen.kt            # Chat UI with bubbles
│   │   │   └── ChatViewModel.kt         # MVI ViewModel
│   │   ├── domain/                      # Use cases (future)
│   │   └── data/                        # Repositories (future)
│   └── build.gradle.kts
│
├── feature-settings/
│   ├── src/main/java/com/neurix/feature/settings/
│   │   ├── presentation/
│   │   │   └── SettingsScreen.kt        # Settings and detail screens
│   │   ├── domain/                      # Use cases (future)
│   │   └── data/                        # Repositories (future)
│   └── build.gradle.kts
│
├── gradle/
│   ├── libs.versions.toml               # Version catalog
│   └── wrapper/
│       └── gradle-wrapper.properties
│
├── .github/
│   └── workflows/
│       └── build.yml                    # CI build pipeline
│
├── build.gradle.kts                     # Root build script
├── settings.gradle.kts                  # Module declarations
├── gradle.properties                    # Gradle JVM & build settings
├── android-library.gradle.kts           # Shared library convention (legacy)
│
├── README.md
├── Architecture.md
├── ProjectStructure.md
└── Roadmap.md
Module Count
Module	Type	Purpose
app	application	Entry point, DI setup
core	library	Aggregator
core-common	library	Base classes, MVI
core-design	library	Theme system
core-ui	library	Shared components
core-navigation	library	Navigation graph
feature-home	library	Home screen
feature-chat	library	Chat screen
feature-settings	library	Settings screen
Total: 9 modules