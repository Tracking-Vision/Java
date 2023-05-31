/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package swing.tracking.vision;

import com.github.britooo.looca.api.core.Looca;
import com.github.britooo.looca.api.group.discos.DiscoGrupo;
import com.github.britooo.looca.api.group.janelas.JanelaGrupo;
import com.github.britooo.looca.api.group.rede.Rede;
import com.github.britooo.looca.api.group.rede.RedeInterface;
import com.hideki.tracking.vision.*;
import org.json.JSONObject;

import javax.swing.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author PAULOROBERTODEALMEID
 */
public class LogSwing extends javax.swing.JFrame {

    LogService logService = new LogService();
    MaquinaService maquinaService = new MaquinaService();
    API api = new API();
    Looca looca = new Looca();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSair;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel panelLog;

    /**
     * Creates new form Log
     */
    public LogSwing() throws IOException, InterruptedException {
        initComponents();
        capturaDados();
        setLocationRelativeTo(null);
        System.out.println(looca.getMemoria().toString());
//
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
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
            java.util.logging.Logger.getLogger(LogSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LogSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LogSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LogSwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new LogSwing().setVisible(true);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void capturaDados() throws IOException, InterruptedException {

        Rede rede = looca.getRede();
        JanelaGrupo janelaGrupo = looca.getGrupoDeJanelas();
        DiscoGrupo disco = looca.getGrupoDeDiscos();


        List<Maquina> hostname = maquinaService.buscarPeloHostname(rede.getParametros().getHostName());
        List<Maquina> hostnameMysql = maquinaService.buscarPeloHostnameMySql(rede.getParametros().getHostName());


        //Frequncia do processador convertida para GHz
        Double usoDisco = (double) (api.getDisco().get(0).getTamanho() - disco.getVolumes().get(0).getDisponivel());
        usoDisco = usoDisco / 1073741824.00;

        //Uso da ram to GB
        Double usoRam = Double.valueOf(api.getMemoriaEmUso());
        usoRam = usoRam / 1073741824.00;

        Double finalUsoDisco = usoDisco;
        Double finalUsoRam = usoRam;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                List<String> janelas = new ArrayList<>();
                List<Long> janelasPid = new ArrayList<>();

                for (int i = 0; i < janelaGrupo.getTotalJanelasVisiveis(); i++) {
                    if (janelaGrupo.getJanelasVisiveis().get(i).getTitulo().length() > 0) {
                        janelas.add(janelaGrupo.getJanelasVisiveis().get(i).getTitulo());
                        janelasPid.add(janelaGrupo.getJanelasVisiveis().get(i).getPid());
                    }
                }
                List<RedeInterface> redes = new ArrayList<>();

                for (int i = 0; i < rede.getGrupoDeInterfaces().getInterfaces().size(); i++) {

                    if (!rede.getGrupoDeInterfaces().getInterfaces().get(i).getEnderecoIpv4().isEmpty() && rede.getGrupoDeInterfaces().getInterfaces().get(i).getPacotesRecebidos() > 0 && rede.getGrupoDeInterfaces().getInterfaces().get(i).getPacotesEnviados() > 0) {

                        redes.add(rede.getGrupoDeInterfaces().getInterfaces().get(i));
                        break;

                    }
                }

                for (int j = 0; j < janelas.size(); j++) {
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
                    Log log = new Log(null, timeStamp, janelasPid.get(j), janelas.get(j), api.getProcessador().getUso(), finalUsoDisco, finalUsoRam, (redes.get(0).getBytesRecebidos() * 8) / 1000000, (redes.get(0).getBytesEnviados() * 8) / 1000000, hostname.get(0).getIdMaquina());
                    Log logMysql = new Log(null, timeStamp, janelasPid.get(j), janelas.get(j), api.getProcessador().getUso(), finalUsoDisco, finalUsoRam, (redes.get(0).getBytesRecebidos() * 8) / 1000000, (redes.get(0).getBytesEnviados() * 8) / 1000000, hostnameMysql.get(0).getIdMaquina());
                    System.out.println(log);
                    logService.salvarLog(log);
                    logService.salvarLogMysql(logMysql);

                    LimitesService limitesService = new LimitesService();
                    List<Limites> limites = limitesService.retornarLimites(log.getFkMaquina());
                    JSONObject json = new JSONObject();
                    System.out.println("Envia mensagem para o slack");
                    json.put("text", "Aviso de uso de recursos \n" + "Processador: " + log.getUsoCpu() + "%\n" + "Disco: " + log.getUsoDisco() + "GB\n" + "Memoria: " + log.getUsoRam() + "GB\n");
                    try {
                        Slack.sendMessage(json);
                    } catch (IOException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    JanelasBloqueadasService janelasBloqueadasService = new JanelasBloqueadasService();
                    System.out.println(hostname.get(0).getFkEmpresa());
                    List<JanelasBloqueadas> janelasBloqueadasList = janelasBloqueadasService.retornarJanelasBloqueadas(hostname.get(0).getFkEmpresa());

                    for (JanelasBloqueadas janelasBloqueadas : janelasBloqueadasList) {
                        if (janelas.get(j).toLowerCase().contains(janelasBloqueadas.getNome().toLowerCase())) {
                            JOptionPane.showMessageDialog(null, "seu computador sera desligado");
                            try {
                                if(looca.getSistema().getSistemaOperacional().equalsIgnoreCase("windows")) {
                                Runtime.getRuntime().exec("shutdown -s -t 120");

                                }else {
                                    Runtime.getRuntime().exec("sudo shutdown now");
                                }
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                }
            }
        }, 0, 60000);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelLog = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        panelLog.setBackground(new java.awt.Color(188, 76, 228));

        jLabel7.setFont(new java.awt.Font("Bahnschrift", 1, 24)); // NOI18N
        jLabel7.setText("Os dados da maquina estão sendo capturados e armazenados.");

        jLabel2.setFont(new java.awt.Font("Bahnschrift", 1, 48)); // NOI18N
        jLabel2.setText("Tracking Vision - Log");

        btnSair.setBackground(new java.awt.Color(102, 0, 102));
        btnSair.setFont(new java.awt.Font("Bahnschrift", 1, 18)); // NOI18N
        btnSair.setForeground(new java.awt.Color(255, 255, 255));
        btnSair.setText("Parar Captura");
        btnSair.setBorder(null);
        btnSair.setBorderPainted(false);
        btnSair.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnSair.setFocusPainted(false);
        btnSair.setFocusable(false);
        btnSair.setMargin(new java.awt.Insets(0, 0, 0, 0));
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLogLayout = new javax.swing.GroupLayout(panelLog);
        panelLog.setLayout(panelLogLayout);
        panelLogLayout.setHorizontalGroup(panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(panelLogLayout.createSequentialGroup().addGap(189, 189, 189).addComponent(jLabel2).addGap(0, 0, Short.MAX_VALUE)).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogLayout.createSequentialGroup().addContainerGap(113, Short.MAX_VALUE).addComponent(jLabel7).addGap(53, 53, 53)).addGroup(panelLogLayout.createSequentialGroup().addGap(336, 336, 336).addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE).addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        panelLogLayout.setVerticalGroup(panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLogLayout.createSequentialGroup().addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE).addGap(12, 12, 12).addComponent(jLabel7).addGap(42, 42, 42).addComponent(btnSair, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(67, 67, 67)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(panelLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(panelLog, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(0, 0, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnSairActionPerformed
    // End of variables declaration//GEN-END:variables
}
