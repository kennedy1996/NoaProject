package com.noaproject.api.entity


class NoaApiDtos {


    class TablesApi(
        val data: Tables
    )
    class Tables(
        val tabs: List<TableData>
    )
    class TableData(
        val name: String,
        val link: TableDataLink
    )
    class TableDataLink(
        val url: String,
        val type: String
    )


    class TablesDetailsApi(
        val data: TablesDetailData
    )
    class TablesDetailData(
        val content: List<TablesDetailDataContent>
    )
    class TablesDetailDataContent(
        val id:Int,
        val name: String,
        val description : String,
        val type: String,
        val image: String,
        val publisher: Publishers?,
        val publishers: List<Publishers>?
    )
    class Publishers(
        val name: String,
        val smallImage: String
    )

}
