### 💡 Problem Definition

You are on a `1-N` board (say `1-100`). From each cell, you can move 1 to 6 steps ahead based on a dice throw. If you land on a **ladder**, you go **up** to its destination. If you land on a **snake**, you go **down** to its tail.

---

### ✅ BFS Approach

1. Treat the board as a **1D array**.
2. Maintain a **visited\[]** array to avoid revisiting cells.
3. Use a **queue** for BFS: Each node in the queue contains `(position, distance)` where:

   * `position` = current cell
   * `distance` = number of dice throws taken to reach here
4. For every position, explore all dice throws (1 to 6):

   * If you land on a snake or ladder, move accordingly.

---

### 📦 Java Code Implementation

```java
import java.util.*;

class SnakeLadderBFS {

    static class Node {
        int position;
        int moves;

        Node(int position, int moves) {
            this.position = position;
            this.moves = moves;
        }
    }

    static int minDiceThrows(int[] board, int N) {
        boolean[] visited = new boolean[N];
        Queue<Node> queue = new LinkedList<>();

        // Start from cell 0
        visited[0] = true;
        queue.add(new Node(0, 0));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            // Reached last cell
            if (curr.position == N - 1)
                return curr.moves;

            // Explore dice throws
            for (int dice = 1; dice <= 6; dice++) {
                int next = curr.position + dice;

                if (next < N && !visited[next]) {
                    visited[next] = true;

                    // Check for snake or ladder
                    int destination = (board[next] != -1) ? board[next] : next;

                    queue.add(new Node(destination, curr.moves + 1));
                }
            }
        }

        return -1; // not reachable
    }

    public static void main(String[] args) {
        int N = 30;
        int[] board = new int[N];
        Arrays.fill(board, -1);

        // Ladders
        board[2] = 21;
        board[4] = 7;
        board[10] = 25;
        board[19] = 28;

        // Snakes
        board[26] = 0;
        board[20] = 8;
        board[16] = 3;
        board[18] = 6;

        System.out.println("Minimum Dice Throws: " + minDiceThrows(board, N));
    }
}
```

---
```
### 🔍 Example

If there’s a ladder from 2 to 21, and you roll a 2 on your first move, you jump from 0 → 2 → 21 in **1 dice throw**.

---

### 🧠 Time Complexity

* **O(N)**: Each cell is visited at most once.
* **Space**: O(N) for visited\[] and queue.

```
