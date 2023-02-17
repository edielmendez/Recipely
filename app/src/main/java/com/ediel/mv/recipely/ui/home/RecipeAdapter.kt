package com.ediel.mv.recipely.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ediel.mv.recipely.databinding.RecipeItemBinding
import com.ediel.mv.recipely.domain.models.Recipe

class RecipeAdapter(private val recipes: MutableList<Recipe>): RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    var onClickTourListener: ((Recipe) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecipeItemBinding.inflate(inflater, parent, false)
        return RecipeViewHolder(binding)
    }

    override fun getItemCount() = recipes.size

    override fun onBindViewHolder(holder: RecipeAdapter.RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    /*fun filterData(list: List<Tour>){
        tours.clear()
        tours.addAll(list)
        notifyDataSetChanged()
    }*/


    inner class RecipeViewHolder(val binding: RecipeItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(recipe: Recipe) {
            binding.recipeName.text = recipe.name
            /*binding.txtTourDescription.text = "${tour.description.subSequence(0,80)}..."
            Picasso.get().load(tour.TourImage).into(binding.tourImage)
            binding.root.setOnClickListener {
                onClickTourListener?.invoke(tour)
            }*/
        }
    }
}