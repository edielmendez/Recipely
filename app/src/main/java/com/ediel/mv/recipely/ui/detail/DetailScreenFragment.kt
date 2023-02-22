package com.ediel.mv.recipely.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.ediel.mv.recipely.R
import com.ediel.mv.recipely.databinding.DetailScreenFragmentBinding
import com.ediel.mv.recipely.ui.home.HomeViewModel
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailScreenFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var adapter: IngredientAdapter? = null
    private var adapterIntructions: InstructionAdapter? = null
    private var _binding: DetailScreenFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModelHome: HomeViewModel by activityViewModels()

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
        _binding = DetailScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapters()
        setUpTabs()
        setUpListeners()
        setUpUi()
    }

    private fun setUpUi() {
        viewModelHome?.selectedRecipe?.let {
            binding?.recipeName?.text = it.name
            Picasso.get().load(it.image).into(binding?.recipeImage)
            binding?.recipeDescription?.text = it.description
            binding.recipeCalories.text = "${it.calories} Kcal"
            binding.recipeCarbs.text = "${it.carbs} Carbohidratos"
            binding.recipeProteins.text = "${it.proteins} Proteínas"
            binding.recipeFats.text = "${it.fats} Grasas"
        }
    }

    private fun setUpListeners() {
        binding?.let {
            it.cardBack.setOnClickListener {
                findNavController().popBackStack()
            }
            it.iconMap.setOnClickListener {
                findNavController().navigate(
                    R.id.action_detailScreenFragment_to_mapScreenFragment,
                    bundleOf(
                        "latitude" to viewModelHome.selectedRecipe?.latitude,
                        "longitude" to viewModelHome.selectedRecipe?.longitude,
                        "name" to viewModelHome.selectedRecipe?.name
                    )
                )
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
        viewModelHome?.selectedRecipe?.let {
            adapter = IngredientAdapter(it.ingredients.toMutableList())
            adapter?.onClickIngredientListener = {
            }
            binding?.rvIngredients?.adapter = adapter
            ////
            adapterIntructions = InstructionAdapter(it.instructions.toMutableList())
            binding?.rvInstructions?.adapter = adapterIntructions
        }

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