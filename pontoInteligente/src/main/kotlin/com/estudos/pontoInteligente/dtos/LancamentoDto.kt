package com.estudos.pontoInteligente.dtos

class LancamentoDto (
        val data: String? = null,
        val tipo: String? = null,
        val descricao: String? = null,
        val localizacao: String? = null,
        val funcionarioId: String,
        var id: String? = null
)