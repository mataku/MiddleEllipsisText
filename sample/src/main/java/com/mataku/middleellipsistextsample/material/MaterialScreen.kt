package com.mataku.middleellipsistextsample.material

import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mataku.middleellipsistextsample.component.ContentHeader
import io.github.mataku.middleellipsistext.MiddleEllipsisText


@Composable
fun MaterialContent() {
  val dispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

  Column(
    modifier = Modifier.fillMaxSize()
  ) {
    ContentHeader(
      text = "Demo",
      onBackPressed = {
        dispatcher?.onBackPressed()
      }
    )

    Spacer(modifier = Modifier.height(32.dp))

    Text(text = "ellipsis applied", fontSize = 20.sp, fontWeight = FontWeight.Bold)

    MiddleEllipsisText(
      text = "soooooooooooooooooooooooooooooooooooooooooooloooooooooooooooooooongtext",
    )

    Spacer(modifier = Modifier.height(32.dp))

    Text(
      text = "ellipsis applied with horizontal margin",
      fontSize = 20.sp,
      fontWeight = FontWeight.Bold
    )

    MiddleEllipsisText(
      text = "sooooooooooooooooooooooooooooooooooooooooooooooloooooooooooooooooooongtext",
      modifier = Modifier.padding(horizontal = 16.dp)
    )

    Spacer(modifier = Modifier.height(32.dp))

    Text(text = "with multibyte string", fontSize = 20.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(4.dp))

    MiddleEllipsisText(
      text = "あいうえお".repeat(10),
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

    val emojis2 = "\uD83D\uDE00".repeat(40)

    MiddleEllipsisText(
      text = emojis2,
    )

    Spacer(modifier = Modifier.height(32.dp))
    Text(text = "with multi-codepoints emoji", fontSize = 20.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = Modifier.height(4.dp))

    val emojis = "\uD83C\uDDE7\uD83C\uDDEB".repeat(40)
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
      MaterialContent()
    }
  }
}
