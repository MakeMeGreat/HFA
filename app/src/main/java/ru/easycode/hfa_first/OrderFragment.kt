package ru.easycode.hfa_first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import ru.easycode.hfa_first.databinding.FragmentOrderBinding

class OrderFragment : Fragment() {
    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(inflater, container, false)
        val view = binding.root
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener {
            val pizzaType = binding.pizzaGroup.checkedRadioButtonId
            if (pizzaType == -1) {
                Toast.makeText(activity, "You need to choose a pizza type", Toast.LENGTH_LONG)
                    .show()
            } else {
                var text = (when (pizzaType) {
                    R.id.radio_diavolo -> "Diavolo pizza"
                    else -> "Funghi pizza"
                })
                text += if (binding.parmesan.isChecked) "; extra parmesan" else ""
                text += if (binding.chiliOil.isChecked) "; extra chili oil" else ""
                Snackbar.make(binding.fab, text, Snackbar.LENGTH_SHORT).show()
            }
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}