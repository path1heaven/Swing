/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HistoryByDate.java
 *
 * 
 */
package hotel;

import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener;
import java.util.Date; 
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author shreee
 */
public class HistoryByDate extends javax.swing.JPanel implements ActionListener{

    /** Creates new form HistoryByDate */
    DefaultTableModel dm;
    TableProperties props;
    public HistoryByDate() {
        dm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //Only the third column
                return false;
            }
        };
        initComponents();
        datepicker.setMaxSelectableDate(new Date());
        props = new TableProperties(tblHistory, dm);
        String[] cols = {"Res. ID", "Guest ID", "Guest Name", "Rooms", "No. of Guests", "Check in", "Check out", "Total paid"};
        props.addColumns(cols);
        props.changeColumnProperties(new int[]{80, 80, 80, 80, 80, 110, 110, 100});
        createPopupMenu();
        btnView.addActionListener(this);
        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnView = new javax.swing.JButton();
        datepicker = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(1440, 1200));
        setPreferredSize(new java.awt.Dimension(830, 618));

        tblHistory.setModel(dm);
        tblHistory.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(tblHistory);

        btnView.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnView.setText("View");

        datepicker.setDateFormatString("MM-dd-yyyy");
        datepicker.setMinSelectableDate(new java.util.Date(1325358978000L));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16));
        jLabel2.setText("Select Date");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(datepicker, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnView, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                .addComponent(btnView)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(datepicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnView, datepicker, jLabel2});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(196, 196, 196)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnView;
    private com.toedter.calendar.JDateChooser datepicker;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHistory;
    // End of variables declaration//GEN-END:variables


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnView)
        {
            if (datepicker.getDate() == null)
            {
                JOptionPane.showMessageDialog(null,"Please select date first");
            }
            else{
                Date d = datepicker.getDate();
                SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
                String s = sf.format(d);
                viewDetails(s);
            }
        }else if (e.getActionCommand().equals("View Full Details"))
        {
            Report r1 = new Report();
            r1.displayCheckoutReport((Integer)dm.getValueAt(tblHistory.getSelectedRow(), 0));
        }
    }
    
    private void createPopupMenu() {
        JPopupMenu popup = new JPopupMenu();
        JMenuItem myMenuItem1 = new JMenuItem("View Full Details");
        JMenuItem myMenuItem2 = new JMenuItem("Do Nothing");
        myMenuItem1.addActionListener(this);
        myMenuItem2.addActionListener(this);
        popup.add(myMenuItem1);
        popup.add(myMenuItem2);
        MouseListener popupListener = new PopupListener(popup,tblHistory);
        tblHistory.addMouseListener(popupListener);
    }

    private void viewDetails(String date) {
        dm.setRowCount(0);
       Connection conn = DbConnection.getConnection();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from reservation_history r,guest g where g.id = r.guest_id and DATE(r.check_in)<='"+date+"' AND DATE(r.check_out)>='"+date+"' order by r.reservation_id");
            while (rs.next()) {
                    dm.addRow(new Object[]{rs.getInt("reservation_id"),rs.getInt("guest_id"),rs.getString("f_name")+" "+ rs.getString("l_name"),rs.getString("rooms"),
                    rs.getInt("num_of_guests"),rs.getDate("check_in"),rs.getDate("check_out"),rs.getInt("total_paid")});
            }
        } catch (SQLException ex) {
            Logger.getLogger(HistoryAll.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
