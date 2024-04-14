package io.github.mataku.middleellipsistext

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.isRoot
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.RobolectricDeviceQualifiers
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(
  qualifiers = RobolectricDeviceQualifiers.Pixel7,
  sdk = [33]
)
class MiddleEllipsisTextTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun layout_multibyte() {
    composeTestRule.captureScreenshot(
      fileName = "MiddleEllipsisText_multibyte.png",
      content = {
        Column(modifier = Modifier.fillMaxSize()) {
          MiddleEllipsisText(
            text = "あいうえおかきくけこさしすせそだぢづでどなにぬねのばびぶべぼまみむめも"
          )
        }
      }
    )
  }
}

fun ComposeContentTestRule.captureScreenshot(
  fileName: String,
  content: @Composable () -> Unit,
  actionsBeforeCapturing: () -> Unit = {}
) {
  this.setContent {
    MaterialTheme {
      Surface {
        content()
      }
    }
  }

  actionsBeforeCapturing.invoke()

  this.onNode(isRoot()).captureRoboImage(
    filePath = "screenshot/${fileName}",
  )
}
