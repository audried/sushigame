package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import comp401sushi.Plate;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver {

	private Belt belt;
	private PlateViewWidget[] belt_labels;
	

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		setLayout(new GridLayout(belt.getSize(), 1));
		
		belt_labels = new PlateViewWidget[belt.getSize()];
		for (int i = 0; i < belt.getSize(); i++) {
			PlateViewWidget pvw = new PlateViewWidget(belt.getPlateAtPosition(i));
			//need to add contructor for plateview widget so u can make it empty?
			pvw.setMinimumSize(new Dimension(300, 20));
			pvw.setPreferredSize(new Dimension(300, 20));
			pvw.setOpaque(true);
			pvw.setBackground(Color.GRAY);
			add(pvw);
			belt_labels[i] = pvw;
		}
		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}

	private void refresh() {
		for (int i=0; i<belt.getSize(); i++) {
			Plate p = belt.getPlateAtPosition(i);
			PlateViewWidget pvw = belt_labels[i];
			int age = belt.getAgeOfPlateAtPosition(i);

			if (p == null) {
				//pvw.setText("");
				Color myColor = Color.decode("#c4d6ff");
				pvw.setBackground(myColor);
			} else {
				belt_labels[i].setPlate(p, age);
				//pvw.setText(p.toString());
				switch (p.getColor()) {
				case RED:
					//these are jlabels but i need them to be plate view widgets
					pvw.changeButtonColor(Color.red); 
					//pvw.setText("red plate: "+p.getContents().getName() + " age: " + age);
				case GREEN:
					pvw.changeButtonColor(Color.GREEN); 
					//pvw.setText("green plate: "+p.getContents().getName() + " age: " + age);
					break;
				case BLUE:
					pvw.changeButtonColor(Color.BLUE); 
					//pvw.setText("blue plate: "+p.getContents().getName() + " age: " + age);
					break;
				case GOLD:
					//pvw.setText("gold plate: "+p.getContents().getName() + " age: " + age);
					pvw.changeButtonColor(Color.orange); 
					break;
				}
			}
		}
	}
}
