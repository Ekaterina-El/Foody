package com.elka.foody.data

import com.google.gson.Gson

object CategoriesAPI {
  const val API_URL = "https://run.mocky.io/v3/058729bd-1402-4578-88de-265481fd7d54"
  val connector = HttpConnector

  fun getAll(onError: ((Throwable) -> Unit) = {}, onSuccess: (CategoriesFeed) -> Unit){
    val a = connector.createRequest(API_URL).map { Gson().fromJson(it, CategoriesFeed::class.java) }
      .subscribe({ feed -> onSuccess(feed)}) {
        error -> onError(error)
      }
  }
}