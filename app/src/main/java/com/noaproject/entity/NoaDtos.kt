package com.noaproject.entity

class NoaDtos {

    class Tables(
        val name: String =""
    )

    class TablesDetail(
        val name: String =""
    )

    class TablesNew(
        val name: String ="",
        val items: List<TablesDetailNew>
    )
    class TablesDetailNew(
        val title: String? ="",
        val description : String? = "",
        val type: String? = "",
        val imageItem: String? = "",
        val publishers: List<Publisher>
    )
    class Publisher(
        val publisherName: String? = "",
        val publisherImage: String? = ""
    )

}