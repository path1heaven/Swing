/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Manage.java
 *
 * 
 */
package hotel;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
/**
 *
 * @author shreee
 */
public class Manage extends javax.swing.JInternalFrame implements ActionListener{

    /** Creates new form Manage */
    public Manage() {
        super("Manage",false,true,false,true);
        initComponents();
        mypanel.setLayout(new FlowLayout());
        PasswordControl pc = new PasswordControl();
        setFrame(pc);
        btnManageItem.addActionListener(this);
        btnManageItemCategory.addActionListener(this);
        btnManagePassword.addActionListener(this);
        btnManageRoom.addActionListener(this);
        btnManageRoomCategory.addActionListener(this);
        btnExit.addActionListener(this);
        setVisible(true);
        setSize(980,700);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnManagePassword = new javax.swing.JButton();
        btnManageItem = new javax.swing.JButton();
        btnManageRoom = new javax.swing.JButton();
        btnManageRoomCategory = new javax.swing.JButton();
        btnManageItemCategory = new javax.swing.JButton();
        mypanel = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();

        btnManagePassword.setText("Manage Password");

        btnManageItem.setText("Manage Item");

        btnManageRoom.setText("Manage Room");

        btnManageRoomCategory.setText("Manage Room Category");

        btnManageItemCategory.setText("Manage Item Category");

        javax.swing.GroupLayout mypanelLayout = new javax.swing.GroupLayout(mypanel);
        mypanel.setLayout(mypanelLayout);
        mypanelLayout.setHorizontalGroup(
            mypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 577, Short.MAX_VALUE)
        );
        mypanelLayout.setVerticalGroup(
            mypanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 459, Short.MAX_VALUE)
        );

        btnExit.setText("Exit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManageItemCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManageRoomCategory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManageItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManageRoom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnManagePassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addComponent(mypanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(btnManagePassword)
                        .addGap(18, 18, 18)
                        .addComponent(btnManageRoom)
                        .addGap(18, 18, 18)
                        .addComponent(btnManageItem)
                        .addGap(18, 18, 18)
                        .addComponent(btnManageRoomCategory)
                        .addGap(18, 18, 18)
                        .addComponent(btnManageItemCategory)
                        .addGap(18, 18, 18)
                        .addComponent(btnExit))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(mypanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnManageItem, btnManageItemCategory, btnManagePassword, btnManageRoom, btnManageRoomCategory});

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnManageItem;
    private javax.swing.JButton btnManageItemCategory;
    private javax.swing.JButton btnManagePassword;
    private javax.swing.JButton btnManageRoom;
    private javax.swing.JButton btnManageRoomCategory;
    private javax.swing.JPanel mypanel;
    // End of variables declaration//GEN-END:variables

    private void setFrame(JPanel frame) {
        //remove all components in panel.
        mypanel.removeAll(); 
        // refresh the panel.
        mypanel.updateUI();
        mypanel.add(frame,FlowLayout.LEFT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == btnManagePassword)
        {
            PasswordControl pc = new PasswordControl();
            setFrame(pc);
        }
        else if (src == btnManageItem)
        {
            ManageItem man_item = new ManageItem();
            setFrame(man_item);
        }
        else if (src == btnManageRoom)
        {
            ManageRoom man_room = new ManageRoom();
            setFrame(man_room);
        }
        else if (src == btnManageItemCategory)
        {
            ManageItemCategory man_itemcat = new ManageItemCategory();
            setFrame(man_itemcat);
        }
        else if (src == btnManageRoomCategory)
        {
            ManageRoomCategory man_roomcat = new ManageRoomCategory();
            setFrame(man_roomcat);
        }
        else if (src == btnExit)
        {
            this.dispose();
        }
    }
}
