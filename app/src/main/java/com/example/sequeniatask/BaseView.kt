package com.example.sequeniatask

interface BaseView<T> {
    fun setPresenter(presenter: T)
}