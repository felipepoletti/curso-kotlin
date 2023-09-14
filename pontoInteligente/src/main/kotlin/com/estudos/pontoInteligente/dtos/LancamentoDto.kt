package com.estudos.pontoInteligente.dtos

import org.jetbrains.annotations.NotNull

class LancamentoDto (
        @NotNull
        val data: String? = null,

        @NotNull
        val tipo: String? = null,

        val descricao: String? = null,
        val localizacao: String? = null,
        val funcionarioId: String,
        var id: String? = null
)