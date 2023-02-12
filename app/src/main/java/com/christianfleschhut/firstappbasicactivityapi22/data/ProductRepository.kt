package com.christianfleschhut.firstappbasicactivityapi22.data

import android.content.Context

class ProductRepository {

    fun getTextFromResource(context: Context, resourceId: Int): String {
        return context.resources.openRawResource(resourceId)
            .bufferedReader()
            .use {
                it.readText()
            }
    }

}