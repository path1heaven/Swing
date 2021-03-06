/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Order.java
 *
 * 
 */
package hotel;

import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author shreee
 */
public class Order extends javax.swing.JInternalFrame implements ActionListener, ItemListener {

    DefaultTableModel dm;
    TableProperties props;
    Connection conn;

    public Order() {
        super("Order", false, true, false, true);
        dm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                //Only the third column
                return false;
            }
        };
        initComponents();
        props = new TableProperties(tblOrder, dm);
        String[] cols = {"Order ID", "Item Code", "Name", "Quantity"};
        props.addColumns(cols);
        props.changeColumnProperties(new int[]{100, 100, 120, 100});
        conn = DbConnection.getConnection();
        loadReservations();
        loadItems();
        cmbReservationID.setSelectedIndex(-1);
        btnAddOrder.addActionListener(this);
        btnCancelOrder.addActionListener(this);
        btnClear.addActionListener(this);
        btnExit.addActionListener(this);
        btnModifyOrder.addActionListener(this);
        cmbCategory.addItemListener(this);
        cmbReservationID.addItemListener(this);
        setVisible(true);
    }

    private void loadReservations() {
        cmbReservationID.removeAllItems();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select reservation_id from reservation_active");
            while (rs.next()) {
                cmbReservationID.addItem(rs.getInt("reservation_id"));
            }
        } catch (Exception e) {
        }
    }

    private void loadItems() {
        cmbCategory.removeAllItems();
        try {
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select category from item_category");
            while (rs.next()) {
                cmbCategory.addItem(rs.getString("category"));
            }
            catChanged((String) cmbCategory.getItemAt(0));
        } catch (Exception e) {
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnExit) {
            this.dispose();
        } else if (src == btnAddOrder) {
            addOrder();
            refreshTable();
        } else if (src == btnClear) {
            cmbCategory.setSelectedIndex(0);
            txtQuantity.setText("");
        } else if (src == btnCancelOrder) {
            if (tblOrder.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Please select order to cancel it");
                return;
            }
            int c = JOptionPane.showConfirmDialog(null, "Are you sure to cancel this order?", "Cancel Order", JOptionPane.YES_NO_OPTION);
            if (c == JOptionPane.NO_OPTION) {
                return;
            }
            int order_id = (Integer) dm.getValueAt(tblOrder.getSelectedRow(), 0);
            PreparedStatement stat;
            try {
                stat = conn.prepareStatement("delete from order_active where order_id = ?");
                stat.setInt(1, order_id);
                int s = stat.executeUpdate();
                if (s >= 1) {
                    JOptionPane.showMessageDialog(null, "Order cancelled successfully");
                    MyLogger.writeLog("order cancelled", "Reservation ID : " + cmbReservationID.getSelectedItem() + " Item : " + dm.getValueAt(tblOrder.getSelectedRow(), 1) + " Quantity : " + dm.getValueAt(tblOrder.getSelectedRow(), 3));
                    refreshTable();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else if (src == btnModifyOrder) {
            if (tblOrder.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Please select order to modify it");
                return;
            }
            String item_name = (String) dm.getValueAt(tblOrder.getSelectedRow(), 2);
            int item_qty = (Integer) dm.getValueAt(tblOrder.getSelectedRow(), 3);
            String qty = (String) JOptionPane.showInputDialog(null, "Enter Quantity for " + item_name, " Modify Order", JOptionPane.PLAIN_MESSAGE, null, null, String.valueOf(item_qty));
            if (qty.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter quantity first");
                txtQuantity.requestFocus();
                return;
            }
            try {
                int q = Integer.parseInt(qty);
                if (q < 1 || q > 999) {
                    JOptionPane.showMessageDialog(null, "Invalid number");
                    txtQuantity.requestFocus();
                    return;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid format for quantity");
                return;
            }
            PreparedStatement stat;
            try {
                stat = conn.prepareStatement("update order_active set quantity = ? where order_id = ?");
                stat.setInt(1, Integer.parseInt(qty));
                stat.setInt(2, (Integer) dm.getValueAt(tblOrder.getSelectedRow(), 0));
                int re = stat.executeUpdate();
                if (re >= 1) {
                    JOptionPane.showMessageDialog(null, "order successfully modified");
                    MyLogger.writeLog("order modified", "Reservation ID : " + cmbReservationID.getSelectedItem() + " Item : " + dm.getValueAt(tblOrder.getSelectedRow(), 1) + " Quantity : " + dm.getValueAt(tblOrder.getSelectedRow(), 3) + " modified to : " + qty);
                    refreshTable();
                }
            } catch (SQLException ex) {
                Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getSource() == cmbReservationID) {
            dm.setRowCount(0);
            if (e.getStateChange() == ItemEvent.SELECTED) {
                refreshTable();
            }
        } else if (e.getSource() == cmbCategory) {
            cmbName.removeAllItems();
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String cat = (String) cmbCategory.getSelectedItem();
                catChanged(cat);
            }
        }
    }

    private void catChanged(String cat) {
        System.out.println(cat);
        PreparedStatement stat;
        try {
            stat = conn.prepareStatement("select name from item where cat_id=?");
            stat.setInt(1, new Item().getCategoryID(cat));
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                cmbName.addItem(rs.getString("name"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addOrder() {
        if (cmbReservationID.getSelectedIndex() == -1) {
            JOptionPane.showMessageDialog(null, "Please select Reservation ID");
            cmbReservationID.requestFocus();
            return;
        }
        if (txtQuantity.getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Please enter quantity first");
            txtQuantity.requestFocus();
            return;
        }
        try {
            int q = Integer.parseInt(txtQuantity.getText());
            if (q < 1 || q > 999) {
                JOptionPane.showMessageDialog(null, "Invalid number");
                txtQuantity.requestFocus();
                return;
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Invalid format for quantity");
            txtQuantity.requestFocus();
            return;
        }
        int item_code = new Item().getItemCode((String) cmbCategory.getSelectedItem(), (String) cmbName.getSelectedItem());
        if (item_code == -1) {
            return;
        }
        PreparedStatement stat;
        try {
            stat = conn.prepareStatement("update order_active set quantity = (quantity+?) where item_code = ? and reservation_id = ?");
            stat.setInt(1, Integer.parseInt(txtQuantity.getText()));
            stat.setInt(2, item_code);
            stat.setInt(3, (Integer) cmbReservationID.getSelectedItem());
            int r = stat.executeUpdate();
            if (r >= 1) {
                JOptionPane.showMessageDialog(null, "order successfully added");
                MyLogger.writeLog("order added", "Reservation ID : " + cmbReservationID.getSelectedItem() + " Item : " + cmbName.getSelectedItem() + " Quantity : " + txtQuantity.getText());
                txtQuantity.setText("");
            } else {
                stat = conn.prepareStatement("insert into order_active(item_code,quantity,reservation_id) values(?,?,?)");
                stat.setInt(1, item_code);
                stat.setInt(2, Integer.parseInt(txtQuantity.getText()));
                stat.setInt(3, (Integer) cmbReservationID.getSelectedItem());

                int re = stat.executeUpdate();
                if (re >= 1) {
                    JOptionPane.showMessageDialog(null, "order successfully added");
                    MyLogger.writeLog("order added", "Reservation ID : " + cmbReservationID.getSelectedItem() + " Item : " + cmbName.getSelectedItem() + " Quantity : " + txtQuantity.getText());
                    txtQuantity.setText("");
                }
            }
            refreshTable();
        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void refreshTable() {
        dm.setRowCount(0);
        if (cmbReservationID.getSelectedIndex() == -1) {
            return;
        }
        PreparedStatement stat;
        try {
            int res_id = (Integer) cmbReservationID.getSelectedItem();
            stat = conn.prepareStatement("select * from order_active,item where reservation_id=? and order_active.item_code = item.item_code order by order_active.order_id");
            stat.setInt(1, res_id);
            ResultSet rs = stat.executeQuery();
            while (rs.next()) {
                dm.addRow(new Object[]{rs.getInt("order_id"), rs.getInt("item_code"), rs.getString("name"), rs.getInt("quantity")});
            }

        } catch (SQLException ex) {
            Logger.getLogger(Order.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        tblOrder = new javax.swing.JTable(dm);
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtQuantity = new javax.swing.JTextField();
        cmbCategory = new javax.swing.JComboBox();
        cmbName = new javax.swing.JComboBox();
        cmbReservationID = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        btnAddOrder = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnCancelOrder = new javax.swing.JButton();
        btnModifyOrder = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();

        jScrollPane1.setViewportView(tblOrder);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Order Details"));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16));
        jLabel2.setText("Category");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16));
        jLabel3.setText("Name");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 16));
        jLabel4.setText("Quantity");

        txtQuantity.setColumns(10);
        txtQuantity.setFont(new java.awt.Font("Tahoma", 0, 14));

        cmbCategory.setFont(new java.awt.Font("Tahoma", 0, 14));

        cmbName.setFont(new java.awt.Font("Tahoma", 0, 14));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbCategory, 0, 149, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbName, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cmbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(cmbCategory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );

        cmbReservationID.setFont(new java.awt.Font("Tahoma", 0, 14));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16));
        jLabel1.setText("Reservation ID");

        btnAddOrder.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnAddOrder.setText("Add Order");

        btnClear.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnClear.setText("Clear");

        btnCancelOrder.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnCancelOrder.setText("Cancel Order");

        btnModifyOrder.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnModifyOrder.setText("Modify Order");

        btnExit.setFont(new java.awt.Font("Tahoma", 0, 14));
        btnExit.setText("Exit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(274, 274, 274)
                .addComponent(btnCancelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnModifyOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(267, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(29, 29, 29)
                        .addComponent(cmbReservationID, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnAddOrder)))))
                .addGap(53, 53, 53))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cmbReservationID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(btnAddOrder)
                        .addGap(15, 15, 15)
                        .addComponent(btnClear)))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelOrder)
                    .addComponent(btnModifyOrder)
                    .addComponent(btnExit))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddOrder;
    private javax.swing.JButton btnCancelOrder;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnModifyOrder;
    private javax.swing.JComboBox cmbCategory;
    private javax.swing.JComboBox cmbName;
    private javax.swing.JComboBox cmbReservationID;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblOrder;
    private javax.swing.JTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}
