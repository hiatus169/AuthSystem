package im.mingxi.authsystem.interfaces

interface AuthDirector {
    fun getUserData(loginId: String): String
    fun getUserSize(): String
    fun uploadUser(data: String): String
}