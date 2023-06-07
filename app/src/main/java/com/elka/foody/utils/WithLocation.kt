package com.elka.foody.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.*
import java.util.*
import java.util.concurrent.Executor
import java.util.function.Consumer


class WithLocation(private val context: Context) {
  private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
  private val geoCoder = Geocoder(context, Locale.getDefault())

  @SuppressLint("MissingPermission", "NewApi")
  fun requestLocation(onSuccess: (String) -> Unit, onError: () -> Unit) {
    onSuccess("Москва")
    // TODO: get city name by current location
  }
}