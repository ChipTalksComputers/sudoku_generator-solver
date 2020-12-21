package application;
import Backend.Global;
import Backend.solve;
import static application.Main.Menuscene;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextFormatter;
import application.custom.Numberfield;

import javafx.scene.control.TextFormatter;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.layout.GridPane;

public class SolverController implements Initializable{
	
	public static Scene Scene;
        Integer[] values = {1,2,3,4,5,6,7,8,9};
		
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
	Button btn;
	@FXML
	Button backBtn;
        @FXML
        Label lbl;
        @FXML 
        GridPane gp;
	
	public static Map<String, TextField> tfMap = new HashMap<String, TextField>();
	
	public SolverController() {
            
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {           
            gp.addEventFilter(KeyEvent.KEY_PRESSED, this::handleArrowNavigation);
	}
	
	public void handleButton(MouseEvent event) {
		if (event.getSource() == btn) {
			getValues();
		}
		else if (event.getSource() == backBtn) {
                    Main.setStageAndScene("application/application.css", Menuscene);
		}
	}
	
	public void getValues() {
                        
                for (int i=0; i<9; i++){
                    for (int j=0; j<9; j++){
                        Global.board[i][j] = 0;
                    }
                }
		
		int i = 1;
		Scene = box1.getScene();
		for (int l=0; l<9; l++) {
			for (int m=0; m<9; m++) {
				
				String text = tfMap.get("box"+i).getText();
				if(text.equals("")) {
					Global.board[l][m] = 0;
                                }
                                else{
                                        Global.board[l][m] = text.charAt(0) - 48;
                                }
                                i++;
			}
		}
                
                for (int k=0; k<9; k++){
                    for (int j=0; j<9; j++){
                        System.out.print(Global.board[k][j]);
                    }
                    System.out.println("");
                }
		print_board();
	}
	
	public void print_board() {
		solve sol = new solve(values);
		if (sol.solve_board() == false){
                    lbl.setText("Invalid Input");
                    return;
                }
                
                for (int i=0; i<9; i++){
                    for (int j=0; j<9; j++){
                        System.out.print(Global.board[i][j]);
                    }
                    System.out.println("");
                }
                
		int k=1;
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				tfMap.get("box"+k).setText(Integer.toString(Global.board[i][j]));
				k++;
			}
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
                tfMap.get("box"+i).requestFocus();
                event.consume();
            }
            
        }
	
	public Map<String, TextField> getMap(){
		return tfMap;
	}
        
        private Stage myStage;
        public void setStage(Stage stage) {
            myStage = stage;
        }
	
}
