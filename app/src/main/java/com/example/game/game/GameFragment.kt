package com.example.game.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.game.MainActivity
import com.example.game.databinding.FragmentGameBinding
import com.example.game.game.recycler.ImageAdapter
import com.example.game.listeners.NavigationListener

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding
        get() = _binding!!

    private val viewModel: GameViewModel by lazy {
        ViewModelProvider(this)[GameViewModel::class.java]
    }

    private var imageAdapter: ImageAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        observe()
    }

    override fun onDestroyView() {
        _binding = null
        imageAdapter = null
        super.onDestroyView()
    }

    private fun observe() {
        with(viewModel) {
            gameImagesLD.observe(viewLifecycleOwner) {
                imageAdapter?.addAllNotify(it)
            }
        }
    }

    private fun initUi() {
        binding.apply {
            ivBack.setOnClickListener { (activity as? NavigationListener)?.navigateBack() }
            ivRestart.setOnClickListener { viewModel.restartGame() }
        }
        initRecycler()
    }

    private fun initRecycler() {
        imageAdapter = ImageAdapter(
            clickEvent = { imageAdapter?.openOrCloseImage(it) }, requireContext(),
            victoryClickedListener = {
                val dialog = VictoryDialogFragment()
                dialog.show((activity as MainActivity).supportFragmentManager, "dialog")
            }
        )

        with(binding.rvImages) {
            adapter = imageAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    companion object {
        fun newInstance(): GameFragment = GameFragment()
    }
}