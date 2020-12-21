package application.custom;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;

public class Numberfield extends TextField{
	public Numberfield(){
            formatText(this);
	}
        
        private void formatText(TextField t){
            
            t.setTextFormatter(new TextFormatter<>(change ->{
                if (change.getControlNewText().matches("[1-9]?")){
                    change.setCaretPosition(0);
                    return change;
                }
                return null;
            }));
        }
	
	/*@Override 
	public void replaceText(int i, int i1, String string) {
		if (string.matches("[0-9]") || string.isEmpty()) {
			super.replaceText(i, i1, string);
		}
	}
	
	@Override
	public void replaceSelection(String string) {
		super.replaceSelection(string);
	}*/
}
