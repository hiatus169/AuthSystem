package im.mingxi.authsystem.core

import im.mingxi.authsystem.bean.UserBean
import im.mingxi.authsystem.interfaces.AuthDirector
import im.mingxi.authsystem.util.Env
import im.mingxi.authsystem.util.FileUtil
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kotlinx.serialization.serializer
import java.io.File
import java.time.LocalDateTime

class Director : AuthDirector {

    override fun getUserData(loginId: String): String {
        val file = File("${Env.userDir}/${loginId}/data.json")
        // Matched userData and return
        if (file.exists()) return FileUtil.readFileString(file)
        // Dont find userData
        return buildJsonObject {
            put("code", 404)
            put("message", "User not found")
            put("time", LocalDateTime.now().toString())
        }.toString()
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun getUserSize(): String {
        return File("${Env.userDir}/").listFiles().count().toString()
    }

    override fun uploadUser(data: String): String {
        val file = File("${Env.userDir}/$data")

        if (file.exists()) return buildJsonObject {
            put("code", 409)
            put("message", "User already exists")
            put("time", LocalDateTime.now().toString())
        }.toString()

        file.mkdir()

        // build userData
        val json = Json.encodeToString(serializer(), UserBean(data, isBan = false, benReason = "", authDate = "", isAuthed = false))
        FileUtil.writeToFile("${Env.userDir}/${data}/data.json", json)
        return buildJsonObject {
            put("code", 200)
            put("message","Sign up successful")
        }.toString()
    }
}