package ru.easycode.hfa_first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import ru.easycode.hfa_first.databinding.FragmentEditTaskBinding

class EditTaskFragment : Fragment() {
    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        val view = binding.root
        val taskId = EditTaskFragmentArgs.fromBundle(requireArguments()).taskId
        val application = requireNotNull(this.activity).application
        val dao =
            TaskDatabase.getInstance(application).taskDao //эта строка и выше нужны чтобы сделать VMFactory
        val viewModelFactory = EditTaskViewModelFactory(taskId, dao)
        val viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(EditTaskViewModel::class.java)// с помощью factory создаем ВМ и получаем ссылку на него

        binding.viewModel = viewModel // связываем с макетом
        binding.lifecycleOwner = viewLifecycleOwner //даем макету взаимодействовать с LiveData

        viewModel.navigateToList.observe(
            viewLifecycleOwner,
            Observer { navigate -> //если свойство вмки тру то переход к таск фрагменту
                if (navigate) {
                    view.findNavController().navigate(R.id.action_editTaskFragment_to_tasksFragment)
                    viewModel.onNavigatedToList() //меняем свойство обратно на false
                }
            })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}