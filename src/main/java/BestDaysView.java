import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class BestDaysView extends JFrame {

	private JPanel BestDaysPanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BestDaysView frame = new BestDaysView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//text field declarations
	JLabel lblDistance = new JLabel("Distance:");
	JLabel lblFloors = new JLabel("Floors:");
	JLabel lblSteps = new JLabel("Steps:");

	JLabel distanceVal = new JLabel();
	JLabel floorsVal = new JLabel();
	JLabel stepsVal = new JLabel();


	//menu button declarations
	//same as all pages
	JButton btnDailyDashboard = new JButton("Daily Dashboard");
	JButton btnBestDays = new JButton("Best Days");
	JButton btnDailyGoals = new JButton("Daily Goals");
	JButton btnLifeTimeTotals = new JButton("Lifetime Totals");
	JButton btnAccolades = new JButton("Accolades");
	JButton btnTimeSeries = new JButton("Time Series");

	//constructor
	public BestDaysView() {		

		//set panel layout
		//same for all pages
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		BestDaysPanel = new JPanel();
		BestDaysPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(BestDaysPanel);
		BestDaysPanel.setLayout(null);

		//page title
		//same for all days
		JLabel lblNewLabel = new JLabel("Best Days");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 22));
		lblNewLabel.setBounds(31, 16, 205, 42);
		BestDaysPanel.add(lblNewLabel);

		//displays best day for distance, steps and floors
		lblDistance.setBounds(72, 70, 100, 16);
		distanceVal.setBounds(250, 70, 100, 16);
		BestDaysPanel.add(lblDistance);
		BestDaysPanel.add(distanceVal);

		lblFloors.setBounds(72, 98, 100, 16);
		floorsVal.setBounds(250, 98, 100, 16);
		BestDaysPanel.add(lblFloors);
		BestDaysPanel.add(floorsVal);

		lblSteps.setBounds(72, 126, 100, 16);
		stepsVal.setBounds(250, 126, 100, 16);
		BestDaysPanel.add(lblSteps);
		BestDaysPanel.add(stepsVal);


		//create side bar menu object
		//Same as all pages
		JToolBar toolBar = new JToolBar();
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setBackground(Color.DARK_GRAY);
		toolBar.setBounds(332, 0, 118, 278);
		BestDaysPanel.add(toolBar);

		//add menu bar components
		//same as all pages
		toolBar.add(btnDailyDashboard);
		toolBar.add(btnBestDays);
		toolBar.add(btnDailyGoals);
		toolBar.add(btnLifeTimeTotals);
		toolBar.add(btnAccolades);
		toolBar.add(btnTimeSeries);

	}


	//method used by controller to update view with distance data
	public void setDistance(float distance) {
		distanceVal.setText(Float.toString(distance));
	}

	//method used by controller to update view with floors data
	public void setFloors(int floors) {
		floorsVal.setText(Integer.toString(floors));
	}

	//method used by controller to update view with steps data
	public void setSteps(int steps) {
		stepsVal.setText(Integer.toString(steps));
	}

	//if the Best Day button in the side bar menu is clicked,
	//execute "action performed" method in the controller
	void addRefreshListener(ActionListener listenForRefresh) {
		btnBestDays.addActionListener(listenForRefresh);
	}

	void displayErrorMessage() {
		//do something
	}

}
