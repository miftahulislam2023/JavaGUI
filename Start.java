//all user-defined libraries
import Entities.Player;
import FileManagement.PlayerManager;
import GUI.PlayerRegGUI;

public class Start{
    public static void main(String[] args){
        PlayerRegGUI playerRegGUI = new PlayerRegGUI();
        PlayerManager playerManager = new PlayerManager();
        Player[] data = playerManager.getAllPlayer();
        for (Player datum : data) {
            datum.show();
        }
    }
}