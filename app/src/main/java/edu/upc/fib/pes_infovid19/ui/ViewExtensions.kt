package edu.upc.fib.pes_infovid19.ui

import android.view.LayoutInflater
import android.view.ViewGroup

fun ViewGroup.inflate(layout: Int) = LayoutInflater.from(context).inflate(layout, this, false)