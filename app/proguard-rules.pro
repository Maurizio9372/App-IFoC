# Regole generali di ProGuard per il progetto

# Mantieni le classi di Room, in modo che il database non venga offuscato
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**

# Mantieni le classi di Hilt per l'iniezione delle dipendenze
-keep class dagger.** { *; }
-keep class javax.inject.** { *; }

# Mantieni le classi di Kotlin (evita problemi con la reflection)
-dontwarn kotlin.**
-keep class kotlin.** { *; }

# Per l'autenticazione biometrica, mantieni le classi e metodi relativi a BiometricPrompt
-keep class androidx.biometric.** { *; }
-dontwarn androidx.biometric.**

# Mantieni le classi di ViewModel
-keep class androidx.lifecycle.ViewModel { *; }
-keep class androidx.lifecycle.ViewModelProvider$* { *; }

# Mantieni le classi di LiveData
-keep class androidx.lifecycle.LiveData { *; }
-keep class androidx.lifecycle.MutableLiveData { *; }

# Per evitare la rimozione dei metodi di interfaccia WebView con JS
# Uncommenta questa sezione se stai usando WebView con JS
# -keepclassmembers class fqcn.of.javascript.interface.for.webview {
#    public *;
# }

# Mantieni le informazioni sul file di origine e il numero di riga per il debug
-keepattributes SourceFile,LineNumberTable

# Se desideri nascondere il nome del file sorgente, puoi usare questa regola
# -renamesourcefileattribute SourceFile

# Aggiungi eventuali altre regole personalizzate in base ai requisiti del tuo progetto
