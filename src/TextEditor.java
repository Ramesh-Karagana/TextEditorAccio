import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;


public class TextEditor implements ActionListener
{
    JFrame frame;
    JMenuBar menuBar;
    JMenu file,edit;
    JMenuItem newfile,openfile,savefile;
    JMenuItem cut,copy,paste,selectAll,close;
    JTextArea textArea;
    TextEditor()
    {
        frame= new JFrame();
        menuBar=new JMenuBar();
        textArea=new JTextArea();
        file=new JMenu("File");
        edit=new JMenu("Edit");
        newfile=new JMenuItem("New Window");
        openfile=new JMenuItem("Open File");
        savefile=new JMenuItem("Save File");
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        menuBar.add(file);
        menuBar.add(edit);
        frame.setJMenuBar(menuBar);
        JPanel panel= new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textArea,BorderLayout.CENTER);
        JScrollPane scrollPane= new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);
        frame.add(panel);
        frame.setBounds(100,100,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);


    }
    @Override
    public void actionPerformed(ActionEvent actionEvent)
    {
        if (actionEvent.getSource()==cut) {
            textArea.cut();
        }
        if (actionEvent.getSource()==copy) {
            textArea.copy();
        }
        if (actionEvent.getSource()==paste) {
            textArea.paste();
        }
        if (actionEvent.getSource()==selectAll) {
            textArea.selectAll();
        }
        if (actionEvent.getSource()==close) {
            System.exit(0);
        }
        if (actionEvent.getSource()==openfile) {
            JFileChooser fileChooser= new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            if (chooseOption == JFileChooser.APPROVE_OPTION)
            {
                // Set the label to the path of the selected directory
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try {
                    // Create a file writer
                    FileReader fileReader = new FileReader(filePath);

                    // Create buffered writer to write
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate="", output="";
                    while((intermediate=bufferedReader.readLine())!=null)
                    {
                        output+=intermediate+"\n";
                    }
                    textArea.setText(output);
                    // Write
                }
                catch (IOException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if (actionEvent.getSource()==savefile) {
            JFileChooser fileChooser= new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);

            if (chooseOption == JFileChooser.APPROVE_OPTION)
            {
                // Set the label to the path of the selected directory
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                    // Create a file writer
                    FileWriter  fileWriter = new FileWriter(file);

                    // Create buffered writer to write
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                    // Write
                }
                catch (IOException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if (actionEvent.getSource()==newfile)
        {
            TextEditor  NewTextEditor =new TextEditor();
        }

    }
    public static void main(String[] args)
    {
        TextEditor  TextEditor =new TextEditor();
    }

}