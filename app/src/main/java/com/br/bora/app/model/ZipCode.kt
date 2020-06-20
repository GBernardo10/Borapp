package com.br.bora.app.model

data class ZipCode(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val estado: String,
    val erro: String
)