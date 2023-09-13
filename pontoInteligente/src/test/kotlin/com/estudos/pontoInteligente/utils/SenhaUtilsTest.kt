package com.estudos.pontoInteligente.utils

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class SenhaUtilsTest {
    private val SENHA = "password"
    private val bCryptEncoder = BCryptPasswordEncoder()

    @Test
    fun testGerarHashSenha() {
        val hash = SenhaUtils().gerarBcrypt(SENHA)
        Assertions.assertTrue(bCryptEncoder.matches(SENHA, hash))
    }
}