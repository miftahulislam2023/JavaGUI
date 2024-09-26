package FileManagement;
import Entities.Student;

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class StudentManager{
    String filePath;
    public StudentManager(){
        filePath = "DataFiles/data.txt";
    }
    public void writeStudent(Student s,boolean append){
        File f = new File(filePath);
        try{
            FileWriter writer = new FileWriter(f,append);
            writer.write(s.getFileWriteFormat());
            writer.flush();
            writer.close();
        }catch(Exception ex){
            
        }

    }
    public Student[] getAllStudents(){
        File f = new File(filePath);
        Student[] students = null;
        try{
            Scanner sc = new Scanner(f);
            //not recommended in professional development
            Scanner sc2 = new Scanner(f);
            int count=0;
            while(sc2.hasNextLine()){
                String temp = sc2.nextLine();
                count++;
            }
            //
            students = new Student[count];
            count=0;
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] data = line.split(";");
                Student s = new Student(data[0],data[1],data[2],data[3]);
                students[count]=s;
                count++;
            }

        }catch(Exception ex){
            
        }
        return students;

    }
    public Student searchStudent(int sl){
        Student[] students = getAllStudents();
        for(int i=0;i<students.length;i++){
            if(students[i].getSl() == sl){
                return students[i];
            }
        }
        return null;
    }
    public void deleteStudent(int sl){
        Student[] students = getAllStudents();
        for(int i=0;i<students.length;i++){
            if(students[i].getSl() == sl){
                students[i] = null;
            }
        }
        for(int i=0;i<students.length;i++){
            if(students[i] != null){
                if(i==0) writeStudent(students[i],false);
                else writeStudent(students[i],true);
            }
            
        }
    }
    public void updateStudent(Student s){
        Student[] students = getAllStudents();
        for(int i=0;i<students.length;i++){
            if(students[i].getSl() == s.getSl()){
                students[i].setName(s.getName());
                students[i].setDob(s.getDob());
                students[i].setSId(s.getSId());
            }
        }
        for(int i=0;i<students.length;i++){
            if(i==0) writeStudent(students[i],false);
            else writeStudent(students[i],true);
        }
    }
    
}