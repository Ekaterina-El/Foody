package com.elka.foody.utils

fun Int.splitBy3(n: Int): String {
  var number = n

  val s = mutableListOf<Int>()
  if (number == 0) s.add(number)
  while(number % 1000 != 0) {
    s.add(number % 1000)
    number /= 1000
  }
  return s.reversed().joinToString(" ")
}