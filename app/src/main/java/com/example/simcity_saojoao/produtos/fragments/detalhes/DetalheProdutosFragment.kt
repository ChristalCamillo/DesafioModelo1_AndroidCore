package com.example.simcity_saojoao.produtos.fragments.detalhes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.*
import com.example.simcity_saojoao.home.MainActivity
import com.example.simcity_saojoao.model.Produto
import com.example.simcitysaojoao.R
import com.example.simcitysaojoao.databinding.FragmentDetalheProdutosBinding

class DetalheProdutosFragment : Fragment() {
    private lateinit var binding: FragmentDetalheProdutosBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalheProdutosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.title = getString(R.string.produtos)

        recuperarProduto()
        favoritarProduto()
    }

    private fun recuperarProduto() {
        val produto = arguments?.getParcelable<Produto>(PRODUTO_KEY)
        if (produto != null) {
            exibirDetalhes(produto = produto)
        }
    }

    private fun exibirDetalhes(produto: Produto) {
        binding.tvNomeProduto.text = produto.getNome()
        binding.tvQuantidade.text = QUANTIDADE + produto.getQuantidade().toString()
        binding.tvValorUnitario.text = VALOR_UNIT + produto.getValorUnit().toString()
        binding.tvReceita.text = RECEITA + produto.getReceita()
    }

    private fun favoritarProduto() {
        binding.icFavoritar.setOnClickListener {
            Toast.makeText(context, MSG_FAVORITADO, Toast.LENGTH_LONG).show()
        }
    }
}
