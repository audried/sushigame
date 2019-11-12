package sushigame.view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import comp401sushi.Plate.Color;
import comp401sushi.Sushi;
import comp401sushi.*;

public class NewPlateWidget extends JPanel {
	
	Color c;
	Sushi s;
	
	public NewPlateWidget(Color c ){
		
		setLayout(new GridLayout(1,4));
		
		JButton a = new JButton("a");
		JButton b = new JButton("b");
		JButton e = new JButton("c");
		JButton d = new JButton("d");
		
		add(a);
		add(b);
		add(e);
		add(d);
	}

}
