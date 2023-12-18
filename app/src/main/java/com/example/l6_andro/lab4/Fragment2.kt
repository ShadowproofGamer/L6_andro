package com.example.l6_andro.lab4

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import com.example.l6_andro.databinding.Fragment2Binding


class Fragment2 : Fragment() {
    private lateinit var _binding: Fragment2Binding
    lateinit var radioGr: RadioGroup
    lateinit var rad1: RadioButton
    lateinit var rad2: RadioButton
    lateinit var rad3: RadioButton
    lateinit var saveButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    fun changeListener() {

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = Fragment2Binding.inflate(inflater, container, false)
        radioGr = _binding.editRadio
        rad1 = _binding.editImg1
        rad2 = _binding.editImg2
        rad3 = _binding.editImg3
        saveButton = _binding.saveButtonTab2
        saveButton.setOnClickListener {_->
            val data = requireActivity().getSharedPreferences("additional", Context.MODE_PRIVATE)
            val editor = data.edit()
            when(radioGr.checkedRadioButtonId){
                rad1.id -> editor.putString("str2", "Setting 1")
                rad2.id -> editor.putString("str2", "Setting 2")
                rad3.id -> editor.putString("str2", "Setting 3")
            }
            editor.apply()
        }

        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment2().apply {
                arguments = Bundle().apply {

                }
            }
    }
}