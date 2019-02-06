package com.antoni.financask.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import com.antoni.financask.R
import com.antoni.financask.model.Tipo
import com.antoni.financask.model.Transacao
import com.antoni.financask.ui.ResumoView
import com.antoni.financask.ui.adapter.ListaTransacoesAdapter
import com.antoni.financask.ui.dialog.AdicionaTransacaoDialog
import com.antoni.financask.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*


class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    // inicialização por lazy (delegação por preguiça)
    // será executada apenas no momento que a property for usada.
    private val viewDaActivity: View by lazy {
        window.decorView
    }

    // para garantir o uso de uma property lazy como atribuição,
    // pode-se usar o lazy também.
    private val viewGroupDaActivity: ViewGroup by lazy {
        viewDaActivity as ViewGroup
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        //viewDaActivity = window.decorView

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
        AdicionaTransacaoDialog(viewGroupDaActivity, this)
            //object expression
            .chama(tipo) {transacaoCriada ->
                adiciona(transacaoCriada)
                lista_transacoes_adiciona_menu.close(true)
            }
    }

    private fun adiciona(transacao: Transacao) {
        transacoes.add(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraLista()
        configuraResumo()
    }

    private fun configuraLista() {
        with(lista_transacoes_listview) {
            adapter = ListaTransacoesAdapter(transacoes, this@ListaTransacoesActivity)
            setOnItemClickListener { _, _, position, _ ->
                val transacao = transacoes[position]
                chamaDialogAlteracao(transacao, position)

            }
        }
    }

    private fun chamaDialogAlteracao(transacao: Transacao, position: Int) {
        AlteraTransacaoDialog(viewGroupDaActivity, this)
            .chama(transacao) { transacaoAlterada ->
                altera(transacaoAlterada, position)
            }
    }

    private fun altera(transacao: Transacao, position: Int) {
        transacoes[position] = transacao
        atualizaTransacoes()
    }

    private fun configuraResumo() {
        //retorna a view da activity
        val resumoView = ResumoView(this, viewDaActivity, transacoes)
        resumoView.atualiza()
    }
}
