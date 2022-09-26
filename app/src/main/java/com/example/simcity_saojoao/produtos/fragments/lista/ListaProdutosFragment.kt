package com.example.simcity_saojoao.produtos.fragments.lista

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.LISTA_KEY
import com.example.PRODUTO_KEY
import com.example.simcity_saojoao.homeActivity.MainActivity
import com.example.simcity_saojoao.produtos.adapter.ProdutosAdapter
import com.example.simcity_saojoao.model.Produto
import com.example.simcitysaojoao.R
import com.example.simcitysaojoao.databinding.FragmentListaProdutosBinding

class ListaProdutosFragment : Fragment() {
    private lateinit var binding: FragmentListaProdutosBinding
    private val produtosAdapter: ProdutosAdapter by lazy {
        ProdutosAdapter(arrayListOf(), ::irParaDetalhesProduto)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListaProdutosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.title = getString(R.string.produtos)

        recuperarLista()
    }

    private fun irParaDetalhesProduto(produto: Produto) {
        val bundle = bundleOf(PRODUTO_KEY to produto)

        NavHostFragment.findNavController(this).navigate(
            R.id.action_listaProdutosFragment_to_detalheProdutosFragment, bundle
        )
    }

    private fun recuperarLista() {
        val lista = arguments?.getParcelableArrayList<Produto>(LISTA_KEY)
        if (lista != null) {
            atualizarLista(lista)
        }
    }

    private fun atualizarLista(lista: ArrayList<Produto>) {
        produtosAdapter.atualizarListaProdutos(lista)
        exibirRecyclerView()
    }

    private fun exibirRecyclerView() {
        binding.rvListaProdutosCadastrados.adapter = produtosAdapter
        binding.rvListaProdutosCadastrados.layoutManager = LinearLayoutManager(context)
    }
}