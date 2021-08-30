package org.ksug.forum.web

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(private val secretkey: TestComponent) {

    @GetMapping("/test")
    fun test(){
        secretkey.getNewKey2()

    }

}