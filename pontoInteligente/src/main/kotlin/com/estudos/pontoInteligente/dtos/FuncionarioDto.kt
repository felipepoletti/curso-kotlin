package com.estudos.pontoInteligente.dtos

data class FuncionarioDto (
        val nome: String = "",
        val email: String = "",
        val senha: String? = null,
        val valorHora: String? = null,
        val qtdHorasTrabalhoDia: String? = null,
        val qtdHorasAlmoco: String? = null,
        val id: String? = null
)