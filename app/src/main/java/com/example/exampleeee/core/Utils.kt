package com.example.exampleeee.core

import android.util.Log
import androidx.paging.LoadState
import com.example.exampleeee.core.Constants.TAG

class Utils {
    companion object {
        fun printError(errorState: LoadState.Error) {
            val error = errorState.error
            Log.d(TAG, error.message ?: error.toString())
        }
    }
}