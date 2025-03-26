package se.payerl.biguglyrandomgenerator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.google.android.material.slider.Slider
import se.payerl.biguglyrandomgenerator.databinding.FragmentFirstBinding
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private var fromVal: Int = 20
    private var toVal: Int = 80

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        setupSlider(binding.fromSelect, binding.fromText, "From: ") { value ->
            fromVal = value.toInt()
        }
        
        setupSlider(binding.toSelect, binding.toText, "To: ") { value ->
            toVal = value.toInt()
        }

        binding.genBtn.setOnClickListener {
            val result = Random(System.currentTimeMillis().toInt()).nextInt(fromVal, toVal + 1)
            binding.textviewFirst.text = result.toString()
        }
    }

    private fun setupSlider(slider: Slider, textView: TextView, prefix: String, onValueChanged: (Float) -> Unit) {
        slider.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                onValueChanged(value)
                textView.text = "$prefix${value.toInt()}"
            }
        }
        textView.text = "$prefix${slider.value.toInt()}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}