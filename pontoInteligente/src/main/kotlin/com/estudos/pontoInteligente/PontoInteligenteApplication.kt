package com.estudos.pontoInteligente

import com.estudos.pontoInteligente.documents.Empresa
import com.estudos.pontoInteligente.documents.Funcionario
import com.estudos.pontoInteligente.enums.PerfilEnum
import com.estudos.pontoInteligente.repositories.EmpresaRepository
import com.estudos.pontoInteligente.repositories.FuncionarioRepository
import com.estudos.pontoInteligente.utils.SenhaUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class PontoInteligenteApplication(val empresaRepository: EmpresaRepository,
                                  val funcionarioRepository: FuncionarioRepository) : CommandLineRunner {
    override fun run(vararg args: String?) {
        empresaRepository.deleteAll()
        funcionarioRepository.deleteAll()

        var empresa: Empresa = Empresa("Empresa", "123456789101112")
        empresa = empresaRepository.save(empresa)

        var admin: Funcionario = Funcionario("Admin", "email@email.com",
            SenhaUtils().gerarBcrypt("123456"), "12345678910",
            PerfilEnum.ROLE_ADMIN, empresa.id!!)
        admin = funcionarioRepository.save(admin)

        var funcionario: Funcionario = Funcionario("Funcionario", "email@email.com",
            SenhaUtils().gerarBcrypt("123456"), "10987654321",
            PerfilEnum.ROLE_USUARIO, empresa.id!!)
        funcionario = funcionarioRepository.save(funcionario)

        System.out.println("Empresa ID: " + empresa.id)
        System.out.println("Admin ID: " + admin.id)
        System.out.println("Funcionario ID: " + funcionario.id)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(PontoInteligenteApplication::class.java, *args)
}

