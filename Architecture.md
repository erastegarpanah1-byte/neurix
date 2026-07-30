Architecture
Overview
Neurix follows Clean Architecture with MVI (Model-View-Intent) presentation pattern, organized into multiple Gradle modules for maximum scalability and separation of concerns.

Module Dependency Graph
app
 ├── core (aggregator)
 │    ├── core-common
 │    ├── core-design
 │    ├── core-ui
 │    └── core-navigation
 ├── feature-home
 │    ├── core-design
 │    ├── core-ui
 │    └── core-common
 ├── feature-chat
 │    ├── core-design
 │    ├── core-ui
 │    └── core-common
 └── feature-settings
      ├── core-design
      ├── core-ui
      ├── core-navigation
      └── core-common
Layer Responsibilities
app
Application entry point (NeurixApplication)
MainActivity with Compose setup
Splash screen orchestration
Hilt dependency injection entry
core Modules
core-common
Base classes: BaseViewModel, Result<T>
MVI contracts: MviState, MviIntent, MviEffect
Shared constants
core-design
Theme: NeurixTheme composable
Colors: NeurixColors object
Typography: NeurixTypography
Shapes: NeurixShapes
Dimensions: NeurixDimens
System UI: NeurixSystemUi
core-ui
Reusable composables: NeurixCard, NeurixGradientCircle
PlaceholderScreen for future features
FadeInView animation wrapper
NeurixTopBar navigation component
core-navigation
Screen sealed class defining all routes
NeurixNavHost composable with animated transitions
Feature Modules
Each feature follows Clean Architecture layering:

feature-{name}/
└── src/main/java/com/neurix/feature/{name}/
    ├── presentation/
    │   ├── {Feature}Contract.kt    # State, Intent, Effect definitions
    │   ├── {Feature}ViewModel.kt   # MVI ViewModel
    │   └── {Feature}Screen.kt     # Compose UI
    ├── domain/                     # Use cases (future)
    └── data/                       # Repositories (future)
MVI Pattern
User Action → Intent → ViewModel → State (UI renders)
                          ↓
                       Effect (one-shot events like navigation)
Dependency Injection
Hilt is used for dependency injection. Each feature module's ViewModel is annotated with @HiltViewModel and injected via hiltViewModel() in Compose.

Navigation
Navigation Compose is used with a single NavHostController. Routes are defined in the Screen sealed class. Transitions use Material Motion patterns (fade + slide).

Theme
The design system follows Material 3 with a custom dark color scheme:

Background: #090909 (deep black)
Surface: #111111 (dark gray)
Primary: #3B82F6 (blue)
Secondary: #7C3AED (purple)
Accent: #00D4FF (cyan)