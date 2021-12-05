package edu.uw.mistyb01.recipefinder

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.uw.mistyb01.recipefinder.network.RecipeSearchAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchResultsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // button to test fetching
        view.findViewById<Button>(R.id.test_btn).setOnClickListener {
            Log.v(ContentValues.TAG, "getting search results")

            // send the fetch request
            RecipeSearchAPI.retrofitService.getRecipeSearchResults(
                "public", "hong kong", "db57c240", "be9ab19fed9021fe5bdfdf8ba653d6e6"
            ).enqueue(object: Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    val body = response.body()
                    Log.v(ContentValues.TAG, "$body")
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.e(ContentValues.TAG, "error: ${t.message}")
                }

            })
        }

        // model test
        val data = mutableListOf<String>()
        for (i in 1..10) {
            data.add("recipe $i")
        }

        val adapter = Adapter(data)
        val recycler = view.findViewById<RecyclerView>(R.id.recycler_search_recipes)
        recycler.layoutManager = LinearLayoutManager(this.context)
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