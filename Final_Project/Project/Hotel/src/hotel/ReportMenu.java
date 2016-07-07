/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ReportMenu.java
 *
 * 
 */
package hotel;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author shreee
 */
public class ReportMenu extends javax.swing.JInternalFrame implements ActionListener{

    /** Creates new form ReportMenu */
    public ReportMenu() {
        super("Generate Report",false,true,false,true);
        initComponents();
        btnByDate.addActionListener(this);
        btnByMonth.addActionListener(this);
        btnByYear.addActionListener(this);
        setLocation(Toolkit.getDefaultToolkit().getScreenSize().width/2 - 180,Toolkit.getDefaultToolkit().getScreenSize().height/2 -155);
        setVisible(true);
        setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        yearpicker = new com.toedter.calendar.JYearChooser();
        datepicker = new com.toedter.calendar.JDateChooser();
        btnByDate = new javax.swing.JButton();
        cmbReportType = new javax.swing.JComboBox();
        btnByMonth = new javax.swing.JButton();
        btnByYear = new javax.swing.JButton();
        cmbMonth = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();

        btnByDate.setText("By Date");
        btnByDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnByDateActionPerformed(evt);
            }
        });

        cmbReportType.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rooms", "Foods and Beverages" }));

        btnByMonth.setText("By Month");

        btnByYear.setText("By Year");

        cmbMonth.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jan", "Feb", "Mar", "Apr", "May", "June", "July", "Aug", "Sept", "Oct", "Nov", "Dec" }));
        cmbMonth.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Miriam Fixed", 1, 14)); // NOI18N
        jLabel1.setText("Report Type");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnByMonth)
                    .addComponent(btnByDate)
                    .addComponent(btnByYear)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepicker, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbReportType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(yearpicker, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbMonth, javax.swing.GroupLayout.Alignment.LEADING, 0, 74, Short.MAX_VALUE)))
                .addGap(47, 47, 47))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnByDate, btnByMonth, btnByYear});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbReportType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(datepicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cmbMonth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(yearpicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnByDate)
                        .addGap(18, 18, 18)
                        .addComponent(btnByMonth)
                        .addGap(18, 18, 18)
                        .addComponent(btnByYear)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnByDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnByDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnByDateActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnByDate;
    private javax.swing.JButton btnByMonth;
    private javax.swing.JButton btnByYear;
    private javax.swing.JComboBox cmbMonth;
    private javax.swing.JComboBox cmbReportType;
    private com.toedter.calendar.JDateChooser datepicker;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private com.toedter.calendar.JYearChooser yearpicker;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        Report r = new Report();
        if (src == btnByDate)
        {
            if (datepicker.getDate() == null)
            {
                JOptionPane.showMessageDialog(null, "Please select date first");
                return;
            }
            if (cmbReportType.getSelectedIndex() == 0)
            {
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sf.format(datepicker.getDate());
                int total = 0;
                try {
                    Statement stat = DbConnection.getConnection().createStatement();
                    ResultSet rs = stat.executeQuery("select sum(total_room_cost) from reservation_history where DATE(check_in) ='"+date+"'");
                    while (rs.next())
                    {
                            total = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ReportMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                r.generateDailyReportForRooms(date,total);
            }
            else if (cmbReportType.getSelectedIndex() == 1)
            {
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sf.format(datepicker.getDate());
                r.generateDailyFoodsReport(date);
            }
        }
        else if (src == btnByMonth)
        {
            if (cmbReportType.getSelectedIndex() == 0)
            {
                int total = 0;
                try {
                    Statement stat = DbConnection.getConnection().createStatement();
                    ResultSet rs = stat.executeQuery("select sum(total_room_cost) from reservation_history where MONTH(check_in) ="+(cmbMonth.getSelectedIndex()+1));
                    while (rs.next())
                    {
                            total = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ReportMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                r.generateMonthlyReportForRooms(cmbMonth.getSelectedIndex()+1,(String)cmbMonth.getSelectedItem(),total);
            }
            else if (cmbReportType.getSelectedIndex() == 1)
            {
                r.generateMonthlyFoodsReport(cmbMonth.getSelectedIndex()+1,(String)cmbMonth.getSelectedItem());
            }
        }
        else if (src == btnByYear)
        {
            if (cmbReportType.getSelectedIndex() == 0)
            {
                int year = yearpicker.getYear();
                int total = 0;
                try {
                    Statement stat = DbConnection.getConnection().createStatement();
                    ResultSet rs = stat.executeQuery("select sum(total_room_cost) from reservation_history where YEAR(check_in) ="+year);
                    while (rs.next())
                    {
                            total = rs.getInt(1);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(ReportMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
                r.generateYearlyReportForRooms(year,total);
            }
            else if (cmbReportType.getSelectedIndex() == 1)
            {
                int year = yearpicker.getYear();
                r.generateYearlyFoodsReport(year);
            }
        }
    }
}
