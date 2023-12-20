package com.example.govermentapp.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.govermentapp.GovernmentApp
import com.example.govermentapp.R
import com.example.govermentapp.databinding.ItemGovernmentInstitutionBinding
import com.example.govermentapp.domain.GovernmentInstitution
import com.example.govermentapp.ui.home.HomeUiAction

class GovernmentInstitutionsViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val binding = ItemGovernmentInstitutionBinding.bind(view)

    fun render(governmentInstitution: GovernmentInstitution, onActionClickListener: (HomeUiAction) -> Unit) {
        binding.tvGovernmentInstitution.text = governmentInstitution.organization
        binding.tvFactOrganization.text = governmentInstitution.fact
        binding.tvWebSiteOrganization.text = governmentInstitution.url
        //additional data
        binding.tvOperations.text = String.format(GovernmentApp.instance.getString(R.string.concat_operations), governmentInstitution.operations)
        binding.tvdDataset.text = String.format(GovernmentApp.instance.getString(R.string.concat_dataset), governmentInstitution.dataset)
        binding.tvCreatedAt.text = String.format(GovernmentApp.instance.getString(R.string.concat_created), governmentInstitution.created_at)

        binding.ivShare.setOnClickListener {
            onActionClickListener(HomeUiAction.ShareItem(governmentInstitution))
        }
        binding.containerOrganization.setOnClickListener {
            binding.clAuxInfo.visibility = if (binding.clAuxInfo.visibility == View.GONE) View.VISIBLE else View.GONE
            onActionClickListener(HomeUiAction.DetailItem)
        }
    }
}