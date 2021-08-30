
package org.ksug.forum.web
import org.ksug.forum.Secretkey
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
@Component
class DbEncryptionConverter: AttributeConverter<String?, String?> {



       // var key : String = ""
companion object{
     private var Secretkey: String = ""
}

    @Autowired
    fun setSecretkey(secretkey : Secretkey){
        DbEncryptionConverter.Secretkey = secretkey.key
        secretkey.also { DbEncryptionConverter.Secretkey = it.key }
    }


    override fun convertToDatabaseColumn(text: String?): String? {
        println(">>>>>>>>>>${getNewKey()}")
        val newKey = SecretKeySpec(getNewKey(), "AES")
        val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, newKey)
        val encoder = Base64.getMimeEncoder()

        return text?.let {
            encoder.encodeToString(cipher.doFinal(it.toByteArray(Charsets.UTF_8)))
        }
    }

    override fun convertToEntityAttribute(encrypted: String?): String? {
        println(">>>>>>>>>>${getNewKey()}")
        val newKey = SecretKeySpec(getNewKey(), "AES")
        val cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING")
        cipher.init(Cipher.DECRYPT_MODE, newKey)

        val decoder = Base64.getMimeDecoder()

        return try {
            encrypted?.let {
                val decodeByte = cipher.doFinal(decoder.decode(it.toByteArray(Charsets.UTF_8)))
                decodeByte.toString(Charsets.UTF_8)
            }
        } catch (e: Exception) {
            encrypted
        }


    }




    //키값 생성
    fun getNewKey(): ByteArray {
        //TestComponent().getNewKey2()
        val keyBytes = ByteArray(16)
        //시크릿키 추후 설정정보에서 가져오는것으로 변경예정//

        val tempKey = Secretkey
        println(">>>>>>>>>$tempKey")
        val oldKeyBytes = tempKey.toByteArray(Charsets.UTF_8)



        //oldKeyBytes.copyInto(keyBytes)
        return keyBytes


    }




}

