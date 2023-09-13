package com.estudos.pontoInteligente.services

import com.estudos.pontoInteligente.documents.Lancamento
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest

interface LancamentoService  {

    fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: PageRequest) : Page<Lancamento>

    fun buscarPorId(id:String): Lancamento?

    fun persistir(lancamento: Lancamento): Lancamento

    fun remover(id: String)
}