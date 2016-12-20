package horcrux;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Encrypt extends JFrame implements ActionListener 
{
    private static byte[] a;
    JLabel l1,l2,l3,l4;
    JFileChooser fc;
    JButton select,encrypt;
    String filename;
    private byte[] message;
    byte[] buffer;
    File file;
    JTextField k,loc;
                
    Encrypt()
    {
        setVisible(true);
	setSize(500,300);
	setLayout(null);
        setTitle("Encrpytion");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	fc=new JFileChooser();	
        l1=new JLabel("Browse the file containing sensitive data ");
        l1.setBounds(50,50,300,20);
        add(l1);
        select=new JButton("Browse");
        select.setBounds(300,50,100,20);
        select.addActionListener(this);
        add(select);
        l3=new JLabel("Enter the key");
        l3.setBounds(50,100,300,20);
        add(l3);
        k=new JTextField();
        add(k);
        k.setBounds(300,100,100,20);
        l4=new JLabel("Store the encrypted data at : ");
        add(l4);
        l4.setBounds(50,150,300,20);
        loc=new JTextField();
        add(loc);
        loc.setBounds(300,150,100,20);
        l2=new JLabel("Encrypt the file");
        l2.setBounds(50,200,300,20);
        add(l2);
        encrypt=new JButton("Encrypt");
        encrypt.setBounds(300,200,100,20);
        encrypt.addActionListener(this);
        add(encrypt);
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Cipher aesCipher = null;
        if(e.getSource()==select)
        {
            int returnVal;
            returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) 
            {
                file = fc.getSelectedFile();
                filename=fc.getSelectedFile().getAbsolutePath();
            } 
        }
        else if(e.getSource()==encrypt)
        {
            try 
            {
                String cipherText = null,message = null;
                byte[] byteCipherText = null; 
                SecretKey secretKey = null ;
                //Reading the file content
                FileInputStream fis = null;
                fis = new FileInputStream(filename);
                buffer = new byte[fis.available()];
                fis.read(buffer);
                message = new String(buffer,"UTF-8");
                fis.close();
                byte[] cText = null;
                System.out.println("key " + k.getText());
                cText = AES.encrypt(k.getText(),message);
                cipherText=new String(cText);
                System.out.println("The encrypted byte is : "+cText);
                System.out.println("The encrypted is : "+cipherText);
                System.out.println("The encrypted  length is : "+cipherText.length());
                System.out.println("The encrypted b length is : "+cText.length);
                 //Writing the encrypted content to the file
                FileWriter fileWriter = null;
                try 
                {
                    //String filenm="C:\\Documents and Settings\\project1\\My Documents\\encrypted.txt";
                    String filenm=loc.getText();
                    File newTextFile;
                    newTextFile = new File(filenm);
                    FileOutputStream fos = new FileOutputStream(filenm);
                    fos.write(cText);
                    fos.close();
                } 
                catch (IOException ex) 
                {
                } 
                JOptionPane.showMessageDialog(null, "Encryption Complete");
            } 
            catch (Exception ex) 
            {
                Logger.getLogger(Encrypt.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    public static void main(String arg[])throws IOException
    {
        Encrypt en;
        en = new Encrypt();
    }
}
