package michal.pawicki.todolistapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import michal.pawicki.todolistapp.databinding.ItemItemBinding

class ItemsAdapters(private val action: (Int, Boolean)-> Unit,
                    private val editItem: (Int) -> Unit,
                    private val deleteItem: (Int) -> Unit) : RecyclerView.Adapter<ItemsAdapters.ViewHolder>() {
    class ViewHolder(val binding: ItemItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val items = mutableListOf<ToDoItemUi>()
    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(items: List<ToDoItemUi>){
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item  = items[position]
        holder.binding.categoryTxt.text = item.title
        holder.binding.contentTxt.text = item.note
        holder.binding.dataTxt.text = item.dateTxt
        holder.binding.itemsStatusButton.setOnCheckedChangeListener(null)
        holder.binding.itemsStatusButton.isChecked = item.status
        holder.binding.itemsStatusButton.setOnCheckedChangeListener { _, checked ->
            if (item.status != checked){
                action(item.id, checked)
            }
        }
        holder.itemView.setOnClickListener{
            editItem(item.id)
        }
        holder.itemView.setOnLongClickListener {
            deleteItem(item.id)
            true
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}