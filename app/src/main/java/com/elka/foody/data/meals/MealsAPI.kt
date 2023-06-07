package com.elka.foody.data.meals

import com.elka.foody.data.HttpConnector
import com.google.gson.Gson

object MealsAPI {
  private const val API_URL = "https://run.mocky.io/v3/c7a508f2-a904-498a-8539-09d96785446e"
  val connector = HttpConnector

  fun getAll(onError: ((Throwable) -> Unit) = {}, onSuccess: (MealsFeed) -> Unit) {
    val a = connector.createRequest(API_URL).map { Gson().fromJson(it, MealsFeed::class.java) }
        .subscribe({ feed -> onSuccess(feed) }) { error ->
          onError(error)
        }
  }
}