//all user-defined libraries
import Entities.FootballPlayer;
import FileManagement.PlayerManager;
import GUI.PlayerRegGUI;

public class Start{
    public static void main(String[] args){
        PlayerRegGUI playerRegGUI = new PlayerRegGUI();
        PlayerManager playerManager = new PlayerManager();
        FootballPlayer[] data = playerManager.getAllPlayer();
        for (FootballPlayer datum : data) {
            datum.show();
        }
    }
}