/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minhthang.ui;

import com.minhthang.DAO.RightBlockDAO;
import com.minhthang.DAO.MidBlockDAO;
import com.minhthang.DAO.LeftBlockDAO;
import com.sun.javafx.animation.TickCalculation;
import java.awt.Choice;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.StyleConstants;

/**
 *
 * @author Minh Thắng
 */
public class BookingSeat extends javax.swing.JDialog implements ActionListener {

    LeftBlockDAO leftBlockDAO = new LeftBlockDAO();
    MidBlockDAO midBlockDAO = new MidBlockDAO();
    RightBlockDAO rightBlockDAO = new RightBlockDAO();

    JTextField seat, totalcost;
    Choice type, block;
    JLabel typelbl, blocklbl, lblbl, mblbl, rblbl, inputseatlbl, totallbl, title;
    JButton submitbut, showbut, paybut;
    String total, selectedseat, selectedblock, tickettype;
    int convertedseat1, convertedseat2, totalprice;
    int totaltickets = 0, currenti;

    Seat leftside[][] = new Seat[4][4];
    Seat midside[][] = new Seat[4][4];
    Seat rightside[][] = new Seat[4][4];

    // Mang luu ve da dat
    Ticket tickets[] = new Ticket[30];

    public BookingSeat() throws SQLException {
        setSize(800, 500);
        setLocation(200, 200);
        setResizable(false);
        setLayout(new FlowLayout());
        ImageIcon imgtitle = new ImageIcon("key.png");
        title = new JLabel(imgtitle);
        typelbl = new JLabel("Ticket Type: ");
        typelbl.setFont(new Font("Verdana", Font.BOLD, 22));
        type = new Choice();
        type.add("Adult");
        type.add("Child");
        type.add("Old");
        blocklbl = new JLabel("Block: ");
        blocklbl.setFont(new Font("Verdana", Font.BOLD, 22));
        block = new Choice();
        block.add("Left Block");
        block.add("Middle Block");
        block.add("Right Block");

        paybut = new JButton("Pay");
        paybut.setBackground(Color.white);
        inputseatlbl = new JLabel("Enter Seat Num: ");
        inputseatlbl.setFont(new Font("Verdana", Font.BOLD, 22));
        seat = new JTextField(2);
        seat.setFont(new Font("Verdana", Font.BOLD, 20));
        seat.setHorizontalAlignment(seat.CENTER);
        totallbl = new JLabel("Total Price: ");
        totallbl.setFont(new Font("Verdana", Font.BOLD, 22));
        totalcost = new JTextField(14);
        totalcost.setFont(new Font("Verdana", Font.BOLD, 22));
        totalcost.setHorizontalAlignment(totalcost.CENTER);
        totalcost.setEditable(false);
        submitbut = new JButton("Booking");
        submitbut.setBackground(Color.white);
        showbut = new JButton("Show");
        showbut.setBackground(Color.white);

        // khoi tao bien cho vi tri ca khoi
        int xleft = 0;
        int yleft = 0;
        int xmid = 0;
        int ymid = 0;
        int xright = 0;
        int yright = 0;
        // vi tri ban dau
        xleft = 50;
        yleft = 310;
        int totalleftside = 0;

        for (int i = 0; i < leftside.length; i++) {
            for (int j = 0; j < 4; j++) {

                leftside[i][j] = new Seat(totalleftside, leftBlockDAO.getStatus(i, j), xleft, yleft);
                xleft += 50;
                totalleftside++;
            }
            xleft = 50;
            yleft += 30;
        }
        xmid = 300;
        ymid = 310; // vi tri ban dau
        int totalmidside = 0; // tong so ghe
        for (int i = 0; i < midside.length; i++) {
            for (int j = 0; j < 4; j++) {
                midside[i][j] = new Seat(totalmidside, midBlockDAO.getStatus(i, j), xmid, ymid);
                xmid += 50; // Di chuyen ghe tiep theo doc theo vi tri x xuong 50
                totalmidside++; // tomg so ghe++
            }
            xmid = 300; // Di chuyen ve ben trai cho hang tiep theo
            ymid += 30;  // Di chuyen xuong cho hang ke tiep
        }
        xright = 550;
        yright = 310; // Vi tri ban dau
        int totalrightside = 0; // Tong so ghe
        for (int i = 0; i < rightside.length; i++) {
            for (int j = 0; j < 4; j++) {
                rightside[i][j] = new Seat(totalrightside, rightBlockDAO.getStatus(i, j), xright, yright);
                xright += 50; // Di chuyen ghe tiep theo doc theo vi tri x xuong 50
                totalrightside++; // Tong so ghe ++
            }
            xright = 550; // Di chuyen ve ben trai cho hang tiep theo
            yright += 30; // Di chuyen xuong hang ke tiep
        }

        // them label va textbox vao man hinh
        getContentPane().add(title);
        getContentPane().add(typelbl);
        getContentPane().add(type);
        getContentPane().add(blocklbl);
        getContentPane().add(block);
        getContentPane().add(showbut);
        getContentPane().add(inputseatlbl);
        getContentPane().add(seat);
        getContentPane().add(submitbut);
        getContentPane().add(totallbl);
        getContentPane().add(totalcost);
        getContentPane().add(paybut);
        setVisible(true);
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        Color mycolor = new Color(240, 240, 240);
        getContentPane().setBackground(mycolor);

        submitbut.addActionListener(this);
        showbut.addActionListener(this);
        paybut.addActionListener(this);
    }

    public void paint(Graphics graf) {
        super.paint(graf);

        for (int i = 0; i < leftside.length; i++) {
            for (int j = 0; j < 4; j++) {
                leftside[i][j].display(graf);
                midside[i][j].display(graf);
                rightside[i][j].display(graf);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        selectedblock = block.getSelectedItem();
        tickettype = type.getSelectedItem();

        if (ae.getSource() == submitbut) {
            selectedseat = seat.getText();
            int selectseat = Integer.parseInt(selectedseat);
            // chuyen sang tao do x y
            if (selectseat == 0) {
                convertedseat1 = 0;
                convertedseat2 = 0;
            }
            if (selectseat == 1) {
                convertedseat1 = 0;
                convertedseat2 = 1;
            }
            if (selectseat == 2) {
                convertedseat1 = 0;
                convertedseat2 = 2;
            }
            if (selectseat == 3) {
                convertedseat1 = 0;
                convertedseat2 = 3;
            }
            if (selectseat == 4) {
                convertedseat1 = 1;
                convertedseat2 = 0;
            }
            if (selectseat == 5) {
                convertedseat1 = 1;
                convertedseat2 = 1;
            }
            if (selectseat == 6) {
                convertedseat1 = 1;
                convertedseat2 = 2;
            }
            if (selectseat == 7) {
                convertedseat1 = 1;
                convertedseat2 = 3;
            }
            if (selectseat == 8) {
                convertedseat1 = 2;
                convertedseat2 = 0;
            }
            if (selectseat == 9) {
                convertedseat1 = 2;
                convertedseat2 = 1;
            }
            if (selectseat == 10) {
                convertedseat1 = 2;
                convertedseat2 = 2;
            }
            if (selectseat == 11) {
                convertedseat1 = 2;
                convertedseat2 = 3;
            }
            if (selectseat == 12) {
                convertedseat1 = 3;
                convertedseat2 = 0;
            }
            if (selectseat == 13) {
                convertedseat1 = 3;
                convertedseat2 = 1;
            }
            if (selectseat == 14) {
                convertedseat1 = 3;
                convertedseat2 = 2;
            }
            if (selectseat == 15) {
                convertedseat1 = 3;
                convertedseat2 = 3;
            }
            if (selectedblock == "Left Block") {
                int iftaken = leftside[convertedseat1][convertedseat2].isTaken();
                if (iftaken == 1) {
                    JOptionPane.showMessageDialog(null, "Ghe da duoc dat");

                } else if (iftaken == 0) {
                    leftside[convertedseat1][convertedseat2].setSeat();
                   
                    
                    try {
                        leftBlockDAO.setStatus(selectseat);
                    } catch (SQLException ex) {
                        Logger.getLogger(BookingSeat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    repaint();
                    tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock);
                    int tempprice = tickets[totaltickets].getSeatPrice();
                    totalprice = totalprice + tempprice;
                    totalcost.setText(Integer.toString(totalprice) + "ngan dong");
                    totaltickets += 1;
                    seat.setText("");

                }
            }
            if (selectedblock == "Middle Block") // Dat cho khoi giua
            {
                int iftaken = midside[convertedseat1][convertedseat2].isTaken();
                if (iftaken == 1) {
                    JOptionPane.showMessageDialog(null, "Ghe da duoc dat");
                } else if (iftaken == 0) {
                    midside[convertedseat1][convertedseat2].setSeat();
                    try {
                        midBlockDAO.setStatus(selectseat);
                    } catch (SQLException ex) {
                        Logger.getLogger(BookingSeat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    repaint();
                    tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock);
                    int tempprice = tickets[totaltickets].getSeatPrice();
                    totalprice = totalprice + tempprice;
                    totalcost.setText(Integer.toString(totalprice) + "ngan dong");
                    totaltickets += 1;
                    seat.setText("");
                }
            }
            if (selectedblock == "Right Block") // Dat cho khoi phat
            {
                int iftaken = rightside[convertedseat1][convertedseat2].isTaken();
                if (iftaken == 1) {
                    JOptionPane.showMessageDialog(null, "Ghe da duoc dat");
                } else if (iftaken == 0) {
                    rightside[convertedseat1][convertedseat2].setSeat();
                    try {
                        rightBlockDAO.setStatus(selectseat);
                    } catch (SQLException ex) {
                        Logger.getLogger(BookingSeat.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    repaint();
                    tickets[totaltickets] = new Ticket(tickettype, selectseat, selectedblock);
                    int tempprice = tickets[totaltickets].getSeatPrice();
                    totalprice = totalprice + tempprice;
                    totalcost.setText(Integer.toString(totalprice) + "ngan dong");
                    totaltickets += 1;
                    seat.setText("");
                }
            }

        }
        // Show
        if (ae.getSource() == showbut) {
            repaint();
        }
        // Pay
        if (ae.getSource() == paybut) {
            try {
                FileWriter stream = new FileWriter("tickets.txt");
                BufferedWriter output = new BufferedWriter(stream);

                for (int i = 0; i < totaltickets; i++) {
                    int ticketnum = tickets[i].getSeatNum();
                    int ticketprice = tickets[i].getSeatPrice();
                    String tickettype = tickets[i].getType();
                    String ticketblock = tickets[i].getBlock();
                    String newline = System.getProperty("line.separator");

                    output.write(newline);
                    output.write("Ticket Details");
                    output.write(newline);
                    output.write(newline);
                    output.write("Ticket Number: " + ticketnum);
                    output.write(newline);
                    output.write("Ticket Price: " + ticketprice);
                    output.write(newline);
                    output.write("Ticket Type: " + tickettype);
                    output.write(newline);
                    output.write("Ticket Block: " + ticketblock);
                    output.write(newline);
                    output.write("____________________________________________________________________________________");
                    output.write(newline);

                }
                output.close();
            } catch (IOException ex) {
                System.err.println("Error: " + ex.getMessage());
            }
            JOptionPane.showMessageDialog(null, " Ticket da duoc in ra file 'tickets.txt'"); //Success message

            for (int i = 0; i < tickets.length; i++) {
                tickets[i] = null; // reset lai mang de dat ve moi
            }
            totaltickets = 0; // Dat lai Tong so ve
            totalprice = 0; // Dat lai tong gia
            totalcost.setText("");
            seat.setText("");

        }

    }

    public static void main(String args[]) throws SQLException {
        new BookingSeat();
    }
}

class Seat {

    private final int boxheight = 30;// chieu cao hop van ban
    private final int boxwidth = 50; // chieu rong hop van ban
    private int seatnumber;
    private int seattaken;
    private int x;
    private int y;

    public Seat(int number, int taken, int xstart, int ystart) {
        seatnumber = number;
        seattaken = taken;
        x = xstart;
        y = ystart;

    }

    public void display(Graphics graf) {
        int xdraw, ydraw;
        switch (seattaken) {
            case 0:
                xdraw = x + boxwidth;
                ydraw = y + boxheight;
                graf.setColor(Color.blue);
                graf.drawRect(x, y, boxwidth, boxheight);
                graf.drawString(Integer.toString(seatnumber), x + 20, y + boxheight * 3 / 4);
                break;
            case 1:
                xdraw = x + boxwidth;
                ydraw = y + boxheight;
                graf.setColor(Color.red);
                graf.drawRect(x, y, boxwidth, boxheight);
                String msg = "Book";
                graf.drawString(msg, x + 20, y + boxheight * 3 / 4); // To mau ghe bang Book duoc in ben trong
                
                break;
            default:
                break;

        }

    }

    public int isTaken() {
        return seattaken;
    }

    public void setSeat() {
        seattaken = 1;
    }

 
}

class Ticket {

    private int price;
    private String tickettype;
    private int seatnum;
    private String blockticket;

    public Ticket(String type, int seatnumber, String block) {
        seatnum = seatnumber;
        tickettype = type;
        blockticket = block;

        if (blockticket == "Left Block") {
            if (type == "Adult") {
                price = 100;
            }
            if (type == "Old") {
                price = 70;
            }
            if (type == "Child") {
                price = 50;
            }
        }
        if (blockticket == "Middle Block") {
            if (type == "Adult") {
                price = 150;
            }
            if (type == "Old") {
                price = 100;
            }
            if (type == "Child") {
                price = 70;
            }
        }
        if (blockticket == "Right Block") {
            if (type == "Adult") {
                price = 100;
            }
            if (type == "Old") {
                price = 70;
            }
            if (type == "Child") {
                price = 50;
            }
        }

    }

    public int getSeatPrice() {
        return price;
    }

    public String getBlock() {
        return blockticket;
    }

    public String getType() {
        return tickettype;
    }

    public int getSeatNum() {
        return seatnum;
    }

}
