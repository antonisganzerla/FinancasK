package com.antoni.financask.extension

fun String.limitaAte(limite: Int): String{
    if(this.length > limite){
        return "${this.substring(0, limite)}..."
    }
    return this
}