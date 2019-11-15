package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class SearchController {
	static String stext="";

	@FXML
    private AnchorPane maxpane;
	
    @FXML
    private TextField text;

    @FXML
    private Button searchbut;

    @FXML
    private Label label;

    @FXML
   private ListView<String> list;

    @FXML
    private Button ok;
    
    @FXML
    void okay(MouseEvent event) throws IOException {
    	MainController.check=true;
    	if(stext.isEmpty())
    	{
    		AnchorPane p = FXMLLoader.load(getClass().getResource("Main.fxml"));
    		maxpane.getChildren().setAll(p);
    	}
    	else
    	{
    	Main.ss=list.getSelectionModel().getSelectedItem();
    	AnchorPane p = FXMLLoader.load(getClass().getResource("Main.fxml"));
		maxpane.getChildren().setAll(p);
	  
    	}  
    	}

    @FXML
    void search(MouseEvent event) throws FileNotFoundException {
    	MainController.check=true;
    	ObservableList<String> words = FXCollections.observableArrayList();
    	File f = new File("C:\\Users\\amogh\\eclipse-workspace\\MusicPlayer\\src\\application\\index");
    	Scanner sc1 = new Scanner(f);
    	stext = text.getText();
    	while(sc1.hasNextLine())
    	{
    		 String s = sc1.nextLine();
    		 String s2=s.toLowerCase();
     	    Pattern p = Pattern.compile(stext.toLowerCase());
     	   Matcher m = p.matcher(s2);
     	    if (m.find())
     	    words.add(s);
     	    else
     	      continue;
    	}
    	list.setItems(words);
    	{
    		if(words.isEmpty())
    		label.setText("SEARCH NOT FOUND");
    	}
    	sc1.close();

    }

}
