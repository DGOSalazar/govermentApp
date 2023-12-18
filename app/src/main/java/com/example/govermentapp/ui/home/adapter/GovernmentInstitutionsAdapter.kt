package com.example.govermentapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.govermentapp.R
import com.example.govermentapp.domain.GovernmentInstitution
import com.example.govermentapp.ui.home.HomeUiAction

class GovernmentInstitutionsAdapter(private var governmentInstitutions:List<GovernmentInstitution> = emptyList(), private var onActionClickListener:(HomeUiAction) -> Unit): RecyclerView.Adapter<GovernmentInstitutionsViewHolder>() {

    fun updateList(newList: List<GovernmentInstitution>){
        governmentInstitutions = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GovernmentInstitutionsViewHolder {
        return GovernmentInstitutionsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_government_institution, parent,false))
    }

    override fun onBindViewHolder(holder: GovernmentInstitutionsViewHolder, position: Int) {
        holder.render(governmentInstitutions[position], onActionClickListener)
    }

    override fun getItemCount() = governmentInstitutions.size
}

