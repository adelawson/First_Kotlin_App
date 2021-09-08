package com.example.myfirstkotlinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.myfirstkotlinapp.databinding.NoteListBinding

class NoteListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bind = NoteListBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.newIntent.setOnClickListener {
            val newIntent = Intent(this,MainActivity::class.java)
            startActivity(newIntent)
        }

        val list = bind.listMain

        list.adapter = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,DataManager.notes)

        list.setOnItemClickListener { parent, view, position, id ->
            val passInfo = Intent(this,MainActivity::class.java)
            passInfo.putExtra(EXTRA_NOTE_POS,position)
            startActivity(passInfo)

        }


    }
}