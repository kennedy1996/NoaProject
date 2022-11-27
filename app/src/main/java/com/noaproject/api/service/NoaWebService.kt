package com.noaproject.api.service

import android.util.Log
import com.noaproject.api.convertToNoaApiTableDetail
import com.noaproject.entity.NoaDtos


private const val TAG = "NoaWebService"

class NoaWebService {
    private val noaApiService: NoaApi =
        InicializatorRetrofit().noaApi

    suspend fun getTable(): List<NoaDtos.TablesNew>{
        var returnV =  mutableListOf<NoaDtos.TablesNew>()
        try {
            val noaGetTableReturn = noaApiService
                .getTables()
            val tabs = noaGetTableReturn.data.tabs
            tabs.forEach {
                val link = it.link.url
                var urlReady = convertToNoaApiTableDetail(link)
                val detail = tableDetails(urlReady)
                returnV.add(
                    NoaDtos.TablesNew(
                        it.name,
                        detail
                    )
                )
            }
        } catch (e: Exception) {
            Log.e(TAG, "noaApiService Table: ", e)
        }
        return returnV
    }

    suspend fun tableDetails(url: String): List<NoaDtos.TablesDetailNew> {
        var returnV =  mutableListOf<NoaDtos.TablesDetailNew>()
        try {
            val noatableDetailsReturn = noaApiService
                .getTablesDetails(url)
            val content = noatableDetailsReturn.data.content
            content.forEach { content ->
                val publishers =  mutableListOf<NoaDtos.Publisher>()
                if(content.publisher==null && content.publishers!=null)
                    content.publishers.forEach { publishers.add(
                        NoaDtos.Publisher(
                            it.name,
                            it.smallImage
                        )
                    )}
                else if(content.publisher!=null && content.publishers==null)
                    publishers.add(
                        NoaDtos.Publisher(
                            content.publisher.name,
                            content.publisher.smallImage
                        )
                    )
                returnV.add(
                    NoaDtos.TablesDetailNew(
                        content.name,
                        content.description,
                        content.type,
                        content.image,
                        publishers
                    )
                )
            }

        } catch (e: Exception) {
            Log.e(TAG, "noaApiService Table Details: ", e)
        }
        return returnV
    }
}