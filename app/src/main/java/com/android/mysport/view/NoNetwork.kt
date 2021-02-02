package com.android.mysport.view

import android.app.Activity
import android.os.Bundle
import com.android.mysport.R

/**
 * This is the activity to show when there is no internet connection.
 */
class NoNetwork : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.no_network)
    }

}