import javax.swing.JFrame;
import javax.swing.JButton;
import java.util.Random;
import javax.swing.JLabel;

public class Slot {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tigrinho");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);

        JButton botao = new JButton("Apostar");
        int[] nums = new int[3];
        Random random = new Random();
        JLabel[] numeros = new JLabel[3];
        JLabel resultato = new JLabel();
        botao.addActionListener(apostar -> {
            for(int i = 0; i<3; i++){
                nums[i] = random.nextInt(8);
                numeros[i] = new JLabel("Número " + i + " = " + nums[i]);
            }
            
            if((nums[0]==nums[1])&&(nums[1]==nums[2])&&(nums[2]==nums[0])){
                resultato.setText("Você ganhou!");
            }else{
                resultato.setText("Fudido!");
            }

        });

        frame.add(resultato);
        frame.add(numeros[0]);
        frame.add(numeros[1]);
        frame.add(numeros[2]);
        frame.add(botao);
        
        
        frame.setVisible(true);
    }
}
