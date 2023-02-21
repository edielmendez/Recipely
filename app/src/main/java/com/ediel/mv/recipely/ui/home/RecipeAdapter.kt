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
    private val tempRecipes: MutableList<Recipe> = mutableListOf()

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
        tempRecipes.addAll(list.toMutableList())
        notifyDataSetChanged()
    }

    fun filterRecipe(word: String){
        if (word.isNullOrEmpty()){
            recipes.clear()
            recipes.addAll(tempRecipes)
        }else{
            val filteredList = recipes.filter {
                it.name.lowercase().contains(word.lowercase()) || matchWithAnyIngredient(word, it)
            }
            recipes.clear()
            recipes.addAll(filteredList)
        }
        notifyDataSetChanged()
    }

    private fun matchWithAnyIngredient(word: String, recipe: Recipe): Boolean{
        return !recipe.ingredients.filter { it.name.lowercase().contains(word.lowercase()) }.isNullOrEmpty()
    }


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