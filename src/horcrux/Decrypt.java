
package horcrux;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Decrypt extends JFrame implements ActionListener
{
    JLabel l1,l2;
    JButton getfile,decrypt;
    JTextField t1;
    JFileChooser fc;
    String filename;
    private byte[] message;
    byte[] buffer;
    File file;
    private byte[] content;

    Decrypt()
    {
        setVisible(true);
	setSize(500,300);
	setLayout(null);
        setTitle("Decrpytion");
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	fc=new JFileChooser();	
        l1=new JLabel("Browse the encrypted file ");
        l1.setBounds(100,50,300,20);
        add(l1);
        getfile=new JButton("Browse");
        add(getfile);
        getfile.addActionListener(this);
        getfile.setBounds(300,50,100,20);
        l2=new JLabel("Enter the key");
        l2.setBounds(100,100,300,20);
        add(l2);
        t1=new JTextField();
        add(t1);
        t1.setBounds(300,100,100,20);
        decrypt=new JButton("Decrypt");
        add(decrypt);
        decrypt.setBounds(200,150,100,20);
        decrypt.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==getfile)
        {
            int returnVal;
            returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) 
            {
                file = fc.getSelectedFile();
                filename = fc.getSelectedFile().getAbsolutePath();
            }
        }
        else if(e.getSource()==decrypt)
        {
            FileInputStream fis = null;
            try {
                String key=t1.getText();
                byte[] encrypted = null;
                File f=new File(filename);
                fis = new FileInputStream(filename);
                try 
                {
                    encrypted=new byte[fis.available()];
                    fis.read(encrypted);
                }
                catch (Exception ex) {
                    Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex);
                }
                fis.close();
                System.out.println("The read len is : "+encrypted.length);
                System.out.println("The read cipher is : "+ encrypted);
                String decrypted = null;
                try {
                    decrypted = AES.decrypt(key, encrypted);
                } catch (GeneralSecurityException ex) {
                    Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("The original text is : "+decrypted);
                JOptionPane.showMessageDialog(null, "Decryption Complete");
                JOptionPane.showMessageDialog(null, "The original data is :\n\n"+decrypted);
            } catch (IOException ex) 
            {
                Logger.getLogger(Decrypt.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    public static void main(String arg[])throws IOException
    {
        Decrypt dd=new Decrypt();
    }
}
