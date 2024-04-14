package com.mataku.middleellipsistextsample.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ContentHeader(
  text: String,
  onBackPressed: (() -> Unit)? = null
) {
  Surface(
    elevation = 2.dp
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
    ) {
      if (onBackPressed != null) {
        Image(
          painter = rememberVectorPainter(image = Icons.AutoMirrored.Default.ArrowBack),
          contentDescription = "back",
          modifier = Modifier
            .align(alignment = Alignment.CenterStart)
            .clickable {
              onBackPressed.invoke()
            }
            .padding(start = 12.dp)
        )
      }

      Text(
        text = text,
        style = TextStyle(
          fontWeight = FontWeight.Medium,
          fontSize = 24.sp,
          letterSpacing = 0.15.sp
        ),
        modifier = Modifier
          .padding(vertical = 16.dp)
          .align(alignment = Alignment.Center)
      )
    }
  }
}

@Preview
@Composable
private fun ContentHeaderSample() {
  MaterialTheme {
    Surface {
      ContentHeader(text = "MiddleEllipsisText Sample", onBackPressed = {})
    }
  }
}
