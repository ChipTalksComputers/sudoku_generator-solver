package application;
	
import static application.GenerateController.map;
import static application.SolverController.tfMap;
import application.custom.Numberfield;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;


public class Main extends Application {
	
	private static Stage primaryStage;
        public static Scene Startscene;
        public static Scene Menuscene;
        public static Scene Generatescene;
        public static Scene Solverscene;
        public Parent StartScreenRoot;
        public Parent MenuRoot;
        public Parent GenerateRoot;
        public Parent SolverRoot;
        
	@Override
	public void start(Stage primaryStage) {
		//Font.loadFont(getClass().getResourceAsStream("/ObelixProBIt-cyr.ttf"), 12);
		try {			
                        StartScreenRoot = FXMLLoader.load(getClass().getResource("/fxmls/StartScreen.fxml"));
                        MenuRoot = FXMLLoader.load(getClass().getResource("/fxmls/Menu.fxml"));
                        GenerateRoot = FXMLLoader.load(getClass().getResource("/fxmls/Generate.fxml"));   
                        SolverRoot = FXMLLoader.load(getClass().getResource("/fxmls/Solver.fxml"));   
                        
                        Startscene = new Scene(StartScreenRoot);
                        Menuscene = new Scene(MenuRoot);
                        Generatescene = new Scene(GenerateRoot);
                        Solverscene = new Scene(SolverRoot);
                        
                        int k=1;
                            for (int i=0; i<9; i++){
                                for(int j=0; j<9; j++){
                                    tfMap.put("box"+k,(TextField)Solverscene.lookup("#box"+k));
                                    map.put("box"+k,(TextField)Generatescene.lookup("#box"+k));
                                k++;
                            }
                        }

                        
                        this.primaryStage = primaryStage;
                        
                        
                        setStageAndScene("application/application.css", Startscene);

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static Stage getPrimaryStage() {
		return Main.primaryStage;
	}
        
        public static void setStageAndScene(String cssPath, Scene scene){
			scene.getStylesheets().add(cssPath);
			primaryStage.setScene(scene);
			primaryStage.show(); 
                        primaryStage.setMinWidth(primaryStage.getWidth());
                        primaryStage.setMinHeight(primaryStage.getHeight());
        }
}

