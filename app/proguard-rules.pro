# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.

# Uncomment this to preserve the line number information for
# debugging stack traces.
-keepattributes SourceFile,LineNumberTable,Signature,*Annotation*

# Gson & Backup Models
-keep class com.example.tarotoneinviewmode.data.backup.** { *; }
-keep class com.example.tarotoneinviewmode.data.local.** { *; }
-keepclassmembers class * {
    @com.google.gson.annotations.SerializedName <fields>;
}