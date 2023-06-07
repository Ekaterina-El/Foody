package com.elka.foody.utils

enum class Work {
  LOAD_CATEGORIES,
  LOAD_MEALS
}

fun hasLoads(works: List<Work>, w: MutableList<Work>) = when {
  w.isEmpty() -> false
  else -> w.map { item -> if (works.contains(item)) 1 else 0 }.reduce { a, b -> a + b } > 0
}
