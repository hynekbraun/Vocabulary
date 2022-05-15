package com.hynekbraun.vocabulary.activities

import android.app.AlertDialog
import android.app.Dialog
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hynekbraun.vocabulary.R
import com.hynekbraun.vocabulary.model.WordListEntity
import com.hynekbraun.vocabulary.adapter.ListAdapter
import com.hynekbraun.vocabulary.viewModel.WordsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add_word.*
import kotlinx.android.synthetic.main.dialog_update_word.*

class MainActivity : AppCompatActivity() {
    /asdasdasd

    private lateinit var viewModel: WordsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(WordsViewModel::class.java)


        // Recyclerview
        val adapter = ListAdapter(this)
        val recyclerView = recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Setting up recyclerview with data
        viewModel.readAllData.observe(this) { words ->
            words.let {
                adapter.setData(words)
            }
        }

        //Buttons functionality
        fab_add_word.setOnClickListener {
            addWord()
        }
        btn_to_practise.setOnClickListener {
            toPractise()
        }


    }

    private fun toPractise() {
        val intent = Intent(this@MainActivity, PractiseActivity::class.java)
        startActivity(intent)
    }

    private fun addWord() {
        val updateDialog = Dialog(this, R.style.Theme_Dialog)
        updateDialog.setCancelable(false)
        /*Set the screen content from a layout resource.
         The resource will be inflated, adding all top-level views to the screen.*/
        updateDialog.setContentView(R.layout.dialog_add_word)

        updateDialog.tv_new_add.setOnClickListener {

            val word = updateDialog.et_add_word.text.toString()
            val translation = updateDialog.et_add_translation.text.toString()
            val notes = updateDialog.et_add_notes.text.toString()



            if (word.isNotEmpty() && translation.isNotEmpty()) {

                val word = WordListEntity(0, word, translation, notes)

                //add new word
                viewModel.addWord(word)
                updateDialog.dismiss()
                Toast.makeText(this, "Your word was successfully added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Please fill in word and word translation", Toast.LENGTH_SHORT)
                    .show()

            }
        }
        updateDialog.tv_new_cancel.setOnClickListener {
            updateDialog.dismiss()
        }
        //Start the dialog and display it on screen.
        updateDialog.show()
    }

    fun updateWord(currentWord: WordListEntity) {
        val updateDialog = Dialog(this, R.style.Theme_Dialog)
        updateDialog.setCancelable(false)
        /*Set the screen content from a layout resource.
         The resource will be inflated, adding all top-level views to the screen.*/
        updateDialog.setContentView(R.layout.dialog_update_word)

        updateDialog.et_update_word.setText(currentWord.word)
        updateDialog.et_update_translation.setText(currentWord.translation)
        updateDialog.et_update_notes.setText(currentWord.notes)

        updateDialog.text_view_update.setOnClickListener {

            val word = updateDialog.et_update_word.text.toString()
            val translation = updateDialog.et_update_translation.text.toString()
            val notes = updateDialog.et_update_notes.text.toString()


            val updatedWord = WordListEntity(currentWord.id, word, translation, notes)

            if (word.isNotEmpty() && translation.isNotEmpty()) {

                viewModel.updateWord(updatedWord)

                Toast.makeText(applicationContext, "Word Updated.", Toast.LENGTH_LONG).show()

                updateDialog.dismiss() // Dialog will be dismissed

            } else {
                Toast.makeText(
                    applicationContext,
                    "Word or Translation cannot be blank",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        updateDialog.text_view_cancel.setOnClickListener( {
            updateDialog.dismiss()
        })
        //Start the dialog and display it on screen.
        updateDialog.show()
    }

    fun deleteWord(currentWord: WordListEntity) {
        val builder = AlertDialog.Builder(this)
        builder.setPositiveButton("Yes") { _, _ ->
            viewModel.deleteWord(currentWord)
            Toast.makeText(this, "Word successfully deleted", Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ -> }
        builder.setTitle("Delete ${currentWord.word}?")
        builder.setMessage("Are you sure you want to delete ${currentWord.word}?")
        builder.create().show()
    }

}