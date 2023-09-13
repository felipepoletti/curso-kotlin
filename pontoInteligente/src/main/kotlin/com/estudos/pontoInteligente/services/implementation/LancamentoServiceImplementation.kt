package com.estudos.pontoInteligente.services.implementation

import com.estudos.pontoInteligente.documents.Lancamento
import com.estudos.pontoInteligente.repositories.LancamentoRepository
import com.estudos.pontoInteligente.services.LancamentoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class LancamentoServiceImplementation(val lancamentoRepository: LancamentoRepository) : LancamentoService {
    override fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: PageRequest): Page<Lancamento> = lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest)

    override fun buscarPorId(id: String): Lancamento? = lancamentoRepository.findById(id).get()

    override fun persistir(lancamento: Lancamento): Lancamento = lancamentoRepository.save(lancamento)

    override fun remover(id: String) = lancamentoRepository.deleteById(id)
}