package Entities;
public class Student{
    private int sl;
    private String name;
    private String sid;
    private String dob;

    public Student(){}
    public Student(int sl,String name, String sid, String dob){
        this.sl = sl;
        this.name = name;
        this.sid = sid;
        this.dob = dob;
    }
    public Student(String sl,String name, String sid, String dob){
        this.sl = Integer.parseInt(sl);
        this.name = name;
        this.sid = sid;
        this.dob = dob;
    } 
    //setter
    public void setSl(int sl){this.sl = sl;}
    public void setName(String name){this.name = name;}
    public void setDob(String dob){this.dob = dob;}
    public void setSId(String sid){this.sid = sid;}
    //getter
    public int getSl(){return sl;}
    public String getName(){return name;}
    public String getDob(){return dob;}
    public String getSId(){return sid;}

    public void show(){
        System.out.println("Sl: " + sl);
        System.out.println("Name: " + name);
        System.out.println("SId: " + sid);
        System.out.println("Dob: " + dob);
    }
    public String getFileWriteFormat(){
        return sl+";"+name+";"+sid+";"+dob+"\n";
    }
}
