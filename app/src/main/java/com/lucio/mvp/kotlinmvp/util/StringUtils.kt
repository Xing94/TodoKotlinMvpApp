package com.lucio.mvp.kotlinmvp.util

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.experimental.and

/**
 * String工具类
 */

object StringUtils {

    /**
     * 判断文本是否为空

     * @param text 待判断文本
     * *
     * @return true:为空,false:不为空
     */
    fun isEmpty(text: String?): Boolean {
        if (text == null) return true
        if (text.length == 0) return true
        for (i in 0..text.length - 1) {
            val c = text[i]
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false
            }
        }
        return true
    }

    /**
     * 进行MD5加密

     * @param info 要加密的信息
     * *
     * @return String 加密后的字符串
     */
    fun encryptToMD5(info: String): String {
        var digesta: ByteArray? = null
        try {
            // 得到一个md5的消息摘要
            val alga = MessageDigest.getInstance("md5")
            // 添加要进行计算摘要的信息
            alga.update(info.toByteArray())
            // 得到该摘要
            digesta = alga.digest()
        } catch (e: NoSuchAlgorithmException) {
            LogUtil.e(e)
        }

        // 将摘要转为字符串
        val rs = byte2hex(digesta!!)
        return rs
    }

    /**
     * 将二进制转化为16进制字符串

     * @param b 二进制字节数组
     * *
     * @return String
     */
    fun byte2hex(b: ByteArray): String {
        var hs = ""
        var stmp: String?
        for (n in b.indices) {
            stmp = Integer.toHexString((b[n] and 0XFF.toByte()).toInt())
            if (stmp.length == 1) {
                hs = hs + "0" + stmp
            } else {
                hs = hs + stmp
            }
        }
        return hs
    }

    /**
     * 判断是否为数字
     * @param text
     * *
     * @return
     */
    fun isNumber(text: String): Boolean {
        try {
            val result = java.lang.Long.parseLong(text)
        } catch (e: Exception) {
            return false
        }

        return true
    }

    /**
     * 判断是否为验证码
     * @param text
     * *
     * @return
     */
    fun isVerify(text: String): Boolean {
        if (isNumber(text) && text.length == 6) {
            return true
        }
        return false
    }

}
