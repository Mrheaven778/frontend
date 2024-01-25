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
		lblTitulo.setBounds(320, 31, 305, 62);
		frame.getContentPane().add(lblTitulo);

		JButton btnNewXml = new JButton("Crear XML");
		btnNewXml.setForeground(new Color(255, 255, 255));
		btnNewXml.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DOMXML dom = new DOMXML(campeones);
				try {
					dom.CrearXML("campeones.xml");

				} catch (Exception IOException) {
					System.out.println("Error al crear el archivo");
				}
			}
		});
		btnNewXml.setBorderPainted(false);
		btnNewXml.setBackground(new Color(14, 89, 19));
		btnNewXml.setBounds(31, 537, 105, 23);
		frame.getContentPane().add(btnNewXml);

		JButton btnCrearJson = new JButton("Crear JSON");
		btnCrearJson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCrearJson.setForeground(Color.WHITE);
		btnCrearJson.setBorderPainted(false);
		btnCrearJson.setBackground(new Color(14, 89, 19));
		btnCrearJson.setBounds(418, 537, 105, 23);
		frame.getContentPane().add(btnCrearJson);

		JButton btnVerDatos = new JButton("Ver Datos SQL");
		btnVerDatos.setForeground(new Color(255, 255, 255));
		btnVerDatos.setBorderPainted(false);
		btnVerDatos.setBackground(new Color(14, 89, 19));
		btnVerDatos.setBounds(669, 85, 136, 23);
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

		JButton btnVerDatosXML = new JButton("Ver Datos xml");
		btnVerDatosXML.setForeground(Color.WHITE);
		btnVerDatosXML.setBorderPainted(false);
		btnVerDatosXML.setBackground(new Color(14, 89, 19));
		btnVerDatosXML.setBounds(68, 85, 136, 23);
		frame.getContentPane().add(btnVerDatosXML);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Sacar datos XML", "Sacar datos API", "Sacar datos JSON"}));
		comboBox.setBounds(418, 85, 207, 22);
		frame.getContentPane().add(comboBox);
		table.getColumnModel().getColumn(0).setPreferredWidth(98);
		table.getColumnModel().getColumn(1).setPreferredWidth(107);
		table.getColumnModel().getColumn(4).setPreferredWidth(115);
		table.getColumnModel().getColumn(5).setPreferredWidth(97);
		table.getColumnModel().getColumn(6).setPreferredWidth(107);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		table.setVisible(false);

		btnVerDatosXML.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				campeones.clear();
				model.setRowCount(0);
				campeones = DOMXML.sacarDatosXML();
				for (Campeon campeon : campeones) {
					model.addRow(new Object[] { campeon.getId(), campeon.getName(), campeon.getRole(),
							campeon.getLane(), campeon.getAttackType(), campeon.getDifficulty(),
							campeon.getReleaseYear(), campeon.getLore() });
				}
				scrollPane.setVisible(true);
				table.setVisible(true);
				btnVerDatosXML.setEnabled(false);
			}
		});
		btnVerDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				campeones.clear();
				model.setRowCount(0);
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
				btnVerDatos.setEnabled(false);
			}
		});

	}
}
