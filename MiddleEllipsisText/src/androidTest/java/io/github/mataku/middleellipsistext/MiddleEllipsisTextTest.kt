package io.github.mataku.middleellipsistext

import androidx.compose.foundation.layout.Box
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
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
  fun layout_appliedEllipsis() {
    composeTestRule.setContent {
      MaterialTheme {
        Surface {
          TestScreen(input = "soooooooooooooooooooooooloooooooooooooooooooongtext")
        }
      }
    }
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
    composeTestRule.onNodeWithText(
      "\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00...\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00\uD83D\uDE00"
    )
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
