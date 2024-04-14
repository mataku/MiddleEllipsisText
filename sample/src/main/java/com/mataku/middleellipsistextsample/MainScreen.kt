package com.mataku.middleellipsistextsample

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mataku.middleellipsistextsample.component.ContentHeader

@Composable
fun MainScreen(
  navigateToMaterial: () -> Unit,
  navigateToMaterial3: () -> Unit
) {
  Column(modifier = Modifier.fillMaxSize()) {
    ContentHeader(text = "MiddleEllipsisText Sample")

    ContentCell(
      title = "Material",
      onTapCell = navigateToMaterial
    )

    ContentCell(
      title = "Material3",
      onTapCell = navigateToMaterial3
    )
  }
}

@Composable
private fun ContentCell(
  title: String,
  onTapCell: () -> Unit
) {
  Column(
    modifier = Modifier
      .fillMaxWidth()
      .height(64.dp)
      .clickable {
        onTapCell.invoke()
      }
      .padding(horizontal = 16.dp),
    verticalArrangement = Arrangement.SpaceEvenly
  ) {
    Text(
      text = title,
      style = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        letterSpacing = 0.15.sp,
      )
    )
  }
}
