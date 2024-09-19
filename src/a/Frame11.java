// Admin Login Form
package a;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class Frame11 extends JFrame implements ActionListener {
private JLabel l1,l2;
private JButton b1;
private Container c;
private JTextField t1;
private JPasswordField p1;
private Font f = new Font("Consolas",Font.BOLD,20);
private int i;    
Frame11(int i){

        setSize(400,400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Admin Login");
        l1 = new JLabel("Username");
        l2 = new JLabel("Password");
        l1.setBounds(30,70,100,50);
        l2.setBounds(30,170,100,50);
        b1 = new JButton("Login");
        b1.setBounds(120,280,150,30);
        t1= new JTextField();
        t1.setBounds(150,70,200,30);
        p1 = new JPasswordField();
        p1.setBounds(150,170,200,30);
        l1.setFont(f);
        t1.setFont(f);
        p1.setFont(f);
        b1.setFont(f);
        l2.setFont(f);
        l1.setForeground(Color.WHITE);
        l2.setForeground(Color.WHITE);
        p1.setForeground(Color.BLACK);
        t1.setForeground(Color.BLACK);
        b1.addActionListener(this);
        
        this.i = i;
        c = this.getContentPane();
        c.setLayout(null);
        c.setBackground(Color.DARK_GRAY);
        c.add(l1);
        c.add(l2);
        c.add(b1);
        c.add(t1);
        c.add(p1);

     this.setVisible(true);

    }



    public void actionPerformed(ActionEvent e) {
       if(e.getSource() == b1){
          Connection c=null;
          PreparedStatement ps;
          ResultSet ss;
          try {
              Class.forName("com.mysql.cj.jdbc.Driver");
              c = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "root");
              String s1 = t1.getText();
              String s2 = p1.getText();
              ps = c.prepareStatement("select * from admin where username=? and pass=?");
              ps.setString(1,s1);
              ps.setString(2,s2);
              ss = ps.executeQuery();
             if(ss.next()){
                 JOptionPane.showMessageDialog(this,"Sucess Login","Message",JOptionPane.PLAIN_MESSAGE);
                 this.dispose();
                 if(this.i == 0){
                 new Frame10();
                 }else
                 {
                 new Admin();
                 }
             }
             else{
                 JOptionPane.showMessageDialog(this,"Wrong Username or Password","Message",JOptionPane.WARNING_MESSAGE);
                 t1.setText(null);
                 p1.setText(null);
             }

          }
          catch(Exception ee){

          }
          finally{
              try{
                  c.close();
              }
              catch(Exception er){}

          }
       }
    }
}

