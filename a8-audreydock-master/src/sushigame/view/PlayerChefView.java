package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;

import comp401sushi.Avocado;
import comp401sushi.AvocadoPortion;
import comp401sushi.CrabPortion;
import comp401sushi.EelPortion;
import comp401sushi.Ingredient;
import comp401sushi.IngredientPortion;
import comp401sushi.Nigiri;
import comp401sushi.Nigiri.NigiriType;
import comp401sushi.Plate;
import comp401sushi.RedPlate;
import comp401sushi.RicePortion;
import comp401sushi.Roll;
import comp401sushi.Sashimi;
import comp401sushi.Sashimi.SashimiType;
import comp401sushi.SeaweedPortion;
import comp401sushi.ShrimpPortion;
import comp401sushi.Sushi;
import comp401sushi.TunaPortion;
import comp401sushi.YellowtailPortion;

public class PlayerChefView extends JPanel implements ActionListener {

	private List<ChefViewListener> listeners;
	private Sushi kmp_roll;
	private Sushi crab_sashimi;
	private Sushi eel_nigiri;
	private int belt_size;

	//position chooser
	private JLabel pos;
	private JSlider positionSlider;
	
	//at top: boxes to pick plate color and type
	private JComboBox<String> typecombo;
	private JComboBox<String> colorcombo;
	//for sashimi and nigiri
	private JComboBox<String> seafoodMenu;
	
	private String sashimiOrNigiri;
	
	//when sushi is created, set to this
	private NigiriType nigiriType;
	private SashimiType sashimiType;
	
	//for gold plate price
	private JSlider priceslider;

	//for choosing roll ingredients
	private JSlider avocado;
	private JSlider crab;
	private JSlider eel;
	private JSlider rice;
	private JSlider seaweed;
	private JSlider shrimp;
	private JSlider tuna;
	private JSlider yellowtail;
	
	private JLabel s;
	private JPanel rollpanel;
	private JLabel rollie;
	private JPanel pricesliderpanel;
	
	private JButton s_button;
	private JButton roll_button;
	private JButton nigiri_button;
	private JPanel sfs;
	private JLabel next;
	private Dictionary labelTable1;
	
	

	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JScrollPane scroll = new JScrollPane(this);
		//add(scroll);
		
		Font  f2  = new Font(Font.SERIF,  Font.BOLD, 30);
		JPanel typePicker = new JPanel();
		typePicker.setLayout(new BoxLayout(typePicker, BoxLayout.Y_AXIS));
		JLabel kitchen = new JLabel("The Kitchen");
		kitchen.setFont(f2);
		JLabel typePickerName = new JLabel("select type");
		

		JPanel colortype = new JPanel();
		colortype.setLayout(new GridLayout(2,3));
		
		JLabel typ = new JLabel("   Choose Type");
		colortype.add(typ);
		JLabel col = new JLabel("   Set Plate Color...");
		colortype.add(col);
		JLabel blank = new JLabel("   ... and click yes");
		colortype.add(blank);
		
		String[] typez = {"Sashimi", "Nigiri", "Roll"};
		typecombo = new JComboBox<String>(typez);
		colortype.add(typecombo);
		
		String[] colors = {"Red", "Blue", "Green", "Gold"};
		colorcombo = new JComboBox<String>(colors);
		colortype.add(colorcombo);
		
		
		JButton yes = new JButton("yes!");
		yes.setActionCommand("yes");
		yes.addActionListener(this);
		colortype.add(yes);
		add(colortype);
		
		Font  f3  = new Font(Font.SERIF,  Font.BOLD, 20);
		next = new JLabel("---now create ur sushi------------");
		next.setFont(f3);
		add(next);
		next.setVisible(false);

		
		s = new JLabel("Choose your ingredient");
		add(s);
		String[] ingredients = {"Tuna", "Yellowtail", "Eel", "Crab", "Shrimp"};
		seafoodMenu = new JComboBox<String>(ingredients);
		add(seafoodMenu);
		
		
		s.setVisible(false);
		seafoodMenu.setVisible(false);

		//only for goldplate
		rollie = new JLabel("Make Your Own Roll by Choosing Ingredients:");
		add(rollie);

		rollpanel = new JPanel();
		rollpanel.setLayout(new BorderLayout());
	
		
		
	

		JPanel framie1 = new JPanel();
		framie1.setLayout(new BorderLayout());
		rollpanel.add(framie1, BorderLayout.NORTH);

		JPanel miniF1 = new JPanel();
		miniF1.setLayout(new BorderLayout());
		framie1.add(miniF1, BorderLayout.WEST);

		JPanel miniF2 = new JPanel();
		miniF2.setLayout(new BorderLayout());
		framie1.add(miniF2, BorderLayout.EAST);

		JLabel av = new JLabel("Avocado");
		miniF1.add(av, BorderLayout.NORTH);
		avocado = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		Hashtable<Integer, JLabel> labelTable1 = new Hashtable<Integer, JLabel>();
		labelTable1.put(0, new JLabel("0.0oz") );
		labelTable1.put(50, new JLabel("0.5oz") );
		labelTable1.put(100, new JLabel("1.0oz") );
		labelTable1.put(150, new JLabel("1.5oz") );
		avocado.setLabelTable(labelTable1);
		avocado.setMajorTickSpacing(10);
		avocado.setPaintTicks(true);
		avocado.setPaintLabels(true);
		setPreferredSize(new Dimension(500, 80));
		miniF1.add(avocado, BorderLayout.SOUTH);

		JLabel cr = new JLabel("Crab");
		miniF2.add(cr, BorderLayout.NORTH);
		crab = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		Hashtable<Integer, JLabel> labelTable2 = new Hashtable<Integer, JLabel>();
		labelTable2.put(0, new JLabel("0.0oz") );
		labelTable2.put(50, new JLabel("0.5oz") );
		labelTable2.put(100, new JLabel("1.0oz") );
		labelTable2.put(150, new JLabel("1.5oz") );
		crab.setLabelTable(labelTable2);
		crab.setMajorTickSpacing(10);
		crab.setPaintTicks(true);
		crab.setPaintLabels(true);
		setPreferredSize(new Dimension(500, 80));
		miniF2.add(crab, BorderLayout.SOUTH);
		
		
		
		

		JPanel framie3 = new JPanel();
		framie3.setLayout(new BorderLayout());
		rollpanel.add(framie3, BorderLayout.AFTER_LAST_LINE);

		JPanel miniF3 = new JPanel();
		miniF3.setLayout(new BorderLayout());
		framie3.add(miniF3, BorderLayout.WEST);

		JPanel miniF4 = new JPanel();
		miniF4.setLayout(new BorderLayout());
		framie3.add(miniF4, BorderLayout.EAST);

		JLabel ee = new JLabel("Eel");
		miniF3.add(ee, BorderLayout.NORTH);
		eel = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		Hashtable<Integer, JLabel> labelTable3 = new Hashtable<Integer, JLabel>();
		labelTable3.put(0, new JLabel("0.0oz") );
		labelTable3.put(50, new JLabel("0.5oz") );
		labelTable3.put(100, new JLabel("1.0oz") );
		labelTable3.put(150, new JLabel("1.5oz") );
		eel.setLabelTable(labelTable3);
		eel.setMajorTickSpacing(10);
		eel.setPaintTicks(true);
		eel.setPaintLabels(true);
		setPreferredSize(new Dimension(500, 80));
		miniF3.add(eel, BorderLayout.SOUTH);

		JLabel r = new JLabel("Rice");
		miniF4.add(r, BorderLayout.NORTH);
		rice = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		Hashtable<Integer, JLabel> labelTable4 = new Hashtable<Integer, JLabel>();
		labelTable4.put(0, new JLabel("0.0oz") );
		labelTable4.put(50, new JLabel("0.5oz") );
		labelTable4.put(100, new JLabel("1.0oz") );
		labelTable4.put(150, new JLabel("1.5oz") );
		rice.setLabelTable(labelTable4);
		rice.setMajorTickSpacing(10);
		rice.setPaintTicks(true);
		rice.setPaintLabels(true);
		setPreferredSize(new Dimension(500, 80));
		miniF4.add(rice, BorderLayout.SOUTH);


		JPanel framie4 = new JPanel();
		framie4.setLayout(new BorderLayout());
		framie3.add(framie4, BorderLayout.AFTER_LAST_LINE);

		JPanel miniF5 = new JPanel();
		miniF5.setLayout(new BorderLayout());
		framie4.add(miniF5, BorderLayout.WEST);

		JPanel miniF6 = new JPanel();
		miniF6.setLayout(new BorderLayout());
		framie4.add(miniF6, BorderLayout.EAST);

		JLabel weed = new JLabel("Seaweed");
		miniF5.add(weed, BorderLayout.NORTH);
		seaweed = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		Hashtable<Integer, JLabel> labelTable5 = new Hashtable<Integer, JLabel>();
		labelTable5.put(0, new JLabel("0.0oz") );
		labelTable5.put(50, new JLabel("0.5oz") );
		labelTable5.put(100, new JLabel("1.0oz") );
		labelTable5.put(150, new JLabel("1.5oz") );
		seaweed.setLabelTable(labelTable5);
		seaweed.setMajorTickSpacing(10);
		seaweed.setPaintTicks(true);
		seaweed.setPaintLabels(true);
		setPreferredSize(new Dimension(500, 80));
		miniF5.add(seaweed, BorderLayout.SOUTH);

		JLabel sh = new JLabel("Shrimp");
		miniF6.add(sh, BorderLayout.NORTH);
		shrimp = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		Hashtable<Integer, JLabel> labelTable6 = new Hashtable<Integer, JLabel>();
		labelTable6.put(0, new JLabel("0.0oz") );
		labelTable6.put(50, new JLabel("0.5oz") );
		labelTable6.put(100, new JLabel("1.0oz") );
		labelTable6.put(150, new JLabel("1.5oz") );
		shrimp.setLabelTable(labelTable6);
		shrimp.setMajorTickSpacing(10);
		shrimp.setPaintTicks(true);
		shrimp.setPaintLabels(true);
		setPreferredSize(new Dimension(500, 80));
		miniF6.add(shrimp, BorderLayout.SOUTH);



		JPanel framie5 = new JPanel();
		framie5.setLayout(new BorderLayout());
		framie4.add(framie5, BorderLayout.AFTER_LAST_LINE);

		JPanel miniF7 = new JPanel();
		miniF7.setLayout(new BorderLayout());
		framie5.add(miniF7, BorderLayout.WEST);

		JPanel miniF8 = new JPanel();
		miniF8.setLayout(new BorderLayout());
		framie5.add(miniF8, BorderLayout.EAST);


		JLabel tu = new JLabel("Tuna");
		miniF7.add(tu, BorderLayout.NORTH);
		tuna = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		Hashtable<Integer, JLabel> labelTable7 = new Hashtable<Integer, JLabel>();
		labelTable7.put(0, new JLabel("0.0oz") );
		labelTable7.put(50, new JLabel("0.5oz") );
		labelTable7.put(100, new JLabel("1.0oz") );
		labelTable7.put(150, new JLabel("1.5oz") );
		tuna.setLabelTable(labelTable7);
		tuna.setMajorTickSpacing(10);
		tuna.setPaintTicks(true);
		tuna.setPaintLabels(true);
		setPreferredSize(new Dimension(1000, 1000));
		miniF7.add(tuna, BorderLayout.SOUTH);

		JLabel y = new JLabel("Yellowtail");
		miniF8.add(y, BorderLayout.NORTH);
		yellowtail = new JSlider(JSlider.HORIZONTAL, 0, 150, 0);
		Hashtable<Integer, JLabel> labelTable8 = new Hashtable<Integer, JLabel>();
		labelTable8.put(0, new JLabel("0.0oz") );
		labelTable8.put(50, new JLabel("0.5oz") );
		labelTable8.put(100, new JLabel("1.0oz") );
		labelTable8.put(150, new JLabel("1.5oz") );
		yellowtail.setLabelTable(labelTable8);
		yellowtail.setMajorTickSpacing(10);
		yellowtail.setPaintTicks(true);
		yellowtail.setPaintLabels(true);
		setPreferredSize(new Dimension(500, 600));
		miniF8.add(yellowtail, BorderLayout.SOUTH);
		
		add(rollpanel);
		rollie.setVisible(false);
		rollpanel.setVisible(false);

		

		
		pos = new JLabel("set position:");
		add(pos);
		positionSlider = new JSlider(0, 19, 10);
		positionSlider.setMajorTickSpacing(2);
		positionSlider.setMinorTickSpacing(1);
		positionSlider.setPaintTicks(true);
		positionSlider.setPaintLabels(true);
		
		add(positionSlider);
		pos.setFont(f3);
		pos.setVisible(false);
		positionSlider.setVisible(false);
		
		pricesliderpanel = new JPanel();
		pricesliderpanel.setLayout(new BoxLayout(pricesliderpanel, BoxLayout.Y_AXIS));
		JLabel pricelabel = new JLabel("gold plate... u fancy. set price here:");
		pricelabel.setFont(f3);
		pricesliderpanel.add(pricelabel);
		priceslider = new JSlider(JSlider.HORIZONTAL, 500, 1000, 750);
		Hashtable<Integer, JLabel> pricelabels = new Hashtable<Integer, JLabel>();
		pricelabels.put(500, new JLabel("$5.00") );
		pricelabels.put(550, new JLabel("$5.50") );
		pricelabels.put(600, new JLabel("$6.00") );
		pricelabels.put(650, new JLabel("$6.50") );
		pricelabels.put(700, new JLabel("$7.00") );
		pricelabels.put(750, new JLabel("$7.50") );
		pricelabels.put(800, new JLabel("$8.00") );
		pricelabels.put(850, new JLabel("$8.50") );
		pricelabels.put(900, new JLabel("$9.00") );
		pricelabels.put(950, new JLabel("$9.50") );
		pricelabels.put(1000, new JLabel("$10.00") );
		priceslider.setLabelTable(pricelabels);
		priceslider.setMajorTickSpacing(50);
		priceslider.setPaintTicks(true);
		priceslider.setPaintLabels(true);
		setPreferredSize(new Dimension(500, 80));
		pricesliderpanel.add(priceslider);
		add(pricesliderpanel);
		
		pricesliderpanel.setVisible(false);
		
		
		
		JPanel create = new JPanel();
		create.setLayout(new BorderLayout());
		

		s_button = new JButton("Make Sashimi");
		s_button.setActionCommand("Make Sashimi");
		s_button.addActionListener(this);
		//create.add(s_button, BorderLayout.EAST);

		nigiri_button = new JButton("Make Nigiri");
		nigiri_button.setActionCommand("Make Nigiri");
		nigiri_button.addActionListener(this);
		//create.add(nigiri_button, BorderLayout.WEST);

		
		roll_button = new JButton("Make Roll");
		roll_button.setActionCommand("Make Roll");
		roll_button.addActionListener(this);
		//create.add(roll_button, BorderLayout.CENTER);
		
		//add(create);
		add(s_button);
		add(nigiri_button);
		add(roll_button);
		s_button.setVisible(false);
		nigiri_button.setVisible(false);
		roll_button.setVisible(false);
		create.setVisible(false);

		kmp_roll = new Roll("KMP Roll", new IngredientPortion[] {new EelPortion(1.0), new AvocadoPortion(0.5), new SeaweedPortion(0.2)});
		crab_sashimi = new Sashimi(Sashimi.SashimiType.CRAB);
		eel_nigiri = new Nigiri(Nigiri.NigiriType.EEL);
	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}

	public void makePriceSlider() {
		JPanel pricesliderpanel = new JPanel();
		JLabel pricelabel = new JLabel("If Gold Plate, Set Price...");
		pricesliderpanel.add(pricelabel);
		priceslider = new JSlider(JSlider.HORIZONTAL, 500, 1000, 750);
		Hashtable<Integer, JLabel> pricelabels = new Hashtable<Integer, JLabel>();
		pricelabels.put(500, new JLabel("$5.00") );
		pricelabels.put(550, new JLabel("$5.50") );
		pricelabels.put(600, new JLabel("$6.00") );
		pricelabels.put(650, new JLabel("$6.50") );
		pricelabels.put(700, new JLabel("$7.00") );
		pricelabels.put(750, new JLabel("$7.50") );
		pricelabels.put(800, new JLabel("$8.00") );
		pricelabels.put(850, new JLabel("$8.50") );
		pricelabels.put(900, new JLabel("$9.00") );
		pricelabels.put(950, new JLabel("$9.50") );
		pricelabels.put(1000, new JLabel("$10.00") );
		priceslider.setLabelTable(pricelabels);
		priceslider.setMajorTickSpacing(50);
		priceslider.setPaintTicks(true);
		priceslider.setPaintLabels(true);
		setPreferredSize(new Dimension(500, 80));
		pricesliderpanel.add(priceslider);
		this.add(pricesliderpanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		int position = positionSlider.getValue();
		
		String sashimiOrNigiri = typecombo.getSelectedItem().toString();
		String color = colorcombo.getSelectedItem().toString();
		String ingredients = seafoodMenu.getSelectedItem().toString();

		double price = Math.round((priceslider.getValue() / 100.0)*100.0)/ 100.0;

		if (ingredients.equals("Tuna")) {
			nigiriType = NigiriType.TUNA; 
			sashimiType = SashimiType.TUNA;
		} else if (ingredients.equals("Yellowtail")) {
			nigiriType = NigiriType.YELLOWTAIL;
			sashimiType = SashimiType.YELLOWTAIL;
		} else if (ingredients.equals("Eel")) {
			nigiriType = NigiriType.EEL;
			sashimiType = SashimiType.EEL;
		} else if (ingredients.equals("Crab")) {
			nigiriType = NigiriType.CRAB;
			sashimiType = SashimiType.CRAB;
		} else if (ingredients.equals("Shrimp")) {
			nigiriType = NigiriType.SHRIMP;
			sashimiType = SashimiType.SHRIMP;
		}


		switch (e.getActionCommand()) {		
		case "yes":
			
			switch(sashimiOrNigiri) {
			case "Sashimi":
				nigiri_button.setVisible(false);
				roll_button.setVisible(false);
				pricesliderpanel.setVisible(false);
				rollie.setVisible(false);
				rollpanel.setVisible(false);
				
				next.setVisible(true);
				s.setVisible(true);
				seafoodMenu.setVisible(true);
				
				
				if (color.equals("Gold")) {
					pricesliderpanel.setVisible(true);
				}
				pos.setVisible(true);
				positionSlider.setVisible(true);
				s_button.setVisible(true);
				
				break;
			case "Nigiri":
				
				s_button.setVisible(false);
				roll_button.setVisible(false);
				pricesliderpanel.setVisible(false);
				rollie.setVisible(false);
				rollpanel.setVisible(false);
				
				next.setVisible(true);
				s.setVisible(true);
				seafoodMenu.setVisible(true);
				
				if (color.equals("Gold")) {
					pricesliderpanel.setVisible(true);
				}
				pos.setVisible(true);
				positionSlider.setVisible(true);
				
				nigiri_button.setVisible(true);
				
				break;
			
			case "Roll":
				nigiri_button.setVisible(false);
				s_button.setVisible(false);
				pricesliderpanel.setVisible(false);
				s.setVisible(false);
				seafoodMenu.setVisible(false);
				next.setVisible(true);
				rollie.setVisible(true);
				rollpanel.setVisible(true);
				
				if (color.equals("Gold")) {
					pricesliderpanel.setVisible(true);
				}
				pos.setVisible(true);
				positionSlider.setVisible(true);
				roll_button.setVisible(true);
				
				break;
			}
			
			break;
			
		case "Make Sashimi":
			switch (color) {
			case "Red":
				makeRedPlateRequest(new Sashimi(sashimiType), position);
				break;
			case "Blue":
				makeBluePlateRequest(new Sashimi(sashimiType), position);
				break;
			case "Green":
				makeGreenPlateRequest(new Sashimi(sashimiType), position);
				break;
			case "Gold":
				makeGoldPlateRequest(new Sashimi(sashimiType), position, price);
				break;
			}
			break;
			
			
		case "Make Nigiri":
			switch (color) {
			case "Red":
				makeRedPlateRequest(new Nigiri(nigiriType), position);
				break;
			case "Blue":
				makeBluePlateRequest(new Nigiri(nigiriType), position);
				break;
			case "Green":
				makeGreenPlateRequest(new Nigiri(nigiriType), position);
				break;
			case "Gold":
				makeGoldPlateRequest(new Nigiri(nigiriType), position, price);
				break;
			}
			break;
			
			
		case "Make Roll":
			switch (color) {
			case "Red":
				makeRedPlateRequest(new Roll("Homemade Roll", helper()), position);
				break;
			case "Blue":
				makeBluePlateRequest(new Roll("Homemade Roll", helper()), position);
				break;
			case "Green":
				makeGreenPlateRequest(new Roll("Homemade Roll", helper()), position);
				break;
			case "Gold":
				makeGoldPlateRequest(new Roll("Homemade Roll", helper()), position, price);
				break;
			}		
		}
	}
	
	public IngredientPortion[] helper () {
		List<IngredientPortion> ings = new ArrayList<IngredientPortion>();

		double avo = Math.round((avocado.getValue()/100.0) * 100.00)/100.00;
		double cra = Math.round((crab.getValue()/100.0) * 100.0)/100.0;
		double ee = Math.round((eel.getValue()/100.0) * 100.0)/100.0;
		double ric = Math.round((rice.getValue()/100.0) * 100.0)/100.0;
		double sea = Math.round((seaweed.getValue()/100.0) * 100.0)/100.0;
		double shr = Math.round((shrimp.getValue()/100.0) * 100.0)/100.0;
		double tun = Math.round((tuna.getValue()/100.0) * 100.0)/100.0;
		double yell = Math.round((yellowtail.getValue()/100.0) * 100.0)/100.0;

		if (avo != 0.0) {
			ings.add(new AvocadoPortion(avo));
		}
		if (cra != 0.0) {
			ings.add(new CrabPortion(cra));
		}
		if (ee != 0.0) {
			ings.add(new EelPortion(ee));
		}
		if (ric != 0.0) {
			ings.add(new RicePortion(ric));
		}
		if (sea != 0.0) {
			ings.add(new SeaweedPortion(sea));
		}
		if (shr != 0.0) {
			ings.add(new ShrimpPortion(shr));
		}
		if (tun != 0.0) {
			ings.add(new TunaPortion(tun));
		}
		if (yell != 0.0) {
			ings.add(new YellowtailPortion(yell));
		}

		IngredientPortion[] send = ings.toArray(new IngredientPortion[ings.size()]);
		return send;
	}
	
	

	
}