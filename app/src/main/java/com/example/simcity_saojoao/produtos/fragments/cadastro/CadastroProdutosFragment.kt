package com.example.simcity_saojoao.produtos.fragments.cadastro

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
import com.example.simcity_saojoao.home.MainActivity
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
        (activity as MainActivity).supportActionBar?.title = getString(R.string.produtos)
        (activity as MainActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
        clicarBotaoCadastrarNovoProduto()
        clicarBotaoVerProdutos()
        recuperarLista()

    }

    private fun cadastrarProduto() {
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
            cadastrarProduto()
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
        when {
            nome.isEmpty() -> {
                binding.etNomeProduto.error = MSG_PREENCHA_CAMPOS
                return true
            }
            qnt.isEmpty() -> {
                binding.etQntProduto.error = MSG_PREENCHA_CAMPOS
                return true
            }
            valorUn.isEmpty() -> {
                binding.etValorProduto.error = MSG_PREENCHA_CAMPOS
                return true
            }
            receita.isEmpty() -> {
                binding.etReceita.error = MSG_PREENCHA_CAMPOS
                return true
            }
        }
        return false
    }

    private fun clicarBotaoVerProdutos() {
        binding.btnVerProdutos.setOnClickListener {
            irParaLista()
        }
    }

    private fun irParaLista() {
        NavHostFragment.findNavController(this).navigate(
            R.id.action_cadastroProdutosFragment_to_listaProdutosFragment, bundleListaNova()
        )
    }

    private fun bundleListaNova(): Bundle {
        return bundleOf(LISTA_KEY to listaNovaProduto)
    }

    private fun recuperarLista() {
        val lista = arguments?.getParcelableArrayList<Produto>(LISTA_KEY)
        if (lista != null) {
            atualizarLista(lista)
        }
    }

    fun atualizarLista(novaLista: ArrayList<Produto>) {
        if (listaNovaProduto.size == 0) {
            listaNovaProduto = novaLista
        } else if (listaNovaProduto.containsAll(novaLista)) {
        } else {
            listaNovaProduto.addAll(novaLista)
        }
    }
}