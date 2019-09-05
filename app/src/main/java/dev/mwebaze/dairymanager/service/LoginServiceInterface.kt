package dev.mwebaze.dairymanager.service

interface LoginServiceInterface {
    fun login(username: String, userPassword: String): Boolean
}