package com.antoni.financask.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import com.antoni.financask.R
import com.antoni.financask.delegate.TransacaoDelegate
import com.antoni.financask.model.Tipo
import com.antoni.financask.model.Transacao
import com.antoni.financask.ui.ResumoView
import com.antoni.financask.ui.adapter.ListaTransacoesAdapter
import com.antoni.financask.ui.dialog.AdicionaTransacaoDialog
import com.antoni.financask.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*


class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraResumo()
        configuraLista()
        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita.setOnClickListener {
            chamaDialogTransacao(Tipo.RECEITA)
        }

        lista_transacoes_adiciona_despesa.setOnClickListener {
            chamaDialogTransacao(Tipo.DESPESA)
        }
    }

    private fun chamaDialogTransacao(tipo: Tipo) {
        AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
            //object expression
            .chama(tipo, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    transacoes.add(transacao)
                    atualizaTransacoes()
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraLista() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(transacoes, this)
        lista_transacoes_listview.setOnItemClickListener { parent, view, position, id ->
            val transacao = transacoes[position]
            AlteraTransacaoDialog(window.decorView as ViewGroup, this)
                .chama(transacao, object : TransacaoDelegate{
                    override fun delegate(transacao: Transacao) {
                        transacoes[position] = transacao
                        atualizaTransacoes()
                    }
                })

        }
    }

    private fun configuraResumo() {
        //retorna a view da activity
        val view = window.decorView
        val resumoView = ResumoView(this, view, transacoes)
        resumoView.atualiza()
    }
}
