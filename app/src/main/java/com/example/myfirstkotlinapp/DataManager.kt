package com.example.myfirstkotlinapp

object DataManager {
    val courses = HashMap<String,CourseInfo>()
    val notes = ArrayList<NoteInfo>()

    init {
        addNotes()
    }


    private fun addNotes(){
        var course = CourseInfo("android_intent", "Android Programming with Intent")
        courses.set(course.courseID, course)
        notes.add(NoteInfo(course, "my title", course.courseTitle))

        course = CourseInfo("android_service", "Android Programming with Service")
        courses.set(course.courseID, course)
        notes.add(NoteInfo(course, "title service", course.courseTitle))
    }
}