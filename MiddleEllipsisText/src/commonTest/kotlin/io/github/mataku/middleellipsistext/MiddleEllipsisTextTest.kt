//package io.github.mataku.middleellipsistext
//
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Surface
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.test.ExperimentalTestApi
//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.compose.ui.test.onNodeWithText
//import androidx.compose.ui.test.runComposeUiTest
//import kotlin.test.Test
//
//@OptIn(ExperimentalTestApi::class)
//class MiddleEllipsisTextTest {
//  @Test
//  fun way2() = runComposeUiTest {
//    setContent {
//      TestScreen()
//    }
//    onNodeWithText("あいうえお".repeat(10)).assertIsDisplayed()
//  }
//}
//
//@Composable
//private fun TestScreen() {
//  MaterialTheme {
//    Surface {
//      Column(modifier = Modifier.fillMaxSize()) {
//        MiddleEllipsisText(
//          text = "あいうえお".repeat(10)
//        )
//      }
//    }
//  }
//}
