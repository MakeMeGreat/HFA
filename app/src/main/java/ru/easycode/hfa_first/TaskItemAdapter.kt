package ru.easycode.hfa_first

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.easycode.hfa_first.databinding.TaskItemBinding

class TaskItemAdapter :
    ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder =
        TaskItemViewHolder.inflateFrom(parent)

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class TaskItemViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TaskItemBinding.inflate(layoutInflater, parent, false)
                return TaskItemViewHolder(binding)
            }
        }

        fun bind(item: Task) {
            binding.task = item
        }
    }

}