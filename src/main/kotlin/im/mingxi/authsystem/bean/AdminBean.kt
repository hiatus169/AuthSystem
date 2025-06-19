package im.mingxi.authsystem.bean

import kotlinx.serialization.Serializable

@Serializable
data class AdminBean(val name: String, val id: String, val action: String, val recentActTime: String)
