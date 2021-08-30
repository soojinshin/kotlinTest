package org.ksug.forum.web


import org.ksug.forum.ForumBootApplication
import org.ksug.forum.Secretkey

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import kotlin.reflect.jvm.internal.impl.javax.inject.Inject

@Component
class TestComponent(

) {

    @Autowired lateinit var  secretkey: Secretkey

   fun getNewKey2() : String{
        val key = secretkey.key
        println(">>>>>>>$key")
        return key
    }


}
