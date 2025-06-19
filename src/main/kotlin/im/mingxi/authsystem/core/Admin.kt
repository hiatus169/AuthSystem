package im.mingxi.authsystem.core

import im.mingxi.authsystem.Config
import im.mingxi.authsystem.interfaces.AdminDirector
import im.mingxi.authsystem.util.Env
import im.mingxi.authsystem.util.FileUtil
import kotlinx.serialization.json.*
import java.io.File
import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

class Admin : AdminDirector {
    override fun setUserData(id: String, loginId: String, key: String, value: String): String {
        val f = File("${Env.adminDir}/data.json")
        if (!f.exists()) return buildJsonObject {
            put("code", 500)
            put("message", "id dont exists")
            put("time", LocalDateTime.now().toString())
        }.toString()

        val mJson = Json.parseToJsonElement(f.readText(Charsets.UTF_8)).jsonObject
        if (!mJson.containsKey(id)) {
            return buildJsonObject {
                put("code", 403)
                put("message", "id dont exists")
                put("time", LocalDateTime.now().toString())
            }.toString()
        }

        val userFile = File("${Env.userDir}/${loginId}/data.json")
        if (!userFile.exists()) return buildJsonObject {
            put("code", 404)
            put("message", "user dont exists")
            put("time", LocalDateTime.now().toString())
        }.toString()

        val userJson = Json.parseToJsonElement(userFile.readText(Charsets.UTF_8)).jsonObject
        val userMap = userJson.toMutableMap()
        userMap[key] = JsonPrimitive(value)
        userFile.writeText(JsonObject(userMap).toString(), StandardCharsets.UTF_8)

        return buildJsonObject {
            put("code", 200)
            put("message", "set successful")
            put("time", LocalDateTime.now().toString())
        }.toString()
    }

    override fun addAdmin(secretKey: String, id: String, name: String): String {
        if (secretKey != Config.secretKey) return buildJsonObject {
            put("code", 401)
            put("message", "Unknown secret key")
            put("time", LocalDateTime.now().toString())
        }.toString()

        val f = File("${Env.adminDir}/data.json")
        if (!f.exists()) f.createNewFile()

        val data = FileUtil.readFileString(f)
        val mJson = Json.parseToJsonElement(data).jsonObject

        if (mJson.containsKey(name)) return buildJsonObject {
            put("code", 409)
            put("message", "add failed, since id already exist")
            put("time", LocalDateTime.now().toString())
        }.toString()

        val map = mJson.toMutableMap()
        map[id] = buildJsonObject {
            put("name", name)
            put("id", id)
            put("action", "")
            put("recentActTime", "")
        }

        f.writeText(JsonObject(map).toString(), StandardCharsets.UTF_8)

        return buildJsonObject {
            put("code", 200)
            put("message", "add successful")
            put("time", LocalDateTime.now().toString())
        }.toString()
    }

    override fun deleteAdmin(secretKey: String, id: String, name: String): String {
        if (secretKey != Config.secretKey) return buildJsonObject {
            put("code", 401)
            put("message", "Unknown secret key")
            put("time", LocalDateTime.now().toString())
        }.toString()

        val f = File("${Env.adminDir}/data.json")
        if (!f.exists()) f.createNewFile()

        val data = FileUtil.readFileString(f)
        val mJson = Json.parseToJsonElement(data).jsonObject

        if (!mJson.containsKey(name)) return buildJsonObject {
            put("code", 409)
            put("message", "delete failed, since id already exist")
            put("time", LocalDateTime.now().toString())
        }.toString()

        val map = mJson.toMutableMap()
        map.remove(id)

        f.writeText(JsonObject(map).toString(), StandardCharsets.UTF_8)

        return buildJsonObject {
            put("code", 200)
            put("message", "delete successful")
            put("time", LocalDateTime.now().toString())
        }.toString()
    }

}