package application;

import static application.Main.Generatescene;
import static application.Main.Solverscene;
import static application.SolverController.tfMap;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import utils.ConnectionUtil;
//import application.SolverController;

public class MenuController implements Initializable{
	
	@FXML
	Button playBtn, SolverBtn;
	
	@FXML
	Label bestscore;
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs;
	int rset;
	
	public MenuController() throws IOException {
                
                
		con = ConnectionUtil.conDB();
        }
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		if (con == null) {
			bestscore.setText("No DB connection");
		}
		else {
		
			String sql1 = "SELECT highscore1 FROM login WHERE id = ?";
			try {
				ps = con.prepareStatement(sql1);
				ps.setString(1, Integer.toString(StartScreenController.id));
				rs = ps.executeQuery();
				if (!rs.next()) {
					return;
				}
				String hs = Integer.toString(rs.getInt("highscore1"));
				if (!hs.equals("-1")) {
					bestscore.setText("Your Best Score: "+hs+"s");
				}
			}catch (Exception e){
					e.printStackTrace();
					//showDialog("Login Successful", null, "Successful");
			}
		}
	}
	
	public void handleButton(MouseEvent event) {
		if(event.getSource() == playBtn) {
                    Main.setStageAndScene("application/grid.css", Generatescene);
		}
		else if(event.getSource() == SolverBtn) {
                    Main.setStageAndScene("application/grid.css", Solverscene);
		}
	}
}
