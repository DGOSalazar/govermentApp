package com.example.govermentapp.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.govermentapp.R
import com.example.govermentapp.databinding.FragmentListBinding
import com.example.govermentapp.ui.home.adapter.GovernmentInstitutionsAdapter
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    companion object {
        fun showPage(
            currentPage: Int
        ): ListFragment {
            val fragment = ListFragment()
            val args = Bundle()
            args.putInt("currentPage", currentPage)
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var mBinding: FragmentListBinding
    private lateinit var institutionsAdapter: GovernmentInstitutionsAdapter
    private val viewModel by activityViewModels<HomeViewModel>()
    private var isQuery = false
    private var page = 0
    private var initial = 0
    private var final = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentListBinding.inflate(layoutInflater, container, false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) page = requireArguments().getInt("currentPage")
        setList()
        initList()
        initSearchView()
        initUIState()
    }
    private fun setList() {
        initial = page - 1
        final = initial + 10
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
        mBinding.rvInstitutions.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = institutionsAdapter
        }
    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.organizationsStateFlow.collect { uiState ->
                    when(uiState){
                        is HomeState.Error -> {

                        }
                        HomeState.Loading -> {

                        }
                        is HomeState.Success -> {
                             institutionsAdapter.updateList(
                                    if(isQuery) uiState.data else uiState.data.slice(initial..final)
                             )
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
    private fun initSearchView() {
        mBinding.svGovernmentInstitutions.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if(newText != null){
                    isQuery = true
                    if (newText.isEmpty()) isQuery = false
                    else viewModel.searchGovernmentInstitution(newText)
                }
                return false
            }
        })
    }
}