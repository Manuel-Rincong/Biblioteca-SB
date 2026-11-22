package view;

public class Login extends javax.swing.JFrame {

    private char caracterOculto;

    public Login() {
        initComponents();
        caracterOculto = txtContrasena.getEchoChar();
        lblCorreo.setLabelFor(txtCorreo);
        lblContrasena.setLabelFor(txtContrasena);
        getRootPane().setDefaultButton(btnIngresar);
        setLocationRelativeTo(null);
    }

    public String getCorreo() {
        return txtCorreo.getText().trim();
    }

    public char[] getContrasena() {
        return txtContrasena.getPassword();
    }

    public void configurarAccionIngresar(java.awt.event.ActionListener accion) {
        btnIngresar.addActionListener(accion);
        txtContrasena.addActionListener(accion);
    }

    public void mostrarError(String mensaje) {
        lblMensaje.setText("<html>" + mensaje + "</html>");
    }

    public void limpiarMensaje() {
        lblMensaje.setText(" ");
    }

    public void limpiarContrasena() {
        txtContrasena.setText("");
        chkMostrarContrasena.setSelected(false);
        txtContrasena.setEchoChar(caracterOculto);
    }

    public void enfocarCorreo() {
        txtCorreo.requestFocusInWindow();
    }

    public void enfocarContrasena() {
        txtContrasena.requestFocusInWindow();
    }

    public void setProcesando(boolean procesando) {
        btnIngresar.setEnabled(!procesando);
        btnSalir.setEnabled(!procesando);
        txtCorreo.setEnabled(!procesando);
        txtContrasena.setEnabled(!procesando);
        chkMostrarContrasena.setEnabled(!procesando);
        btnIngresar.setText(procesando ? "Verificando..." : "Iniciar sesión");
        setDefaultCloseOperation(procesando
                ? javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE
                : javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlIdentidad = new javax.swing.JPanel();
        pnlPresentacion = new javax.swing.JPanel();
        pnlLibros = new javax.swing.JPanel();
        pnlLibroRosado = new javax.swing.JPanel();
        pnlLibroRosadoMarcaSuperior = new javax.swing.JPanel();
        pnlLibroRosadoMarcaInferior = new javax.swing.JPanel();
        pnlLibroDorado = new javax.swing.JPanel();
        pnlLibroDoradoMarcaSuperior = new javax.swing.JPanel();
        pnlLibroDoradoMarcaInferior = new javax.swing.JPanel();
        pnlLibroNaranja = new javax.swing.JPanel();
        pnlLibroNaranjaMarcaSuperior = new javax.swing.JPanel();
        pnlLibroNaranjaMarcaInferior = new javax.swing.JPanel();
        lblFraseUno = new javax.swing.JLabel();
        lblFraseDos = new javax.swing.JLabel();
        lblDescripcionUno = new javax.swing.JLabel();
        lblDescripcionDos = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        pnlAcceso = new javax.swing.JPanel();
        pnlFormulario = new javax.swing.JPanel();
        lblAcceso = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblMensaje = new javax.swing.JLabel();
        lblCorreo = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        txtContrasena = new javax.swing.JPasswordField();
        chkMostrarContrasena = new javax.swing.JCheckBox();
        btnIngresar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblContrasena = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Biblioteca | Iniciar sesión");
        setResizable(false);

        pnlIdentidad.setBackground(new java.awt.Color(197, 80, 121));
        pnlIdentidad.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 34, 36, 30));
        pnlIdentidad.setPreferredSize(new java.awt.Dimension(336, 580));

        pnlPresentacion.setOpaque(false);

        pnlLibros.setOpaque(false);

        pnlLibroRosado.setBackground(new java.awt.Color(246, 207, 222));
        pnlLibroRosado.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 7, 12, 7));
        pnlLibroRosado.setPreferredSize(new java.awt.Dimension(42, 100));
        pnlLibroRosado.setLayout(new java.awt.BorderLayout(0, 0));

        pnlLibroRosadoMarcaSuperior.setBackground(new java.awt.Color(251, 233, 240));
        pnlLibroRosadoMarcaSuperior.setPreferredSize(new java.awt.Dimension(24, 3));
        pnlLibroRosadoMarcaSuperior.setLayout(new java.awt.BorderLayout(0, 0));
        pnlLibroRosado.add(pnlLibroRosadoMarcaSuperior, java.awt.BorderLayout.NORTH);

        pnlLibroRosadoMarcaInferior.setBackground(new java.awt.Color(251, 233, 240));
        pnlLibroRosadoMarcaInferior.setPreferredSize(new java.awt.Dimension(24, 3));
        pnlLibroRosadoMarcaInferior.setLayout(new java.awt.BorderLayout(0, 0));
        pnlLibroRosado.add(pnlLibroRosadoMarcaInferior, java.awt.BorderLayout.SOUTH);

        pnlLibroDorado.setBackground(new java.awt.Color(224, 182, 94));
        pnlLibroDorado.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 7, 12, 7));
        pnlLibroDorado.setPreferredSize(new java.awt.Dimension(42, 124));
        pnlLibroDorado.setLayout(new java.awt.BorderLayout(0, 0));

        pnlLibroDoradoMarcaSuperior.setBackground(new java.awt.Color(241, 222, 183));
        pnlLibroDoradoMarcaSuperior.setPreferredSize(new java.awt.Dimension(24, 3));
        pnlLibroDoradoMarcaSuperior.setLayout(new java.awt.BorderLayout(0, 0));
        pnlLibroDorado.add(pnlLibroDoradoMarcaSuperior, java.awt.BorderLayout.NORTH);

        pnlLibroDoradoMarcaInferior.setBackground(new java.awt.Color(241, 222, 183));
        pnlLibroDoradoMarcaInferior.setPreferredSize(new java.awt.Dimension(24, 3));
        pnlLibroDoradoMarcaInferior.setLayout(new java.awt.BorderLayout(0, 0));
        pnlLibroDorado.add(pnlLibroDoradoMarcaInferior, java.awt.BorderLayout.SOUTH);

        pnlLibroNaranja.setBackground(new java.awt.Color(235, 155, 102));
        pnlLibroNaranja.setBorder(javax.swing.BorderFactory.createEmptyBorder(14, 7, 12, 7));
        pnlLibroNaranja.setPreferredSize(new java.awt.Dimension(42, 86));
        pnlLibroNaranja.setLayout(new java.awt.BorderLayout(0, 0));

        pnlLibroNaranjaMarcaSuperior.setBackground(new java.awt.Color(246, 210, 186));
        pnlLibroNaranjaMarcaSuperior.setPreferredSize(new java.awt.Dimension(24, 3));
        pnlLibroNaranjaMarcaSuperior.setLayout(new java.awt.BorderLayout(0, 0));
        pnlLibroNaranja.add(pnlLibroNaranjaMarcaSuperior, java.awt.BorderLayout.NORTH);

        pnlLibroNaranjaMarcaInferior.setBackground(new java.awt.Color(246, 210, 186));
        pnlLibroNaranjaMarcaInferior.setPreferredSize(new java.awt.Dimension(24, 3));
        pnlLibroNaranjaMarcaInferior.setLayout(new java.awt.BorderLayout(0, 0));
        pnlLibroNaranja.add(pnlLibroNaranjaMarcaInferior, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout pnlLibrosLayout = new javax.swing.GroupLayout(pnlLibros);
        pnlLibros.setLayout(pnlLibrosLayout);
        pnlLibrosLayout.setHorizontalGroup(
            pnlLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLibrosLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(pnlLibroRosado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(pnlLibroDorado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlLibroNaranja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlLibrosLayout.setVerticalGroup(
            pnlLibrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLibrosLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(pnlLibroRosado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(pnlLibroDorado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pnlLibrosLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(pnlLibroNaranja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblFraseUno.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        lblFraseUno.setForeground(new java.awt.Color(255, 255, 255));
        lblFraseUno.setText("Tu biblioteca");

        lblFraseDos.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        lblFraseDos.setForeground(new java.awt.Color(255, 255, 255));
        lblFraseDos.setText("más cerca");

        lblDescripcionUno.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblDescripcionUno.setForeground(new java.awt.Color(255, 235, 242));
        lblDescripcionUno.setText("Libros y equipos para");

        lblDescripcionDos.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        lblDescripcionDos.setForeground(new java.awt.Color(255, 235, 242));
        lblDescripcionDos.setText("tu vida universitaria.");

        lblMarca.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMarca.setForeground(new java.awt.Color(255, 255, 255));
        lblMarca.setText("B I B L I O T E C A");

        javax.swing.GroupLayout pnlPresentacionLayout = new javax.swing.GroupLayout(pnlPresentacion);
        pnlPresentacion.setLayout(pnlPresentacionLayout);
        pnlPresentacionLayout.setHorizontalGroup(
            pnlPresentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPresentacionLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(pnlPresentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFraseUno)
                    .addGroup(pnlPresentacionLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(pnlPresentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPresentacionLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblDescripcionDos))
                            .addComponent(lblDescripcionUno, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFraseDos)))
                    .addGroup(pnlPresentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(pnlLibros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        pnlPresentacionLayout.setVerticalGroup(
            pnlPresentacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPresentacionLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(lblMarca)
                .addGap(18, 18, 18)
                .addComponent(pnlLibros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblFraseUno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFraseDos)
                .addGap(28, 28, 28)
                .addComponent(lblDescripcionUno)
                .addGap(6, 6, 6)
                .addComponent(lblDescripcionDos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlIdentidadLayout = new javax.swing.GroupLayout(pnlIdentidad);
        pnlIdentidad.setLayout(pnlIdentidadLayout);
        pnlIdentidadLayout.setHorizontalGroup(
            pnlIdentidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIdentidadLayout.createSequentialGroup()
                .addComponent(pnlPresentacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 73, Short.MAX_VALUE))
        );
        pnlIdentidadLayout.setVerticalGroup(
            pnlIdentidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPresentacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pnlAcceso.setBackground(new java.awt.Color(255, 255, 255));
        pnlAcceso.setBorder(javax.swing.BorderFactory.createEmptyBorder(38, 48, 30, 48));

        pnlFormulario.setBackground(new java.awt.Color(255, 255, 255));
        pnlFormulario.setPreferredSize(new java.awt.Dimension(428, 490));

        lblAcceso.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        lblAcceso.setForeground(new java.awt.Color(166, 53, 94));
        lblAcceso.setText("ACCESO AL SISTEMA");

        lblTitulo.setFont(new java.awt.Font("SansSerif", 1, 30)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(59, 40, 49));
        lblTitulo.setText("Iniciar sesión");
        lblTitulo.setFocusable(false);

        lblMensaje.setFont(new java.awt.Font("SansSerif", 0, 13)); // NOI18N
        lblMensaje.setForeground(new java.awt.Color(111, 85, 98));
        lblMensaje.setText("Usa el correo registrado en la biblioteca.");

        lblCorreo.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblCorreo.setForeground(new java.awt.Color(59, 40, 49));
        lblCorreo.setText("Correo electrónico");

        txtCorreo.setBackground(new java.awt.Color(255, 247, 250));
        txtCorreo.setColumns(20);
        txtCorreo.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        txtCorreo.setForeground(new java.awt.Color(59, 40, 49));
        txtCorreo.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 208, 217)), javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14)));
        txtCorreo.setCaretColor(new java.awt.Color(166, 53, 94));

        txtContrasena.setBackground(new java.awt.Color(255, 247, 250));
        txtContrasena.setColumns(20);
        txtContrasena.setFont(new java.awt.Font("SansSerif", 0, 15)); // NOI18N
        txtContrasena.setForeground(new java.awt.Color(59, 40, 49));
        txtContrasena.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 208, 217)), javax.swing.BorderFactory.createEmptyBorder(10, 14, 10, 14)));
        txtContrasena.setCaretColor(new java.awt.Color(166, 53, 94));

        chkMostrarContrasena.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        chkMostrarContrasena.setForeground(new java.awt.Color(111, 85, 98));
        chkMostrarContrasena.setText("Mostrar contraseña");
        chkMostrarContrasena.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        chkMostrarContrasena.setFocusPainted(false);
        chkMostrarContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkMostrarContrasenaActionPerformed(evt);
            }
        });

        btnIngresar.setBackground(new java.awt.Color(166, 53, 94));
        btnIngresar.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresar.setText("Iniciar sesión");
        btnIngresar.setBorderPainted(false);
        btnIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIngresar.setFocusPainted(false);
        btnIngresar.setPreferredSize(new java.awt.Dimension(400, 46));

        btnSalir.setBackground(new java.awt.Color(255, 204, 204));
        btnSalir.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(172, 55, 64));
        btnSalir.setText("Salir");
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSalir.setFocusPainted(false);
        btnSalir.setMargin(new java.awt.Insets(4, 14, 4, 14));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        lblContrasena.setFont(new java.awt.Font("SansSerif", 1, 13)); // NOI18N
        lblContrasena.setForeground(new java.awt.Color(59, 40, 49));
        lblContrasena.setText("Contraseña");

        javax.swing.GroupLayout pnlFormularioLayout = new javax.swing.GroupLayout(pnlFormulario);
        pnlFormulario.setLayout(pnlFormularioLayout);
        pnlFormularioLayout.setHorizontalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnSalir)
                            .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(lblCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(lblContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(chkMostrarContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 19, Short.MAX_VALUE))
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFormularioLayout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addGroup(pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblMensaje)
                            .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlFormularioLayout.setVerticalGroup(
            pnlFormularioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFormularioLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblAcceso)
                .addGap(30, 30, 30)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblMensaje)
                .addGap(38, 38, 38)
                .addComponent(lblCorreo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblContrasena)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chkMostrarContrasena)
                .addGap(36, 36, 36)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(btnSalir)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout pnlAccesoLayout = new javax.swing.GroupLayout(pnlAcceso);
        pnlAcceso.setLayout(pnlAccesoLayout);
        pnlAccesoLayout.setHorizontalGroup(
            pnlAccesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccesoLayout.createSequentialGroup()
                .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );
        pnlAccesoLayout.setVerticalGroup(
            pnlAccesoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAccesoLayout.createSequentialGroup()
                .addComponent(pnlFormulario, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlIdentidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAcceso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlIdentidad, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                    .addComponent(pnlAcceso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkMostrarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkMostrarContrasenaActionPerformed
        txtContrasena.setEchoChar(chkMostrarContrasena.isSelected() ? (char) 0 : caracterOculto);
    }//GEN-LAST:event_chkMostrarContrasenaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> {
        Login login = new Login();

        controlador.AutenticacionControlador autenticacion =
                new controlador.AutenticacionControlador();

        controlador.LoginControlador control =
                new controlador.LoginControlador(
                        login,
                        autenticacion,
                        sesion -> javax.swing.JOptionPane.showMessageDialog(
                                login,
                                "Ingreso correcto.\nNombre: " + sesion.getNombre()
                                        + "\nPerfil: " + sesion.getTipoUsuario(),
                                "Inicio de sesión",
                                javax.swing.JOptionPane.INFORMATION_MESSAGE
                        )
                );

        control.mostrar();
    });
}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JCheckBox chkMostrarContrasena;
    private javax.swing.JLabel lblAcceso;
    private javax.swing.JLabel lblContrasena;
    private javax.swing.JLabel lblCorreo;
    private javax.swing.JLabel lblDescripcionDos;
    private javax.swing.JLabel lblDescripcionUno;
    private javax.swing.JLabel lblFraseDos;
    private javax.swing.JLabel lblFraseUno;
    private javax.swing.JLabel lblMarca;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlAcceso;
    private javax.swing.JPanel pnlFormulario;
    private javax.swing.JPanel pnlIdentidad;
    private javax.swing.JPanel pnlLibroDorado;
    private javax.swing.JPanel pnlLibroDoradoMarcaInferior;
    private javax.swing.JPanel pnlLibroDoradoMarcaSuperior;
    private javax.swing.JPanel pnlLibroNaranja;
    private javax.swing.JPanel pnlLibroNaranjaMarcaInferior;
    private javax.swing.JPanel pnlLibroNaranjaMarcaSuperior;
    private javax.swing.JPanel pnlLibroRosado;
    private javax.swing.JPanel pnlLibroRosadoMarcaInferior;
    private javax.swing.JPanel pnlLibroRosadoMarcaSuperior;
    private javax.swing.JPanel pnlLibros;
    private javax.swing.JPanel pnlPresentacion;
    private javax.swing.JPasswordField txtContrasena;
    private javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables
}
