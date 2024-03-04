package com.example.game.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.game.databinding.FragmentVictoryDialogBinding
import com.example.game.home.HomeFragment
import com.example.game.listeners.NavigationListener

class VictoryDialogFragment : DialogFragment() {
    private var _binding: FragmentVictoryDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVictoryDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backHome()
    }
    private fun backHome(){
        binding.ivHome.setOnClickListener {
            (activity as? NavigationListener)?.navigateToHomeScreen()
            dismiss()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}