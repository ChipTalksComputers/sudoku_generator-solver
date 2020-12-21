package application;

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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import utils.ConnectionUtil;

public class StartScreenController implements Initializable{
	
	@FXML
	TextField username;
	@FXML
	PasswordField password;
	@FXML
	Button submit, signup;
	@FXML
	Label login, usernameText, passwordText, prompt, title;

	public static int id;
	
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs;
	int rset;
	
	
	public StartScreenController() {
		con = ConnectionUtil.conDB();
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
                title.setId("title");
		if (con == null) {
			login.setText("Problem connecting to database");
                        login.setId("DBFail");
		}
                else{
                    login.setId("login");
                }
	}
	
	public void handleButton(MouseEvent event) {
		if(event.getSource() == submit) {
			if (login.getText().equals("Sign up")) {
				SignUp();
			}
			else if(login.getText().equals("Login")) {
				Login();
			}
			
		}
		else if(event.getSource() == signup) {
			if (signup.getText().equals("Sign up")) {
				login.setText("Sign up");
				usernameText.setText("Enter new username");
				passwordText.setText("Enter new password");
				signup.setText("Login");
				username.setText("");
				password.setText("");
			}
			else if(signup.getText().equals("Login")) {
				login.setText("Login");
				usernameText.setText("Username");
				passwordText.setText("Password");
                                signup.setId("signup");
				signup.setText("Sign up");
				username.setText("");
				password.setText("");
                                prompt.setText("");
			}
			
		}
	}
	
	private void Login() {
		
		String Username = username.getText();
		String Password = password.getText();
		
		String sql = "SELECT * FROM login WHERE username = ? and password = ?";
		//String sql1 = "SELECT id FROM login WHERE username = ?"; 
		
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, Username);
			ps.setString(2, Password);
			rs = ps.executeQuery();
			if (!rs.next()) {
				prompt.setTextFill(Color.TOMATO);
				prompt.setText("Failed");
			}
			else {
				id = rs.getInt(1);
				//prompt.setTextFill(Color.LIGHTSEAGREEN);
				//prompt.setText("Successful");
				Parent MenuRoot;
				try {
					MenuRoot = FXMLLoader.load(getClass().getResource("/fxmls/Menu.fxml"));
					Scene scene = new Scene(MenuRoot);
                                        scene.getStylesheets().add("application/application.css");
					Main.getPrimaryStage().setScene(scene);
					Main.getPrimaryStage().show();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
			//showDialog("Login Successful", null, "Successful");
		}
	
	}
	
	private void SignUp() {
		
		String Username = username.getText();
		String Password = password.getText();
		
		String sql = "INSERT INTO login (username, password) VALUES (?, ?)";
		String sqlCheck = "SELECT * FROM login WHERE username = ?";
                
                try {
                    
                    ps = con.prepareStatement(sqlCheck);
                    ps.setString(1, Username);
                    rs = ps.executeQuery();
                    if (!rs.next()){
                      try {
                    	ps = con.prepareStatement(sql);
                    	ps.setString(1, Username);
                    	ps.setString(2, Password);
                        if (Username.equals("") || Password.equals("")){
                                prompt.setTextFill(Color.TOMATO);
                    		prompt.setText("Failed");                                
                        }
                        else{
                            rset = ps.executeUpdate();
                            if (rset == 0) {
                    		prompt.setTextFill(Color.TOMATO);
                    		prompt.setText("Failed");
                            }
                            else {
                    		prompt.setTextFill(Color.GREENYELLOW);
                    		prompt.setText("Successful");
                            }
                        }
                    }
                    catch (Exception e){
                    	e.printStackTrace();
			//showDialog("Login Successful", null, "Successful");
                    }
                    }
                    else{
                          prompt.setTextFill(Color.TOMATO);
                    	  prompt.setText("Username exists");
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
	
	}
}
