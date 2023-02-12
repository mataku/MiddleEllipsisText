package io.github.mataku.middleellipsistext

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.captureToImage
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import org.junit.Rule
import org.junit.Test

// ./gradlew MiddleEllipsisText:pixel4Api31DebugAndroidTest
class MiddleEllipsisTextTest {
  @get:Rule
  val composeTestRule = createComposeRule()

  @Test
  fun layout_inputIsEmpty_noCrash() {
    composeTestRule.setContent {
      MaterialTheme {
        Surface {
          TestScreen(input = "")
        }
      }
    }
    composeTestRule.onRoot().assertIsNotDisplayed()
  }

  @Test
  fun layout_notAppliedEllipsis() {
    composeTestRule.setContent {
      MaterialTheme {
        Surface {
          TestScreen(input = "a")
        }
      }
    }
    composeTestRule.onNodeWithText("a").assertIsDisplayed()
  }

  @Test
  fun layout_notAppliedEllipsis_multipleCodePointsEmoji() {
    val flagEmoji = "\uD83C\uDDE7\uD83C\uDDEB"
    val emojis = flagEmoji.repeat(9)
    composeTestRule.setContent {
      MaterialTheme {
        Surface {
          TestScreen(
            input = emojis,
          )
        }
      }
    }
    composeTestRule.onNodeWithText(emojis)
      .assertIsDisplayed()
  }


  @Test
  fun layout_appliedEllipsis() {
    composeTestRule.setContent {
      MaterialTheme {
        Surface {
          TestScreen(input = "soooooooooooooooooooooooloooooooooooooooooooongtext")
        }
      }
    }
    val bitmap = composeTestRule.onRoot().captureToImage().asAndroidBitmap()
    TestHelper.saveScreenshot("layout_appliedEllipsis", bitmap)
    composeTestRule.onNodeWithText("sooooooooooooooooooo...oooooooooooooongtext")
      .assertIsDisplayed()
  }

  @Test
  fun layout_appliedEllipsis_customEllipsisChar() {
    composeTestRule.setContent {
      MaterialTheme {
        Surface {
          TestScreen(
            input = "soooooooooooooooooooooooloooooooooooooooooooongtext",
            ellipsisChar = ','
          )
        }
      }
    }
    composeTestRule.onNodeWithText("sooooooooooooooooooo,,,oooooooooooooongtext")
      .assertIsDisplayed()
  }

  @Test
  fun layout_appliedEllipsis_multibyteString() {
    composeTestRule.setContent {
      MaterialTheme {
        Surface {
          TestScreen(
            input = "soooooooooooooooooooooooloooooooooooooooooooongtextツツツツツツ",
          )
        }
      }
    }
    composeTestRule.onNodeWithText("sooooooooooooooooooo...oooongtextツツツツツツ")
      .assertIsDisplayed()
  }

  @Test
  fun layout_appliedEllipsis_basicEmoji() {
    composeTestRule.setContent {
      MaterialTheme {
        Surface {
          val emojis = "\uD83D\uDE00".repeat(20)

          TestScreen(
            input = emojis,
          )
        }
      }
    }
    val bitmap = composeTestRule.onRoot().captureToImage().asAndroidBitmap()
    TestHelper.saveScreenshot("basic_emoji", bitmap)
    composeTestRule.onNodeWithText(
      "\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00...\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00"
    )
      .assertIsDisplayed()
  }

  @Test
  fun layout_appliedEllipsis_multipleCodePointsEmoji() {
    val flagEmoji = "\uD83C\uDDE7\uD83C\uDDEB"
    composeTestRule.setContent {
      MaterialTheme {
        Surface {
          val emojis = flagEmoji.repeat(20)

          TestScreen(
            input = emojis,
          )
        }
      }
    }
    val bitmap = composeTestRule.onRoot().captureToImage().asAndroidBitmap()
    TestHelper.saveScreenshot("complex_emoji", bitmap)
    composeTestRule.onNodeWithText(
      "${flagEmoji.repeat(9)}...${flagEmoji.repeat(9)}"
    )
      .assertIsDisplayed()
  }

  @Test
  fun layout_appliedEllipsis_containsMultibyteStringAndEmoji() {
    val flagEmoji = "\uD83C\uDDE7\uD83C\uDDEB"
    composeTestRule.setContent {
      MaterialTheme {
        Surface {
          TestScreen(
            input = "soooooooooooooooooooooooloooooooooooooooooooongtextツツツツツ${flagEmoji}",
          )
        }
      }
    }
    composeTestRule.onNodeWithText("soooooooooooooooooo...oooongtextツツツツツ${flagEmoji}")
      .assertIsDisplayed()
  }
}

@Composable
private fun TestScreen(input: String, ellipsisChar: Char = '.', ellipsisCharCount: Int = 3) {
  Box(modifier = Modifier) {
    MiddleEllipsisText(
      text = input,
      ellipsisChar = ellipsisChar,
      ellipsisCharCount = ellipsisCharCount
    )
  }
}
