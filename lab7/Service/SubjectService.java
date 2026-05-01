package com.example.lab7.Service;

import com.example.lab7.Model.Subject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SubjectService {
    ArrayList<Subject> subjects = new ArrayList<>();

    public ArrayList<Subject> getSubjects (){
        return subjects ;
    }
    public boolean addSubjects (Subject subject){
        for (Subject s : subjects){
            if(s.getID().equals(subject.getID())){
                return false ;
            }
        }
        subjects.add(subject);
        return true ;
    }
    public boolean updateSubjects (String id , Subject subject){
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getID().equals(id)){
                subjects.set(i,subject);
                return true;
            }
        }
        return false;
    }

    public boolean deleteSubjects (String id){
        for (Subject s : subjects){
            if(s.getID().equals(id)){
                subjects.remove(s);
                return true ;
            }
        }
        return false;
    }

    public ArrayList<Subject> listSubjectsByAuthor (String author){
        ArrayList<Subject> authorSubjects = new ArrayList<>();
        for (Subject s : subjects){
            if(s.getAuthor().equalsIgnoreCase(author)){
                authorSubjects.add(s);
            }
        }
        return authorSubjects;

    }
    public ArrayList<Subject> searchByTeacher (String teacher ){
        ArrayList<Subject> teacherSubjects = new ArrayList<>();
        for (Subject s : subjects){
            if(s.getTeacher().equalsIgnoreCase(teacher)){
                teacherSubjects.add(s);
            }
        }
        return teacherSubjects;

    }
    public ArrayList<Subject> listSubjectsByCategory(String category ){
        ArrayList<Subject> categorySubjects = new ArrayList<>();
        for (Subject s : subjects){
            if(s.getCategory().equalsIgnoreCase(category)){
                categorySubjects.add(s);
            }
        }
        return categorySubjects;

    }


}
