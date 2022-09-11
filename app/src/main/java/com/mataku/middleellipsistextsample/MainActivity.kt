package com.mataku.middleellipsistextsample

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import io.github.mataku.middleellipsistext.MiddleEllipsisText

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
    setContent {
      MaterialTheme {
        Surface {
          MainContent(
            "sooooooooooooooooooooooloooooooooooongtext"
          )
        }
      }
    }
  }
}

@Composable
private fun MainContent(
  text: String
) {
  Box(modifier = Modifier.fillMaxSize()) {
    MiddleEllipsisText(
      text = text,
    )
  }
}

@Preview
@Composable
private fun MainContentPreview() {
  MaterialTheme {
    Surface {
      MainContent(LoremIpsum(1000).values.first().replace("\n", ""))
    }
  }
}

@Preview
@Composable
private fun MainContentMultibytePreview() {
  MaterialTheme {
    Surface {
      MainContent(LoremIpsum(1000).values.first().replace("\n", "") + "ツツツツツツ")
    }
  }
}
