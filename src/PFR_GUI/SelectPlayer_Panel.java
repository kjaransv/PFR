package PFR_GUI;

import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class SelectPlayer_Panel extends JPanel {

	/**
	 * Create the panel.
	 */
	public SelectPlayer_Panel() {
		setLayout(null);
		
		JList list_1 = new JList();
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"Team1", "Team2"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setBounds(225, 71, -148, -66);
		add(list_1);
		
		JList list = new JList();
		list.setBounds(230, 71, -135, -66);
		add(list);

	}

}
