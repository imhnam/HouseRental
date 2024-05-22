
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author thoai
 */
public class LoginWindow extends javax.swing.JFrame {

    private static boolean isCustomerFrameOpened = false;
    private boolean isErrorMessageDisplayed = false;

    public LoginWindow() {
        initComponents();
        //căn giữa
        this.setLocationRelativeTo(null);
        //hiển thị icon
        jLabel_UserName_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagines/user-icon.png")));
        jLabel_Password_Icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagines/Lock-icon.png")));
        jLabel_Imagine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagines/loginhouserental.jpg")));
        jButton_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoginActionPerformed(evt);
            }
        });

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_Imagine = new javax.swing.JLabel();
        jLabel_Title = new javax.swing.JLabel();
        jLabel_title1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel_UserName_Icon = new javax.swing.JLabel();
        jLabel_Password_Icon = new javax.swing.JLabel();
        jTextField_Username = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton_Login = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel_Imagine.setBackground(new java.awt.Color(204, 255, 255));
        jLabel_Imagine.setText("jLabel1");
        jLabel_Imagine.setOpaque(true);

        jLabel_Title.setFont(new java.awt.Font("Arial", 2, 24)); // NOI18N
        jLabel_Title.setText("BÀI TẬP LỚN OOP");

        jLabel_title1.setFont(new java.awt.Font("Arial", 1, 27)); // NOI18N
        jLabel_title1.setText("Quản Lý Nhà Cho Thuê");

        jLabel2.setBackground(new java.awt.Color(204, 204, 255));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setOpaque(true);

        jLabel_UserName_Icon.setBackground(new java.awt.Color(204, 204, 255));
        jLabel_UserName_Icon.setOpaque(true);

        jLabel_Password_Icon.setBackground(new java.awt.Color(204, 204, 255));
        jLabel_Password_Icon.setOpaque(true);

        jTextField_Username.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_Username.setText("username");
        jTextField_Username.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextField_UsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextField_UsernameFocusLost(evt);
            }
        });

        jPasswordField1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(204, 204, 204));
        jPasswordField1.setText("password");
        jPasswordField1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jPasswordField1FocusLost(evt);
            }
        });

        jButton_Login.setBackground(new java.awt.Color(0, 51, 255));
        jButton_Login.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jButton_Login.setForeground(new java.awt.Color(255, 255, 255));
        jButton_Login.setText("Đăng Nhập");
        jButton_Login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton_Login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_LoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel_Imagine, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabel_title1)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton_Login, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_UserName_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_Username))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_Password_Icon, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPasswordField1))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel_Title, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel_Imagine, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel_Title, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(jLabel_title1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_UserName_Icon, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField_Username))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_Password_Icon, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                    .addComponent(jPasswordField1))
                .addGap(34, 34, 34)
                .addComponent(jButton_Login, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_UsernameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_UsernameFocusGained
        //xóa username ở tk
        //nếu tk == "username"
        if (jTextField_Username.getText().trim().equals("username")) {
            jTextField_Username.setText("");
            //thay thành màu đen
            jTextField_Username.setForeground(Color.black);
        }
    }//GEN-LAST:event_jTextField_UsernameFocusGained

    private void jTextField_UsernameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextField_UsernameFocusLost
        //nếu tk rỗng -> đặt "username"
        if (jTextField_Username.getText().trim().equals("")
                || jTextField_Username.getText().trim().toLowerCase().equals("username")) {
            jTextField_Username.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_jTextField_UsernameFocusLost

    private void jPasswordField1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusGained
        //xóa password nếu pass  == "password"
        //đặt phông pass
        String password = String.valueOf(jPasswordField1.getPassword());
        if (password.trim().toLowerCase().equals("password")) {
            jPasswordField1.setText("");
            jPasswordField1.setForeground(Color.BLACK);
        }    }//GEN-LAST:event_jPasswordField1FocusGained

    private void jPasswordField1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jPasswordField1FocusLost
        //nếu pass rỗng -> đặt thành  "password"
        String password = String.valueOf(jPasswordField1.getPassword());
        if (password.trim().equals("") || password.trim().toLowerCase().equals("password")) {
            jPasswordField1.setText("password");
            jPasswordField1.setForeground(new Color(204, 204, 204));
        }
    }//GEN-LAST:event_jPasswordField1FocusLost

    private void jButton_LoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_LoginActionPerformed
        //Đăng nhập nếu đúng tài khoản: admin, mật khẩu: admin
        String username = jTextField_Username.getText();
        String password = String.valueOf(jPasswordField1.getPassword());

        if (username.equals("admin") && password.equals("admin")) {
            if (!isCustomerFrameOpened) {
                // Mở giao diện chính của ứng dụng
                JFrameCustomer customerFrame = new JFrameCustomer();
                customerFrame.setVisible(true);

                // Đặt giá trị biến là true để chỉ mở một lần
                isCustomerFrameOpened = true;

                // Đóng cửa sổ đăng nhập
                this.dispose();
            }
        } else {
            // Kiểm tra xem thông báo đã được hiển thị hay chưa
            if (!isErrorMessageDisplayed) {
                // Hiển thị thông báo đăng nhập thất bại
                JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không đúng!");
                isErrorMessageDisplayed = true;
            }
        }
    }//GEN-LAST:event_jButton_LoginActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Login;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_Imagine;
    private javax.swing.JLabel jLabel_Password_Icon;
    private javax.swing.JLabel jLabel_Title;
    private javax.swing.JLabel jLabel_UserName_Icon;
    private javax.swing.JLabel jLabel_title1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField_Username;
    // End of variables declaration//GEN-END:variables
}
