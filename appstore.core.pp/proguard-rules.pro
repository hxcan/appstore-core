# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Data/Program/adt-bundle-linux-x86_64-20131030/sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

-keep class android.arch.lifecycle.** {*;}
-keep class org.apache.commons.io.IOUtils
-keep class org.apache.commons.io.-$$Lambda$KvkWVsVCTWBQ-_o1aATU4UW8gQk
-keep class org.apache.commons.io.** { *; } # All classes in the com.example package
-keep class com.stupidbeauty.commons.io.** { *; } # All classes in the com.example package
-keep class com.stupidbeauty.commons.io.IOUtils

-keep class com.iflytek.msc.** { *; } # All classes in the com.example package
# com/iflytek/msc/MSCSessionInfo
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

-keepnames class * { @butterknife.Bind *;}

-keepclassmembers class com.stupidbeauty.hxlauncher.bean.** {
 !transient <fields>;
}

-addconfigurationdebugging
