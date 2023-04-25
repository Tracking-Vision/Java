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
import com.hideki.tracking.vision.API;
import com.hideki.tracking.vision.Log;
import com.hideki.tracking.vision.LogService;
import com.hideki.tracking.vision.Maquina;
import com.hideki.tracking.vision.MaquinaService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author PAULOROBERTODEALMEID
 */
public class LogSwing extends javax.swing.JFrame {

    /**
     * Creates new form Log
     */
    public LogSwing() {
        initComponents();
        capturaDados();
    }

    private void capturaDados() {
        LogService logService = new LogService();
        MaquinaService maquinaService = new MaquinaService();

        API api = new API();
        Looca looca = new Looca();
        Rede rede = looca.getRede();
        JanelaGrupo janelaGrupo = looca.getGrupoDeJanelas();
        DiscoGrupo disco = looca.getGrupoDeDiscos();

        List<Maquina> hostname = maquinaService.buscarPeloHostname(rede.getParametros().getHostName());

        //Frequncia do processador convertida para GHz
        Double usoDisco = Double.valueOf(api.getDisco().get(0).getTamanho() - disco.getVolumes().get(0).getDisponivel());
        usoDisco = usoDisco / 1073741824.00;

        //Uso da ram to GB
        Double usoRam = Double.valueOf(api.getMemoriaEmUso());
        usoRam = usoRam / 1073741824.00;

        Double finalUsoDisco = usoDisco;
        Double finalUsoRam = usoRam;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
//                        Double usoDisco = Double.valueOf(api.getDisco().get(0).getTamanho() - disco.getVolumes().get(0).getDisponivel());
//                        usoDisco = usoDisco / 1073741824.00;
//
//                        //Uso da ram to GB
//                        Double usoRam = Double.valueOf(api.getMemoriaEmUso());
//                        usoRam = usoRam / 1073741824.00;

                String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());

                lblCPu.setText(String.format("%.2f", (api.getProcessadorEmUso())));
                lblDisco.setText(String.format("%.2f", (finalUsoDisco)));
                lblHora.setText(timeStamp);
                lblRam.setText(String.format("%.2f", (finalUsoRam)));
            }
        }, 0, 5000);

        Double finalUsoDisco1 = usoDisco;
        Double finalUsoRam1 = usoRam;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Dentro do timertask");
                List<String> janelas = new ArrayList();
                List<Long> janelasPid = new ArrayList();
                System.out.println("FOR JANELAS: ");
                for (int i = 0; i < janelaGrupo.getTotalJanelasVisiveis(); i++) {
                    if (janelaGrupo.getJanelasVisiveis().get(i).getTitulo().length() > 0) {
                        janelas.add(janelaGrupo.getJanelasVisiveis().get(i).getTitulo());
                        janelasPid.add(janelaGrupo.getJanelasVisiveis().get(i).getPid());
                    }
                }
                System.out.println("FOR INSERT: " + janelas.size());
                List<RedeInterface> redes = new ArrayList();

                for (int i = 0; i < rede.getGrupoDeInterfaces().getInterfaces().size(); i++) {

                    if (!rede.getGrupoDeInterfaces().getInterfaces().get(i).getEnderecoIpv4().isEmpty() && rede.getGrupoDeInterfaces().getInterfaces().get(i).getPacotesRecebidos() > 0 && rede.getGrupoDeInterfaces().getInterfaces().get(i).getPacotesEnviados() > 0) {

                        redes.add(rede.getGrupoDeInterfaces().getInterfaces().get(i));

                    }
                }

                for (int j = 0; j < janelas.size(); j++) {
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
                    Log log = new Log(null, timeStamp, janelasPid.get(j), janelas.get(j), api.getProcessador().getUso(), finalUsoDisco1, finalUsoRam1, redes.get(0).getBytesRecebidos(), redes.get(0).getBytesEnviados(), hostname.get(0).getIdMaquina());
                    logService.salvarLog(log);

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

        jLabel2 = new javax.swing.JLabel();
        panelLog = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblCPu = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblDisco = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblRam = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel2.setText("Tracking Vision - Log");

        panelLog.setBackground(new java.awt.Color(204, 153, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel1.setText("Horario Capturado: ");

        lblHora.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblHora.setText("--");

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setText("Uso da CPU: ");

        lblCPu.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblCPu.setText("--");

        jLabel4.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel4.setText("Uso do Disco: ");

        lblDisco.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblDisco.setText("--");

        jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel5.setText("Uso da Ram: ");

        lblRam.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblRam.setText("--");

        jLabel7.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel7.setText("A inserção desses dados na nuvem ocorrem a cada minuto");

        javax.swing.GroupLayout panelLogLayout = new javax.swing.GroupLayout(panelLog);
        panelLog.setLayout(panelLogLayout);
        panelLogLayout.setHorizontalGroup(
            panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addGroup(panelLogLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDisco))
                    .addGroup(panelLogLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCPu))
                    .addGroup(panelLogLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblHora))
                    .addGroup(panelLogLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblRam)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        panelLogLayout.setVerticalGroup(
            panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLogLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(lblHora))
                .addGap(29, 29, 29)
                .addGroup(panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblCPu))
                .addGap(28, 28, 28)
                .addGroup(panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(lblDisco))
                .addGap(27, 27, 27)
                .addGroup(panelLogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(lblRam))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jLabel7))
        );

        jLabel6.setText("Dados atualizados a cada 5 segundos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(174, 174, 174))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(130, 130, 130)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelLog, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LogSwing().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lblCPu;
    private javax.swing.JLabel lblDisco;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblRam;
    private javax.swing.JPanel panelLog;
    // End of variables declaration//GEN-END:variables
}
