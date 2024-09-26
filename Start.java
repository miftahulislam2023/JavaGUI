//all user-defined libraries
import Entities.Student;
import FileManagement.StudentManager;
import GUI.StudentRegGUI;

public class Start{
    public static void main(String[] args){
        StudentRegGUI g = new StudentRegGUI();
        StudentManager smanager = new StudentManager();
        Student[] data = smanager.getAllStudents();
        for(int i =0; i<data.length;i++){
            data[i].show();
        }
    }
}