package dev.mwebaze.diarymanager.service

interface LoginServiceInterface {
    fun login(username: String, userPassword: String): Boolean
}