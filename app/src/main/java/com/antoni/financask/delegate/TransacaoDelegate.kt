package com.antoni.financask.delegate

import com.antoni.financask.model.Transacao

interface TransacaoDelegate {

    fun delegate(transacao: Transacao)
}