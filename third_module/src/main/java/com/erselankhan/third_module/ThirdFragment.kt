package com.erselankhan.third_module

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.erselankhan.third_module.Constants.BROADCAST_FROM_THIRD_MODULE
import com.erselankhan.third_module.Constants.DATA
import com.erselankhan.third_module.databinding.FragmentThirdBinding

class ThirdFragment: Fragment() {

    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        binding.passData.setOnClickListener {
            val intent = Intent(BROADCAST_FROM_THIRD_MODULE)
            intent.putExtra(DATA, "pass data from third module")
            LocalBroadcastManager.getInstance(requireContext()).sendBroadcast(intent)
        }
    }
}

object Constants {

    const val DATA = "data"
    const val BROADCAST_FROM_THIRD_MODULE = "data_from_third_module"
}