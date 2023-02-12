package io.github.mataku.middleellipsistext

import android.graphics.Bitmap
import androidx.test.platform.app.InstrumentationRegistry
import java.io.FileOutputStream

object TestHelper {
  fun saveScreenshot(filename: String, bmp: Bitmap) {
//    if (System.getenv("CI") != null) {
//      return
//    }

    val path = InstrumentationRegistry.getInstrumentation().targetContext.filesDir.canonicalPath
    FileOutputStream("$path/$filename.png").use { out ->
      bmp.compress(Bitmap.CompressFormat.PNG, 100, out)
    }
    println("Saved screenshot to $path/$filename.png")
  }
}
