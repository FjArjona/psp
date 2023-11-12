package es.florida.aev1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Order extends JFrame {

    private List<JTextField> textFields;
    private JTextField fileNameField;
    private JCheckBox saveToFileCheckBox;
    private Manufacture manufacture;
    private String[] tipos = {"I", "O", "T", "J", "L", "S", "Z"};
    private int numTotalPieces = 0;
    List<String> piecesToFabricate =  new ArrayList<>();
 
    /**
     *  Interfaz gráfica y constructor de la clase
     */
    public Order() {
        manufacture = new Manufacture();

        setTitle("Tetris");
        setSize(565, 466);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel fileNameLabel = new JLabel("Nombre de archivo:");
        fileNameLabel.setBounds(27, 332, 213, 23);

        fileNameField = new JTextField(10);
        fileNameField.setToolTipText("Especificar nombre de archivo");
        fileNameField.setBounds(6, 362, 289, 54);

        saveToFileCheckBox = new JCheckBox("Guardar en archivo ?");
        saveToFileCheckBox.setBounds(6, 292, 244, 46);

        // Configuración del botón de fabricación
        JButton fabricateButton = new JButton("Fabricar");
        fabricateButton.setToolTipText("Iniciar fabricación");
        fabricateButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        fabricateButton.setBounds(305, 358, 234, 58);
        fabricateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fabricarPiezas();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.add(fileNameLabel);
        panel.add(fileNameField);
        panel.add(saveToFileCheckBox);
        panel.add(fabricateButton);

        JPanel panelTiposPiezas = new JPanel();
        panelTiposPiezas.setLayout(new GridLayout(7, 2, 10, 10));
        panelTiposPiezas.setBounds(6, 11, 533, 294);

        textFields = new ArrayList<>();
        
        for (String tipo : tipos) {
            JLabel lblTipo = new JLabel("Tipo " + tipo);
            lblTipo.setFont(new Font("Tahoma", Font.BOLD, 15));

            JTextField textField = new JTextField();
            textField.setToolTipText("Cantidad a fabricar");

            panelTiposPiezas.add(lblTipo);
            panelTiposPiezas.add(textField);

            textFields.add(textField);
        }

        panel.add(panelTiposPiezas);
        getContentPane().add(panel);
    }

    /**
//     * Metodo para añadir elementos a la lista de piezas
//     * @param tipo tipo de pieza
//     * @param quantity cantidad de piezas
//     */
//    private synchronized void addPiecesToList(String tipo , int quantity) {
//    	piecesToFabricate.add(tipo + ":" + quantity);
//    }
    /**
     * Método para fabricar las piezas
     */
    private void fabricarPiezas() {
    	
//        List<String> piecesToFabricate = new ArrayList<>();
        for (int i = 0; i < textFields.size(); i++) {
            String quantity = textFields.get(i).getText();
            
            if (!quantity.isEmpty()) {
//            	addPiecesToList(tipos[i], Integer.parseInt(quantity));
                piecesToFabricate.add(tipos[i] + ":" + Integer.parseInt(quantity));
                numTotalPieces = numTotalPieces + Integer.parseInt(quantity);
            }
        }
        // Configuración para guardar en archivo y nombre del archivo

        boolean saveToFile = saveToFileCheckBox.isSelected();
        String fileName = fileNameField.getText();
        // Procesamiento de fabricación por tipo y cantidad
        for (String piece : piecesToFabricate) {
            String[] split = piece.split(":");
            String pieceType = split[0];
            int cantidad = Integer.parseInt(split[1]);
            
            // Crear y ejecutar hilo para fabricar las piezas
            Thread hilo = new Thread(new Runnable() {
              @Override
              public void run() {
                  manufacture.fabricarPieza(pieceType, cantidad, saveToFile, fileName,numTotalPieces);   
              }
          });         
          hilo.start();
        }   
    }

    /**
     * Método principal que crea una instancia de la clase Order y la muestra en la pantalla.
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
    	
            Order order = new Order();
            order.setVisible(true);
        }    
}