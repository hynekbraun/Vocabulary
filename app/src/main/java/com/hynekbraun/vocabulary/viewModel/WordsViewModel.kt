package com.hynekbraun.vocabulary.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hynekbraun.vocabulary.database.RepositoryWords
import com.hynekbraun.vocabulary.database.WordsDatabase
import com.hynekbraun.vocabulary.model.WordListEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WordsViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<WordListEntity>>
    private val repository: RepositoryWords

    init {
        val wordDao = WordsDatabase.getDatabase(
            application
        ).userDao()
        repository = RepositoryWords(wordDao)
        readAllData = repository.readAllData
    }

    fun addWord(word: WordListEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addWord(word)
        }
    }

    fun updateWord(word: WordListEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateWord(word)
        }
    }

    fun deleteWord(word: WordListEntity){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteWord(word)
        }
    }

    fun deleteAllWords(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllWords()
        }
    }

}