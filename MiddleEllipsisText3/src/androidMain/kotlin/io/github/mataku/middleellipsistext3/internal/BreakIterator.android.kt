package io.github.mataku.middleellipsistext3.internal

internal class AndroidBreakIterator : BreakIterator {

  private val instance = android.icu.text.BreakIterator.getCharacterInstance()

  override fun next(): Int {
    return instance.next()
  }

  override fun makeCharacterInstance(): BreakIterator {
    return this
  }

  override fun setText(text: String?) {
    instance.setText(text)
  }

  override fun current(): Int {
    return instance.current()
  }
}

actual fun getBreakIterator(): BreakIterator = AndroidBreakIterator()
