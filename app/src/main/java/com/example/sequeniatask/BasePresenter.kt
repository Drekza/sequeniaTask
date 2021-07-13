package com.example.sequeniatask

import android.view.View

interface BasePresenter<in T> {
    fun attach(view: T)
    fun onDestroy()
}