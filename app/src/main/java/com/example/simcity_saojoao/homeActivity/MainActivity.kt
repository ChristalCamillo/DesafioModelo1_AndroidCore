package com.example.simcity_saojoao.homeActivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.simcity_saojoao.cadastroProdutos.CadastroProdutosActivity
import com.example.simcitysaojoao.R
import com.example.simcitysaojoao.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setTitle(R.string.nomeApp)

        clicarBotaoIrProdutos(irParaCadastrarProdutos())
    }

    private fun clicarBotaoIrProdutos(intent: Intent) {
        binding.btnIrProdutos.setOnClickListener {
            startActivity(intent)
        }
    }

    private fun irParaCadastrarProdutos() = Intent(this, CadastroProdutosActivity::class.java)
}