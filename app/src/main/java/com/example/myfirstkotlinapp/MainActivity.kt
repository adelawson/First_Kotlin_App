package com.example.myfirstkotlinapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.example.myfirstkotlinapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var notePos = POS_NOT_SET
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinAdpt = ArrayAdapter<CourseInfo>(this,android.R.layout.simple_spinner_item
            ,DataManager.courses.values.toList())
        spinAdpt.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerMain.adapter = spinAdpt


        notePos= intent.getIntExtra(EXTRA_NOTE_POS, POS_NOT_SET)

        if (notePos != POS_NOT_SET){
            displayNote()
        }

    }
    fun displayNote(){
        val note = DataManager.notes[notePos]
        binding.noteTitle.setText(notePos.toString())
        binding.noteTitle2.setText(note.text)

        val coursePos = DataManager.courses.values.indexOf(note.course)
        binding.spinnerMain.setSelection(coursePos)

    }

    fun nextNote(){
        if (notePos<1){
        ++notePos
        displayNote()
        invalidateOptionsMenu()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_sample-> {
                nextNote()
                return true
            }
            R.id.action_next->{
                val nextPG = Intent(this,NoteListActivity::class.java)
                startActivity(nextPG)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        val menuItem = menu?.findItem(R.id.action_sample)
        if (menuItem != null){
            menuItem.setIcon(R.drawable.ic_baseline_arrow_right_24)
            menuItem.isEnabled= false

        }
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onPause() {
        super.onPause()
        saveData()
    }
    private fun saveData(){
        val note = DataManager.notes[notePos]
        note.title = binding.noteTitle.text.toString()
        note.text = binding.noteTitle2.text.toString()
        note.course= binding.spinnerMain.selectedItem as CourseInfo
    }
}