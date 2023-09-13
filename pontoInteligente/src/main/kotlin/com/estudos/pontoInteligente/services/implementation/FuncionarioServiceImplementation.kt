package com.estudos.pontoInteligente.services.implementation

import com.estudos.pontoInteligente.documents.Funcionario
import com.estudos.pontoInteligente.repositories.FuncionarioRepository
import com.estudos.pontoInteligente.services.FuncionarioService
import org.springframework.stereotype.Service

@Service
class FuncionarioServiceImplementation(val funcionarioRepository: FuncionarioRepository) : FuncionarioService {
    override fun persistir(funcionario: Funcionario): Funcionario =
            funcionarioRepository.save(funcionario)

    override fun buscarPorCpf(cpf: String): Funcionario? = funcionarioRepository.findByCpf(cpf)

    override fun buscarPorEmail(email: String): Funcionario? = funcionarioRepository.findByEmail(email)

    override fun buscarPorId(id: String): Funcionario? = funcionarioRepository.findById(id).get()
}