package com.example.simcity_saojoao.cadastroProdutos

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.PRODUTO_KEY
import com.example.simcity_saojoao.cadastroProdutos.fragments.CadastroProdutosFragment
import com.example.simcity_saojoao.model.Produto
import com.example.simcitysaojoao.R
import com.example.simcitysaojoao.databinding.ActivityCadastroprodutosBinding

class CadastroProdutosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCadastroprodutosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroprodutosBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar?.title = "Produtos"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        acessarActionBar()
        }
    private fun acessarActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle(R.string.produtos)
    }

}
