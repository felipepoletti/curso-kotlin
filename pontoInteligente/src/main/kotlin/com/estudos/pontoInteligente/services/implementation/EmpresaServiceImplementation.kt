package com.estudos.pontoInteligente.services.implementation

import com.estudos.pontoInteligente.documents.Empresa
import com.estudos.pontoInteligente.repositories.EmpresaRepository
import com.estudos.pontoInteligente.services.EmpresaService
import org.springframework.stereotype.Service

@Service
class EmpresaServiceImplementation(val empresaRepository: EmpresaRepository) : EmpresaService {
    override fun buscarPorCnpj(cnpj: String): Empresa? = empresaRepository.findByCnpj(cnpj)

    override fun persistir(empresa: Empresa): Empresa = empresaRepository.save(empresa)
}