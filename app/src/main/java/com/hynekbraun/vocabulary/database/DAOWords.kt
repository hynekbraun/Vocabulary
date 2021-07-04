package com.hynekbraun.vocabulary.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hynekbraun.vocabulary.model.WordListEntity


//
@Dao
interface DAOWords {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWord(word: WordListEntity)

    @Update
    suspend fun updateWord(word: WordListEntity)

    @Delete
    suspend fun deleteWord(word: WordListEntity)

    @Query("DELETE FROM words_table")
    suspend fun deleteAllWords()

    @Query("SELECT * FROM words_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<WordListEntity>>


}