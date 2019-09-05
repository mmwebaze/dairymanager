package dev.mwebaze.dairymanager.service

class LoginService : LoginServiceInterface {

    override fun login(username: String, userPassword: String): Boolean {
        println("I am logged in: ${username}");

        if (username.equals("michael") && userPassword.equals("1234"))
            return true

        return true
    }
}