package com.example.l5_andro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.l5_andro.databinding.FragmentShowBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ShowFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ShowFragment : Fragment() {
    private lateinit var _binding: FragmentShowBinding
    lateinit var showName: TextView
    lateinit var showSpec: TextView
    lateinit var showStrength: ProgressBar
    lateinit var showType: ImageView
    lateinit var showDanger: CheckBox
    lateinit var returnButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentShowBinding.inflate(inflater, container, false)
        showName=_binding.showName
        showSpec=_binding.showSpec
        showStrength=_binding.showStrengthBar
        showType=_binding.showType
        showDanger=_binding.showDangerous
        returnButton=_binding.showReturnButton
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //var returnButton = requireActivity().findViewById<View>(R.id.show_return_button)
        returnButton.setOnClickListener { requireActivity().onBackPressed() }
        parentFragmentManager.setFragmentResultListener("msgtochild", viewLifecycleOwner){
                _, bundle ->
            run {
                /*
                    "name" to currData.text_name,
                    "spec" to currData.text_spec,
                    "strength" to currData.item_strength,
                    "danger" to currData.dangerous,
                    "type" to currData.item_type,
                    "humanoids" to currData.humanoids
                 */
                showName.text = bundle.getString("name")
                showSpec.text = bundle.getString("spec")
                showDanger.isChecked = bundle.getBoolean("danger")
                showStrength.progress = bundle.getFloat("strength").toInt()
                when(bundle.getString("type")){
                    "Human" -> showType.setImageResource(R.drawable.human)
                    "NPC" -> showType.setImageResource(R.drawable.npc)
                    "Orc" -> showType.setImageResource(R.drawable.enemy)
                    else -> showType.setImageResource(R.drawable.human)
                }
            }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            ShowFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}