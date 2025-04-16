package org;

public class Teacher extends User{
    public Course makeCourse() {
        return new Course(this);
    }
}
