package com.ediel.mv.recipely.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ediel.mv.recipely.databinding.IngredientItemBinding
import com.ediel.mv.recipely.databinding.InstructionItemBinding
import com.ediel.mv.recipely.domain.models.Instruction

class InstructionAdapter(private val instructions: MutableList<Instruction>): RecyclerView.Adapter<InstructionAdapter.RecipeViewHolder>() {
    var onClickInstructionListener: ((Instruction) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = InstructionItemBinding.inflate(inflater, parent, false)
        return RecipeViewHolder(binding)
    }

    override fun getItemCount() = instructions.size

    override fun onBindViewHolder(holder: InstructionAdapter.RecipeViewHolder, position: Int) {
        holder.bind(instructions[position])
    }


    inner class RecipeViewHolder(val binding: InstructionItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Instruction) {
            binding.instructionText.text = item.text
            binding.root.setOnClickListener {
                onClickInstructionListener?.invoke(item)
            }
        }
    }
}