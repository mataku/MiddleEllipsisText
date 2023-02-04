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
}

@Composable
private fun TestScreen(input: String) {
  Box(modifier = Modifier) {
    MiddleEllipsisText(text = input)
  }
}
