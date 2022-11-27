package com.noaproject.api

private const val URL_DEFAULT = "https://api.newsoveraudio.com/v1/feed/section/"

fun convertToNoaApiTableDetail(link: String): String {
    val defaultRegex =
        URL_DEFAULT.toRegex()
    val delim = "?"
    var new = link.replace(defaultRegex, "")
    new = new.split(delim)[0]
    return new
}