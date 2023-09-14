package com.estudos.pontoInteligente.dtos

data class CadastroPJDto (
    val nome: String = "",
    val email: String = "",
    val senha: String = "",
    val cpf: String = "",
    val cnpj: String = "",
    val razaoSocial: String = "",
    val id: String? = null
)