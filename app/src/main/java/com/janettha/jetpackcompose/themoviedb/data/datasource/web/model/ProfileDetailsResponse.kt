package com.janettha.jetpackcompose.themoviedb.data.datasource.web.model

import com.google.gson.annotations.SerializedName

data class ProfileDetailsResponse (
    @SerializedName("avatar") var avatar: Avatar? = Avatar(),
    @SerializedName("id") var id: Int? = null,
    @SerializedName("iso_639_1") var iso6391: String? = null,
    @SerializedName("iso_3166_1") var iso31661: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("include_adult") var includeAdult: Boolean? = null,
    @SerializedName("username") var username: String? = null
)

data class Avatar(
    @SerializedName("gravatar") var gravatar: Gravatar? = Gravatar(),
    @SerializedName("tmdb") var tmdb: Tmdb? = Tmdb()
)

data class Gravatar(@SerializedName("hash") var hash: String? = null)

data class Tmdb(@SerializedName("avatar_path") var avatarPath: String? = null)
