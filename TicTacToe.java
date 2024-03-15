import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame implements ActionListener {
    private JButton[] buttons = new JButton[9];
    private JLabel label = new JLabel("Tic Tac Toe Game", SwingConstants.CENTER);
    private String currentPlayer = "X";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TicTacToe());
    }

    public TicTacToe() {
        setLayout(new BorderLayout());
        add(label, BorderLayout.NORTH);
        JPanel panel = new JPanel(new GridLayout(3, 3));
        for (int i = 0; i < buttons.length; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(this);
            panel.add(buttons[i]);
        }
        add(panel, BorderLayout.CENTER);
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.getText().equals("")) {
            button.setText(currentPlayer);
            button.setEnabled(false);
            if (checkWinner(currentPlayer)) {
                JOptionPane.showMessageDialog(this, currentPlayer + " wins!");
                System.exit(0);
            }
            currentPlayer = currentPlayer.equals("X") ? "O" : "X";
        }
    }

    private boolean checkWinner(String player) {
        for (int i = 0; i < 3; i++) {
            if (checkRow(i, player) || checkColumn(i, player)) {
                return true;
            }
        }
        return checkDiagonals(player);
    }

    private boolean checkRow(int row, String player) {
        for (int i = 0; i < 3; i++) {
            if (!buttons[row * 3 + i].getText().equals(player)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int column, String player) {
        for (int i = 0; i < 3; i++) {
            if (!buttons[column + i * 3].getText().equals(player)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonals(String player) {
        boolean mainDiagonal = true;
        boolean secondaryDiagonal = true;

        for (int i = 0; i < 3; i++) {
            if (!buttons[i * 3 + i].getText().equals(player)) {
                mainDiagonal = false;
            }
            if (!buttons[i * 3 + (2 - i)].getText().equals(player)) {
                secondaryDiagonal = false;
            }
        }

        return mainDiagonal || secondaryDiagonal;
    }
}