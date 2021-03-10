package main;

import java.awt.Color;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;;

public class Main extends Application {
	
	Pane shapePane;

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Test");
		
		String shapeSelected = "";
		VBox vBoxLeft = new VBox();
		VBox vBoxCoordinates = new VBox();
		VBox vBoxShape = new VBox();
		VBox vBoxColor = new VBox();
		VBox vBoxSubmit = new VBox();
		shapePane = new Pane();
		//VBox vBoxRight = new VBox();
		
		NumberTextField x = new NumberTextField();
		
		HBox inputX = new HBox();
		inputX.getChildren().add(new Text("X :"));
		inputX.getChildren().add(x);
		
		NumberTextField y = new NumberTextField();
		HBox inputY = new HBox();
		inputY.getChildren().add(new Text("Y :"));
		inputY.getChildren().add(y);
		
		vBoxCoordinates.getChildren().add(inputX);
		vBoxCoordinates.getChildren().add(inputY);
		
		NumberTextField height = new NumberTextField();
		HBox inputHeight = new HBox();
		inputHeight.getChildren().add(new Text("Height :"));
		inputHeight.getChildren().add(height);
		
		NumberTextField width = new NumberTextField();
		HBox inputWidth = new HBox();
		inputWidth.getChildren().add(new Text("Width :"));
		inputWidth.getChildren().add(width);
		
		NumberTextField radius = new NumberTextField();
		HBox inputRadius = new HBox();
		inputRadius.getChildren().add(new Text("Radius :"));
		inputRadius.getChildren().add(radius);
		
		ChoiceBox<String> cbForme = new ChoiceBox<>();
		HBox inputShape = new HBox();
		inputShape.getChildren().add(new Text("Forme :"));
		inputShape.getChildren().add(cbForme);
		
		ChoiceBox<String> cbColor = new ChoiceBox<>();
		HBox inputColor = new HBox();
		inputColor.getChildren().add(new Text("Couleur :"));
		inputColor.getChildren().add(cbColor);
		
		Button buttonSubmit = new Button("Valider");
		
		cbForme.getItems().add("Rectangle");
		cbForme.getItems().add("Cercle");
		
		cbForme.setOnAction((event) -> {
			String selectedItem = cbForme.getSelectionModel().getSelectedItem().toString();
			System.out.println(selectedItem);
			
			if(selectedItem.equals("Rectangle")) {
				vBoxShape.getChildren().remove(inputRadius);
				vBoxShape.getChildren().add(inputHeight);
				vBoxShape.getChildren().add(inputWidth);
			}
			if(selectedItem.equals("Cercle")) {
				vBoxShape.getChildren().remove(inputWidth);
				vBoxShape.getChildren().remove(inputHeight);
				vBoxShape.getChildren().add(inputRadius);
			}

		});
		
		
		cbColor.getItems().add("Blue");
		cbColor.getItems().add("Green");
		
		buttonSubmit.setOnAction((event) -> {
			String shape = cbForme.getSelectionModel().getSelectedItem().toString();
			Double valueY = Double.parseDouble(y.getText() != null ? y.getText() : "0");
			Double valueX = Double.parseDouble(x.getText() != null ? x.getText() : "0");
			
			if(shape.equals("Rectangle")) {
				Double valueWidth = Double.parseDouble(width.getText() != null ? width.getText() : "0");
				Double valueHeight = Double.parseDouble(height.getText() != null ? height.getText() : "0");
				addShape(cbForme.getSelectionModel().getSelectedItem().toString(), cbColor.getSelectionModel().getSelectedItem().toString(), new Double[]{valueX, valueY, valueWidth, valueHeight});
			}
			if(shape.equals("Cercle")) {
				Double valueRadius = Double.parseDouble(radius.getText() != null ? radius.getText() : "0");
				addShape(cbForme.getSelectionModel().getSelectedItem().toString(), cbColor.getSelectionModel().getSelectedItem().toString(), new Double[]{valueX, valueY, valueRadius});
			}
			
		});
		
		vBoxShape.getChildren().add(inputShape);
		vBoxColor.getChildren().add(inputColor);
		vBoxSubmit.getChildren().add(buttonSubmit);
		
		vBoxLeft.getChildren().addAll(vBoxShape,vBoxCoordinates,vBoxColor,vBoxSubmit);
		
		vBoxLeft.setAlignment(Pos.CENTER);
		
		//////// shapes
		
		Rectangle r2 = new Rectangle(10,10,20,20);
		//r2.setFill(Paint.valueOf("Transparent"));
		
		Rectangle r = new Rectangle(10,10,10,10);
		r.setFill(Paint.valueOf("pink"));
		
		
		shapePane.getChildren().addAll(r2,r);
		
		SplitPane splitPane = new SplitPane();
		splitPane.getItems().addAll(vBoxLeft, shapePane);
		splitPane.setDividerPositions(0.25);
		vBoxLeft.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.25));
		//vBoxRight.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.75));
		
		Scene scene = new Scene(splitPane, 600,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void addShape(String shape, String color, Double[] params) {
		Shape newShape;
		if(shape.equals("Rectangle")) {
			newShape = new Rectangle(params[0], params[1], params[2], params[3]);
		}
		else {
			newShape = new Circle(params[0], params[1], params[2]);
		}
		newShape.setFill(Paint.valueOf(color));
		newShape.setOnMouseClicked((event) -> {
			shapePane.getChildren().remove(newShape);
		});
		shapePane.getChildren().add(newShape);
	}
	
	public class NumberTextField extends TextField
	{

	    @Override
	    public void replaceText(int start, int end, String text)
	    {
	        if (validate(text))
	        {
	            super.replaceText(start, end, text);
	        }
	    }

	    @Override
	    public void replaceSelection(String text)
	    {
	        if (validate(text))
	        {
	            super.replaceSelection(text);
	        }
	    }

	    private boolean validate(String text)
	    {
	        return text.matches("[0-9]*");
	    }
	}
	
	public static void main(String[] args)  {
		Application.launch(args);
	}
	

}
