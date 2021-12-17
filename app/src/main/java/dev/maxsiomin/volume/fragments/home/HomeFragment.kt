package dev.maxsiomin.volume.fragments.home

import android.content.Context.MEDIA_ROUTER_SERVICE
import android.media.MediaRouter
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.maxsiomin.volume.R
import dev.maxsiomin.volume.databinding.FragmentHomeBinding
import dev.maxsiomin.volume.extensions.addOnVolumeChangedCallback
import dev.maxsiomin.volume.extensions.addValueChangedListener
import dev.maxsiomin.volume.extensions.id
import dev.maxsiomin.volume.fragments.base.BaseFragment
import timber.log.Timber
import kotlin.math.roundToInt


@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding

    override val mRoot get() = binding.root

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var mediaRouter: MediaRouter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        (activity as AppCompatActivity).supportActionBar?.let {
            it.title = viewModel.getString(R.string.app_name)
            it.setDisplayHomeAsUpEnabled(false)
        }

        binding = FragmentHomeBinding.bind(view)

        mediaRouter = requireContext().getSystemService(MEDIA_ROUTER_SERVICE) as MediaRouter

        mediaRouter.addOnVolumeChangedCallback {
            updateVolume()
        }

        with (binding.seekBar) {
            max = viewModel.seekBarMax

            updateVolume()

            addValueChangedListener { progress, fromUser ->
                Timber.d(progress.toString())
                if (fromUser)
                    viewModel.volume = (progress.toDouble() / (100.0 / 15.0)).roundToInt()
                binding.currentVolumeTextView.text = progress.toString()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        updateVolume()
    }

    private fun updateVolume() {
        (viewModel.volume.toDouble() * (100.0 / 15.0)).roundToInt().let {
            binding.seekBar.progress = it
            binding.currentVolumeTextView.text = it.toString()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        with (inflater) {
            inflate(R.menu.home_fragment_menu, menu)
            super.onCreateOptionsMenu(menu, this)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.id) {
            R.id.settings_menu_item -> findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
            else -> return false
        }
        return true
    }
}
