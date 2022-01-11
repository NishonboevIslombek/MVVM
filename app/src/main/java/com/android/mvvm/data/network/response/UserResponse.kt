package com.android.mvvm.data.network.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
class UserResponse {

    @Json(name = "total_count")
    var totalCount: Long? = null

    @Json(name = "incomplete_results")
    var inCompleteResult: Boolean? = null

    @Json(name = "items")
    var items: List<User>? = null

    @JsonClass(generateAdapter = true)
    class User : Serializable {

        @Json(name = "id")
        var id: Long? = null

        @Json(name = "login")
        var login: String? = null

        @Json(name = "node_id")
        var nodeId: String? = null

        @Json(name = "avatar_url")
        var avatarUrl: String? = null

        @Json(name = "gravatar_id")
        var grAvatarId: String? = null

        @Json(name = "url")
        var url: String? = null

        @Json(name = "html_url")
        var htmlUrl: String? = null

        @Json(name = "followers_url")
        var followersUrl: String? = null

        @Json(name = "gists_url")
        var gistsUrl: String? = null

        @Json(name = "starred_url")
        var starredUrl: String? = null

        @Json(name = "subscriptions_url")
        var subscriptionsUrl: String? = null

        @Json(name = "organizations_url")
        var organizationsUrl: String? = null

        @Json(name = "repos_url")
        var reposUrl: String? = null

        @Json(name = "events_url")
        var eventsUrl: String? = null

        @Json(name = "received_events_url")
        var receivedEventsUrl: String? = null

        @Json(name = "type")
        var type: String? = null

        @Json(name = "site_admin")
        var siteAdmin: Boolean? = null

        @Json(name = "score")
        var score: Double? = null

        @Json(name = "name")
        var name: String? = null

        @Json(name = "company")
        var company: String? = null

        @Json(name = "blog")
        var blog: String? = null

        @Json(name = "location")
        var location: String? = null

        @Json(name = "twitter_username")
        var twitterUsername: String? = null

        @Json(name = "public_repos")
        var sizeRepos: String? = null

        @Json(name = "public_gists")
        var sizeGists: String? = null

        @Json(name = "followers")
        var followers: String? = null

        @Json(name = "following")
        var following: String? = null

        @Json(name = "created_at")
        var createdAt: String? = null

    }
}