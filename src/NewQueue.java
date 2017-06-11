import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextPane;

public class NewQueue extends JPanel {

	/**
	 * Create the panel.
	 * @throws Throwable 
	 */
	public NewQueue(Statement stmt, int teamID) throws Throwable {
		setLayout(null);
		String getPatients = "SELECT Patient.name, Patient.ID, Patient.Priority, Queue.ArrivalTime FROM Queue JOIN Patient ON Queue.PatientID = Patient.ID WHERE Queue.teamID =" +  "'" + teamID + "'" + " ORDER BY Patient.Priority DESC,Queue.ArrivalTime Asc";
		
		ResultSet rs4 = null;
		try {
			rs4 = stmt.executeQuery(getPatients);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[] arr = { "Patient Name ", "PatientID","Patient Priority", "Patient Arrival Time", "Waiting Time"};
		String[][] arr2;
		
		int patients =0;
		while(rs4.next()) {
			
			patients++;
		}
		System.out.println(patients);
		ResultSet rs5;
		rs5 = stmt.executeQuery(getPatients);
		arr2 = new String[patients][5];
		int k = 0;
		
		while(rs5.next()) {
			
			arr2[k][0] = "" + rs5.getString("name");
			arr2[k][1] = "" + rs5.getInt("id");
			arr2[k][2] = "" + rs5.getString("priority");
			arr2[k][3] = "" + (rs5.getTime("arrivaltime")).toString();
			

			k++;
		}
		
		JTextPane textPane = new JTextPane();
		textPane.setText("Team " + teamID  );
		textPane.setBounds(174,40, 85, 16);
		add(textPane);
		
		
		JTable table1 = new JTable(arr2,arr);
		table1.setBackground(Color.WHITE);
		table1.setBounds(57,84, 332, 154);
		add(table1);
		

	}

}
