package Interfaz;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Controlador.Controlador;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JOptionPane;


public class ventanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombreProvincia;
	private JTextField similaridad;
	private Controlador controlador;
	private JTextField ejeY;
	private JTextField ejeX;
	private JComboBox<String> comboBox1;
	private JComboBox<String> comboBox2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventanaPrincipal frame = new ventanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

    public ventanaPrincipal() {
    	 controlador = new Controlador();
        initialize();
    }
	
	 private void initialize() {	
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 676, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titulo = new JLabel("REGIONALIZADOR DE PROVINCIAS");
		titulo.setBackground(Color.BLUE);
		titulo.setForeground(Color.WHITE);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		titulo.setBounds(181, 11, 310, 25);
		contentPane.add(titulo);
		
		nombreProvincia = new JTextField();
		nombreProvincia.setColumns(10);
		nombreProvincia.setBounds(182, 95, 195, 20);
		contentPane.add(nombreProvincia);
		
		JButton btnCrearProvincia = new JButton("CREAR PROVINCIA");
		btnCrearProvincia.setFont(new Font("Tahoma", Font.BOLD, 11));

		btnCrearProvincia.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	String nombre = nombreProvincia.getText().trim().toUpperCase();
		        String xTexto = ejeX.getText().trim();
		        String yTexto = ejeY.getText().trim();
		        //verifico que el nombre no esté vacío
		        if (nombre.isEmpty()) {
		            JOptionPane.showMessageDialog(ventanaPrincipal.this,"Ingresá el nombre de la provincia.");
		            return;
		        }
		        //verifico que las coordenadas estén completas
		        if (xTexto.isEmpty() || yTexto.isEmpty()) {
		            JOptionPane.showMessageDialog(ventanaPrincipal.this,"Ingresá las coordenadas X e Y.");
		            return;
		        }
		        //verifico que la provincia no esté repetida
		        for (int i = 0; i < comboBox1.getItemCount(); i++) {
		            if (comboBox1.getItemAt(i).toString().equalsIgnoreCase(nombre)) {
		            	JOptionPane.showMessageDialog(ventanaPrincipal.this,"La provincia ya fue ingresada.");
		                return;
		            }
		        }
		    	try {
		        	double x = Double.parseDouble(xTexto.replace(",","."));
		        	double y = Double.parseDouble(yTexto.replace(",","."));			        
			        controlador.agregarProvincia(nombre, x, y);
			        comboBox1.addItem(nombre);
			        comboBox2.addItem(nombre);		      
			        nombreProvincia.setText("");
			        ejeX.setText("");
			        ejeY.setText("");
			        
			        JOptionPane.showMessageDialog(ventanaPrincipal.this,"Provincia creada correctamente.");
		    	} catch (NumberFormatException ex) {
		    		JOptionPane.showMessageDialog(ventanaPrincipal.this,"Las coordenadas deben ser números válidos.");
		    	}
		    }
		});
		btnCrearProvincia.setBounds(448, 110, 173, 23);
		contentPane.add(btnCrearProvincia);
		
		JButton btnCrearArista = new JButton("CREAR SIMILARIDAD");
		btnCrearArista.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCrearArista.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String provincia1 = (String) comboBox1.getSelectedItem();
		        String provincia2 = (String) comboBox2.getSelectedItem();
		        double valor = Double.parseDouble(similaridad.getText());
		        controlador.agregarConexion(provincia1, provincia2, valor);
		        similaridad.setText("");
		    }
		});
		btnCrearArista.setBounds(448, 307, 173, 23);
		contentPane.add(btnCrearArista);
		
		comboBox1 = new JComboBox<>();
		comboBox1.setBounds(102, 266, 173, 22);
		contentPane.add(comboBox1);
		
		JLabel labelProv2 = new JLabel("Provincia 2:");
		labelProv2.setForeground(Color.WHITE);
		labelProv2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelProv2.setBounds(20, 309, 72, 14);
		contentPane.add(labelProv2);
		
		comboBox2 = new JComboBox<>();
		comboBox2.setBounds(102, 307, 173, 22);
		contentPane.add(comboBox2);
		
		JLabel labelSimilitud = new JLabel("Similaridad:");
		labelSimilitud.setForeground(Color.WHITE);
		labelSimilitud.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelSimilitud.setBounds(20, 352, 72, 14);
		contentPane.add(labelSimilitud);
		
		similaridad = new JTextField();
		similaridad.setColumns(10);
		similaridad.setBounds(107, 351, 65, 20);
		contentPane.add(similaridad);
		
		JLabel labelProvincia = new JLabel("Nombre de la provincia:");
		labelProvincia.setForeground(Color.WHITE);
		labelProvincia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelProvincia.setBounds(20, 95, 152, 17);
		contentPane.add(labelProvincia);
		
		JButton btnNewButton = new JButton("MOSTRAR MAPA");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					mostrarGrafo();
			}
		});
		btnNewButton.setBounds(243, 406, 173, 23);
		contentPane.add(btnNewButton);
		
		JLabel labelX = new JLabel("Coordenada X:");
		labelX.setForeground(Color.WHITE);
		labelX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelX.setBounds(20, 134, 98, 17);
		contentPane.add(labelX);
		
		JLabel labelY = new JLabel("Coordenada Y:");
		labelY.setForeground(Color.WHITE);
		labelY.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelY.setBounds(20, 162, 98, 17);
		contentPane.add(labelY);
		
		ejeY = new JTextField();
		ejeY.setColumns(10);
		ejeY.setBounds(129, 162, 43, 20);
		contentPane.add(ejeY);
		
		ejeX = new JTextField();
		ejeX.setColumns(10);
		ejeX.setBounds(129, 134, 43, 20);
		contentPane.add(ejeX);
		
		JLabel subTitulo2 = new JLabel("CREAR SIMILARIDAD ENTRE PROVINCIAS");
		subTitulo2.setForeground(Color.WHITE);
		subTitulo2.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		subTitulo2.setBounds(176, 230, 302, 25);
		contentPane.add(subTitulo2);
		
		JLabel subTitulo1 = new JLabel("INGRESAR PROVINCIAS");
		subTitulo1.setBackground(Color.LIGHT_GRAY);
		subTitulo1.setForeground(Color.WHITE);
		subTitulo1.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		subTitulo1.setBounds(231, 59, 173, 25);
		contentPane.add(subTitulo1);
		
		JLabel labelProv1 = new JLabel("Provincia 1:");
		labelProv1.setForeground(Color.WHITE);
		labelProv1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelProv1.setBounds(20, 268, 72, 14);
		contentPane.add(labelProv1);
		
		JLabel labelCantRegiones = new JLabel("Elegir cantidad de regiones a obtener:");
		labelCantRegiones.setForeground(Color.WHITE);
		labelCantRegiones.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelCantRegiones.setBounds(20, 459, 237, 17);
		contentPane.add(labelCantRegiones);
		
		JTextField cantRegiones = new JTextField();
		cantRegiones.setColumns(10);
		cantRegiones.setBounds(267, 459, 43, 20);
		contentPane.add(cantRegiones);
		
		JButton btnRegionalizar = new JButton("REGIONALIZAR");
		btnRegionalizar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegionalizar.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int k = Integer.parseInt(cantRegiones.getText());
		    	ventanaGrafo ventana = new ventanaGrafo(controlador,k);
		    	ventana.setVisible(true);
		    }
		});
		btnRegionalizar.setBounds(448, 458, 173, 23);
		contentPane.add(btnRegionalizar);
		
		JLabel lblEjemplo = new JLabel("Ej: 0.25");
		lblEjemplo.setForeground(Color.GRAY);
		lblEjemplo.setBounds(181, 354, 46, 14);
		contentPane.add(lblEjemplo);
	 }
	 
	 private void mostrarGrafo() {
		 ventanaGrafo ventana = new ventanaGrafo(controlador, this);
		 ventana.setVisible(true);
		 this.setVisible(false);
	}
}
