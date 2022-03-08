package com.krobawsky.retocomercio.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.krobawsky.retocomercio.data.model.UserModel
import com.krobawsky.retocomercio.databinding.ItemUserBinding

class UserAdapter(private val listener: OnItemClickListener) : ListAdapter<UserModel, UserAdapter.TasksViewHolder>(DiffCallback())  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner  class TasksViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.apply {
                root.setOnClickListener {
                    val position = adapterPosition
                    if (position != RecyclerView.NO_POSITION) {
                        val task = getItem(position)
                        listener.onItemClick(task)
                    }
                }
            }
        }

        fun bind(task: UserModel) {
            binding.apply {
                textViewName.text = task.name
                textViewEmail.text = task.email
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(task: UserModel)
    }

    class DiffCallback : DiffUtil.ItemCallback<UserModel>() {
        override fun areItemsTheSame(oldItem: UserModel, newItem: UserModel) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: UserModel, newItem: UserModel) =
            oldItem == newItem
    }
}