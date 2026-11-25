# Implementation Plan - UI Optimization & Hilt Migration

This plan aims to fix the perceived delay in the Archive and I-Ching screens and modernize the project's dependency injection using Hilt.

## Proposed Changes

### 1. UI Optimization (Archive & I-Ching)
Fix the "sticky" tab behavior by separating selection state from data loading and ensuring synchronous updates for in-memory data.

#### [MODIFY] [ArchiveViewModel.kt](file:///C:/Users/PC 01/StudioProjects/TarotOneInViewMode/app/src/main/java/com/example/tarotoneinviewmode/ui/archive/ArchiveViewModel.kt)
- Switch from `mutableStateOf` to `MutableStateFlow` for better consistency with modern Compose.
- Update `selectedTab` immediately in `onTabSelected`.
- Load cards synchronously if the repository is already in-memory.

#### [MODIFY] [IChingArchiveViewModel.kt](file:///C:/Users/PC 01/StudioProjects/TarotOneInViewMode/app/src/main/java/com/example/tarotone inviewmode/app/src/main/java/com/example/tarotoneinviewmode/ui/iching/IChingArchiveViewModel.kt)
- Update `selectedTrigram` immediately.
- Optimize the filtering logic to avoid unnecessary coroutine suspension for in-memory data.

---

### 2. Hilt Integration - Setup
Add the necessary infrastructure for Hilt.

#### [MODIFY] [build.gradle.kts (Project)](file:///C:/Users/PC 01/StudioProjects/TarotOneInViewMode/build.gradle.kts)
- Add Hilt plugin to the classpath.

#### [MODIFY] [build.gradle.kts (App)](file:///C:/Users/PC 01/StudioProjects/TarotOneInViewMode/app/build.gradle.kts)
- Apply Hilt plugin.
- Add Hilt dependencies (`hilt-android`, `hilt-compiler`, `androidx-hilt-navigation-compose`).

#### [MODIFY] [OraculoApp.kt](file:///C:/Users/PC 01/StudioProjects/TarotOneInViewMode/app/src/main/java/com/example/tarotoneinviewmode/OraculoApp.kt)
- Annotate with `@HiltAndroidApp`.
- (Cleanup) Remove manual repository and database lazy properties.

#### [MODIFY] [MainActivity.kt](file:///C:/Users/PC 01/StudioProjects/TarotOneInViewMode/app/src/main/java/com/example/tarotoneinviewmode/MainActivity.kt)
- Annotate with `@AndroidEntryPoint`.

---

### 3. Hilt Integration - Dependency Injection
Define how objects are provided and refactor ViewModels.

#### [NEW] [DatabaseModule.kt](file:///C:/Users/PC 01/StudioProjects/TarotOneInViewMode/app/src/main/java/com/example/tarotoneinviewmode/di/DatabaseModule.kt)
- Provide `OraculoDatabase`, `LeituraDao`, `PraticaDao`, and `OraculoPreferences`.

#### [NEW] [RepositoryModule.kt](file:///C:/Users/PC 01/StudioProjects/TarotOneInViewMode/app/src/main/java/com/example/tarotoneinviewmode/di/RepositoryModule.kt)
- Provide `LeituraRepository`, `PraticaRepository`, and `TarotRepository` (binding `InMemoryTarotRepository`).

#### [MODIFY] All ViewModels
- Annotate with `@HiltViewModel`.
- Use `@Inject constructor`.
- Remove manual Factory classes.

#### [MODIFY] [TarotNavHost.kt](file:///C:/Users/PC 01/StudioProjects/TarotOneInViewMode/app/src/main/java/com/example/tarotoneinviewmode/ui/navigation/TarotNavHost.kt)
- Replace manual `viewModel(factory = ...)` calls with `hiltViewModel()`.

## Verification Plan

### Automated Tests
- Run `gradlew assembleDebug` to verify Hilt code generation and compilation.

### Manual Verification
- **Archive Screen:** Verify that clicking tabs feels instant and the background image changes without delay.
- **I-Ching Archive:** Verify that trigram filtering is snappy.
- **App Startup:** Ensure the app launches correctly and Room/DataStore are properly injected.
- **Navigation:** Test deep links from notifications to ensure `MainActivity` and `NavHost` still handle them correctly with Hilt.
