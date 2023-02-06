package ext

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension

fun CommonExtension<*, *, *, *>.androidConfiguration() {
  compileSdk = 33

  if (this is LibraryExtension) {
    defaultConfig {
      minSdk = 24
    }
  } else if (this is BaseAppModuleExtension) {
    defaultConfig {
      minSdk = 24
      targetSdk = 32
    }
  }
}
