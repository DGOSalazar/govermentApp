package com.example.govermentapp.ui.home.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.govermentapp.databinding.ItemGovernmentInstitutionBinding
import com.example.govermentapp.domain.models.GovernmentInstitution
import com.example.govermentapp.ui.home.HomeUiAction

class GovernmentInstitutionsViewHolder(view:View):RecyclerView.ViewHolder(view) {

    private val binding = ItemGovernmentInstitutionBinding.bind(view)

    fun render(governmentInstitution: GovernmentInstitution, onActionClickListener: (HomeUiAction) -> Unit) {
        binding.tvGovernmentInstitution.text = governmentInstitution.organization
        binding.tvFactOrganization.text = governmentInstitution.fact
        binding.tvWebSiteOrganization.text = governmentInstitution.url

        binding.ivShare.setOnClickListener {
            onActionClickListener(HomeUiAction.ShareItem(governmentInstitution))
        }
        binding.containerOrganization.setOnClickListener {
            onActionClickListener(HomeUiAction.DetailItem)
        }
    }
}