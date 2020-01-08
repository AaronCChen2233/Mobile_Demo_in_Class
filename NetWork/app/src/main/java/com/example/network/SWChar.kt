package com.example.network

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

class Data(var count: Int, var results: Array<SWChar>) {
    class Deserializer : ResponseDeserializable<Data> {
        override fun deserialize(content: String) =
            Gson().fromJson(content, Data::class.java)
    }
}

data class SWChar(var name: String, var height: Int, var mass: Int) {
    class Deserializer : ResponseDeserializable<SWChar> {
        override fun deserialize(content: String): SWChar =
            Gson().fromJson(content, SWChar::class.java)
    }
}