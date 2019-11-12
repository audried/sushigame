package sushigame.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;
import sushigame.view.HighToLowBalanceComparator;
import sushigame.view.SpoiledComparator;
import sushigame.view.ConsumedComparator;

public class ScoreboardWidget extends JPanel implements BeltObserver {

	private SushiGameModel game_model;
	private JLabel display;
	private Chef[] chefs;
	private JComboBox combo;
	private JLabel sortby;
	
	private HighToLowBalanceComparator hlc;
	private SpoiledComparator sc;
	private ConsumedComparator cc;
	
	
	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		
		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.TOP);
		setLayout(new BorderLayout());
		add(display, BorderLayout.NORTH);
		display.setText(makeScoreboardHTML(new HighToLowBalanceComparator()));
		
		JPanel jp = new JPanel();
		jp.setLayout(new GridLayout(2,1));
		
		sortby = new JLabel("  Sort Players By...");
		//sortby.setText("sort by");
		jp.add(sortby);
		String[] options = {"Profit", "Amount Sold", "Amount Spoiled"};
		combo = new JComboBox<String>(options);
		jp.add(combo);
		
		add(jp, BorderLayout.CENTER);
		
		
	}


//	private String makeScoreboardHTML() {
//		String sb_html = "<html>";
//		sb_html += "<h1>Scoreboard</h1>";
//	
//
//		// Create an array of all chefs and sort by balance.
//		Chef[] opponent_chefs= game_model.getOpponentChefs();
//		chefs = new Chef[opponent_chefs.length+1];
//		chefs[0] = game_model.getPlayerChef();
//		for (int i=1; i<chefs.length; i++) {
//			chefs[i] = opponent_chefs[i-1];
//		}
//		
//		Arrays.sort(chefs, new HighToLowBalanceComparator());
//		
//		for (Chef c : chefs) {
//			sb_html += "<p>  "+ c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ")";
//			sb_html += "<p>     "+Math.round(c.getFoodSpoiled()*100.0)/100.0 + " ounces spoiled, "+Math.round(c.getFoodSold()*100.0)/100.0 + " ounces sold</p><br>";
//			
//		}
//		return sb_html;
//	}
	
	
	private String makeScoreboardHTML(HighToLowBalanceComparator comparator) {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";
	

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		
		Arrays.sort(chefs, comparator);
		
		for (Chef c : chefs) {
			sb_html += "<p>  "+ c.getName() + " ($" + Math.round(c.getBalance()*100.0)/100.0 + ")";
			//sb_html += "<p>     "+Math.round(c.getFoodSpoiled()*100.0)/100.0 + " ounces spoiled, "+Math.round(c.getFoodSold()*100.0)/100.0 + " ounces sold</p><br>";
		}	
		return sb_html;
	}
	
	private String makeScoreboardHTML(SpoiledComparator comparator) {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		
		Arrays.sort(chefs, comparator);
		
		for (Chef c : chefs) {
			sb_html += "<p>  "+ c.getName() + " (" + Math.round(c.getFoodSold()*100.0)/100.0 + " oz. sold)";
			//sb_html += "<p>     "+Math.round(c.getFoodSpoiled()*100.0)/100.0 + " ounces spoiled, "+Math.round(c.getFoodSold()*100.0)/100.0 + " ounces sold</p><br>";
			
		}
		return sb_html;
	}
	
	private String makeScoreboardHTML(ConsumedComparator comparator) {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";
	

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		
		Arrays.sort(chefs, comparator);
		
		for (Chef c : chefs) {
			sb_html += "<p>  "+ c.getName() + " (" + Math.round(c.getFoodSpoiled()*100.0)/100.0 + " oz. spoiled)";
			//sb_html += "<p>     "+Math.round(c.getFoodSpoiled()*100.0)/100.0 + " ounces spoiled, "+Math.round(c.getFoodSold()*100.0)/100.0 + " ounces sold</p><br>";
			
		}
		return sb_html;
	}

	
	public void refresh() {
		
		//display.setText(makeScoreboardHTML(new HighToLowBalanceComparator()));		
		
		String input = combo.getSelectedItem().toString();
		
		if(input.equals("Profit")) {
			display.setText(makeScoreboardHTML(new HighToLowBalanceComparator()));	
		}
		else if(input.equals("Amount Sold")) {
			display.setText(makeScoreboardHTML(new SpoiledComparator()));
		}
		else if(input.equals("Amount Spoiled")) {
			display.setText(makeScoreboardHTML(new ConsumedComparator()));	
		}
		else {
			display.setText(makeScoreboardHTML(new HighToLowBalanceComparator()));	
		}
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}
	
}
