package im.mingxi.authsystem

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class AuthSystemApplication

fun main(args: Array<String>) {
    runApplication<AuthSystemApplication>(*args)
}