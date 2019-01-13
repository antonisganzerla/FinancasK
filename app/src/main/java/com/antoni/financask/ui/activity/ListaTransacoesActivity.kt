package com.antoni.financask.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.antoni.financask.R
import com.antoni.financask.model.Tipo
import com.antoni.financask.model.Transacao
import com.antoni.financask.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)


        val transacoes: List<Transacao> = transacoesExemplo()

        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }



    private fun transacoesExemplo(): List<Transacao> {
        return listOf(
            Transacao(
                BigDecimal(20.5),
                "Comida", tipo = Tipo.DESPESA
            ),
            Transacao(
                BigDecimal(100.0),
                "Economia", tipo = Tipo.RECEITA
            ),

            Transacao(BigDecimal(75.0), tipo = Tipo.RECEITA)
        )
    }
}
