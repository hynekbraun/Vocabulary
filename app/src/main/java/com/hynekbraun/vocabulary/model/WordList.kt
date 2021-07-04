package com.hynekbraun.vocabulary.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "words_table")
data class WordListEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val word: String,
    val translation: String,
    val notes: String
): Parcelable