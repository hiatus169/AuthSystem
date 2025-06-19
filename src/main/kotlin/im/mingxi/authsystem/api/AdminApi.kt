package im.mingxi.authsystem.api

import im.mingxi.authsystem.core.Admin
import im.mingxi.authsystem.interfaces.AdminDirector
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/api")
class AdminApi : AdminDirector {
    val director: Admin = Admin()

    @GetMapping("/setUserData")
    override fun setUserData(
        @RequestParam(name = "id", required = true) id: String,
        @RequestParam(name = "loginId", required = true) loginId: String,
        @RequestParam(name = "key", required = true) key: String,
        @RequestParam(name = "value", required = true) value: String
    ): String {
        return director.setUserData(id, loginId, key, value)
    }

    @GetMapping("/addAdmin")
    override fun addAdmin(
        @RequestParam(name = "secretKey", required = true) secretKey: String,
        @RequestParam(name = "id", required = true) id: String,
        @RequestParam(name = "name", required = true) name: String
    ): String {
        return director.addAdmin(secretKey, id, name)
    }

    @GetMapping("/deleteAdmin")
    override fun deleteAdmin(
        @RequestParam(name = "secretKey", required = true) secretKey: String,
        @RequestParam(name = "id", required = true) id: String,
        @RequestParam(name = "name", required = true) name: String
    ): String {
        return director.deleteAdmin(secretKey, id, name)
    }

}