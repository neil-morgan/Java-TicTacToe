import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

// a class seems to be a way to group related objects and functions together, you can then use that class
// in other files to use those objects and functions without having to re-write them

// so if I look below, JFrame is a class that has objects and functions that are useful for creating a window
// so I can import the entire class and use it in my TicTacToe class to create a window
// similar concept to importing a library in JS

// public is an "access control modifier" - it allows other classes to access this class
// if it was private, other classes would not be able to access it
public class TicTacToe {
    int boardWith = 600;
    int boardHeight = 700;
    String gameName = new String("Tic Tac Toe");

    // JFrame frame - declares the type of variable and gives it a name
    // = new JFrame() - creates a new JFrame object which is assigned to the
    // variable
    JFrame frame = new JFrame(gameName);
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel resetPanel = new JPanel();
    JButton resetButton = new JButton("Reset");
    JButton[][] board = new JButton[3][3];

    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardWith, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.darkGray);
        textLabel.setForeground(Color.white);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText(gameName);
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.darkGray);

        resetButton.setBackground(Color.white);
        resetButton.setForeground(Color.darkGray);
        resetButton.setFont(new Font("Arial", Font.BOLD, 50));
        resetButton.setFocusable(false);

        resetPanel.setLayout(new BorderLayout());
        resetPanel.setPreferredSize(new Dimension(600, 50)); // Adjust the width and height as needed

        resetPanel.add(resetButton);


        frame.add(textPanel, BorderLayout.NORTH);
        frame.add(boardPanel);
        frame.add(resetPanel, BorderLayout.SOUTH);

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.darkGray);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver)
                            return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                            if (!gameOver) {
                                currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                textLabel.setText(currentPlayer + "'s turn");
                            }

                        }
                    }
                });
            }
        }

        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        board[r][c].setText("");
                        board[r][c].setBackground(Color.darkGray);
                        board[r][c].setForeground(Color.darkGray);
                    }
                }
                currentPlayer = playerX;
                textLabel.setText(currentPlayer + "'s turn");
                gameOver = false;
                turns = 0;
            }
        });
    }

    void checkWinner() {
        for (int r = 0; r < 3; r++) {
            if (board[r][0].getText() == "")
                continue;

            if (board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[r][i]);
                }
                gameOver = true;
                return;
            }
        }

        for (int c = 0; c < 3; c++) {
            if (board[0][c].getText() == "")
                continue;

            if (board[0][c].getText() == board[1][c].getText() &&
                    board[1][c].getText() == board[2][c].getText()) {
                for (int i = 0; i < 3; i++) {
                    setWinner(board[i][c]);
                }
                gameOver = true;
                return;
            }
        }

        if (board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText()
                && board[0][0].getText() != "") {
            for (int i = 0; i < 3; i++) {
                setWinner(board[i][i]);
            }
            gameOver = true;
            return;
        }

        if (board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText()
                && board[0][2].getText() != "") {
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver = true;
            return;
        }

        if (turns == 9) {
            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    setTie(board[r][c]);
                }
            }
            gameOver = true;

        }
    }

    void setWinner(JButton tile) {
        tile.setForeground(Color.gray);
        tile.setBackground(Color.green);
        textLabel.setText(currentPlayer + " is the winner!");
    }

    void setTie(JButton tile) {
        tile.setForeground(Color.gray);
        tile.setBackground(Color.red);
        textLabel.setText("It's a tie!");
    }

};