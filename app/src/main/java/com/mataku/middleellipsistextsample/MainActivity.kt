package com.mataku.middleellipsistextsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
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
    ContentHeader(text = "Demo")

    Spacer(modifier = Modifier.height(32.dp))

    Text(text = "ellipsis applied", fontSize = 20.sp, fontWeight = FontWeight.Bold)

    MiddleEllipsisText(
      text = "soooooooooooooooooooooooloooooooooooooooooooongtext",
    )

    Spacer(modifier = Modifier.height(32.dp))

    Text(
      text = "ellipsis applied with horizontal margin",
      fontSize = 20.sp,
      fontWeight = FontWeight.Bold
    )

    MiddleEllipsisText(
      text = "soooooooooooooooooooooooloooooooooooooooooooongtext",
      modifier = Modifier.padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(32.dp))

    Text(text = "with multibyte string", fontSize = 20.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(4.dp))

    MiddleEllipsisText(
      text = "soooooooooooooooooooooooloooooooooooooooooooongtextツツツツツツ",
    )

    Spacer(modifier = Modifier.height(32.dp))

    Text(text = "with short text", fontSize = 20.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(4.dp))

    MiddleEllipsisText(
      text = "sooooooooooツツツツツツ",
    )

    Spacer(modifier = Modifier.height(32.dp))

    Text(text = "with emoji", fontSize = 20.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(4.dp))

    val emojis2 = "\uD83D\uDE00".repeat(20)

    MiddleEllipsisText(
      text = emojis2,
    )

    Spacer(modifier = Modifier.height(32.dp))
    Text(text = "with multi-codepoints emoji", fontSize = 20.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(4.dp))

    val emojis = "\uD83C\uDDE7\uD83C\uDDEB".repeat(20)
    MiddleEllipsisText(
      text = emojis,
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

@Composable
fun ContentHeader(
  text: String
) {
  Surface(
    elevation = 2.dp
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
    ) {
      Text(
        text = text,
        style = TextStyle(
          fontWeight = FontWeight.Medium,
          fontSize = 24.sp,
          letterSpacing = 0.15.sp
        ),
        modifier = Modifier
          .padding(16.dp)
      )
    }
  }
}
