package edu.uw.mistyb01.recipefinder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // model test
        val data = mutableListOf<String>()
        for (i in 1..10) {
            data.add("recipe $i")
        }

        val adapter = Adapter(data)
        val recycler = findViewById<RecyclerView>(R.id.recycler_search_recipes)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter

    }
}

class Adapter(private val recipeData: List<String>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    // stores references to the views that we connect the data tp
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recipeTextView: TextView = view.findViewById<TextView>(R.id.txt_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(
            R.layout.recipe_item, parent, false)

        return ViewHolder(inflatedView)
    }

    // called whenever the data is connected to the view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipeData[position]
        holder.recipeTextView.text = recipe
    }

    override fun getItemCount(): Int {
        return recipeData.size
    }

}