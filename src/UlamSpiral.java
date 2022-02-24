import javax.swing.*;

public class UlamSpiral extends JFrame {

  public UlamSpiral() {
    add(new Board());
    setResizable(false);
    pack();
    setTitle("Ulam Spiral");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }

  public static void main(String[] args) {
    UlamSpiral ulam = new UlamSpiral();
  }

}
