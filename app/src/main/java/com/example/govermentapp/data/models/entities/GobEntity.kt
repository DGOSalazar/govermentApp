package com.example.govermentapp.data.models.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gob_table")
data class GobEntity (@PrimaryKey(autoGenerate = true)
                      @ColumnInfo(name="identity") var identity : Int = 0,
                      @ColumnInfo(name="_id") var id : String="",
                      @ColumnInfo(name="date_insert") var date_insert : String ="",
                      @ColumnInfo(name="slug") var slug : String="",
                      @ColumnInfo(name="columns") var columns : String="",
                      @ColumnInfo(name="fact") var fact : String="",
                      @ColumnInfo(name="organization") var organization : String="",
                      @ColumnInfo(name="resource") var resource : String="",
                      @ColumnInfo(name="url") var url : String="",
                      @ColumnInfo(name="operations") var operations : String="",
                      @ColumnInfo(name="dataset") var dataset : String="",
                      @ColumnInfo(name="created_at") var created_at : String="")
