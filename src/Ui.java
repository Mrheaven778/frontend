import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Ui {

	private static ArrayList<Campeon> campeones = new ArrayList();
	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ui window = new Ui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(28, 25, 23));
		frame.setResizable(false);
		frame.setBounds(100, 100, 906, 621);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblTitulo = new JLabel("Proyecto Final Sergio Abrodes");
		lblTitulo.setForeground(new Color(10, 189, 16));
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblTitulo.setBounds(31, 39, 305, 62);
		frame.getContentPane().add(lblTitulo);

		JButton btnVerDatos = new JButton("Ver Datos");
		btnVerDatos.setForeground(new Color(255, 255, 255));
		btnVerDatos.setBorderPainted(false);
		btnVerDatos.setBackground(new Color(14, 89, 19));
		btnVerDatos.setBounds(629, 130, 164, 23);
		frame.getContentPane().add(btnVerDatos);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setEnabled(false);
		scrollPane.setBounds(100, 164, 693, 309);
		scrollPane.setVisible(false);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setForeground(Color.BLACK);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "ID", "Nombre Campeon", "Rol", "Linea",
				"Tipo de ataque", "Difficultad", "Fecha lanzamiento", "Lore" }) {
			Class[] columnTypes = new Class[] { String.class, String.class, String.class, String.class, String.class,
					Object.class, Object.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(
				new DefaultComboBoxModel(new String[] { "Sacar datos XML", "Sacar datos API", "Sacar datos JSON" }));
		comboBox.setBounds(100, 130, 519, 22);
		frame.getContentPane().add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Escribir a XML", "Escribir a JSON", "Escribir a SQL"}));
		comboBox_1.setBounds(274, 484, 519, 22);
		frame.getContentPane().add(comboBox_1);
		
		JButton btnConvertir = new JButton("Escribir datos ");
		btnConvertir.setForeground(Color.WHITE);
		btnConvertir.setBorderPainted(false);
		btnConvertir.setBackground(new Color(14, 89, 19));
		btnConvertir.setBounds(100, 484, 164, 23);
		frame.getContentPane().add(btnConvertir);
		table.getColumnModel().getColumn(0).setPreferredWidth(98);
		table.getColumnModel().getColumn(1).setPreferredWidth(107);
		table.getColumnModel().getColumn(4).setPreferredWidth(115);
		table.getColumnModel().getColumn(5).setPreferredWidth(97);
		table.getColumnModel().getColumn(6).setPreferredWidth(107);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.setVisible(false);
		btnVerDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campeones.clear();
				model.setRowCount(0);
				scrollPane.setVisible(false);
				table.setVisible(false);
				if (comboBox.getSelectedItem().equals("Sacar datos XML")) {
					campeones = DOMXML.sacarDatosXML();
					for (Campeon campeon : campeones) {
						model.addRow(new Object[] { campeon.getId(), campeon.getName(), campeon.getRole(),
								campeon.getLane(), campeon.getAttackType(), campeon.getDifficulty(),
								campeon.getReleaseYear(), campeon.getLore() });
					}
					scrollPane.setVisible(true);
					table.setVisible(true);
				}
				if (comboBox.getSelectedItem().equals("Sacar datos API")) {
					String response = Api.peticionApi();
					if (response != null) {
						System.out.println("La api no responde ");
					}
					System.out.println(response);
					Json.guardadDatos(response);
					campeones = Json.getChapeones(response);
					for (Campeon campeon : campeones) {
						model.addRow(new Object[] { campeon.getId(), campeon.getName(), campeon.getRole(),
								campeon.getLane(), campeon.getAttackType(), campeon.getDifficulty(),
								campeon.getReleaseYear(), campeon.getLore() });
					}
					scrollPane.setVisible(true);
					table.setVisible(true);
				}
				if (comboBox.getSelectedItem().equals("Sacar datos JSON")) {
					campeones = Json.getChapeones(Json.conseguirDatosJSON());
					for (Campeon campeon : campeones) {
						model.addRow(new Object[] { campeon.getId(), campeon.getName(), campeon.getRole(),
								campeon.getLane(), campeon.getAttackType(), campeon.getDifficulty(),
								campeon.getReleaseYear(), campeon.getLore() });
					}
					scrollPane.setVisible(true);
					table.setVisible(true);
				}
			}
		});
		btnConvertir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox_1.getSelectedItem().equals("Escribir a XML")) {
					try {
						DOMXML domxml = new DOMXML(campeones);
						domxml.CrearXML("campeones.xml");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

	}
}
