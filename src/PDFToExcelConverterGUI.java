import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import com.aspose.pdf.Document;
import com.aspose.pdf.ExcelSaveOptions;

public class PDFToExcelConverterGUI extends JFrame {
    private JLabel pdfLabel, excelLabel;
    private JTextField pdfField, excelField;
    private JButton browsePDF, browseExcel, convertButton;

    public PDFToExcelConverterGUI() {
        super("PDF to Excel Converter");

        // GUI elemek inicializálása
        pdfLabel = new JLabel("PDF fájl:");
        excelLabel = new JLabel("Excel fájl:");
        pdfField = new JTextField(20);
        excelField = new JTextField(20);
        browsePDF = new JButton("Tallózás...");
        browseExcel = new JButton("Tallózás...");
        convertButton = new JButton("Átalakítás");

        // GUI elemek elrendezése
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        panel.add(pdfLabel, c);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(pdfField, c);
        c.gridx = 2;
        c.gridy = 0;
        panel.add(browsePDF, c);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(excelLabel, c);
        c.gridx = 1;
        c.gridy = 1;
        panel.add(excelField, c);
        c.gridx = 2;
        c.gridy = 1;
        panel.add(browseExcel, c);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(convertButton, c);
        add(panel);

        // Töltse be a PDF fájlt a Tallózás gombbal
        browsePDF.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    pdfField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        // Tallózza az Excel fájlt a Tallózás gombbal
        browseExcel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    excelField.setText(selectedFile.getAbsolutePath());
                }
            }
        });

        // PDF átalakítása Excel fájlra az Átalakítás gombbal
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String pdfFileName = pdfField.getText();
                String excelFileName = excelField.getText();
                try {
                Document doc = new Document(pdfFileName);
                // Set Excel options
                ExcelSaveOptions options = new ExcelSaveOptions();
                // Set output format
                options.setFormat(ExcelSaveOptions.ExcelFormat.XLSX);
                // Convert PDF to XLSX
                doc.save(excelFileName, options);
                    JOptionPane.showMessageDialog(null, "Az átalakítás befejeződött!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Hiba történt: " + ex.getMessage(), "Hiba", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ablak beállítások
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new PDFToExcelConverterGUI();
    }
}

