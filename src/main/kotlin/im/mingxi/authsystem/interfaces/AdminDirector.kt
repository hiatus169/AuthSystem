package im.mingxi.authsystem.interfaces

interface AdminDirector {
    fun setUserData(id: String, loginId: String, key: String, value: String): String
    fun addAdmin(secretKey: String, id: String, name: String): String
    fun deleteAdmin(secretKey: String, id: String, name: String): String
}