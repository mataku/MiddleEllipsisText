package io.github.mataku.middleellipsistext

import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.TextUnit
import kotlin.streams.toList

@Composable
fun MiddleEllipsisText(
  text: String,
  modifier: Modifier = Modifier,
  color: Color = Color.Unspecified,
  fontSize: TextUnit = TextUnit.Unspecified,
  fontStyle: FontStyle? = null,
  fontWeight: FontWeight? = null,
  fontFamily: FontFamily? = null,
  letterSpacing: TextUnit = TextUnit.Unspecified,
  textDecoration: TextDecoration? = null,
  textAlign: TextAlign? = null,
  lineHeight: TextUnit = TextUnit.Unspecified,
  softWrap: Boolean = true,
  onTextLayout: (TextLayoutResult) -> Unit = {},
  style: TextStyle = LocalTextStyle.current,
  ellipsisChar: Char = '.',
  ellipsisCharCount: Int = 3
) {
  if (text.isEmpty()) {
    Text(
      text = text,
      color = color,
      fontSize = fontSize,
      fontStyle = fontStyle,
      fontWeight = fontWeight,
      fontFamily = fontFamily,
      letterSpacing = letterSpacing,
      textDecoration = textDecoration,
      textAlign = textAlign,
      lineHeight = lineHeight,
      softWrap = softWrap,
      onTextLayout = onTextLayout,
      style = style
    )
  } else {
    var textLayoutResult: TextLayoutResult? = null
    val ellipsisText = ellipsisChar.toString().repeat(ellipsisCharCount)

    SubcomposeLayout(modifier) { constraints ->
      subcompose("MiddleEllipsisText_calculate") {
        Text(
          text = text + ellipsisChar,
          color = color,
          fontSize = fontSize,
          fontStyle = fontStyle,
          fontWeight = fontWeight,
          fontFamily = fontFamily,
          letterSpacing = letterSpacing,
          textDecoration = textDecoration,
          textAlign = textAlign,
          lineHeight = lineHeight,
          softWrap = softWrap,
          onTextLayout = { textLayoutResult = it },
          style = style
        )
      }[0].measure(Constraints())

      textLayoutResult ?: return@SubcomposeLayout layout(0, 0) {}

      val placeable = subcompose("MiddleEllipsisText_apply") {
        val codePoints = text.codePoints().toList()
        val mappedStrings = codePoints.map {
          String(Character.toChars(it))
        }
        val combinedText = remember(text, ellipsisText, textLayoutResult) {
          if (textLayoutResult!!.getBoundingBox(text.lastIndex).right <= constraints.maxWidth) {
            text
          } else {
            val textWidth = textLayoutResult!!.getBoundingBox(text.lastIndex + 1).width
            val ellipsisTextWidth: Float = textWidth * ellipsisCharCount
            val remainingWidth = constraints.maxWidth - ellipsisTextWidth
            val textFromStart = mutableListOf<String>()
            val textFromEnd = mutableListOf<String>()
            var leftPoint = 0
            var rightPoint = mappedStrings.last().length - 1
            var mappedLeftIndex = 0
            var mappedRightIndex = mappedStrings.lastIndex
            var leftTextWidth = 0F
            var rightTextWidth = 0F

            kotlin.run {
              repeat(codePoints.size) {
                val leftPosition = leftPoint
                val rightPosition = text.lastIndex - rightPoint
                val leftTextBoundingBox = textLayoutResult!!.getBoundingBox(leftPosition)
                val rightTextBoundingBox = textLayoutResult!!.getBoundingBox(rightPosition)

                if (leftPosition >= rightPosition) {
                  return@run
                }

                // For multibyte string handling
                if (leftTextWidth <= rightTextWidth && leftTextWidth + leftTextBoundingBox.width + rightTextWidth <= remainingWidth) {
                  val leftChar = mappedStrings[mappedLeftIndex]
                  textFromStart.add(leftChar)
                  leftTextWidth += leftTextBoundingBox.width
                  leftPoint += leftChar.length
                  mappedLeftIndex += 1
                } else if (leftTextWidth >= rightTextWidth && leftTextWidth + rightTextWidth + rightTextBoundingBox.width <= remainingWidth) {
                  val rightChar = mappedStrings[mappedRightIndex]
                  textFromEnd.add(0, rightChar)
                  rightTextWidth += rightTextBoundingBox.width
                  rightPoint += rightChar.length
                  mappedRightIndex -= 1
                } else {
                  return@run
                }
              }
            }
            textFromStart.joinToString(separator = "") + ellipsisText + textFromEnd
              .joinToString(
                separator = ""
              )
          }
        }
        Text(
          text = combinedText,
          color = color,
          fontSize = fontSize,
          fontStyle = fontStyle,
          fontWeight = fontWeight,
          fontFamily = fontFamily,
          letterSpacing = letterSpacing,
          textDecoration = textDecoration,
          textAlign = textAlign,
          lineHeight = lineHeight,
          softWrap = softWrap,
          maxLines = 1,
          onTextLayout = onTextLayout,
          style = style
        )
      }[0].measure(constraints)

      layout(placeable.width, placeable.height) {
        placeable.place(0, 0)
      }
    }
  }
}
