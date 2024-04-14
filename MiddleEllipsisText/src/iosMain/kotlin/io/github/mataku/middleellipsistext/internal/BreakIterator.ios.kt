package io.github.mataku.middleellipsistext.internal

class IOSBreakIterator : BreakIterator {

  private val instance = org.jetbrains.skia.BreakIterator.makeCharacterInstance()

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

actual fun getBreakIterator(): BreakIterator = IOSBreakIterator()
