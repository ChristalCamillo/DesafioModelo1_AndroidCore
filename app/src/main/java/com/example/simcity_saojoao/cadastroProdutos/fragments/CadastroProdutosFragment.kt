package com.example.simcity_saojoao.cadastroProdutos.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import com.example.LISTA_KEY
import com.example.MSG_PREENCHA_CAMPOS
import com.example.MSG_PRODUTO_CADASTRADO
import com.example.simcity_saojoao.cadastroProdutos.CadastroProdutosActivity
import com.example.simcity_saojoao.model.Produto
import com.example.simcitysaojoao.R
import com.example.simcitysaojoao.databinding.FragmentCadastroProdutosBinding


class CadastroProdutosFragment : Fragment() {
    private lateinit var binding: FragmentCadastroProdutosBinding
    private lateinit var nome: String
    private lateinit var qnt: String
    private lateinit var valorUn: String
    private lateinit var receita: String
    private var listaNovaProduto = mutableListOf<Produto>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCadastroProdutosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clicarBotaoCadastrarNovoProduto()
        clicarBotaoVerProdutos()
        recuperarlistaProdutos()
    }

    private fun adicionarItemListaProdutos() {
        recuperarDados()
        if (!verificarCampos()) {
            val produto = Produto(
                nome = nome,
                quantidade = qnt.toInt(),
                valorUnitario = valorUn.toDouble(),
                receita = receita
            )
            listaNovaProduto.add(produto)
            Toast.makeText(context, MSG_PRODUTO_CADASTRADO, Toast.LENGTH_LONG).show()
        }
    }

    private fun clicarBotaoCadastrarNovoProduto() {
        binding.btnCadastrarNovoProduto.setOnClickListener {
            adicionarItemListaProdutos()
            limparCampos()
        }
    }

    private fun recuperarDados() {
        this.nome = binding.etNomeProduto.text.toString()
        this.qnt = binding.etQntProduto.text.toString()
        this.valorUn = binding.etValorProduto.text.toString()
        this.receita = binding.etReceita.text.toString()
    }

    private fun limparCampos() {
        binding.etNomeProduto.text.clear()
        binding.etQntProduto.text.clear()
        binding.etValorProduto.text.clear()
        binding.etReceita.text.clear()
    }

    fun verificarCampos(): Boolean {
        val context = context as CadastroProdutosActivity
        return if (nome.isEmpty() || qnt.isEmpty() || valorUn.isEmpty() || receita.isEmpty()) {
            Toast.makeText(context, MSG_PREENCHA_CAMPOS, Toast.LENGTH_LONG).show()
            true
        } else {
            false
        }
    }

    private fun clicarBotaoVerProdutos() {
        binding.btnVerProdutos.setOnClickListener {
            irParaListaCadastrados()
        }
    }

    private fun irParaListaCadastrados() {
        NavHostFragment.findNavController(this).navigate(
            R.id.action_cadastroProdutosFragment_to_listaProdutosFragment, bundleListaNova()
        )
    }

    private fun bundleListaNova(): Bundle {
        return bundleOf(LISTA_KEY to listaNovaProduto)
    }

    private fun recuperarlistaProdutos() {
        val lista = arguments?.getParcelableArrayList<Produto>(LISTA_KEY)
        if (lista != null) {
            atualizarListaProdutos(lista)
        }
    }

    fun atualizarListaProdutos(novaLista: ArrayList<Produto>) {
        if (listaNovaProduto.size == 0) {
            listaNovaProduto = novaLista
        } else if (listaNovaProduto.containsAll(novaLista)) {
        } else {
            listaNovaProduto.addAll(novaLista)
        }
    }
}