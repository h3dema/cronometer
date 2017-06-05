import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.util.Timer;
import java.util.TimerTask;

class Cronometro{
  
  JLabel contagemTempo;
  boolean running = false;
  Timer tm;
  Integer contador = 0;
  final int fontsize = 100;
  final int timetick = 1000;
  
  public void init() {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame janela = new JFrame("Cronometro");
    janela.setSize(300,200);
    janela.setAlwaysOnTop(true);
    janela.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    janela.setLayout(new BorderLayout());
    
    contagemTempo = new JLabel("00:00:00");
    contagemTempo.setFont(new Font(contagemTempo.getName(), Font.PLAIN, fontsize));
    janela.add(contagemTempo, BorderLayout.CENTER);    
    
    JPanel painel = new JPanel();
    painel.setLayout(new GridLayout(2, 1));
        
    JButton btIniciar = new JButton("Iniciar");
    btIniciar.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (!running) {
          contagemTempo.setText("00:00:00");
          tm = new Timer();
          tm.scheduleAtFixedRate(new TimerTask() {
            
            @Override
            public void run() {
              contador++;
              int seg = contador % 60;
              int min = contador / 60;
              int hora = min / 60;
              min %= 60;
              contagemTempo.setText(String.format("%02d:%02d:%02d", hora,min,seg));
            }
          }, timetick, timetick);
          running = true;
        }
      }
    });
    
    JButton btReset = new JButton("Parar");
    btReset.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        if (running) {
          tm.cancel();
          running = false;
          contador = 0;          
        }
      }
    });
    
    painel.add(btIniciar);
    painel.add(btReset);
    
    janela.add(painel,BorderLayout.EAST);
    
    janela.pack();
    janela.setVisible(true);
  }
  
  public static void main(String[] args) {
    Cronometro c = new Cronometro();
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        c.init();
      }
    });
  }
  
}