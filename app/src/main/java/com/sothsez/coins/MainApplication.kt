package com.sothsez.coins

import android.app.Application

class MainApplication : Application(){
    init {
        instance = this;
    }

    companion object{
        private var instance: MainApplication? = null

        fun applicationContext() : MainApplication{
            return instance as MainApplication
        }
    }
  }