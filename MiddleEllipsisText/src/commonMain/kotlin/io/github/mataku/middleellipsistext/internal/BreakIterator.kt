package io.github.mataku.middleellipsistext.internal

interface BreakIterator {
  fun next(): Int

  fun makeCharacterInstance(): BreakIterator

  fun setText(text: String?)

  fun current(): Int

  companion object {
    const val DONE = -1
  }
}

expect fun getBreakIterator(): BreakIterator
