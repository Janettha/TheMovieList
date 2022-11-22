package com.janettha.jetpackcompose.themoviedb.data.datasource.web.dto.request

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class SnapShot(
    var id: String = "",
    var title: String = "",
    var photoUrl: String ="",
)