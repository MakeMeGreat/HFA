package ru.easycode.hfa_first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import ru.easycode.hfa_first.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root

        val application =
            requireNotNull(this.activity).application /*получаем ссылку на текущее приложение, и если нет датаБэйса создаем его и в переменную кладет его экземпляр*/
        val dao =
            TaskDatabase.getInstance(application).taskDao /*вызываем функцию датаБэйза и получаем ссылку на объект ТаскДао*/
        val viewModelFactory = TasksViewModelFactory(dao) //интсанс фабрики
        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(TasksViewModel::class.java)// инстанс ВМ с аргументом в конструкторе
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        val adapter = TaskItemAdapter { taskId ->
            Toast.makeText(context, "Clicled task $taskId", Toast.LENGTH_SHORT).show()
        }
        binding.tasksList.adapter = adapter
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}