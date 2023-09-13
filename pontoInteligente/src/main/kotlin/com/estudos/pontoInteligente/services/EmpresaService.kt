package com.estudos.pontoInteligente.services

import com.estudos.pontoInteligente.documents.Empresa

interface EmpresaService {
    fun buscarPorCnpj(cnpj: String): Empresa?

    fun persistir(empresa: Empresa): Empresa
}