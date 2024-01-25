package se.payerl.biguglyrandomgenerator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.navigation.fragment.findNavController
import se.payerl.biguglyrandomgenerator.databinding.FragmentFirstBinding
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), OnSeekBarChangeListener {

    private var _binding: FragmentFirstBinding? = null
    private var fromVal: Int = 0;
    private var toVal: Int = 100;

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
        this.binding.fromSelect.setOnSeekBarChangeListener(this)
        this.binding.toSelect.setOnSeekBarChangeListener(this)
        this.binding.fromSelect.progress = 20
        this.binding.toSelect.progress = 80
        this.binding.genBtn.setOnClickListener {
            this.binding.textviewFirst.text =
                "${Random(System.currentTimeMillis().toInt()).nextInt(fromVal, toVal+1)}";
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}
    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        when(seekBar?.id) {
            R.id.fromSelect -> {
                fromVal = progress
                this.binding.fromText.text = "$progress"
                this.binding.toSelect.min = progress
            }
            else -> {
                toVal = progress
                this.binding.toText.text = "$progress"
                this.binding.fromSelect.max = progress
            }
        }
    }
}