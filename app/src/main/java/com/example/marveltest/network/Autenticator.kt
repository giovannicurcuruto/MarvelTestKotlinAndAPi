package com.example.marveltest.network

class Autenticator {

    val ts = "1655511747473"
    val publicKey = "982aa73aea2266c39c2cf93cea695e34"
    var hash = "723a0b0ec0c4050c7d836d82f6019788"

    fun setApiInfo(): String {
        val apiData = ts + publicKey + hash
        return apiData
    }

}