package com.example.autoserviceandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class WorkshopAdapter(
    private val workshops: List<Workshop>,
    private val onWorkshopSelected: (Workshop) -> Unit
) : RecyclerView.Adapter<WorkshopAdapter.WorkshopViewHolder>() {

    private var selectedPosition = -1

    inner class WorkshopViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rootLayout: View = itemView.findViewById(R.id.clWorkshopItem)
        val ivWorkshop: ImageView = itemView.findViewById(R.id.ivWorkshop)
        val tvName: TextView = itemView.findViewById(R.id.tvWorkshopName)
        val tvDistance: TextView = itemView.findViewById(R.id.tvDistance)
        val tvRating: TextView = itemView.findViewById(R.id.tvRating)
        val tvAddress: TextView = itemView.findViewById(R.id.tvAddress)
        val rbWorkshop: RadioButton = itemView.findViewById(R.id.rbWorkshop)

        init {
            itemView.setOnClickListener {
                if (adapterPosition == RecyclerView.NO_POSITION) return@setOnClickListener
                val previousSelection = selectedPosition
                selectedPosition = adapterPosition
                
                notifyItemChanged(previousSelection)
                notifyItemChanged(selectedPosition)
                
                onWorkshopSelected(workshops[selectedPosition])
            }
            
            rbWorkshop.setOnClickListener {
                itemView.performClick()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkshopViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_workshop, parent, false)
        return WorkshopViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkshopViewHolder, position: Int) {
        val workshop = workshops[position]
        
        holder.tvName.text = workshop.name
        holder.tvDistance.text = workshop.distance
        holder.tvRating.text = workshop.rating
        holder.tvAddress.text = workshop.address
        holder.ivWorkshop.setImageResource(workshop.imageResId)
        
        val isSelected = position == selectedPosition
        holder.rbWorkshop.isChecked = isSelected
        
        if (isSelected) {
            holder.rootLayout.setBackgroundResource(R.drawable.bg_card_blue_stroke)
            holder.rbWorkshop.buttonTintList = ContextCompat.getColorStateList(holder.itemView.context, R.color.accent_blue)
        } else {
            holder.rootLayout.setBackgroundResource(R.drawable.bg_white_stroke_rounded)
            holder.rbWorkshop.buttonTintList = ContextCompat.getColorStateList(holder.itemView.context, R.color.border_color)
        }
    }

    override fun getItemCount(): Int = workshops.size
}
