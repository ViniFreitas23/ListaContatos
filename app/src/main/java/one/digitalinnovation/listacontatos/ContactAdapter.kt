package one.digitalinnovation.listacontatos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*

class contactAdapter(var listener: ClickItem) : Adapter<contactAdapter.contactAdapterViewHolder>(){

    private val list: MutableList<contact> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): contactAdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return contactAdapterViewHolder(view, list, listener)
    }

    override fun onBindViewHolder(holder: contactAdapterViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun updateList(list: List<contact>){
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class contactAdapterViewHolder(itemView: View, var list: List<contact>, var listener: ClickItem) : ViewHolder(itemView){
        private val tvName: TextView = itemView.findViewById(R.id.tv_name)
        private val tvPhone: TextView = itemView.findViewById(R.id.tv_phone)
        private val ivPhoto: ImageView = itemView.findViewById(R.id.iv_photo)

        init {
            itemView.setOnClickListener {
                listener.clickItemContact(list[adapterPosition])
            }
        }

        fun bind(contact: contact){
            tvName.text = contact.name
            tvPhone.text = contact.phone

        }
    }
}