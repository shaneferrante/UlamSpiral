

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Board extends JPanel {

  private final int size = 801;
  private final int boxes = 801; //Must be odd and divisible by size
  private boolean drawNums = false;

  private final int[][] directions = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

  private int[][] board;
  private boolean[][] primes;

  public Board() {
    setPreferredSize(new Dimension(size, size));
    setFocusable(true);
    board = new int[boxes][boxes];
    primes = new boolean[boxes][boxes];
    setBackground(Color.BLACK);
    fillBoard();
  }

  private void fillBoard() {
    int mid = boxes/2;
    int direction = 0;
    int num = 1;
    int currR = mid;
    int currC = mid;
    int numUntilTurn = 1;
    int numPerTurn = 1;
    int phase = 0;
    for (int i = 0; i < boxes*boxes; i++) {
      board[currR][currC] = num;
      num++;
      currR += directions[direction][0];
      currC += directions[direction][1];
      numUntilTurn--;
      if (numUntilTurn==0) {
        phase++;
        if (phase % 2 == 0) {
          numPerTurn++;
        }
        numUntilTurn = numPerTurn;
        direction = (direction+1)%4;
      }
    }
    fillPrimes();
  }

  private void fillPrimes() {
    for (int i = 0; i < boxes; i++) {
      for (int j = 0; j < boxes; j++) {
        primes[i][j] = isPrime(board[i][j]);
      }
    }
  }

  private boolean isPrime(int num) {
    if (num==1) return false;
    for(int i = 2; i <= Math.sqrt(num); i++) {
      if (num%i==0) return false;
    }
    return true;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setFont(new Font("Arial", Font.BOLD, size/boxes/2));
    for (int i = 0; i < boxes; i++) {
      for (int j = 0; j < boxes; j++) {
        if (primes[i][j]) {
          g.setColor(Color.WHITE);
          g.fillOval(size/boxes*j, size/boxes*i, size/boxes, size/boxes);
          if (drawNums) {
            g.setColor(Color.BLACK);
            drawStringCentered(g, "" + board[i][j],
                    new Rectangle(size/boxes*j, size/boxes*i,
                            size/boxes, size/boxes));
          }
        }
      }
    }
  }

  private void drawStringCentered(Graphics g, String s, Rectangle r) {
    int x = r.x + r.width/2-g.getFontMetrics().stringWidth(s)/2;
    int y = r.y + r.height/2-g.getFontMetrics().getHeight()/2+g.getFontMetrics().getAscent();
    g.drawString(s, x, y);
  }

}
