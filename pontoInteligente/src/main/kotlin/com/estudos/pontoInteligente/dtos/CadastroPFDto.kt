package com.estudos.pontoInteligente.dtos

data class CadastroPFDto (
    val nome: String = "",
    val email: String = "",
    val senha: String = "",
    val cpf: String = "",
    val cnpj: String = "",
    val empresaId: String = "",
    val valorHora: String? = null,
    val qtdHorasTrabalhoDia: String? = null,
    val qtdHorasAlmoco: String? = null,
    val id: String? = null
)