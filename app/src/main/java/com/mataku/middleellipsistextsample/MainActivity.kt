package com.mataku.middleellipsistextsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.mataku.middleellipsistext.MiddleEllipsisText

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      MaterialTheme {
        Surface {
          MainContent()
        }
      }
    }
  }
}

@Composable
private fun MainContent() {
  Column(
    modifier = Modifier.fillMaxSize()
  ) {
    Text(text = "Demo", fontSize = 24.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(8.dp))

    MiddleEllipsisText(
      text = "soooooooooooooooooooooooloooooooooooooooooooongtext",
    )

    Spacer(modifier = Modifier.height(32.dp))

    Text(text = "Demo with multibyte string", fontSize = 24.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(8.dp))

    MiddleEllipsisText(
      text = "soooooooooooooooooooooooloooooooooooooooooooongtextツツツツツツ",
    )

    Spacer(modifier = Modifier.height(32.dp))

    Text(text = "Demo with not ellipsis", fontSize = 24.sp, fontWeight = FontWeight.Bold)

    MiddleEllipsisText(
      text = "sooooooooooツツツツツツ",
    )

  }
}

@Preview
@Composable
private fun MainContentPreview() {
  MaterialTheme {
    Surface {
      MainContent()
    }
  }
}
