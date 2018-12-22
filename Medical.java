package med;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Medical extends JFrame implements ActionListener
{
  JLabel lblu,lblp,image;
  JTextField txtu;
  JPasswordField pwd;
  JButton LogIn,Reset;
  JPanel pan;
  Connection con;

  public Medical(String title)
  {
    super(title);

    pan=new JPanel();
    pan.setLayout(null);
    pan.setBackground(Color.PINK);
    add(pan);

    lblu=new JLabel("Name");
    lblu.setBounds(50,40,80,25);
    pan.add(lblu);

    txtu=new JTextField(10);
    txtu.setBounds(190,40,165,25);
    pan.add(txtu);

    lblp=new JLabel("Password");
    lblp.setBounds(50,80,80,25);
    pan.add(lblp);

    pwd=new JPasswordField(10);
    pwd.setBounds(190,80,165,25);
    pwd.setEchoChar('*');
    pan.add(pwd);

    LogIn=new JButton("LogIn");
    LogIn.setBounds(40,130,100,25);
    pan.add(LogIn);
    LogIn.addActionListener(this);

    Reset=new JButton("Reset");
    Reset.setBounds(200,130,100,25);
    pan.add(Reset);
    Reset.addActionListener(this);

  }

  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource()==LogIn)
    {
        
        try 
        {
          Class.forName("com.mysql.jdbc.Driver");
          con=DriverManager.getConnection("jdbc:mysql://localhost:3306/example","root","pass");            
          Statement stmt=con.createStatement();
          ResultSet rs=stmt.executeQuery("select * from owner");
          while(rs.next())
          {                 
            if (txtu.getText().equals(rs.getString(1))&& pwd.getText().equals(rs.getString(2)))
            {
              // this.setVisible(false);
              this.dispose();
              Main mn=new Main("MEDICAL STORE MANAGEMENT");
              mn.setVisible(true);
              mn.setSize(900,500);
              mn.setDefaultCloseOperation(EXIT_ON_CLOSE);
              mn.setLocationRelativeTo(null);
              mn.setResizable(false);
            }
            else
            {
              JOptionPane.showMessageDialog(null,"Incorrect username or password");
            }
          }
            
      } 
      catch (Exception ex)
      {
          System.out.println(ex);
      }  
    }

    if (e.getSource()==Reset)
    {
      txtu.setText("");
      pwd.setText("");
    }
  }

  public static void main(String[] args)
  {
    Medical m=new Medical("LOGIN");
    m.setVisible(true);
    // Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // m.setSize(screenSize.width, screenSize.height);
    m.setSize(450,240);
    m.setDefaultCloseOperation(EXIT_ON_CLOSE);
    m.setLocationRelativeTo(null);
    m.setResizable(false);
  }
}
