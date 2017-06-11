import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JComboBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditInfoDoc extends JPanel {

	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public EditInfoDoc(Statement stmt,int teamID) {
		setLayout(null);
		int id = 0;
		
		String getPatients = "SELECT Patient.name, Patient.ID, Patient.Priority, Queue.ArrivalTime"
				+ " FROM Queue JOIN Patient ON Queue.PatientID = Patient.ID WHERE Queue.teamID =" +  "'" + teamID + "'" + " ORDER BY Patient.Priority DESC,Queue.ArrivalTime Asc LIMIT(1)";
		
		ResultSet rs4 = null;
		try {
			rs4 = stmt.executeQuery(getPatients);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String str = "";
		try {
			while(rs4.next()) {
				str += rs4.getString("name") + " ";
				id = rs4.getInt("ID");
				str += id + " ";
			
				str += rs4.getString("Priority") + " ";
				str += rs4.getTime("arrivaltime").toString();
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JTextPane txtpnEditForm = new JTextPane();
		txtpnEditForm.setBounds(195, 5, 60, 16);
		txtpnEditForm.setText("Edit Form");
		add(txtpnEditForm);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(91, 52, 276, 25);
		textPane.setText(str);
		add(textPane);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Proc-1", "Proc-2", "Proc-3", "Proc-4", "Proc-5"}));
		comboBox.setBounds(48, 109, 190, 27);
		add(comboBox);
		
		JTextPane txtpnMedicalIssue = new JTextPane();
		txtpnMedicalIssue.setText("Medical Procedure");
		txtpnMedicalIssue.setBounds(48, 89, 122, 16);
		add(txtpnMedicalIssue);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Drug 1", "Drug 2", "Drug 3", "Drug 4", "Drug 5", "Drug 6", "Drug 7", "Drug 8", "Drug 9", "Drug 10"}));
		comboBox_1.setBounds(48, 198, 190, 16);
		comboBox_1.setVisible(true);
		add(comboBox_1);
		
		JTextPane txtpnDrugs = new JTextPane();
		txtpnDrugs.setText("Drugs");
		txtpnDrugs.setBounds(48, 173, 46, 16);
		add(txtpnDrugs);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Sent :)", "Stay :("}));
		comboBox_2.setBounds(316, 155, 102, 16);
		add(comboBox_2);
		
		JTextPane txtpnSentHomeOr = new JTextPane();
		txtpnSentHomeOr.setText("Sent Home or Not");
		txtpnSentHomeOr.setBounds(306, 130, 122, 13);
		add(txtpnSentHomeOr);
		final int t = id;
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sqlStr = "INSERT INTO tDoctorForm VALUES(" + t  +  "," + "'" + comboBox.getSelectedItem().toString() +
						"'" + "," + "'" + comboBox_1.getSelectedItem().toString() + "'" + "," + "'" + comboBox_2.getSelectedItem().toString() + "'" + ")";
				String delQ = "DELETE FROM Queue WHERE PatientID =" + t ;
				try {
					stmt.executeUpdate(sqlStr);
					stmt.executeUpdate(delQ);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				btnSubmit.setEnabled(false);
				
			}
		});
		btnSubmit.setBounds(176, 237, 117, 29);
		add(btnSubmit);

	}
}
