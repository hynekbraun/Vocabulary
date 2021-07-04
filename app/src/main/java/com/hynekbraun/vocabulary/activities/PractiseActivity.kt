package com.hynekbraun.vocabulary.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.hynekbraun.vocabulary.R
import com.hynekbraun.vocabulary.model.WordListEntity
import com.hynekbraun.vocabulary.viewModel.PractiseViewModel
import kotlinx.android.synthetic.main.activity_practise.*

class PractiseActivity : AppCompatActivity() {
    private lateinit var viewModel: PractiseViewModel
    private var testList: MutableList<WordListEntity> = mutableListOf()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practise)
        viewModel = ViewModelProvider(this).get(PractiseViewModel::class.java)

        //observe data, get the list of words
        viewModel.readAllData.observe(this) { words ->
            words.let {
                testList = words.toMutableList()
            }
            var randomWord: WordListEntity = testList.random()
            tv_practise_answer.text = "Right answer is: ${randomWord.translation}"
            tv_practise_notes.text = randomWord.notes
            tv_practise_wordToTranslate.text = randomWord.word


            btn_practise_submit.setOnClickListener {
                if (et_practise_answer.text.toString() == randomWord.translation) {
                    tv_practise_answer.visibility = View.INVISIBLE
                    tv_practise_notes.visibility = View.INVISIBLE
                    Toast.makeText(this, "You were right", Toast.LENGTH_SHORT).show()
                    randomWord = testList.random()
                    tv_practise_answer.text = "Right answer is: ${randomWord.translation}"
                    tv_practise_notes.text = randomWord.notes
                    tv_practise_wordToTranslate.text = randomWord.word
                    et_practise_answer.setText("")


                } else {
                    Toast.makeText(this, "Wrong answer", Toast.LENGTH_SHORT).show()
                }
            }
            btn_practise_answer.setOnClickListener {
                if (tv_practise_answer.isVisible) {
                    tv_practise_answer.visibility = View.INVISIBLE
                } else {
                    tv_practise_answer.visibility = View.VISIBLE
                }
            }
            btn_practise_notes.setOnClickListener {
                if (tv_practise_notes.isVisible) {
                    tv_practise_notes.visibility = View.INVISIBLE
                } else {
                    tv_practise_notes.visibility = View.VISIBLE
                }
            }

        }
    }
}