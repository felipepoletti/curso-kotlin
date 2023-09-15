package com.estudos.pontoInteligente.controllers

import com.estudos.pontoInteligente.documents.Funcionario
import com.estudos.pontoInteligente.documents.Lancamento
import com.estudos.pontoInteligente.dtos.LancamentoDto
import com.estudos.pontoInteligente.enums.TipoEnum
import com.estudos.pontoInteligente.response.Response
import com.estudos.pontoInteligente.services.FuncionarioService
import com.estudos.pontoInteligente.services.LancamentoService
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.text.SimpleDateFormat

@RestController
@RequestMapping("/api/lancamentos")
class LancamentoController(val lancamentoService: LancamentoService,
                           val funcionarioService: FuncionarioService) {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

    @Value("\${paginacao.qtd_por_pagina}")
    val qtdPorPagina: Int = 15

    @PostMapping
    fun adicionar(@Validated @RequestBody lancamentoDto: LancamentoDto,
                  result: BindingResult): ResponseEntity<Response<LancamentoDto>> {
        val response: Response<LancamentoDto> = Response<LancamentoDto>()
        validarFuncionario(lancamentoDto, result)

        if (result.hasErrors()) {
            result.allErrors.forEach { erro -> erro.defaultMessage?.let { response.erros.add(it) } }
            return ResponseEntity.badRequest().body(response)
        }

        val lancamento: Lancamento = converterDtoParaLancamento(lancamentoDto, result)
        lancamentoService.persistir(lancamento)
        response.data = converterLancamentoDto(lancamento)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun listarPorId(@PathVariable("id") id: String) : ResponseEntity<Response<LancamentoDto>> {
        val response: Response<LancamentoDto> = Response<LancamentoDto>()
        val lancamento = lancamentoService.buscarPorId(id)

        if (lancamento == null) {
            response.erros.add("Lançamento não encontrado para o id: $id")
            return ResponseEntity.badRequest().body(response)
        }

        response.data = converterLancamentoDto(lancamento)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/funcionario/{funcionarioId}")
    fun listarPorFuncionarioId(@PathVariable("funcionarioId") funcionarioId: String,
                               @RequestParam(value = "pag", defaultValue = "0") pag: Int,
                               @RequestParam(value = "ord", defaultValue = "id") ord: String,
                               @RequestParam(value = "dir", defaultValue = "DESC") dir: String):
            ResponseEntity<Response<Page<LancamentoDto>>> {

        val response: Response<Page<LancamentoDto>> = Response<Page<LancamentoDto>>()

        val pageRequest: PageRequest = PageRequest.of(pag, qtdPorPagina, Sort.Direction.valueOf(dir), ord)
        val lancamentos: Page<Lancamento> =
            lancamentoService.buscarPorFuncionarioId(funcionarioId, pageRequest)

        val lancamentosDto: Page<LancamentoDto> =
            lancamentos.map { lancamento -> converterLancamentoDto(lancamento) }

        response.data = lancamentosDto
        return ResponseEntity.ok(response)
    }

    private fun converterLancamentoDto(lancamento: Lancamento): LancamentoDto =
        LancamentoDto(dateFormat.format(lancamento.data), lancamento.tipo.toString(),
             lancamento.descricao, lancamento.localizacao,
             lancamento.funcionarioId, lancamento.id)

    private fun converterDtoParaLancamento(lancamentoDto: LancamentoDto,
                                           result: BindingResult): Lancamento {
        if (lancamentoDto.id != null) {
            val lanc: Lancamento? = lancamentoService.buscarPorId(lancamentoDto.id!!)
            if (lanc == null) result.addError((ObjectError("lancamento",
                "Lançamento não encontrado")))
        }

        return Lancamento(dateFormat.parse(lancamentoDto.data), TipoEnum.valueOf(lancamentoDto.tipo!!),
            lancamentoDto.funcionarioId!!, lancamentoDto.descricao,
            lancamentoDto.localizacao, lancamentoDto.id)
    }

    private fun validarFuncionario(lancamentoDto: LancamentoDto, result: BindingResult) {
        if (lancamentoDto.funcionarioId == null) {
            result.addError(ObjectError("funcionario",
                "Funcionário não informado."))
        }

        val funcionario: Funcionario? = funcionarioService.buscarPorId(lancamentoDto.funcionarioId)
        if (funcionario == null) {
            result.addError(ObjectError("funcionario",
                "Funcionário não encontrado. ID inexistente."))
        }
    }
}