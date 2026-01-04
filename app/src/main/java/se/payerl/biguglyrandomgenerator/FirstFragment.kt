package se.payerl.biguglyrandomgenerator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.slider.RangeSlider
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
        
        // Setup RangeSlider
        binding.rangeSlider.addOnChangeListener { slider, _, fromUser ->
            if (fromUser) {
                val values = slider.values
                fromVal = values[0].toInt()
                toVal = values[1].toInt()
            }
        }

        // Initialize values from RangeSlider
        val initialValues = binding.rangeSlider.values
        fromVal = initialValues[0].toInt()
        toVal = initialValues[1].toInt()

        binding.genBtn.setOnClickListener {
            val result = Random(System.currentTimeMillis().toInt()).nextInt(fromVal, toVal + 1)
            binding.textviewFirst.text = result.toString()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}