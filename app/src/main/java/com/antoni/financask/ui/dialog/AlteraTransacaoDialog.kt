package com.antoni.financask.ui.dialog

import android.content.Context
import android.view.ViewGroup
import com.antoni.financask.R
import com.antoni.financask.delegate.TransacaoDelegate
import com.antoni.financask.extension.formataParaBrasileiro
import com.antoni.financask.model.Tipo
import com.antoni.financask.model.Transacao


class AlteraTransacaoDialog(viewGroup: ViewGroup,
                            private val context: Context) : FormularioTransacaoDialog(context, viewGroup){


    override val tituloBotaoPositivo: String
        get() = "Alterar"


    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.altera_receita
        }
        return R.string.altera_despesa
    }


    fun chama(transacao: Transacao, transacaoDelegate: TransacaoDelegate) {

        val tipo = transacao.tipo

        super.chama(tipo, transacaoDelegate)

        inicializaCampos(transacao)
    }


    private fun inicializaCampos(transacao: Transacao) {
        inicializaCampoValor(transacao)
        inicializaCampoData(transacao)
        inicializaCampoCategoria(transacao)
    }

    private fun inicializaCampoCategoria(transacao: Transacao) {
        val categorias = context.resources.getStringArray(categoriaPor(transacao.tipo))
        val posicaoCategoria = categorias.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicaoCategoria)
    }

    private fun inicializaCampoData(transacao: Transacao) {
        campoData.setText(transacao.data.formataParaBrasileiro())
    }

    private fun inicializaCampoValor(transacao: Transacao) {
        campoValor.setText(transacao.valor.toString())
    }


}