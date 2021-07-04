package com.hynekbraun.vocabulary.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.hynekbraun.vocabulary.database.RepositoryWords
import com.hynekbraun.vocabulary.database.WordsDatabase
import com.hynekbraun.vocabulary.model.WordListEntity

class PractiseViewModel(application: Application): AndroidViewModel(application){
    val readAllData: LiveData<List<WordListEntity>>
    private val repository: RepositoryWords

    init {
        val wordDao = WordsDatabase.getDatabase(
            application
        ).userDao()
        repository = RepositoryWords(wordDao)
        readAllData = repository.readAllData
    }
}