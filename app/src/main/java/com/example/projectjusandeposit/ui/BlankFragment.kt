package com.example.projectjusandeposit.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.lifecycle.Observer
import com.example.projectjusandeposit.AppState
import com.example.projectjusandeposit.R
import com.example.projectjusandeposit.databinding.FragmentBlankBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.processNextEventInCurrentThread
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class BlankFragment : Fragment() {
    private var _bindig:FragmentBlankBinding? = null
    private val binding get() =_bindig!!
    private val viewModel: BlankViewModel by viewModel()

    companion object {
        fun newInstance() = BlankFragment()
        var percent:Int = 0;
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _bindig = FragmentBlankBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.blankLiveData.observe(viewLifecycleOwner, Observer {
            renderData(it)
        })


        binding.customSeekBar.seekBarPercentage.setOnSeekBarChangeListener(object :OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                percent = progress
                binding.textViewPercentage.text = percent.toString() + "%"
                if (progress < 10){
                    binding.customSeekBar.textViewSeekBarValue.text = "Пенсионный"
                }
                else if (progress < 15){
                    binding.customSeekBar.textViewSeekBarValue.text = "Оптимальный"
                }
                else if (progress < 19){
                    binding.customSeekBar.textViewSeekBarValue.text = "Комфорт"
                }
                else if (progress < 24){
                    binding.customSeekBar.textViewSeekBarValue.text = "Бизнесмен"
                }
                else if (progress < 25){
                    binding.customSeekBar.textViewSeekBarValue.text = "Максимум"
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        binding.buttonClear.setOnClickListener{
            viewModel.removeAll()
        }

        binding.buttonOK.setOnClickListener{
            viewModel.okButtunPress()
        }
    }

    private fun renderData(appState: AppState) {
        when (appState)     {
            is AppState.Success -> {
                val temp = binding.editTextDeposit.text.toString()
                if (temp == ""){
                    viewModel.error()
                }else {
                    val commision:Double = temp.toDouble() * percent/100
                    binding.textViewCommision.text = commision.toString()
                    val summ:Double = temp.toDouble()+commision
                    binding.textViewDeposit.text = summ.toString()
                }

            }
            is AppState.Error -> {
                Snackbar.make(binding.fragmentBlank,"Депозит нулево",Snackbar.LENGTH_INDEFINITE).show()
            }
            is AppState.Delete ->{
                binding.textViewPercentage.text = "0%"
                binding.customSeekBar.textViewSeekBarValue.text = "Пенсионный"
                binding.textViewDeposit.text = "0"
                binding.textViewCommision.text = "0"
                binding.customSeekBar.seekBarPercentage.setProgress(0)
                percent = 0
            }
        }
    }


}