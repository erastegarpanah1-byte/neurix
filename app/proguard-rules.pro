# Neurix ProGuard Rules

# Keep Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# Keep Kotlin metadata
-keepattributes *Annotation*
-keepattributes InnerClasses
-keep class kotlin.Metadata { *; }