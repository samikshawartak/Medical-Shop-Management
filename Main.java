package med;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Main extends JFrame
{
  JTabbedPane pane;
  JButton but;
  public Main(String title)
  {
    super(title);

    pane=new JTabbedPane(JTabbedPane.TOP);
    //pane.setBounds(40,40,800,800);
    add(pane);
    pane.addTab("BILL GENERATION",new Bill());
    pane.addTab("ADD NEW MEDICINE STOCK",new Add());
    pane.addTab("UPDATE EXISTING STOCK",new Update());
    pane.addTab("REMOVE MEDICINE STOCK",new Remove());
    pane.addTab("LOGOUT",new Log());
  }

  public static void main(String[] args)
  {
    Main mn=new Main("MEDICAL STORE MANAGEMENT");
    mn.setVisible(true);
    mn.setSize(900,500);
    mn.setDefaultCloseOperation(EXIT_ON_CLOSE);
    mn.setLocationRelativeTo(null);
    mn.setResizable(false);
  }
}

class Bill extends JPanel implements ActionListener
{
  //JPanel p1;
  JLabel bl1,bl2;
  JTextField btf1,btf2;
  JButton bb;
  Connection con;
  String med;
  int qty,quan;
    
  
  public Bill()
  {
    this.setBackground(Color.PINK);
    this.setLayout(null);

    bl1=new JLabel("Enter Medicine");
    bl1.setBounds(200,100,100,30);
    add(bl1);

    btf1=new JTextField();
    btf1.setBounds(450,100,150,30);
    add(btf1);

    bl2=new JLabel("Enter Quantity");
    bl2.setBounds(200,200,100,30);
    add(bl2);

    btf2=new JTextField();
    btf2.setBounds(450,200,150,30);
    add(btf2);

    bb=new JButton("GENERATE BILL");
    bb.setBounds(280,300,200,30);
    add(bb);
    bb.addActionListener(this);
    
  }

    public void actionPerformed(ActionEvent e) 
    {
        med=btf1.getText();
        qty=Integer.parseInt(btf2.getText());
        
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/example","root","pass");
            PreparedStatement pstmt=con.prepareStatement("update medicine set quantity=quantity-? where name=?");
            pstmt.setInt(1,qty);
            pstmt.setString(2,med);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Bill generated");
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }
    }
}

class Add extends JPanel implements ActionListener
{
  JLabel al1,al2,al3;
  JTextField atf1,atf2,atf3;
  JButton ab;
  String mname,comp;
  int quan;
  Connection con;
  public Add()
  {
    this.setBackground(Color.PINK);
    this.setLayout(null);

    al1=new JLabel("Enter Medicine");
    al1.setBounds(200,70,100,30);
    add(al1);

    atf1=new JTextField();
    atf1.setBounds(500,70,150,30);
    add(atf1);

    al2=new JLabel("Enter Company Name");
    al2.setBounds(200,150,150,30);
    add(al2);

    atf2=new JTextField();
    atf2.setBounds(500,150,150,30);
    add(atf2);

    al3=new JLabel("Enter Quantity");
    al3.setBounds(200,230,100,30);
    add(al3);

    atf3=new JTextField();
    atf3.setBounds(500,230,150,30);
    add(atf3);

    ab=new JButton("ADD");
    ab.setBounds(310,320,200,30);
    add(ab);
    ab.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) 
  {
      mname=atf1.getText();
      comp=atf2.getText();
      quan=Integer.parseInt(atf3.getText());
      
      try
      {
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost:3306/example","root","pass");
        PreparedStatement pstmt=con.prepareStatement("insert into medicine(name,company,quantity) values(?,?,?)");
        pstmt.setString(1,mname);
        pstmt.setString(2, comp);
        pstmt.setInt(3,quan);
        pstmt.executeUpdate();
        JOptionPane.showMessageDialog(null,"New stock inserted successfully");
        con.close();
      }
      catch(Exception ex)
      {
        System.out.println(ex);
      }
  }

}

class Update extends JPanel implements ActionListener
{
  JLabel ul1,ul2;
  JTextField utf1,utf2;
  JButton ub;
  Connection con;
  String med;
  int qty;
  public Update()
  {
    this.setBackground(Color.PINK);
    this.setLayout(null);

    ul1=new JLabel("Enter Medicine to Update");
    ul1.setBounds(200,100,150,30);
    add(ul1);

    utf1=new JTextField();
    utf1.setBounds(500,100,150,30);
    add(utf1);

    ul2=new JLabel("Enter Quantity to Update");
    ul2.setBounds(200,200,150,30);
    add(ul2);

    utf2=new JTextField();
    utf2.setBounds(500,200,150,30);
    add(utf2);

    ub=new JButton("UPDATE");
    ub.setBounds(310,300,200,30);
    add(ub);
    ub.addActionListener(this);
  }

  public void actionPerformed(ActionEvent e) 
  {
    med=utf1.getText();
    qty=Integer.parseInt(utf2.getText());
    try 
    {
       Class.forName("com.mysql.jdbc.Driver");
       con=DriverManager.getConnection("jdbc:mysql://localhost:3306/example","root","pass");
       PreparedStatement pstmt=con.prepareStatement("update medicine set quantity=quantity+? where name=?");
       pstmt.setInt(1, qty);
       pstmt.setString(2, med);
       pstmt.executeUpdate();
       JOptionPane.showMessageDialog(null,"Stock Updated Successfully");
    } 
    catch (Exception ex) 
    {
        System.out.println("ex");
    }
  }
}

class Remove extends JPanel implements ActionListener
{
  JLabel rl1;
  JTextField rtf1;
  JButton rb;
  Connection con;
  String med;
  public Remove()
  {
    this.setBackground(Color.PINK);
    this.setLayout(null);

    rl1=new JLabel("Enter Medicine to Remove Stock");
    rl1.setBounds(180,120,200,30);
    add(rl1);

    rtf1=new JTextField();
    rtf1.setBounds(500,120,150,30);
    add(rtf1);

    rb=new JButton("REMOVE");
    rb.setBounds(320,220,200,30);
    add(rb);
    rb.addActionListener(this);
  }

    public void actionPerformed(ActionEvent e) 
    {
       med=rtf1.getText();
        try 
        {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/example","root","pass");
            PreparedStatement pstmt=con.prepareStatement("delete from medicine where name=?");
            pstmt.setString(1, med);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null,"Stock removed successfully");
        } 
        catch (Exception ex) 
        {
            System.out.println("ex");
        }
    }
}

class Log extends JPanel implements ActionListener
{
  JButton lb1,lb2;
  JTextArea ta;
  Connection con;
        
  public Log()
  {
    this.setBackground(Color.PINK);
    this.setLayout(null);

    lb1=new JButton("SHOW STOCK");
    lb1.setBounds(180,120,200,30);
    add(lb1);
    lb1.addActionListener(this);

    lb2=new JButton("LOGOUT");
    lb2.setBounds(500,120,200,30);
    add(lb2);
    lb2.addActionListener(this);
    
    ta=new JTextArea(20,10);
    ta.setBounds(50, 240, 800, 150);
    add(ta);
    ta.setBackground(Color.PINK);
  }

  public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==lb1)
    {
      try 
      {
          Class.forName("com.mysql.jdbc.Driver");
          con=DriverManager.getConnection("jdbc:mysql://localhost:3306/example","root","pass");            
          Statement stmt=con.createStatement();
          ResultSet rs=stmt.executeQuery("select * from medicine");
          while(rs.next())
          ta.append(rs.getInt(1)+"                     "+rs.getString(2)+"                  "
                  + " "+rs.getString(3)+"                 "+rs.getInt(4)+"\n");
          con.close();
            
      } 
      catch (Exception ex)
      {
          System.out.println(ex);
      }  
    }
    if (e.getSource()==lb2)
    {
        System.exit(0);
    }
  }
}