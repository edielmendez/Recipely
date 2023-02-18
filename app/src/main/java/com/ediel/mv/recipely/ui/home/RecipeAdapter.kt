package com.ediel.mv.recipely.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ediel.mv.recipely.databinding.RecipeItemBinding
import com.ediel.mv.recipely.domain.models.Recipe
import com.squareup.picasso.Picasso

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

    fun setRecipes(list: List<Recipe>){
        recipes.clear()
        recipes.addAll(list)
        notifyDataSetChanged()
    }

    /*fun filterData(list: List<Tour>){
        tours.clear()
        tours.addAll(list)
        notifyDataSetChanged()
    }*/


    inner class RecipeViewHolder(val binding: RecipeItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(recipe: Recipe) {
            binding.recipeName.text = recipe.name
            Picasso.get().load(recipe.image).into(binding.recipeImageSrc)
            binding.root.setOnClickListener {
                onClickTourListener?.invoke(recipe)
            }
        }
    }
}

/*private class RecipeDiffCallback : DiffUtil.ItemCallback<Recipe>() {
    override fun areItemsTheSame(
        oldItem: Recipe,
        newItem: Recipe
    ): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(
        oldItem: Recipe,
        newItem: Recipe
    ): Boolean {
        return oldItem.name == newItem.name
    }
}*/