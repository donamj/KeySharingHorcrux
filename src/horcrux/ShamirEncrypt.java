package horcrux;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class ShamirEncrypt extends JFrame implements ActionListener 
{
	JLabel l1,l2,l3;
	JTextField t1,t2,t3;
	TextArea ta;
	JButton b1,b2;
	Random ran=new Random();
	Choice combo;
	public ShamirEncrypt()
	{
		setVisible(true);
		setSize(600,600);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l1=new JLabel("Secret key");
		add(l1);
		l1.setBounds(100,50,100,20);
		t1=new JTextField();
		add(t1);
		t1.setBounds(250,50,300,20);
		l2=new JLabel("Total Shareholders");
		add(l2);
		l2.setBounds(100,100,130,20);
		t2=new JTextField();
		add(t2);
		t2.setBounds(250,100,100,20);
		l3=new JLabel("Security Level");
		add(l3);
		l3.setBounds(100,150,130,20);
		combo=new Choice();
		add(combo);
		combo.setBounds(250,150,50,20);
		combo.add("------");
		combo.add("1");
		combo.add("2");
		combo.add("3");
		combo.add("4");
		b1=new JButton("Split");
		add(b1);
		b1.setBounds(250,230,100,20);
		b1.addActionListener(this);
		ta=new TextArea();
		add(ta);
		ta.setBounds(150,300,300,200);
                ta.setText(null);
	}
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		int share,val,level,i,j,prod;
		long ans,key;
		int[] ran_arr= new int[200];
		if(e.getSource()==b1)
		{
                        ta.setText(null);
			key=Long.parseLong(t1.getText());
			System.out.println("Key "+key);
			share=Integer.parseInt(t2.getText());
			level=Integer.parseInt((String)combo.getSelectedItem());
			switch(level)
			{
				case 1:	val=share;
					break;
				case 2:	val=(int)(.75*share);
					break;
				case 3:	val=(int)(.5*share);
					break;
				case 4:	val=(int)(.25*share);
					break;
				default:val=share;
					break;
			}
			for(i=1;i<val;i++)
			{
				ran_arr[i]=ran.nextInt(share+10);				
			}
			for(i=1;i<=share;i++)
			{
				ans=key;
				for(j=1;j<val;j++)
				{
					ans=ans+(long)((Math.pow(i,j))*ran_arr[j]);
				}	
				ta.append("\n"+i+".  "+ans);	
			}
		}
	}
	public static void main(String arg[])throws IOException
	{
		ShamirEncrypt n=new ShamirEncrypt();
	}
}

