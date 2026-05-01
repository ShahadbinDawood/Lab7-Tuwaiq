package com.example.lab7.Service;

import com.example.lab7.Model.Classes;
import com.example.lab7.Model.Subject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClassesService {
    ArrayList<Classes> classesList = new ArrayList<>();

    public ArrayList<Classes> getClasses() {
        return classesList;
    }

    public boolean addClasses(Classes classes) {
        for (Classes c : classesList) {
            if (c.getID().equals(classes.getID())) {
                return false;
            }
        }
        classesList.add(classes);
        return true;
    }

    public boolean updateClasses(String id, Classes classes) {
        for (int i = 0; i < classesList.size(); i++) {
            if (classesList.get(i).getID().equals(id)) {
                classesList.set(i, classes);
                return true;
            }
        }
        return false;
    }

    public boolean deleteClasses(String id) {
        for (Classes c : classesList) {
            if (c.getID().equals(id)) {
                classesList.remove(c);
                return true;
            }
        }
        return false;
    }

    public boolean addSubject(String id, Subject subject) {
        for (Classes c : classesList) {
            if (c.getID().equals(id)) {
                 c.getSubjects().add(subject);
                return true;
            }
        }
        return false;

    }
    public Classes getClassByID(String id) {
        for (Classes c : classesList) {
            if (c.getID().equals(id)) {
                return c;
            }
        }
        return null;

    }
    public Classes searchWithCategory(String category) {

        for (Classes c : classesList) {
               for (Subject s :c.getSubjects()){
                   if (s.getCategory().equalsIgnoreCase(category))
                       return  c;
               }
        }
        return null;

    }


}