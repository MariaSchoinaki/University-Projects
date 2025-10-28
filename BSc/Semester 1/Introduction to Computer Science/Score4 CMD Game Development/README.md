# **Score4 - Command Line Game Development**

## **Overview**
This project is a Python-based **command-line implementation of Score4**, a two-player strategy game similar to Connect Four. The game is played on a **grid-based board with 5 to 10 columns**, where players drop pieces ('O' and 'X') into a selected column. The objective is to connect **four pieces in a row, column, or diagonal** to win. The game supports **multiple rounds, score tracking, and save/load functionality**.

---

## **Features**
### **1. Game Mechanics**
- **Two-player turn-based gameplay**.
- Players take turns dropping a piece into a column.
- The piece settles into the lowest available row in that column.

### **2. Win Conditions**
- A player wins if they connect **four pieces in a row, column, or diagonal**.
- The game detects winning combinations and highlights them.

### **3. Multiple Rounds & Scoring**
- Players can **compete over multiple rounds**.
- After a winning move, the winning pieces are removed, and the remaining pieces slide down.
- **Score tracking** based on the number of removed pieces.

### **4. Game Saving & Loading**
- Players can **pause the game and save the current board state**.
- Saved games are stored in a **CSV file**, tracking the board layout and scores.
- Players can **resume the game** from the last saved state.

### **5. Input Validation**
- Prevents players from choosing **full columns**.
- Enforces a valid column range (1 - selected grid size).
- Ensures a proper numeric input for column selection.

---

## **Project Structure**
```
├── escor4.py                  # Python implementation of Score4 game
├── Score4 CMD Game Development.pdf  # Project documentation
├── README.md                  # Project overview and instructions
```

---

## **Installation & Execution**
### **Prerequisites**
- **Python 3.x** installed.
- Basic understanding of **command-line interaction**.

### **Running the Game**
To start a new game, run the following command:
```sh
python escor4.py
```

### **Saving & Loading Games**
- During gameplay, press **'s'** to save the current game.
- The program will prompt for a **filename**, which will store the game state.
- To load a saved game, restart the program and choose **'S' (load game)** instead of **'N' (new game)**.

---

## **How to Play**
1. **Choose the number of columns** (between 5 and 10) when starting a new game.
2. Players take turns selecting a column **(1 - max columns)** to drop their piece.
3. The first player to connect **four consecutive pieces** wins the round.
4. The game continues for the selected number of rounds.
5. The player with the highest total score at the end wins the match.

---

## **Testing & Debugging**
- The game logic is structured with **modular functions**, making it easy to test.
- The program includes **docstrings** and **comments** for clarity.

---

## **Future Enhancements**
- **AI opponent** for single-player mode.
- **GUI version** using `Tkinter` or `Pygame`.
- **Online multiplayer mode**.

---

## Contributors
- **Developer:** [Maria Schoinaki](https://github.com/MariaSchoinaki)