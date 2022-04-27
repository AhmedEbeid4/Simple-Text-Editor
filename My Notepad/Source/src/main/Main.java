package main;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        new progress_par();
    }
}
class progress_par extends JFrame {
    JProgressBar bar ;
    int counter =0;

    public progress_par(){
        ImageIcon icon = new ImageIcon("notepad.png");
        setIconImage(icon.getImage());
        setResizable(false);
        bar= new JProgressBar();
        bar.setValue(0);
        bar.setBounds(10,0,400,50);
        bar.setStringPainted(true);
        bar.setFont(new Font("MV Boli" , Font.BOLD,25));
        bar.setForeground(Color.black);
        bar.setBackground(Color.white);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500,200);
        setVisible(true);
        setSize(435,90);
        setLayout(null);
        add(bar);
        fill();

    }
    public void fill(){
        if(counter==100){
            bar.setString("Done :)");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            dispose();
            new menu_par();
            return;
        }
        try {
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        counter +=1;
        bar.setValue(counter);
        fill();
    }
}
class menu_par implements ActionListener {
    JScrollPane scrollPane;

    JTextArea textArea;

    JComboBox font_Size;
    JComboBox font_type;

    int a=11;
    String b = "times new roman";


    JFrame message = new JFrame();
    JFrame main = new JFrame();

    JButton Change_Font_Color;


    JMenuBar menuBar;
    JButton button;

    JMenu fileMenu;
    JMenu editMenu;

    JMenuItem font;

    JMenuItem Save_Item;
    JMenuItem newItem;
    JMenuItem closeItem;
    JMenuItem Background_Color;

    JMenuItem select_file_Item;
    public menu_par(){
        ImageIcon icon = new ImageIcon("notepad.png");
        main.setIconImage(icon.getImage());
        message.setIconImage(icon.getImage());
        main.setVisible(true);
        main.setDefaultCloseOperation(main.DISPOSE_ON_CLOSE);
        main.setLayout(new GridLayout());
        main.setLocation(480,130);
        main.setSize(500,500);
        textArea = new JTextArea();
        textArea.setFont(new Font("times new roman" , Font.PLAIN , 18));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        scrollPane = new JScrollPane(textArea);

        main.add(scrollPane);
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        editMenu = new JMenu("Edit");

        newItem = new JMenuItem("new");
        newItem.addActionListener(this);
        closeItem = new JMenuItem("close");
        closeItem.addActionListener(this);
        Save_Item = new JMenuItem("save");
        Save_Item.addActionListener(this);

        select_file_Item = new JMenuItem("Open");
        select_file_Item.addActionListener(this);


        font = new JMenuItem("font");
        font.addActionListener(this);

        Background_Color = new JMenuItem("background");
        Background_Color.addActionListener(this);
        editMenu.add(font);
        editMenu.add(Background_Color);

        fileMenu.add(newItem);
        fileMenu.add(Save_Item);
        fileMenu.add(select_file_Item);
        fileMenu.add(closeItem);

        Border border = BorderFactory.createLineBorder(Color.black);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        main.setJMenuBar(menuBar);

        message.setSize(400,200);
        message.setLayout(null);
        message.setVisible(false);
        message.setResizable(false);
        message.setLocation(450,100);
        message.setDefaultCloseOperation(message.DISPOSE_ON_CLOSE);

        JLabel label1 = new JLabel("Font size ");
        label1.setBounds(10,10,150,50);
        label1.setFont(new Font("Arial" , Font.PLAIN, 30));

        JLabel label2 = new JLabel("Font Type ");
        label2.setBounds(10,70,150,50);
        label2.setFont(new Font("Arial" , Font.PLAIN, 30));

        message.add(label1);
        message.add(label2);
        String [] fontsSizes = new String[100];

        for(int i =1; i<fontsSizes.length+1;i++){
            fontsSizes[i-1] = String.valueOf(i);
        }
        font_Size = new JComboBox(fontsSizes);
        font_Size.setBounds(145,10,230,50);

        font_Size.setFont(new Font("times new roman" , Font.BOLD,30));
        font_Size.setEditable(true);
        font_Size.setSelectedItem("18");
        font_Size.addActionListener(this);

        String[] font_types =GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        font_type = new JComboBox(font_types);
        font_type.setBounds(145,70,230,50);

        font_type.setFont(new Font("times new roman" , Font.BOLD,30));
        font_type.setEditable(true);
        font_type.setSelectedItem("times new roman");
        font_type.addActionListener(this);

        button = new JButton("Submit");
        button.setBounds(160,170,90,40);
        button.setFocusable(false);
        button.addActionListener(this);

        Change_Font_Color = new JButton("Change font color");
        Change_Font_Color.setBounds(10,130,140,20);
        Change_Font_Color.setFocusable(false);
        Change_Font_Color.addActionListener(this);
        Change_Font_Color.setBorder(border);

        message.add(font_Size);
        message.add(font_type);
        message.add(Change_Font_Color);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()== Background_Color){
            JColorChooser colorChooser = new JColorChooser();
            Color color = colorChooser.showDialog(main,"choose a color" ,Color.BLACK);;
            textArea.setBackground(color);
        }
        if(e.getSource() == font_Size){
            try{
                int a = Integer.parseInt((String) font_Size.getSelectedItem());
                if(a>0){
                    textArea.setFont(new Font(textArea.getFont().getFamily(),Font.PLAIN,a));
                }else{
                    JOptionPane.showMessageDialog(message,"it should be positive" , null,JOptionPane.ERROR_MESSAGE);
                }
            }catch (Exception r1){
                JOptionPane.showMessageDialog(message,"it should be number" , null, JOptionPane.ERROR_MESSAGE);
            }

        }
        if(e.getSource() == font_type){
            String b= String.valueOf(font_type.getSelectedItem());
            textArea.setFont(new Font( b, Font.PLAIN, textArea.getFont().getSize()));
        }
        if(e.getSource() == Change_Font_Color){

            JColorChooser colorChooser1 = new JColorChooser();
            Color color1 = colorChooser1.showDialog(main,"choose a color" ,Color.BLACK);
            textArea.setForeground(color1);
        }
        if(e.getSource()==select_file_Item){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("C:\\Users\\Ahmed Ebeid\\Desktop"));
            FileNameExtensionFilter filter = new FileNameExtensionFilter("txt files" ,"txt");
            fileChooser.setFileFilter(filter);
            int r = fileChooser.showOpenDialog(main);
            if(r==JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                Scanner fileIn = null;
                try {
                    fileIn=new Scanner(file);
                    if(file.isFile()){
                        while (fileIn.hasNextLine()){
                            String Line = fileIn.nextLine() +"\n" ;
                            textArea.append(Line);
                        }
                    }
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }finally {
                    fileIn.close();
                }
            }
        }

        if(e.getSource() == newItem){
            new menu_par();

        }
        if(e.getSource() == closeItem){
            main.dispose();
        }
        if(e.getSource() == Save_Item){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File("C:\\Users\\Ahmed Ebeid\\Desktop"));
            int r=fileChooser.showSaveDialog(main);
            if(r==JFileChooser.APPROVE_OPTION){
                File file ;
                PrintWriter findOut = null;
                file= new File(fileChooser.getSelectedFile().getAbsolutePath());
                try {
                    findOut= new PrintWriter(file);
                    findOut.println(textArea.getText());
                }catch (Exception u1){
                    u1.printStackTrace();
                }finally {
                    findOut.close();
                }
            }
        }
        if(e.getSource() == font){
            message.setVisible(true);
        }
    }
}
