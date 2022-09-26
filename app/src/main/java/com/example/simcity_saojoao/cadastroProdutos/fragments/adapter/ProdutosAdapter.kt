package com.example.simcity_saojoao.cadastroProdutos.fragments.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simcity_saojoao.model.Produto
import com.example.simcitysaojoao.databinding.ProdutoItemBinding
import kotlin.reflect.KFunction1

class ProdutosAdapter(
    private var listaProduto: MutableList<Produto>,
    private val clickProduto: KFunction1<Produto, Unit>
) : RecyclerView.Adapter<ProdutosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProdutoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = listaProduto[position]
        holder.exibirInformacoesView(produto)
        holder.binding.cvItem.setOnClickListener {
            clickProduto(produto)
        }
    }

    override fun getItemCount(): Int {
        return listaProduto.size
    }

    fun atualizarListaProdutos(novaLista: ArrayList<Produto>) {
        if (listaProduto.size == 0) {
            listaProduto = novaLista
        } else if (listaProduto.containsAll(novaLista)) {
        } else {
            listaProduto.addAll(novaLista)
        }
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ProdutoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun exibirInformacoesView(produto: Produto) {
            binding.tvQntENomeProduto.text = "${produto.getQuantidade()} - ${produto.getNome()}"
        }
    }
}