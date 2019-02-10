//
// Name: Escobedo, David
// Project: 3
// Due: 3/12/18
// Course: CS-245-01-w18
// Description: My version of the Notepad application.
//

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Font;
import java.io.*;
import java.util.Calendar;
import javax.swing.filechooser.FileFilter;

public class JNotepad implements ActionListener{
    
    private JFrame jfrm;
    private JLabel jlab;
    private JFileChooser chooser = new JFileChooser();
    private File file;
    private JTextField jtf;
    private String str;
    private JTextArea jta;
    private JCheckBoxMenuItem jmiWordWrap;
    private JLabel jmsg;
    private int findIdx;
    private JButton button;
    private JButton button2;
    private Font font;
    private Font curFont;
    private Color curColor;
    //private JMenuItem jmiNew;
   
	 
		//main construtor to initialize frame, layout, size, menubar, text area and caret eventlistener
    public JNotepad() {
        jfrm = new JFrame("JNotepad");
        jfrm.setLayout(new FlowLayout());
        jfrm.setSize(1000,1000);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JMenuBar jmb = new JMenuBar();
        JPanel pan = new JPanel();
        pan.setLayout(new BorderLayout());
        jta = new JTextArea();
        font = new Font("Courier", Font.PLAIN, 12);
        jta.setFont(font);
        
        //jta.setLineWrap(true);
        //jta.setWrapStyleWord(true);
        
        JScrollPane jscrlp = new JScrollPane(jta);
        jscrlp.setPreferredSize(new Dimension(1000,700));
        pan.add(jscrlp, BorderLayout.CENTER);
        jfrm.add(pan);
        
        jta.addCaretListener(new CaretListener () {
            public void caretUpdate(CaretEvent ce) {
                str = jta.getText();
                findIdx = jta.getCaretPosition();
            }
        });
        
        //Create File Menu
        JMenu jmFile = new JMenu("File");
        jmFile.setMnemonic(KeyEvent.VK_F);
        JMenuItem jmiNew = new JMenuItem("New", KeyEvent.VK_N);
        jmiNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiOpen = new JMenuItem("Open...");
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiSave = new JMenuItem("Save");
        jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiSaveAs = new JMenuItem("Save As...");
        JMenuItem jmiPageSetup = new JMenuItem("Page Setup...", KeyEvent.VK_U);
        JMenuItem jmiPrint = new JMenuItem("Print...");
        jmiPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiExit = new JMenuItem("Exit", KeyEvent.VK_X);
        jmFile.add(jmiNew);
        jmFile.add(jmiOpen);
        jmFile.add(jmiSave);
        jmFile.add(jmiSaveAs);
        jmFile.addSeparator();
        jmFile.add(jmiPageSetup);
        jmFile.add(jmiPrint);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        jmb.add(jmFile);
        
        //Create Edit Menu
        JMenu jmEdit = new JMenu("Edit");
        jmEdit.setMnemonic(KeyEvent.VK_E);
        JMenuItem jmiUndo = new JMenuItem("Undo");
        JMenuItem jmiCut = new JMenuItem("Cut");
        jmiCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiCopy = new JMenuItem("Copy");
        jmiCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiPaste = new JMenuItem("Paste");
        jmiPaste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiDelete = new JMenuItem("Delete");
        jmiDelete.setAccelerator(KeyStroke.getKeyStroke("DELETE"));
        JMenuItem jmiFind = new JMenuItem("Find...");
        jmiFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiFindNext = new JMenuItem("Find Next");
        JMenuItem jmiReplace = new JMenuItem("Replace...");
        jmiReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiGoto = new JMenuItem("Go To...");
        jmiGoto.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiSelectAll = new JMenuItem("Select All");
        jmiSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        JMenuItem jmiTimeDate = new JMenuItem("Time/Date");
        jmiTimeDate.setAccelerator(KeyStroke.getKeyStroke("F5"));
        
        jmEdit.add(jmiUndo);
        jmEdit.addSeparator();
        jmEdit.add(jmiCut);
        jmEdit.add(jmiCopy);
        jmEdit.add(jmiPaste);
        jmEdit.add(jmiDelete);
        jmEdit.addSeparator();
        jmEdit.add(jmiFind);
        jmEdit.add(jmiFindNext);
        jmEdit.add(jmiReplace);
        jmEdit.add(jmiGoto);
        jmEdit.addSeparator();
        jmEdit.add(jmiSelectAll);
        jmEdit.add(jmiTimeDate);
        jmb.add(jmEdit);
        
        //Create Format Menu
        JMenu jmFormat = new JMenu("Format");
        jmFormat.setMnemonic(KeyEvent.VK_O);
        jmiWordWrap = new JCheckBoxMenuItem("Word Wrap");
        jmiWordWrap.setMnemonic(KeyEvent.VK_W);
        jmiWordWrap.setSelected(false);
        JMenuItem jmiFont = new JMenuItem("Font...", KeyEvent.VK_F);
        jmFormat.add(jmiWordWrap);
        jmFormat.add(jmiFont);
        jmb.add(jmFormat);
        
        //Create View Menu
        JMenu jmView = new JMenu("View");
        jmView.setMnemonic(KeyEvent.VK_V);
        JMenuItem jmiStatusBar = new JMenuItem("Status Bar", KeyEvent.VK_S);
        jmView.add(jmiStatusBar);
        jmb.add(jmView);
        
        //Create Help Menu
        JMenu jmHelp = new JMenu("Help");
        jmHelp.setMnemonic(KeyEvent.VK_H);
        JMenuItem jmiViewHelp = new JMenuItem("View Help", KeyEvent.VK_H);
        JMenuItem jmiAboutNotepad = new JMenuItem("About Notepad");
        jmHelp.add(jmiViewHelp);
        jmHelp.addSeparator();
        jmHelp.add(jmiAboutNotepad);
        jmb.add(jmHelp);
        
        //JPopupMenu
        JPopupMenu jpu = new JPopupMenu();
        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        jpu.add(cut);
        jpu.add(copy);
        jpu.add(paste);
        jta.addMouseListener(new MouseAdapter () {
            public void mousePressed(MouseEvent me) {
                if(me.isPopupTrigger()) {
                    jpu.show(me.getComponent(), me.getX(), me.getY());
                }
            }
            public void mouseReleased(MouseEvent me) {
                if(me.isPopupTrigger()) {
                    jpu.show(me.getComponent(), me.getX(), me.getY());
                }
            }
        });
        
        //Add action listeners for the File items 
        jmiNew.addActionListener(this);
        jmiOpen.addActionListener(this);
        jmiSave.addActionListener(this);
        jmiSaveAs.addActionListener(this);
        jmiPageSetup.addActionListener(this);
        jmiPrint.addActionListener(this);
        jmiExit.addActionListener(this);
        
        //Add action listeners for the Edit items 
        jmiUndo.addActionListener(this);
        jmiCut.addActionListener(this);
        jmiCopy.addActionListener(this);
        jmiPaste.addActionListener(this);
        jmiDelete.addActionListener(this);
        jmiFind.addActionListener(this);
        jmiFindNext.addActionListener(this);
        jmiReplace.addActionListener(this);
        jmiGoto.addActionListener(this);
        jmiSelectAll.addActionListener(this);
        jmiTimeDate.addActionListener(this);
        
        //Add action listeners for the Format items 
        jmiWordWrap.addActionListener(this);
        jmiFont.addActionListener(this);
        
        //Add action listeners for the View items 
        jmiStatusBar.addActionListener(this);
        
        //Add action listeners for the Help items 
        jmiViewHelp.addActionListener(this);
        jmiAboutNotepad.addActionListener(this);
        
        //Add action listeners for the Popup items 
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        
        //image
        Image icon = Toolkit.getDefaultToolkit().getImage("JNotepad.png");
        jfrm.setIconImage(icon);
        
        jfrm.setJMenuBar(jmb);
        //jfrm.pack();
        jfrm.setVisible(true);
        //jfrm.setLocationRelativeTo(null);
        
    }
    
		//function o handle events and perform appropriate action
    public void actionPerformed(ActionEvent ae) {
        String comStr = ae.getActionCommand();
        if (comStr.equals("Exit")) jfrm.dispose();
        if (comStr.equals("About Notepad")) {
            JOptionPane.showMessageDialog(jfrm, "(c) David Escobedo");
        }
        if(comStr.equals("Word Wrap")) {
            if(jmiWordWrap.isSelected()) {
                jta.setLineWrap(true);
                jta.setWrapStyleWord(true);
            }
            else{
                jta.setLineWrap(false);
                jta.setWrapStyleWord(false);
            }
            
        }
        if(comStr.equals("Time/Date")) {
            Calendar cal = Calendar.getInstance();
            int hour = cal.get(Calendar.HOUR);
            int minute = cal.get(Calendar.MINUTE);
            int amPm = cal.get(Calendar.AM_PM);
            int month = cal.get(Calendar.MONTH) + 1;
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int year = cal.get(Calendar.YEAR);
            String ampm = "";
            if(amPm == 0) {
                ampm = "AM";
            }
            else{
                ampm = "PM";
            }
            
            int pos = jta.getCaretPosition();
            jta.insert(String.format("%2d:%2d " + ampm + "%2d/%2d/%4d", hour,minute,month,day,year), pos);
        }
        if(comStr.equals("cut") || comStr.equals("Cut")) {
            jta.cut();
        }
        if(comStr.equals("copy") || comStr.equals("Copy")) {
            jta.copy();
        }
        if(comStr.equals("paste") || comStr.equals("Paste")) {
            jta.paste();
        }
        if(comStr.equals("Select All")) {
            jta.selectAll();
        }
        if(comStr.equals("Delete")) {
            jta.replaceSelection("");
        }
        
        if(comStr.equals("Find...")) {
            JDialog jf = new JDialog(jfrm);
            jf.setLayout(new FlowLayout());
            jf.setSize(350,200);
            jf.setLocationRelativeTo(jfrm);
            jlab = new JLabel("Find what:");
            jtf = new JTextField(20);
            button = new JButton("Find Next");
            button2 = new JButton("Cancel");
            jmsg = new JLabel();
            jf.add(jlab);
            jf.add(jtf);
            jf.add(button);
            jf.add(button2);
            jf.add(jmsg);
            
            button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    find(findIdx);
            }});
            
            button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    jf.dispose();
            }});
            
            jf.setVisible(true);
        }
        
        if(comStr.equals("Find Next")) {
            JDialog jf = new JDialog(jfrm);
            jf.setLayout(new FlowLayout());
            jf.setSize(350,200);
            jf.setLocationRelativeTo(jfrm);
            jlab = new JLabel("Find what:");
            jtf = new JTextField(20);
            button = new JButton("Find Next");
            button2 = new JButton("Cancel");
            jmsg = new JLabel();
            jf.add(jlab);
            jf.add(jtf);
            jf.add(button);
            jf.add(button2);
            jf.add(jmsg);
            
            button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    find(findIdx+1);
            }});
            
            button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    jf.dispose();
            }});
            
            jf.setVisible(true);
        }
        
        if(comStr.equals("Open...")) {
             chooser.setFileFilter(new NPFileFilter());
            if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION){
                String path = chooser.getSelectedFile().getAbsolutePath();
                file = chooser.getSelectedFile();
                try{
                    FileReader reader = new FileReader(file.getAbsolutePath());
                    jta.read(reader, null);
                    jfrm.setTitle(file.getName());
                    reader.close();
                }
                catch(Exception exc){
                }
            }
        }
        
        if(comStr.equals("Save")) {
             if(file!=null){
                save();
            }
            else{
                savePop();
            }
        }
        
        if(comStr.equals("Save As...")) {
            savePop();
        }
        
        if(comStr.equals("Font...")) {
            JFontChooser jfontc = new JFontChooser();
             if(jfontc.showDialog(jfrm)){
                curFont=jfontc.getFont();
                curColor=jfontc.getColor();
                jta.setFont(curFont);
                jta.setForeground(curColor);
            }
           
             
        }
        ///* for New?
        if (comStr.equals("New")) {
            new JNotepad();
        }
        //*/
    }
    //implement the find feature
    public void find(int start) {
        String str = jta.getText();
        String findStr = jtf.getText();
        int idx = str.indexOf(findStr, start);
        
        if(idx > -1) {
            jta.setCaretPosition(idx);
            findIdx = idx;
            jmsg.setText("String found");
        }
        else {
            jmsg.setText("String not found");
        }
        jta.requestFocusInWindow();
        jta.select(idx, idx + findStr.length());
    }
    
		//implement the save feature
    public void save(){
        PrintWriter writer;
        try{
            writer=new PrintWriter(file);
            writer.print(jta.getText());
            writer.close();
        }
        catch(Exception exc){
        }
    }
    
    public void savePop(){
        PrintWriter writer;
        if(chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
            file=chooser.getSelectedFile();
            try{
                writer=new PrintWriter(file);
                writer.print(jta.getText());
                jfrm.setTitle(file.getName());
                writer.close();
            }
            catch(Exception exc){
            }
        }
    }
    
    public static void main(String[]args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new JNotepad();
            }
        });
    }
}

//file functionality
class NPFileFilter extends FileFilter{
    public boolean accept(File name) {
        if(name.getName().endsWith(".txt")||name.getName().endsWith(".java")){
            return true;
        }
        if(name.isDirectory()){
            return true;
        }
        return false;
    }
    public String getDescription() {
       return "Text and Java Files";
    }
}


