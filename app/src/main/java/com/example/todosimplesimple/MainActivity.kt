package com.example.todosimplesimple

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.apache.commons.io.FileUtils
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    var listoftaks = mutableListOf<String>()
    lateinit var adapter: TaskItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val onLongClickListener = object : TaskItemAdapter.OnlongClickListener {
            override fun onItemLongclicked(position: Int) {
                listoftaks.removeAt(position)
                adapter.notifyDataSetChanged()

                saveItems()
            }

        }

        loadItems()


        val recycleView = findViewById<RecyclerView>(R.id.recycleView)
        adapter = TaskItemAdapter(listoftaks, onLongClickListener)
        recycleView.adapter=adapter
        recycleView.layoutManager = LinearLayoutManager(this)

        val userInputtedTask= findViewById<EditText>(R.id.addtext)

        findViewById<Button>(R.id.button).setOnClickListener {
            val userInputtedTask= findViewById<EditText>(R.id.addtext).text.toString()

            listoftaks.add(userInputtedTask)

            adapter.notifyItemInserted(listoftaks.size -1)

            findViewById<EditText>(R.id.addtext).setText("")

            saveItems()

        }


    }

    //save data
    fun getDataFile() : File {
        return File(filesDir, "crow.txt")
    }

    fun loadItems(){
        try {
            listoftaks = FileUtils.readLines(getDataFile(), Charset.defaultCharset())
        } catch (ioException: IOException){
            ioException.printStackTrace()}
    }


    fun saveItems(){

        try {

            FileUtils.writeLines(getDataFile(), listoftaks)
        } catch (ioException: IOException){
            ioException.printStackTrace()
        }
    }

}

