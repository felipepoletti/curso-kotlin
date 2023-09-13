package com.estudos.pontoInteligente.repositories

import com.estudos.pontoInteligente.documents.Funcionario
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface FuncionarioRepository : MongoRepository<Funcionario, String> {

    fun findByEmail(email: String): Funcionario?

    fun findByCpf(cpf: String): Funcionario?
}