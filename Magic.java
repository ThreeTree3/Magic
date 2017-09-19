
import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.canvas.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.stage.Stage;

public class Magic extends Application {
	Canvas canvas;
	GraphicsContext gc;
	Slider slider;
	ChoiceBox<String> choiceBox;
	Button button;
	public void start(Stage stage) {
		
		// set main window
		stage.setWidth(800);
		stage.setHeight(510);
		
		// making object
		canvas = new Canvas(500, 500); 
		gc = canvas.getGraphicsContext2D();
		
		// slider
		slider = new Slider(0, 15, 0);
		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setOnMouseDragged(event-> buttonPressed());
		//slider.setOnClicked(event-> buttonPressed());
		
		// choice box
		choiceBox = new ChoiceBox<>();
		choiceBox.getItems().addAll("Black", "Turquise", "Gold");
		choiceBox.setValue("Black");
		
		// button
		button = new Button("OK");
		button.setOnAction(event -> buttonPressed());
		
		// Set VBox
		VBox box = new VBox(3);
		box.setPadding(new Insets(20, 25, 25, 25));
		box.getChildren().addAll(slider);
		box.getChildren().addAll(choiceBox);
		box.getChildren().addAll(button);
		
		//making group object
		GridPane root = new GridPane();
		GridPane.setConstraints(box,0,1);
		GridPane.setConstraints(canvas,1,1);
		root.getChildren().addAll(canvas, box);
		stage.setScene(new Scene(root));
				
		// show stage
		stage.show();
	}
	public void buttonPressed(){
		int sliderValue = 0;
		//‰~Žü‚Ì”¼Œa
		int r = 200;
		sliderValue = (int)slider.getValue();
		
		gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
		gc.beginPath();
		
		ArrayList<Point2D> points = new ArrayList<>();
		for (int i = 0; i < sliderValue; i++) { 
			double x = 250 + r * Math.cos(2 * Math.PI * i / sliderValue); 
			double y = 250 + r * Math.sin(2 * Math.PI * i / sliderValue);
			points.add(new Point2D(x, y));
		}
		for (int i = 0; i < sliderValue; i++) {
			for (int j = i + 1; j < sliderValue; j++) { 
				gc.moveTo(points.get(i).getX(), points.get(i).getY()); 
				gc.lineTo(points.get(j).getX(), points.get(j).getY());
			}
		}
		if(choiceBox.getValue() == "Black"){
			gc.setStroke(Color.web("#000000"));
		}
		if(choiceBox.getValue() == "Turquise"){
			gc.setStroke(Color.web("#40E0D0"));
		}
		if(choiceBox.getValue() == "Gold"){
			gc.setStroke(Color.hsb(40, 0.7, 0.8));
		}
		gc.stroke();
	}
	public static void main(String[] args) {
		launch();
	}

}