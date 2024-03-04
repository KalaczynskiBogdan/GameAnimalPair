package com.example.game.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.game.MainActivity
import com.example.game.databinding.FragmentHomeBinding
import com.example.game.game.GameFragment
import com.example.game.listeners.NavigationListener

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playTheGame()
    }

    private fun playTheGame() {
        binding.ivPlay.setOnClickListener {
            (activity as NavigationListener).navigateToGameScreen()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


    companion object {
        fun newInstance(): HomeFragment = HomeFragment()
    }
}