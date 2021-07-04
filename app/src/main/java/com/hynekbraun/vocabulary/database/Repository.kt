package com.hynekbraun.vocabulary.database

import androidx.lifecycle.LiveData
import com.hynekbraun.vocabulary.database.DAOWords
import com.hynekbraun.vocabulary.model.WordListEntity


//Communicator between DAO and ViewModel

class RepositoryWords(private val wordDAO: DAOWords) {

    val readAllData: LiveData<List<WordListEntity>> = wordDAO.readAllData()

    suspend fun addWord(user: WordListEntity){
        wordDAO.addWord(user)
    }

    suspend fun updateWord(user: WordListEntity){
        wordDAO.updateWord(user)
    }

    suspend fun deleteWord(user: WordListEntity){
        wordDAO.deleteWord(user)
    }

    suspend fun deleteAllWords(){
        wordDAO.deleteAllWords()
    }
}