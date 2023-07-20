package com.example.myapplication

data class Product(
    var id: Int,
    var name: String,
    var des: String,
    var url: Int,
    var isSelect: Boolean,
    var amount: Int = 1
)