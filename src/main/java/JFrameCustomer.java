
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author thoai
 */
public final class JFrameCustomer extends javax.swing.JFrame {

    List<Customer> list = new ArrayList<Customer>();
    Customer customer;
    private static final String FILE_PATH = "customer_data.bin";
    private static int pos = 0;
    private static int check = 0;

    public JFrameCustomer() {
        initComponents();
        //Thêm icon và màu
        jLabel_icon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagines/home-page-icon.png")));
        Color tablecolor = new Color(204,255,255);
        jTable_customer.setBackground(tablecolor);


        // Tải dữ liệu từ file nhị phân
        loadDataFromFile();

        // Nếu danh sách rỗng, thêm các khách hàng mặc định
        if (list.isEmpty()) {
            // Thêm khách hàng mặc định
            list.add(new Customer("Nguyen Van A", "0000000001", 20, "Ha Noi", "0111111111", 3, 3, "Ha Dong", "Nam", ""));
            list.add(new Customer("Nguyen Thi B", "0000000002", 22, "Hai Phong", "0222222222", 2, 2, "Cau Giay", "Nữ", ""));
            list.add(new Customer("Le Van C", "0000000003", 29, "Ha Noi", "0333333333", 5, 5, "Yen Nghia", "Nam", ""));

            list.add(new Customer("Lê Văn Nin", "000233441", 25, "Phu Tho", "01123343847", 4, 4, "Van Quan", "Khac", ""));
            list.add(new Customer("Pham Thu Thuy", "0133434444", 32, "Dak Lak", "078356735", 1, 1, "Cau Giay", "Nữ", ""));
            list.add(new Customer("Tong Van", "0144044422", 45, "Long An", "8262373637", 7, 7, "Ho Tung Mau", "Nam", ""));
            saveDataToFile();//Lưu dữ liệu
        }
        View();
        viewTable();
    }

    public void View() {
        showCustomer(pos);
        OnOff(false, true, true, true);
    }

    public void showCustomer(int index) {
        // Kiểm tra danh sách khách hàng rỗng hoặc chỉ số không hợp lệ
        if (list.isEmpty() || index < 0 || index >= list.size()) {
            return;
        }

        // Lấy thông tin khách hàng từ danh sách
        customer = list.get(index);

        // Hiển thị thông tin khách hàng lên giao diện
        this.jTextField_name.setText(customer.getName());
        this.jTextField_age.setText(String.valueOf(customer.getAge()));
        this.jTextField_quequan.setText(customer.getQuequan());
        this.jTextField_cccd.setText(customer.getCCCD());
        this.jTextField_sdt.setText(customer.getPhone());
        this.jTextField_time.setText(String.valueOf(customer.getTime()));
        this.jTextField_address.setText(customer.getAddress());

        // Hiển thị giới tính của khách hàng trong jComboBox_sex
        String[] genders = {"Nam", "Nữ", "Khác"};
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(genders);
        jComboBox_sex.setModel(model);
        jComboBox_sex.setSelectedItem(customer.getGioitinh());
    }

    public void viewTable() {
        // Hiển thị thông tin lên bảng
        DefaultTableModel model = (DefaultTableModel) jTable_customer.getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

        for (int i = 0; i < list.size(); i++) {
            Customer customer = list.get(i);
            int rowIndex = i + 1;
            double amountToPay = customer.getTime() * 1000000; // Tính số tiền phải trả

            // Tính toán ngày hết hạn
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.add(java.util.Calendar.MONTH, customer.getTime());
            java.util.Date expirationDate = calendar.getTime();
            java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
            String expirationDateStr = dateFormat.format(expirationDate);

            Object[] rowData = new Object[]{rowIndex, customer.getName(), customer.getAge(), customer.getQuequan(), customer.getPhone(), customer.getCCCD(), customer.getAddress(), customer.getTime(), amountToPay, expirationDateStr, customer.getGioitinh()};
            model.addRow(rowData);
        }

        // Tạo một SelectionListener cho bảng
        jTable_customer.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                // Lấy chỉ mục hàng được chọn
                int selectedRow = jTable_customer.getSelectedRow();

                // Kiểm tra xem chỉ mục hàng có hợp lệ hay không
                if (selectedRow >= 0 && selectedRow < list.size()) {
                    Customer selectedCustomer = list.get(selectedRow);
                    jComboBox_sex.setSelectedItem(selectedCustomer.getGioitinh());
                }
            }
        });
    }

    public void OnOff(boolean a, boolean b, boolean c, boolean d) {
        // Ẩn hoặc hiển thị các nút
        jButton_edit.show(b);
        jButton_delete.show(d);
        jButton_save.show(a);
        jButton_huy.show(a);
        jButton_add.show(c);
    }

    public final void saveDataToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream(FILE_PATH);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(list);
            objectOut.flush();
            objectOut.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public final void loadDataFromFile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try {
                FileInputStream fileIn = new FileInputStream(FILE_PATH);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                list = (List<Customer>) objectIn.readObject();
                objectIn.close();
                fileIn.close();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void calculateStatistics() {
        int totalCustomers = list.size();
        int totalAge = 0;
        int minAge = Integer.MAX_VALUE;
        int maxAge = Integer.MIN_VALUE;
        int totalRevenue = 0;
        int maleCount = 0;
        int femaleCount = 0;
        int other = 0;
        int totalMonths = 0;

        for (Customer customer : list) {
            int age = customer.getAge();
            totalAge += age;
            minAge = Math.min(minAge, age);
            maxAge = Math.max(maxAge, age);

            // Tính toán doanh thu
            totalRevenue += customer.getTime() * 1000000;

            // Đếm số lượng nam và nữ
            if (customer.getGioitinh().equalsIgnoreCase("Nam")) {
                maleCount++;
            } else if (customer.getGioitinh().equalsIgnoreCase("Nữ")) {
                femaleCount++;
            } else {
                other++;
            }

            // Tính tổng số tháng thuê
            totalMonths += customer.getTime();
        }

        double averageAge = (double) totalAge / totalCustomers;
        double averageMonths = (double) totalMonths / totalCustomers;

        JOptionPane.showMessageDialog(null, "THỐNG KÊ KHÁCH HÀNG\n"
                + "Tổng số khách hàng: " + totalCustomers + "\n"
                + "Tổng số nam: " + maleCount + "\n"
                + "Tổng số nữ: " + femaleCount + "\n"
                + "Khác: " + other + "\n"
                + "Tuổi trung bình: " + averageAge + "\n"
                + "Tuổi nhỏ nhất: " + minAge + "\n"
                + "Tuổi lớn nhất: " + maxAge + "\n"
                + "Tổng doanh thu: " + totalRevenue + "\n"
                + "Tổng số tháng thuê trung bình: " + averageMonths,
                "Thống kê", JOptionPane.INFORMATION_MESSAGE);
    }

    public boolean validateData() {
        String name = jTextField_name.getText();
        String ageStr = jTextField_age.getText();
        String quequan = jTextField_quequan.getText();
        String cccd = jTextField_cccd.getText();
        String sdt = jTextField_sdt.getText();
        String timeStr = jTextField_time.getText();
        String address = jTextField_address.getText();
        String gioitinh = jComboBox_sex.getSelectedItem().toString();

        if (name.isEmpty() || ageStr.isEmpty() || quequan.isEmpty() || cccd.isEmpty() || sdt.isEmpty() || timeStr.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin khách hàng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        try {
            int age = Integer.parseInt(ageStr);
            int time = Integer.parseInt(timeStr);

            if (age <= 0 || time <= 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập tuổi và thời gian hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tuổi và thời gian hợp lệ.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!sdt.matches("\\d{10}")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại không hợp lệ. Vui lòng nhập 10 chữ số.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel_icon = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel_name = new javax.swing.JLabel();
        jLabel_age = new javax.swing.JLabel();
        jLabel_quequan = new javax.swing.JLabel();
        jLabel_sdt = new javax.swing.JLabel();
        jLabel_cccd = new javax.swing.JLabel();
        jTextField_name = new javax.swing.JTextField();
        jTextField_age = new javax.swing.JTextField();
        jTextField_quequan = new javax.swing.JTextField();
        jTextField_cccd = new javax.swing.JTextField();
        jTextField_sdt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField_time = new javax.swing.JTextField();
        jButton_edit = new javax.swing.JButton();
        jButton_delete = new javax.swing.JButton();
        jButton_save = new javax.swing.JButton();
        jButton_huy = new javax.swing.JButton();
        jButton_search = new javax.swing.JButton();
        jButton_add = new javax.swing.JButton();
        jLabel_address = new javax.swing.JLabel();
        jTextField_address = new javax.swing.JTextField();
        jTextField_search = new javax.swing.JTextField();
        jLabel_sex = new javax.swing.JLabel();
        jComboBox_sex = new javax.swing.JComboBox<>();
        jButton_satistics1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable_customer = new javax.swing.JTable();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ NHÀ CHO THUÊ");

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("QUẢN LÝ NHÀ CHO THUÊ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(316, 316, 316))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel_icon, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(55, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        jLabel_name.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel_name.setText("Họ và Tên: ");

        jLabel_age.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel_age.setText("Tuổi: ");

        jLabel_quequan.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel_quequan.setText("Quê Quán: ");

        jLabel_sdt.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel_sdt.setText("SDT: ");

        jLabel_cccd.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel_cccd.setText("CCCD: ");

        jTextField_name.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jTextField_age.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_age.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_ageActionPerformed(evt);
            }
        });

        jTextField_quequan.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_quequan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_quequanActionPerformed(evt);
            }
        });

        jTextField_cccd.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jTextField_sdt.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("Thời gian thuê: ");

        jTextField_time.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jButton_edit.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_edit.setText("Sửa");
        jButton_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_editActionPerformed(evt);
            }
        });

        jButton_delete.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_delete.setText("Xóa");
        jButton_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_deleteActionPerformed(evt);
            }
        });

        jButton_save.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_save.setText("Lưu");
        jButton_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_saveActionPerformed(evt);
            }
        });

        jButton_huy.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_huy.setText("Hủy");
        jButton_huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_huyActionPerformed(evt);
            }
        });

        jButton_search.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_search.setText("Tìm kiếm");
        jButton_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_searchActionPerformed(evt);
            }
        });

        jButton_add.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_add.setText("Thêm");
        jButton_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_addActionPerformed(evt);
            }
        });

        jLabel_address.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel_address.setText("Địa chỉ nhà thuê: ");

        jTextField_address.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N

        jTextField_search.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jTextField_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_searchActionPerformed(evt);
            }
        });

        jLabel_sex.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel_sex.setText("Giới Tính: ");

        jComboBox_sex.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jComboBox_sex.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nam", "Nữ", "Khác" }));
        jComboBox_sex.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox_sexItemStateChanged(evt);
            }
        });

        jButton_satistics1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jButton_satistics1.setText("Thống kê");
        jButton_satistics1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_satistics1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(jButton_search))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton_satistics1)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jButton_save, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jButton_huy, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_quequan, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField_quequan))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jButton_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_cccd)
                                .addGap(35, 35, 35)
                                .addComponent(jTextField_cccd))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_sdt)
                                .addGap(48, 48, 48)
                                .addComponent(jTextField_sdt))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel_age)
                                .addGap(46, 46, 46)
                                .addComponent(jTextField_age))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel_name)
                                    .addComponent(jLabel_sex))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jTextField_name))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox_sex, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 181, Short.MAX_VALUE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel_address))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField_address)
                                    .addComponent(jTextField_time))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_name)
                    .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_age, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_sex, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_sex, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_quequan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_quequan, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_cccd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_time, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_address, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_add, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_edit, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_save, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_huy, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addComponent(jTextField_search, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton_search)
                .addGap(18, 18, 18)
                .addComponent(jButton_satistics1)
                .addContainerGap(67, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(204, 255, 255));

        jTable_customer.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Họ và Tên", "Tuổi", "Quê Quán", "SDT", "CCCD", "Địa chỉ thuê", "Thời gian thuê", "Số tiền phải trả", "Ngày Hết Hạn", "Giới Tính"
            }
        ));
        jTable_customer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable_customerMouseClicked(evt);
            }
        });
        jTable_customer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable_customerKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable_customer);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 803, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_huyActionPerformed
        //Hủy đối tượng
        jTextField_search.setText("");
        View();
        viewTable();
        OnOff(false, true, true, true);
    }//GEN-LAST:event_jButton_huyActionPerformed

    private void jTable_customerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable_customerMouseClicked
        //thay đổi thông tin khi click hàng khác
        pos = this.jTable_customer.getSelectedRow();
        View();
    }//GEN-LAST:event_jTable_customerMouseClicked

    private void jTable_customerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable_customerKeyReleased
        //thay đổi thông tin khi di chuyển hàng bằng phím
        pos = this.jTable_customer.getSelectedRow();
        View();
    }//GEN-LAST:event_jTable_customerKeyReleased

    private void jButton_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_addActionPerformed
        //thêm đối tượng
        //reset về trạng thái rỗng
        this.jTextField_name.setText("");
        this.jTextField_age.setText("");
        this.jTextField_quequan.setText("");
        this.jTextField_cccd.setText("");
        this.jTextField_sdt.setText("");
        this.jTextField_time.setText("");
        this.jTextField_address.setText("");
        OnOff(true, false, false, false);
        check = 1;
    }//GEN-LAST:event_jButton_addActionPerformed

    private void jButton_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_saveActionPerformed
        // Kiểm tra dữ liệu trước khi lưu
        if (!validateData()) {
            return;
        }
        // Lấy thông tin từ các textField/comboBox
        String name = jTextField_name.getText();
        int age = Integer.parseInt(jTextField_age.getText());
        String quequan = jTextField_quequan.getText();
        String phone = jTextField_sdt.getText();
        String cccd = jTextField_cccd.getText();
        int time = Integer.parseInt(jTextField_time.getText());
        String address = jTextField_address.getText();
        String sex = (String) jComboBox_sex.getSelectedItem();

        if (check == 1) { // Thêm mới
            // Thêm đối tượng mới vào danh sách
            list.add(new Customer(name, cccd, age, quequan, phone, time, time, address, sex, ""));
        } else { // Cập nhật
            list.set(pos, new Customer(name, cccd, age, quequan, phone, time, time, address, sex, ""));
        }
        // Lưu dữ liệu xuống file
        saveDataToFile();
        JOptionPane.showMessageDialog(null, "Đã lưu thông tin khách hàng.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

        // Reset form 
        jTextField_search.setText("");
        View();
        viewTable();
        OnOff(false, true, true, true);
    }//GEN-LAST:event_jButton_saveActionPerformed

    private void jButton_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_editActionPerformed
        //Sửa đối tượng
        String currentSex = customer.getGioitinh();
        jComboBox_sex.setSelectedItem(currentSex);
        if (jButton_save.getText().equals("Lưu")) {

            String newSex = (String) jComboBox_sex.getSelectedItem();
            customer.setGioitinh(newSex);
        }
        OnOff(true, false, false, false);
        check = -1;
    }//GEN-LAST:event_jButton_editActionPerformed

    private void jButton_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_deleteActionPerformed

        //Xóa đối tượng
        int selectedIndex = jTable_customer.getSelectedRow(); // Lấy chỉ số hàng được chọn trong bảng

        if (selectedIndex >= 0) {
            int option = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa khách hàng này không?","Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                list.remove(selectedIndex); // Xóa khách hàng khỏi danh sách
                viewTable(); // Cập nhật lại bảng
                saveDataToFile();

                if (selectedIndex >= list.size()) {
                    // Nếu chỉ số hàng được chọn lớn hơn hoặc bằng kích thước danh sách,
                    // điều chỉnh chỉ số hiện tại để trỏ đến phần tử cuối cùng của danh sách
                    pos = list.size() - 1;
                    saveDataToFile();
                }

                if (list.isEmpty()) {
                    // Nếu danh sách rỗng sau khi xóa, thiết lập chỉ số hiện tại và kiểm tra là 0
                    pos = 0;
                }

                View(); // Hiển thị thông tin của khách hàng hiện tại
            }
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn khách hàng để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jButton_deleteActionPerformed

    private void jButton_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_searchActionPerformed
        // Tìm kiếm 
        // Khi nút "Tìm kiếm" được nhấn
        String keyword = jTextField_search.getText().trim().toLowerCase();
        List<Customer> searchResults = new ArrayList<>();

        for (Customer customer : list) {
            if (customer.getName().toLowerCase().contains(keyword)
                    || customer.getCCCD().toLowerCase().contains(keyword)
                    || customer.getQuequan().toLowerCase().contains(keyword)
                    || customer.getPhone().toLowerCase().contains(keyword)
                    || customer.getAddress().toLowerCase().contains(keyword)) {
                searchResults.add(customer);
            }
        }

        if (!searchResults.isEmpty()) {
            showCustomer(0); // Hiển thị thông tin khách hàng đầu tiên trong kết quả tìm kiếm
            DefaultTableModel model = (DefaultTableModel) jTable_customer.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ trong bảng

            int rowIndex = 1;
            for (Customer customer : searchResults) {
                // Tính toán ngày hết hạn
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                calendar.add(java.util.Calendar.MONTH, customer.getTime());
                java.util.Date expirationDate = calendar.getTime();
                java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
                String expirationDateStr = dateFormat.format(expirationDate);
                double amountToPay = customer.getTime() * 1000000; // Tính số tiền phải trả
                Object[] rowData = new Object[]{rowIndex, customer.getName(), customer.getAge(), customer.getQuequan(),
                    customer.getPhone(), customer.getCCCD(), customer.getAddress(), customer.getTime(),
                    amountToPay, expirationDateStr, customer.getGioitinh()};
                model.addRow(rowData);
                rowIndex++;
            }
        } else {
            // Không tìm thấy kết quả
            JOptionPane.showMessageDialog(this, "Không tìm thấy kết quả phù hợp.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
        OnOff(true, true, true, true);
    }//GEN-LAST:event_jButton_searchActionPerformed

    private void jTextField_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_searchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_searchActionPerformed

    private void jTextField_quequanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_quequanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_quequanActionPerformed

    private void jTextField_ageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_ageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_ageActionPerformed

    private void jComboBox_sexItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox_sexItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox_sexItemStateChanged

    private void jButton_satistics1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_satistics1ActionPerformed
        // Thong ke du lieu
        calculateStatistics();
    }//GEN-LAST:event_jButton_satistics1ActionPerformed

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
            java.util.logging.Logger.getLogger(JFrameCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrameCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrameCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrameCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrameCustomer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_add;
    private javax.swing.JButton jButton_delete;
    private javax.swing.JButton jButton_edit;
    private javax.swing.JButton jButton_huy;
    private javax.swing.JButton jButton_satistics1;
    private javax.swing.JButton jButton_save;
    private javax.swing.JButton jButton_search;
    private javax.swing.JComboBox<String> jComboBox_sex;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_address;
    private javax.swing.JLabel jLabel_age;
    private javax.swing.JLabel jLabel_cccd;
    private javax.swing.JLabel jLabel_icon;
    private javax.swing.JLabel jLabel_name;
    private javax.swing.JLabel jLabel_quequan;
    private javax.swing.JLabel jLabel_sdt;
    private javax.swing.JLabel jLabel_sex;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable_customer;
    private javax.swing.JTextField jTextField_address;
    private javax.swing.JTextField jTextField_age;
    private javax.swing.JTextField jTextField_cccd;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JTextField jTextField_quequan;
    private javax.swing.JTextField jTextField_sdt;
    private javax.swing.JTextField jTextField_search;
    private javax.swing.JTextField jTextField_time;
    // End of variables declaration//GEN-END:variables
}
