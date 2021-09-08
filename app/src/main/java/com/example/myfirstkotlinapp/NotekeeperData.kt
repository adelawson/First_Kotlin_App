package com.example.myfirstkotlinapp

data class CourseInfo(val courseID:String, val courseTitle:String) {
    override fun toString(): String {
        return courseTitle
    }
}

data class NoteInfo(var course:CourseInfo, var title:String, var text:String)