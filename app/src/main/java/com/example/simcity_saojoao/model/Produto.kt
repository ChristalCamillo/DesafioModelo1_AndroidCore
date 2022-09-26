package com.example.simcity_saojoao.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Produto(
    private var nome: String,
    private var quantidade: Int,
    private var valorUnitario: Double,
    private var receita: String
) : Parcelable {
    private var valorTotal = quantidade * valorUnitario

    fun getNome() = this.nome
    fun getQuantidade() = this.quantidade
    fun getValorUnit() = this.valorUnitario
    fun getReceita() = this.receita
    fun getValorTotal() = this.valorTotal
}