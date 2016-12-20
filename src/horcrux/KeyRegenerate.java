package horcrux;
import java.io.*;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;

public class KeyRegenerate extends JFrame implements ActionListener,ItemListener
{
	JLabel l1,l2,l3,an;
	JTextField t1;
	JButton b,bb;
	JComboBox combo;
	Scrollbar scrol;
	int n,level,val;
	JFrame frame=new JFrame();
	JTextField[] t= new JTextField[200];	
	JScrollPane pane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	public KeyRegenerate()
	{
		int i,j;
		frame.setContentPane(pane);
		frame.setVisible(true);
		frame.setSize(700,600);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		l1=new JLabel("Total Shareholders");
		frame.add(l1);
		l1.setBounds(100,50,150,20);
		t1=new JTextField();
		frame.add(t1);
		t1.setBounds(250,50,100,20);
		l2=new JLabel("Security Level");
		frame.add(l2);
		l2.setBounds(100,100,300,20);
		combo=new JComboBox();
		combo.setBounds(250,100,50,20);
		combo.addItem("------");
		combo.addItem("1");
		combo.addItem("2");
		combo.addItem("3");
		combo.addItem("4");
		bb=new JButton("Ok");
		frame.add(bb);
		bb.addActionListener(this);
		bb.setBounds(350,100,50,20);
		frame.add(combo);	
		combo.addItemListener(this);
	}
    @Override
	public void itemStateChanged(ItemEvent ie)
	{
			int i,j,share;
			level=Integer.parseInt((String)combo.getSelectedItem());
			share=Integer.parseInt(t1.getText());
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
	}
    @Override
	public void actionPerformed(ActionEvent e)
	{
		int i,j,share,dec,pos=0;
		double mult,sum=0,fl,flsum=0;
                long temp;
		long[] x=new long[200];
		long[] y=new long[200];
		if(e.getSource()==b)
		{
			level=Integer.parseInt((String)combo.getSelectedItem());
			share=Integer.parseInt(t1.getText());
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
			for(i=0,j=0;i<(val*2);i+=2,j++)
			{
					x[j]=Long.parseLong(t[i].getText());	
					y[j]=Long.parseLong(t[i+1].getText());
			}
			for(i=0;i<val-1;i++)
			{
				for(j=0;j<(val-1);j++)
				{
					if(x[j]>x[j+1])
					{
						temp=x[j];
						x[j]=x[j+1];	
						x[j+1]=temp;
						temp=y[j];
						y[j]=y[j+1];
						y[j+1]=temp;
					}
				}
			}
			for(i=0;i<val;i++)
				System.out.println(x[i]+" "+y[i]+"\n");
			for (i = 0; i <val; i++) 
			{
        			mult = 1;
        			for (j = 0; j <val; j++) 
				{

 	        			if (j != i) 
					{
                				mult *=((double) ( 0- x[j]) / (double)(x[i] - x[j]));
					}
            			}
            			sum += mult * y[i];
				
				System.out.println("Mult "+mult+"\n");
			System.out.println("Sum "+sum+"\n");
        		}
			System.out.println("the sum is "+sum);
			JOptionPane.showMessageDialog(null, "The key is "+sum);
		}
		else if(e.getSource()==bb)
		{
			l3=new JLabel("Enter the share along with its number .");
			add(l3);
			l3.setBounds(100,150,300,20);
			
			for(i=0,j=0;i<(2*val);i=i+2,j++)
			{
		
				t[i]=new JTextField(5);
				frame.add(t[i]);
				t[i].setBounds(100,200+(25*j),50,20);
				t[i+1]=new JTextField(15);
				frame.add(t[i+1]);
				t[i+1].setBounds(200,200+(25*j),150,20);
										
			}
			b=new JButton("Generate Key");
			frame.add(b);
			b.addActionListener(this);
			b.setBounds(200,200+(25*j)+10,150,20);
		}
	}
	public static void main(String arg[])throws IOException
	{
		KeyRegenerate n=new KeyRegenerate();
	}
}

	