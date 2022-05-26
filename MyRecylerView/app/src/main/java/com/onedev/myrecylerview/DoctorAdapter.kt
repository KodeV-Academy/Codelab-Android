package com.onedev.myrecylerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DoctorAdapter: RecyclerView.Adapter<DoctorAdapter.ListViewHolder>() {

    var onItemClick: ((Data.Doctor) -> Unit)? = null
    private val listData = ArrayList<Data.Doctor>()

    @SuppressLint("NotifyDataSetChanged")
    fun setListData(list: List<Data.Doctor>) {
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgDoctor: ImageView = itemView.findViewById(R.id.img_doctor)
        var tvNameDoctor: TextView = itemView.findViewById(R.id.tv_name_doctor)
        var tvSpecialistDoctor: TextView = itemView.findViewById(R.id.tv_specialist_doctor)
        var tvExperienceDoctor: TextView = itemView.findViewById(R.id.tv_experience_doctor)
        var tvPriceDoctor: TextView = itemView.findViewById(R.id.tv_price_doctor)

        fun bind(data: Data.Doctor) {
            Glide.with(imgDoctor)
                .load(data.image)
                .into(imgDoctor)

            tvNameDoctor.text = data.name
            tvSpecialistDoctor.text = data.specialist
            tvExperienceDoctor.text = data.experience
            tvPriceDoctor.text = data.price

            itemView.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DoctorAdapter.ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_doctor, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: DoctorAdapter.ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size
}