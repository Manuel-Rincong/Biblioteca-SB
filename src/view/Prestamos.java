package view;

public class Prestamos extends javax.swing.JFrame {

    private java.awt.event.ActionListener accionRegistrar;
    private java.awt.event.ActionListener accionConsultar;
    private java.awt.event.ActionListener accionSeleccion;

    public Prestamos() {
        initComponents();
        txtFechaPrestamo.setText(java.time.LocalDate.now().toString());
        tblPrestamos.getTableHeader().setReorderingAllowed(false);
        tblPrestamos.getTableHeader().setFont(new java.awt.Font("SansSerif", 1, 12));
        tblPrestamos.getTableHeader().setBackground(new java.awt.Color(238, 232, 244));
        tblPrestamos.getTableHeader().setForeground(new java.awt.Color(67, 45, 86));
        tblPrestamos.getColumnModel().getColumn(0).setPreferredWidth(40);
        tblPrestamos.getColumnModel().getColumn(1).setPreferredWidth(125);
        tblPrestamos.getColumnModel().getColumn(2).setPreferredWidth(150);
        tblPrestamos.getColumnModel().getColumn(3).setPreferredWidth(110);
        tblPrestamos.getColumnModel().getColumn(4).setPreferredWidth(110);
        tblPrestamos.getColumnModel().getColumn(5).setPreferredWidth(90);
        actualizarBotones();
        setLocationRelativeTo(null);
    }

    public void configurarAcciones(java.awt.event.ActionListener registrar,
                                   java.awt.event.ActionListener consultar) {
        accionRegistrar = registrar;
        accionConsultar = consultar;
        btnRegistrar.setEnabled(registrar != null);
        btnConsultar.setEnabled(consultar != null);
        actualizarBotones();
    }

    private void actualizarBotones() {
        btnRegistrar.setBackground(btnRegistrar.isEnabled()
                ? new java.awt.Color(104, 62, 135) : new java.awt.Color(238, 232, 244));
    }

    public void configurarCambioSeleccion(java.awt.event.ActionListener accion) {
        accionSeleccion = accion;
    }

    public int getIndiceUsuario() {
        return cmbUsuarios.getSelectedIndex() - 1;
    }

    public int getIndiceRecurso() {
        return cmbRecursos.getSelectedIndex() - 1;
    }

    public String getFechaPrestamo() {
        return txtFechaPrestamo.getText().trim();
    }

    public String getFechaLimite() {
        return txtFechaLimite.getText().trim();
    }

    public void cargarUsuarios(String[] nombres) {
        cmbUsuarios.removeAllItems();
        cmbUsuarios.addItem("Seleccione un usuario");
        for (String nombre : nombres) {
            cmbUsuarios.addItem(nombre);
        }
        cmbUsuarios.setSelectedIndex(0);
    }

    public void cargarRecursos(String[] nombres) {
        cmbRecursos.removeAllItems();
        cmbRecursos.addItem("Seleccione un recurso");
        for (String nombre : nombres) {
            cmbRecursos.addItem(nombre);
        }
        cmbRecursos.setSelectedIndex(0);
    }

    public void mostrarFechaPrestamo(java.time.LocalDate fecha) {
        txtFechaPrestamo.setText(fecha == null ? "" : fecha.toString());
    }

    public void mostrarFechaLimite(java.time.LocalDate fecha) {
        txtFechaLimite.setText(fecha == null ? "" : fecha.toString());
    }

    public void mostrarPrestamos(Object[][] filas) {
        javax.swing.table.DefaultTableModel modelo =
                (javax.swing.table.DefaultTableModel) tblPrestamos.getModel();
        modelo.setRowCount(0);
        for (Object[] fila : filas) {
            modelo.addRow(fila);
        }
    }

    public void mostrarEstado(String mensaje) {
        javax.swing.JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarFormulario() {
        cmbUsuarios.setSelectedIndex(0);
        cmbRecursos.setSelectedIndex(0);
        txtFechaPrestamo.setText(java.time.LocalDate.now().toString());
        txtFechaLimite.setText("");
        cmbUsuarios.requestFocusInWindow();
    }

    private void notificarSeleccion(java.awt.event.ActionEvent evt) {
        txtFechaLimite.setText("");
        if (accionSeleccion != null && getIndiceUsuario() >= 0 && getIndiceRecurso() >= 0) {
            accionSeleccion.actionPerformed(evt);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEncabezado = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        pnlContenido = new javax.swing.JPanel();
        pnlFormulario = new javax.swing.JPanel();
        lblDatos = new javax.swing.JLabel();
        pnlCampos = new javax.swing.JPanel();
        pnlUsuario = new javax.swing.JPanel();
        lblUsuario = new javax.swing.JLabel();
        cmbUsuarios = new javax.swing.JComboBox<>();
        pnlRecurso = new javax.swing.JPanel();
        lblRecurso = new javax.swing.JLabel();
        cmbRecursos = new javax.swing.JComboBox<>();
        pnlFechaPrestamo = new javax.swing.JPanel();
        lblFechaPrestamo = new javax.swing.JLabel();
        txtFechaPrestamo = new javax.swing.JTextField();
        pnlFechaLimite = new javax.swing.JPanel();
        lblFechaLimite = new javax.swing.JLabel();
        txtFechaLimite = new javax.swing.JTextField();
        pnlAcciones = new javax.swing.JPanel();
        btnRegistrar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        pnlListado = new javax.swing.JPanel();
        pnlCabeceraTabla = new javax.swing.JPanel();
        lblListado = new javax.swing.JLabel();
        btnConsultar = new javax.swing.JButton();
        scrPrestamos = new javax.swing.JScrollPane();
        tblPrestamos = new javax.swing.JTable();
        lblAyudaTabla = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Biblioteca | Gestión de préstamos");
        setMinimumSize(new java.awt.Dimension(980, 620));
        setPreferredSize(new java.awt.Dimension(1100, 680));

        pnlEncabezado.setBackground(new java.awt.Color(67, 45, 86));
        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 26, 22, 26));
        pnlEncabezado.setLayout(new java.awt.BorderLayout(0, 8));

        lblTitulo.setText("GESTIÓN DE PRÉSTAMOS");
        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 25)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        pnlEncabezado.add(lblTitulo, java.awt.BorderLayout.NORTH);

        lblSubtitulo.setText("Préstamo de libros y equipos | Sistema de biblioteca");
        lblSubtitulo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(236, 224, 246));
        pnlEncabezado.add(lblSubtitulo, java.awt.BorderLayout.SOUTH);

        pnlContenido.setBackground(new java.awt.Color(247, 245, 250));
        pnlContenido.setBorder(javax.swing.BorderFactory.createEmptyBorder(22, 26, 20, 26));

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 22, 22, 22));
        pnlFormulario.setPreferredSize(new java.awt.Dimension(340, 440));

        lblDatos.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblDatos.setForeground(new java.awt.Color(104, 62, 135));
        lblDatos.setText("Datos del préstamo");

        pnlCampos.setOpaque(false);

        pnlUsuario.setOpaque(false);

        lblUsuario.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblUsuario.setForeground(new java.awt.Color(53, 47, 62));
        lblUsuario.setText("Usuario");

        cmbUsuarios.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbUsuarios.setForeground(new java.awt.Color(67, 45, 86));
        cmbUsuarios.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un usuario" }));
        cmbUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbUsuariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlUsuarioLayout = new javax.swing.GroupLayout(pnlUsuario);
        pnlUsuario.setLayout(pnlUsuarioLayout);
        pnlUsuarioLayout.setHorizontalGroup(
            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(cmbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlUsuarioLayout.setVerticalGroup(
            pnlUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuarioLayout.createSequentialGroup()
                .addComponent(lblUsuario)
                .addGap(6, 6, 6)
                .addComponent(cmbUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlRecurso.setOpaque(false);
        pnlRecurso.setLayout(new java.awt.BorderLayout(0, 6));

        lblRecurso.setText("Recurso");
        lblRecurso.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblRecurso.setForeground(new java.awt.Color(53, 47, 62));
        pnlRecurso.add(lblRecurso, java.awt.BorderLayout.NORTH);

        cmbRecursos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cmbRecursos.setForeground(new java.awt.Color(67, 45, 86));
        cmbRecursos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione un recurso" }));
        cmbRecursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRecursosActionPerformed(evt);
            }
        });
        pnlRecurso.add(cmbRecursos, java.awt.BorderLayout.CENTER);

        pnlFechaPrestamo.setOpaque(false);

        lblFechaPrestamo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblFechaPrestamo.setForeground(new java.awt.Color(53, 47, 62));
        lblFechaPrestamo.setText("Fecha de préstamo (AAAA-MM-DD)");

        txtFechaPrestamo.setEditable(false);
        txtFechaPrestamo.setBackground(new java.awt.Color(247, 245, 250));
        txtFechaPrestamo.setColumns(18);
        txtFechaPrestamo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtFechaPrestamo.setForeground(new java.awt.Color(67, 45, 86));
        txtFechaPrestamo.setMargin(new java.awt.Insets(5, 8, 5, 8));

        javax.swing.GroupLayout pnlFechaPrestamoLayout = new javax.swing.GroupLayout(pnlFechaPrestamo);
        pnlFechaPrestamo.setLayout(pnlFechaPrestamoLayout);
        pnlFechaPrestamoLayout.setHorizontalGroup(
            pnlFechaPrestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(txtFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlFechaPrestamoLayout.setVerticalGroup(
            pnlFechaPrestamoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFechaPrestamoLayout.createSequentialGroup()
                .addComponent(lblFechaPrestamo)
                .addGap(6, 6, 6)
                .addComponent(txtFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlFechaLimite.setOpaque(false);
        pnlFechaLimite.setLayout(new java.awt.BorderLayout(0, 6));

        lblFechaLimite.setText("Fecha límite (automática)");
        lblFechaLimite.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblFechaLimite.setForeground(new java.awt.Color(53, 47, 62));
        pnlFechaLimite.add(lblFechaLimite, java.awt.BorderLayout.NORTH);

        txtFechaLimite.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtFechaLimite.setForeground(new java.awt.Color(67, 45, 86));
        txtFechaLimite.setBackground(new java.awt.Color(247, 245, 250));
        txtFechaLimite.setColumns(18);
        txtFechaLimite.setMargin(new java.awt.Insets(5, 8, 5, 8));
        txtFechaLimite.setEditable(false);
        pnlFechaLimite.add(txtFechaLimite, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnlCamposLayout = new javax.swing.GroupLayout(pnlCampos);
        pnlCampos.setLayout(pnlCamposLayout);
        pnlCamposLayout.setHorizontalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlRecurso, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlCamposLayout.setVerticalGroup(
            pnlCamposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCamposLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(pnlUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(pnlRecurso, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(pnlFechaPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(pnlFechaLimite, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlAcciones.setOpaque(false);
        pnlAcciones.setLayout(new java.awt.GridLayout(1, 2, 10, 0));

        btnRegistrar.setBackground(new java.awt.Color(238, 232, 244));
        btnRegistrar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setEnabled(false);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setOpaque(true);
        btnRegistrar.setPreferredSize(new java.awt.Dimension(112, 38));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });
        pnlAcciones.add(btnRegistrar);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnLimpiar.setBackground(new java.awt.Color(238, 232, 244));
        btnLimpiar.setForeground(new java.awt.Color(67, 45, 86));
        btnLimpiar.setFocusPainted(false);
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setOpaque(true);
        btnLimpiar.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        btnLimpiar.setPreferredSize(new java.awt.Dimension(112, 38));
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        pnlAcciones.add(btnLimpiar);

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addComponent(lblDatos)
                .addGap(20, 20, 20)
                .addComponent(pnlCampos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pnlAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlListado.setBackground(new java.awt.Color(255, 255, 255));
        pnlListado.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pnlCabeceraTabla.setOpaque(false);
        pnlCabeceraTabla.setLayout(new java.awt.BorderLayout(12, 0));

        lblListado.setText("Préstamos registrados");
        lblListado.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblListado.setForeground(new java.awt.Color(104, 62, 135));
        pnlCabeceraTabla.add(lblListado, java.awt.BorderLayout.CENTER);

        btnConsultar.setText("Consultar");
        btnConsultar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnConsultar.setBackground(new java.awt.Color(238, 232, 244));
        btnConsultar.setForeground(new java.awt.Color(67, 45, 86));
        btnConsultar.setEnabled(false);
        btnConsultar.setFocusPainted(false);
        btnConsultar.setContentAreaFilled(false);
        btnConsultar.setOpaque(true);
        btnConsultar.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        btnConsultar.setPreferredSize(new java.awt.Dimension(112, 38));
        btnConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarActionPerformed(evt);
            }
        });
        pnlCabeceraTabla.add(btnConsultar, java.awt.BorderLayout.EAST);

        scrPrestamos.setPreferredSize(new java.awt.Dimension(620, 320));

        tblPrestamos.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        tblPrestamos.setForeground(new java.awt.Color(67, 45, 86));
        tblPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Usuario", "Recurso", "Fecha préstamo", "Fecha límite", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPrestamos.setFillsViewportHeight(true);
        tblPrestamos.setGridColor(new java.awt.Color(231, 224, 236));
        tblPrestamos.setRowHeight(28);
        tblPrestamos.setSelectionBackground(new java.awt.Color(231, 219, 240));
        tblPrestamos.setSelectionForeground(new java.awt.Color(67, 45, 86));
        tblPrestamos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        scrPrestamos.setViewportView(tblPrestamos);

        lblAyudaTabla.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblAyudaTabla.setForeground(new java.awt.Color(53, 47, 62));
        lblAyudaTabla.setText("Consulta el usuario, el recurso, las fechas y el estado de cada préstamo.");

        btnCerrar.setBackground(new java.awt.Color(238, 232, 244));
        btnCerrar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnCerrar.setForeground(new java.awt.Color(67, 45, 86));
        btnCerrar.setText("Cerrar");
        btnCerrar.setBorder(javax.swing.BorderFactory.createEmptyBorder(9, 16, 9, 16));
        btnCerrar.setContentAreaFilled(false);
        btnCerrar.setFocusPainted(false);
        btnCerrar.setOpaque(true);
        btnCerrar.setPreferredSize(new java.awt.Dimension(112, 38));
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlListadoLayout = new javax.swing.GroupLayout(pnlListado);
        pnlListado.setLayout(pnlListadoLayout);
        pnlListadoLayout.setHorizontalGroup(
            pnlListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListadoLayout.createSequentialGroup()
                .addGroup(pnlListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlListadoLayout.createSequentialGroup()
                        .addComponent(lblAyudaTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(148, 148, 148)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlCabeceraTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(scrPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 648, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlListadoLayout.setVerticalGroup(
            pnlListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListadoLayout.createSequentialGroup()
                .addComponent(pnlCabeceraTabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(scrPrestamos, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(pnlListadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAyudaTabla)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout pnlContenidoLayout = new javax.swing.GroupLayout(pnlContenido);
        pnlContenido.setLayout(pnlContenidoLayout);
        pnlContenidoLayout.setHorizontalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlContenidoLayout.createSequentialGroup()
                .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(pnlListado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlContenidoLayout.setVerticalGroup(
            pnlContenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlListado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 1100, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(pnlContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(pnlContenido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        if (accionRegistrar != null) {
            accionRegistrar.actionPerformed(evt);
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        if (accionConsultar != null) {
            accionConsultar.actionPerformed(evt);
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarFormulario();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void cmbUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbUsuariosActionPerformed
        notificarSeleccion(evt);
    }//GEN-LAST:event_cmbUsuariosActionPerformed

    private void cmbRecursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRecursosActionPerformed
        notificarSeleccion(evt);
    }//GEN-LAST:event_cmbRecursosActionPerformed

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Prestamos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JComboBox<String> cmbRecursos;
    private javax.swing.JComboBox<String> cmbUsuarios;
    private javax.swing.JLabel lblAyudaTabla;
    private javax.swing.JLabel lblDatos;
    private javax.swing.JLabel lblFechaLimite;
    private javax.swing.JLabel lblFechaPrestamo;
    private javax.swing.JLabel lblListado;
    private javax.swing.JLabel lblRecurso;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JPanel pnlAcciones;
    private javax.swing.JPanel pnlCabeceraTabla;
    private javax.swing.JPanel pnlCampos;
    private javax.swing.JPanel pnlContenido;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JPanel pnlFechaLimite;
    private javax.swing.JPanel pnlFechaPrestamo;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlListado;
    private javax.swing.JPanel pnlRecurso;
    private javax.swing.JPanel pnlUsuario;
    private javax.swing.JScrollPane scrPrestamos;
    private javax.swing.JTable tblPrestamos;
    private javax.swing.JTextField txtFechaLimite;
    private javax.swing.JTextField txtFechaPrestamo;
    // End of variables declaration//GEN-END:variables
}
