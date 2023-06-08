package com.elka.foody.utils

enum class Work {
  LOAD_CATEGORIES,
  LOAD_MEALS,
  LOAD_BASKET,
  CHANGE_COUNT_OF_MEALS,
  ADD_MEAL
}

fun hasLoads(works: List<Work>, w: MutableList<Work>) = when {
  w.isEmpty() -> false
  else -> w.map { item -> if (works.contains(item)) 1 else 0 }.reduce { a, b -> a + b } > 0
}
