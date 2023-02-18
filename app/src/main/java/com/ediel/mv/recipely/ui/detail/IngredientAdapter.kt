package com.ediel.mv.recipely.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ediel.mv.recipely.databinding.IngredientItemBinding
import com.ediel.mv.recipely.domain.models.Ingredient

class IngredientAdapter(private val ingredients: MutableList<Ingredient>): RecyclerView.Adapter<IngredientAdapter.RecipeViewHolder>() {
    var onClickIngredientListener: ((Ingredient) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = IngredientItemBinding.inflate(inflater, parent, false)
        return RecipeViewHolder(binding)
    }

    override fun getItemCount() = ingredients.size

    override fun onBindViewHolder(holder: IngredientAdapter.RecipeViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }


    inner class RecipeViewHolder(val binding: IngredientItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(recipe: Ingredient) {
            binding.ingredientName.text = recipe.name
            binding.root.setOnClickListener {
                onClickIngredientListener?.invoke(recipe)
            }
        }
    }
}