package com.antoni.financask.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.antoni.financask.R
import com.antoni.financask.model.Transacao
import com.antoni.financask.extension.formataParaBrasileiro
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(
    transacoes: List<Transacao>,
    context: Context) : BaseAdapter() {

    private val transacoes = transacoes
    private val context = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent, false)

        view.transacao_valor.text = transacoes.get(position).valor.toString()
        view.transacao_categoria.text = transacoes.get(position).categoria
        view.transacao_data.text = transacoes.get(position).data.formataParaBrasileiro()
        return view
    }

    override fun getItem(position: Int): Transacao {
        return transacoes.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }

}