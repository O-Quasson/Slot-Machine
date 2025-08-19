import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.util.Objects;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.io.File;
import javax.sound.sampled.*;
import java.lang.Thread;
import java.io.IOException;
import java.util.Objects;

public class Slot {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tigrinho");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);

        File dir1 = new File("./src/win");
        File dir2 = new File("./src/lose");
        File dir3 = new File("./src/gambling");

        File winSound = null;
        for (File file : Objects.requireNonNull(dir1.listFiles())) {
            if (file.getName().toLowerCase().endsWith(".wav")) {
                winSound = file;
                break;
            }
        }

        File loseSound = null;
        for (File file : Objects.requireNonNull(dir2.listFiles())) {
            if (file.getName().toLowerCase().endsWith(".wav")) {
                loseSound = file;
                break;
            }
        }

        File gamblingSound = null;
        for (File file : Objects.requireNonNull(dir3.listFiles())) {
            if (file.getName().toLowerCase().endsWith(".wav")) {
                gamblingSound = file;
                break;
            }
        }

        AudioInputStream audioStream1 = null;
        AudioInputStream audioStream2 = null;
        AudioInputStream audioStream3 = null;

        Clip[] clip1 = new Clip[2];
        try {
            audioStream1 = AudioSystem.getAudioInputStream(winSound);
            clip1[0] = AudioSystem.getClip();
            clip1[0].open(audioStream1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        Clip[] clip2 = new Clip[2];
        try {
            audioStream2 = AudioSystem.getAudioInputStream(loseSound);
            clip2[0] = AudioSystem.getClip();
            clip2[0].open(audioStream2);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        Clip[] clip3 = new Clip[2];
        try {
            audioStream3 = AudioSystem.getAudioInputStream(gamblingSound);
            clip3[0] = AudioSystem.getClip();
            clip3[0].open(audioStream3);
            clip3[0].start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        JPanel painelGeral = new JPanel();
        JPanel painel = new JPanel();
        JPanel painel1 = new JPanel();
        JPanel painel2 = new JPanel();
        JPanel painel3 = new JPanel();
        JPanel painelResultado = new JPanel();

        painelGeral.add(painelResultado);
                
        painelGeral.setBackground(Color.RED);
        frame.add(painelGeral);
        painelGeral.setBounds(0, 0, 500, 500);
        painelGeral.setLayout(null);
        painel.setLayout(null);

        painel.setBackground(Color.BLACK);
        painel1.setBackground(Color.WHITE);
        painel2.setBackground(Color.WHITE);
        painel3.setBackground(Color.WHITE);
        painelResultado.setBackground(Color.WHITE);

        JButton botao = new JButton("Apostar");
        int[] nums = new int[3];
        Random random = new Random();
        JLabel[] numeros = new JLabel[3];
        numeros[0] = new JLabel("0");
        numeros[1] = new JLabel("0");
        numeros[2] = new JLabel("0");

        painel1.add(numeros[0]);
        painel2.add(numeros[1]);
        painel3.add(numeros[2]);
        
        painel.add(painel1);
        painel.add(painel2);
        painel.add(painel3);

        painel1.setBounds(25,15,100, 150);
        painel2.setBounds(130,15,100, 150);
        painel3.setBounds(235,15,100,150);
        
        numeros[0].setFont(new Font("Hourglass", Font.BOLD, 100));
        numeros[1].setFont(new Font("Hourglass", Font.BOLD, 100));
        numeros[2].setFont(new Font("Hourglass", Font.BOLD, 100));
    
        JLabel resultato = new JLabel();
        painelResultado.add(resultato);
        resultato.setBounds(90, 25, 180, 50);
        resultato.setFont(new Font("Hourglass", Font.BOLD, 25));

        botao.addActionListener(apostar -> {
            for(int i = 0; i<3; i++){
                nums[i] = random.nextInt(8);
                numeros[i].setText("" + nums[i]);
            }
            
            if((nums[0]==nums[1])&&(nums[1]==nums[2])&&(nums[2]==nums[0])){
                resultato.setText("Você ganhou!");
                clip1[0].setFramePosition(0);
                clip1[0].start();
            }else{
                resultato.setText("Fudido!");
                clip2[0].setFramePosition(0);
                clip2[0].start();
            }

        });

        painelGeral.add(botao);
        painelGeral.add(painel);

        painel.setBounds(70,50, 360, 175);
        botao.setBounds(150,250,200,50);
        painelResultado.setBounds(70,350,360,50);
        
        frame.setVisible(true);
    }
}