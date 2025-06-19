package im.mingxi.authsystem.bean

import kotlinx.serialization.Serializable

@Serializable
data class UserBean(val loginId: String,val isBan: Boolean,val benReason : String, val authDate: String, val isAuthed: Boolean)
