package com.ediel.mv.recipely.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ediel.mv.recipely.R
import com.ediel.mv.recipely.databinding.DetailScreenFragmentBinding
import com.ediel.mv.recipely.databinding.HomeScreenFragmentBinding
import com.ediel.mv.recipely.ui.home.MockRecipes
import com.ediel.mv.recipely.ui.home.RecipeAdapter
import com.google.android.material.tabs.TabLayout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailScreenFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var adapter: IngredientAdapter? = null
    private var adapterIntructions: InstructionAdapter? = null
    private var binding: DetailScreenFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailScreenFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapters()
        setUpTabs()
        setUpListeners()
    }

    private fun setUpListeners() {
        binding?.let {
            it.cardBack.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setUpTabs() {
        binding?.tabs?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab?.position){
                    0 -> {
                        binding?.rvIngredients?.visibility = View.VISIBLE
                        binding?.rvInstructions?.visibility = View.GONE
                    }
                    1 -> {
                        binding?.rvInstructions?.visibility = View.VISIBLE
                        binding?.rvIngredients?.visibility = View.GONE
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun setUpAdapters() {
        adapter = IngredientAdapter(MockIngredients.ingredients.toMutableList())
        adapter?.onClickIngredientListener = {
        }
        //binding?.rvIngredients?.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        binding?.rvIngredients?.adapter = adapter
        ////
        adapterIntructions = InstructionAdapter(MockInstructions.instructions.toMutableList())
        binding?.rvInstructions?.adapter = adapterIntructions
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailScreenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}