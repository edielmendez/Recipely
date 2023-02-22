package com.ediel.mv.recipely.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.ediel.mv.recipely.R
import com.ediel.mv.recipely.databinding.HomeScreenFragmentBinding
import com.ediel.mv.recipely.ui.common.RecipelyBaseFragment
import com.ediel.mv.recipely.ui.ext.nonNullObserve
import dagger.hilt.android.AndroidEntryPoint

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class HomeScreenFragment : RecipelyBaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var adapter: RecipeAdapter
    private var binding: HomeScreenFragmentBinding? = null
    private val viewModel: HomeViewModel by activityViewModels()

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
        binding = HomeScreenFragmentBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        binding?.topBar?.topBarTitle?.text = "Recipely App"
        subscribeUi()
        setUpSearchView()
    }

    private fun setUpSearchView(){
        binding?.svRecipes?.setOnQueryTextListener(object : OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.filterRecipe(word = newText.toString())
                return false
            }

        })
    }

    private fun subscribeUi() {
        viewModel.uiState.nonNullObserve(viewLifecycleOwner){
            when(it){
                is HomeUIState.Loading -> {
                    if (it?.isLoading == true){
                        showLoader()
                    }else{
                        hideLoader()
                    }
                }
                is HomeUIState.Success -> {
                    it.data?.let { recipes -> adapter?.setRecipes(recipes) }
                }
                is HomeUIState.Error -> {

                }
            }
        }
    }

    private fun setUpAdapter() {
        adapter = RecipeAdapter(mutableListOf())
        adapter.onClickTourListener = {
            viewModel.selectedRecipe = it
            findNavController().navigate(R.id.action_homeScreenFragment_to_detailScreenFragment)
        }
        binding?.rvRecipes?.layoutManager = GridLayoutManager(activity, 2, GridLayoutManager.VERTICAL, false)
        binding?.rvRecipes?.adapter = adapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeScreenFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}