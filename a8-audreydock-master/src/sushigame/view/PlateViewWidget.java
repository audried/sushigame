package sushigame.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.DefaultCaret;


import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import comp401sushi.*;
import comp401sushi.IngredientPortion;
import comp401sushi.Plate;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import comp401sushi.Plate;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

/*
 * You must develop some sort of "plate view widget" which is able 
 * to convey this information in a more interesting and elegant manner 
 * and modify BeltView to use your new widget instead of a JLabel.
 */
public class PlateViewWidget extends JPanel implements BeltObserver, ActionListener {

	private Plate plate;
	private JPanel panel;
	private JButton[] buttonarray = new JButton[1];
	private JButton info;
	private int age;
	//private String name;
	
	public PlateViewWidget(Plate p) {
		
		this.plate = p;
		setLayout(new GridLayout(1, 1));
		setPreferredSize(new Dimension(250, 15));
		RoundButton info = new RoundButton("info");
		info.setBackground(Color.white);
	    info.setForeground(Color.black);
	    info.setBorder(BorderFactory.createDashedBorder(Color.black));
		info.setMaximumSize(new Dimension(20,20));
		info.setMinimumSize(new Dimension(10,10));
		info.setSize(new Dimension(20, 15));
		info.addActionListener(this);
		info.setText("click for plate info");
		add(info);
		
		buttonarray[0] = info;
		
		setVisible(true);

	}
	
	public void setPlate(Plate p, int age) {
		this.plate = p;
		this.age = age;
	}
	
	//returns Sashimi, Nigiri, or Roll
	public String getSimpleName() {
		String simpleName = plate.getContents().getClass().getSimpleName();
		return simpleName;
	}
		
	public IngredientPortion[] getPlateIngredients() {
		IngredientPortion[] ings = plate.getContents().getIngredients();
		return ings;
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void changeButtonColor(Color c) {
		for (int i = 0; i< buttonarray.length; i++) {
			//buttonarray[i].setBackground(c);
			//buttonarray[i].setOpaque(true);
			buttonarray[i].setForeground(c);
		
		}
		
	}

	//all buttons have actionlistener
	//anytime button is click, run this method to call newFrame()
	@Override
	public void actionPerformed(ActionEvent e) {
		NewFrame();
	}
	
	
	public void NewFrame() {
		JFrame f = new JFrame();
		f.setSize(200,250);
		f.setVisible(true);
		
		//TODO: set location of the frame? it covers up the scoreboard

		JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(16,1));
		infoPanel.setBackground(Color.decode("#c4d6ff"));
	
		f.add(infoPanel);
	
		JLabel name = new JLabel();
		if (plate == null) {
			
			name.setText("NO PLATE HERE");
			infoPanel.add(name);
		}
		else {
			Font  f2  = new Font(Font.SANS_SERIF,  Font.BOLD, 16);
			Font  f3  = new Font(Font.SANS_SERIF,  Font.BOLD, 14);
			
			name.setText(plate.getContents().getName().toString());
			name.setFont(f2);
			
			JLabel price = new JLabel("price: $"+ String.format("%.2f",plate.getPrice()));
			JLabel ingredients = new JLabel("ingredients:");
			JLabel plateColor = new JLabel("Plate color: "+plate.getColor().toString());
			JLabel profit = new JLabel("Profit: $"+ String.format("%.2f",plate.getProfit()));
			JLabel ageOfPlate = new JLabel("age: " + age);
			JLabel chef = new JLabel("made by: "+plate.getChef().getName().toString());
			
			
			infoPanel.add(name);
			infoPanel.add(plateColor);
			infoPanel.add(ingredients);
			
			for (int i = 0; i < plate.getContents().getIngredients().length; i++) {
				JLabel inglabel = new JLabel(" -"+plate.getContents().getIngredients()[i].getName() + 
						", "+ String.format("%.2f",plate.getContents().getIngredients()[i].getAmount()) + " ounces");
				infoPanel.add(inglabel);
				
			}
			
			infoPanel.add(price);
			infoPanel.add(profit);
			infoPanel.add(ageOfPlate);
			infoPanel.add(chef);
		}
		
		
		f.setVisible(true);
		
	}
	


}
