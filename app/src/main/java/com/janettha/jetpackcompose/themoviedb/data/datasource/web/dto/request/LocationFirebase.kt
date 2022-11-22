package com.janettha.jetpackcompose.themoviedb.data.datasource.web.dto.request

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class LocationFirebase(
    var latitude: String = "",
    var longitude: String = "",
    var currentdate: String = "",
)