package com.example.govermentapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.govermentapp.R
import com.example.govermentapp.databinding.FragmentHomeBinding
import com.example.govermentapp.ui.home.adapter.GovernmentInstitutionsAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var institutionsAdapter: GovernmentInstitutionsAdapter

    private val viewModel:HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initList()
        initUIState()
        initSearchView()
    }

    private fun initSearchView() {
        binding.svGovernmentInstitutions.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){
                    viewModel.searchGovernmentInstitution(newText)
                }
                return false
            }

        })
    }

    private fun initList() {
        institutionsAdapter = GovernmentInstitutionsAdapter { action ->
            when(action){
                HomeUiAction.DetailItem -> {
                    Toast.makeText(context, "You clicked in detail item", Toast.LENGTH_SHORT).show()
                }
                is HomeUiAction.ShareItem -> {
                    viewModel.shareGovernmentInstitution(action.governmentInstitution)
                }
            }

        }
        binding.rvInstitutions.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = institutionsAdapter
        }
    }

    private fun initUIState() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.organizationsStateFlow.collect { uiState ->
                    when(uiState){
                        is HomeState.Error -> {

                        }
                        HomeState.Loading -> {

                        }
                        is HomeState.Success -> {
                            institutionsAdapter.updateList(uiState.data)
                        }
                    }
                }
            }
        }

        viewModel.organizationLiveData.observe(viewLifecycleOwner) { organization ->
            val intent = Intent(Intent.ACTION_SEND)
            intent.putExtra(
                Intent.EXTRA_TEXT,
                requireActivity().resources.getString(
                    R.string.message_organization,
                    organization.organization,
                    organization.url,
                    organization.latitude.toString(),
                    organization.longitude.toString()
                )
            )
            intent.type = "text/plain"
            intent.setPackage("com.whatsapp")
            startActivity(intent)
        }
    }
}