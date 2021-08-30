package org.ksug.forum

import org.ksug.forum.domain.PasswordHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ApplicationListener
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.core.env.Environment
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import swagger.Swagger

fun main(args: Array<String>) {
    SpringApplication.run(ForumBootApplication::class.java, *args)
}

@SpringBootApplication
@Import(Swagger::class)
class ForumBootApplication {
    
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        PasswordHelper.passwordEncoder = NoOpPasswordEncoder.getInstance()
        return PasswordHelper.passwordEncoder
    }



}


@ConfigurationProperties("data.secret")
@Configuration
data class Secretkey (
    var key : String = ""
)