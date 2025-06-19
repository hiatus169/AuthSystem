package im.mingxi.authsystem.api

import im.mingxi.authsystem.core.Director
import im.mingxi.authsystem.interfaces.AuthDirector
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.nio.charset.StandardCharsets
import java.util.Base64

@RestController
@RequestMapping("/api")
class AuthApi: AuthDirector {
    val director: Director = Director()

    @GetMapping("/getUserData")
    override fun getUserData(@RequestParam loginId: String): String {
        return director.getUserData(loginId)
    }
    @GetMapping("/uploadUser")
    override fun uploadUser(@RequestParam data: String): String {
        return director.uploadUser(data)
    }

    @GetMapping("/getUserSize")
    override fun getUserSize(): String {
        return director.getUserSize()
    }

}