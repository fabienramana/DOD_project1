package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

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
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;;

public class Main extends Application {
	
	Pane shapePane;
	Map<String,Form> formes;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		formes = new HashMap<String, Form>();
		
		primaryStage.setTitle("Test");
		
		VBox vBoxLeft = new VBox();
		VBox vBoxCoordinates = new VBox();
		VBox vBoxShape = new VBox();
		VBox vBoxColor = new VBox();
		VBox vBoxSubmit = new VBox();
		shapePane = new Pane();
		shapePane.setLayoutX(500);
		shapePane.setLayoutY(500);
		
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
		
		
		SplitPane splitPane = new SplitPane();
		splitPane.getItems().addAll(vBoxLeft, shapePane);
		splitPane.setDividerPositions(0.25);
		vBoxLeft.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.25));
		shapePane.maxWidthProperty().bind(splitPane.widthProperty().multiply(0.75));
		
		Scene scene = new Scene(splitPane, 600,600);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	
	public void addShape(String shape, String color, Double[] params) {
		Form forme;
		if(shape.equals("Rectangle")) {
			forme = new Rect(params[0], params[1], params[2], params[3], color);
			formes.put(params[0]+ params[1]+ params[2]+ params[3]+"", forme);
		}
			
		else {
			forme = new Cercle(params[0], params[1], params[2], color);
			formes.put(params[0]+ params[1]+ params[2]+"", forme);
		}
			
		System.out.println(forme);
		
		//formes.add(forme);

		changeContour();
		generateShapes();
		
	}
	
	public void removeShape() {
		
	}
	
	public void generateShapes() {
		
		Iterator<Entry<String, Form>> it = formes.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String, Form> f = it.next();
			Shape newShape;
			if(f.getValue() instanceof Rect) {
				Rect r = (Rect) f.getValue();
				newShape = new Rectangle(r.x, r.y, r.width, r.height);
			}else {
				Cercle c = (Cercle) f.getValue();
				newShape = new Circle(c.cx, c.cy, c.radius);
			}
			System.out.println(newShape.toString());
			newShape.setOnMouseClicked((event) -> {
				if(newShape instanceof Rectangle) {
					Double x = ((Rectangle) newShape).getX();
					Double y = ((Rectangle) newShape).getX();
					Double width = ((Rectangle) newShape).getWidth();
					Double height = ((Rectangle) newShape).getHeight();
					System.out.println(((Rectangle) newShape).getX());
					formes.remove(x+y+width+height+"");
				}
				if(newShape instanceof Circle) {
					Double x = ((Circle) newShape).getCenterX();
					Double y = ((Circle) newShape).getCenterY();
					Double radius = ((Circle) newShape).getRadius();
					System.out.println(((Circle) newShape).getRadius());
					formes.remove(x+y+radius+"");
					
				}
				changeContour();
				generateShapes();
				//shapePane.getChildren().remove(newShape);
			});
			newShape.setFill(Paint.valueOf(f.getValue().color));
			shapePane.getChildren().add(newShape);
		}
	}
	
	public void changeContour() {
		shapePane.getChildren().clear();
		Consommation c = new Consommation();
		Mutation m = new Mutation();
        Emission e = new Emission();
        List<Form> f = new ArrayList<>(formes.values());
		List<Form> s1 = e.create(m.mutate(f.stream().filter(forme -> forme.type.equals(Form.CERCLE)).collect(Collectors.toList())));
        List<Form> s2 = e.create(f.stream().filter(forme -> forme.type.equals(Form.RECTANGLE)).collect(Collectors.toList()));
          
        List<Form> espaces = new ArrayList<>();
        espaces.addAll(s1);
        espaces.addAll(s2);
        System.out.println(espaces.size());
		Espace espace = c.getEspace(espaces);
		for(Form r: espaces){
            System.out.println(r.toString());
        }
		System.out.println("Espace total "+espace.toString());
		Shape newShape = new Rectangle(espace.top_x, espace.top_y, espace.bottom_x-espace.top_x,espace.bottom_y+espace.top_y);
		
		System.out.println(newShape);
		newShape.setFill(Color.valueOf("transparent"));
		newShape.setStroke(Color.valueOf("black"));
		
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
