package application;
import Backend.Generate;
import static Backend.Generate.answer;
import static Backend.Generate.question;
import Backend.Global;
import Backend.solve;
import Backend.is_valid;
import static application.Main.Menuscene;
import static application.SolverController.tfMap;
import application.custom.Numberfield;
import application.custom.timer;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import utils.ConnectionUtil;

public class GenerateController implements Initializable{
	
	@FXML
	Button checkBtn, ansBtn, genBtn, resBtn;
	
	@FXML
	TextField box1,box2,box3,box4,box5,box6,box7,box8,box9,box10,
			  box11,box12,box13,box14,box15,box16,box17,box18,box19,box20,
			  box21,box22,box23,box24,box25,box26,box27,box28,box29,box30,
			  box31,box32,box33,box34,box35,box36,box37,box38,box39,box40,
			  box41,box42,box43,box44,box45,box46,box47,box48,box49,box50,
			  box51,box52,box53,box54,box55,box56,box57,box58,box59,box60,
			  box61,box62,box63,box64,box65,box66,box67,box68,box69,box70,
			  box71,box72,box73,box74,box75,box76,box77,box78,box79,box80,box81
	;
	
	@FXML
	Label timeLabel;
	@FXML
	Button backBtn;
	@FXML
	Label ryt;
        @FXML
        GridPane gp;
	
	int gen = 1;

	timer Timer;
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs;
	int rset;	
		
	static Map<String, TextField> map = new HashMap<String ,TextField>();
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
            gp.addEventFilter(KeyEvent.KEY_PRESSED, this::handleArrowNavigation);
	}
	
	public GenerateController(){
		//TODO
	}
	
	public void handleButton(MouseEvent event) {

                ryt.setText("");
		if (event.getSource() == genBtn) {
                    try{
                        Timer.stop();
                    }catch(Exception e){
                    
                    }
                    generate();
                    setTime();
		}
                else if (event.getSource() == checkBtn){
                    check();
                }
                else if (event.getSource() == ansBtn){                    
                    showAnswer();
                }
                else if (event.getSource() == resBtn){
                    try{
                        Timer.stop();
                    }catch(Exception e){
                    
                    }
                    reset();
                    setTime();
                }
		else if(event.getSource() == backBtn) {
                    Main.setStageAndScene("application/application.css", Menuscene);
                    try{
                        Timer.stop();
                    }catch(Exception e){
                    
                    }
		}
	}
	
	private void setTime() {
		Timer = new timer(timeLabel);
		Timer.start();
	}
	
	private void generate() {
		Scene scene = box1.getScene();
		Generate gen = new Generate();
		gen.gen();

		
		
		int k=1;
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				map.put("box"+k, (TextField)scene.lookup("#box"+k));
				TextField boxTemp = map.get("box"+k);
				if (Global.board[i][j] == 0) {
					boxTemp.setText("");
				}
				else {
					boxTemp.setText(Integer.toString(Global.board[i][j]));
					boxTemp.setEditable(false);
                                }
				k++;
			}
		}
	}
	
	private void check() {
		int correct = 1;
		int k=1;
		for(int i=0; i<9; i++) {
                    for(int j=0; j<9; j++) {
                        if (!map.get("box"+k).getText().equals(Integer.toString(answer[i][j])))  {
                            System.out.println("Wrong "+answer[i][j]+" "+i+" "+j+" "+map.get("box"+k).getText());
                            correct = 0;
                            i = 9;
                            break;
                        }      
			k++;
                    }
		}

		if (correct == 1) {
			ryt.setText("Correct!");
			ryt.setTextFill(Color.GREENYELLOW);
			con = ConnectionUtil.conDB();
			if (con == null) {
				return;
			}

			int highscore = (int)Timer.getTime();
			String id = Integer.toString(StartScreenController.id);
                        
                        System.out.println(id);
			
			String sql = "SELECT * FROM login WHERE id = ?";			
			try {
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				//ps.setString(2, Password);
				rs = ps.executeQuery();
				if (!rs.next()) {
                                    return;
				}
				else {
					int curHS = rs.getInt(4);
					if (curHS == -1 || highscore < curHS) {
						try {
							String sql1 = "UPDATE login SET highscore1 = ? WHERE id = ?";
							ps = con.prepareStatement(sql1);
							ps.setString(1, Integer.toString(highscore));
							ps.setString(2, id);
							rset = ps.executeUpdate();
							if (rset == 0) {
								return;
							}
						}catch (Exception e){
								e.printStackTrace();
								//showDialog("Login Successful", null, "Successful");
						}
					}
				}
			}
			catch (Exception e){
				e.printStackTrace();
				//showDialog("Login Successful", null, "Successful");
			}
		}
		else {
			ryt.setText("Incorrect!");
			ryt.setTextFill(Color.DARKRED);			
		}	
	}
        
        public void handleArrowNavigation(KeyEvent event){
        Node source = (Node)event.getSource();
        Node focused = source.getScene().getFocusOwner();
        if (event.getCode().isArrowKey() && event.getSource() == gp) {
            String boxId = focused.getId();
            Integer i = Integer.valueOf(boxId.substring(3));
            if (event.getCode() == LEFT && (i-1) % 9 != 0){
                i--;
            }
            else if (event.getCode() == RIGHT && i%9 != 0) {                
                i++;
            }
            else if (event.getCode() == UP && i > 9){
                i-=9;
            }
            else if (event.getCode() == DOWN && i <= 72){
                i+=9;
            }
            System.out.println(i);
            map.get("box"+i).requestFocus();
            event.consume();
       }
            
    }
        
    private void showAnswer(){
        int k=1;
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                map.get("box"+k).setText(Integer.toString(answer[i][j]));
                k++;
            }
        }
    }
    
    private void reset(){
        int k=1;
        for (int i=0; i<9; i++){
            for (int j=0; j<9; j++){
                if (question[i][j] != 0){
                    map.get("box"+k).setText(Integer.toString(question[i][j]));
                }
                else{
                    map.get("box"+k).setText("");
                }
                k++;
            }
        }
    }
}
