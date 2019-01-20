package com.antoni.financask.ui.activity

import android.app.DatePickerDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.antoni.financask.R
import com.antoni.financask.delegate.TransacaoDelegate
import com.antoni.financask.model.Transacao
import com.antoni.financask.ui.ResumoView
import com.antoni.financask.ui.adapter.ListaTransacoesAdapter
import com.antoni.financask.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*


class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraLista()
        configuraResumo()

        lista_transacoes_adiciona_receita.setOnClickListener {
            AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
                    //object expression
                .configuraDialog(object : TransacaoDelegate{
                    override fun delegate(transacao: Transacao) {
                        atualizaTransacoes(transacao)
                       lista_transacoes_adiciona_menu.close(true)
                    }
                })
        }
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraLista()
        configuraResumo()
    }

    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
    }

    private fun configuraResumo() {
        //retorna a view da activity
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }
}
