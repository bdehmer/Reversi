public class Game {
				public static final char black = 'B';
				public static final char white = 'W';
				public char[][] board = { 
								{'_', '_', '_', '_', '_', '_', '_', '_'},
								{'_', '_', '_', '_', '_', '_', '_', '_'},
								{'_', '_', '_', '_', '_', '_', '_', '_'},
								{'_', '_', '_', 'W', 'B', '_', '_', '_'},
								{'_', '_', '_', 'B', 'W', '_', '_', '_'},
								{'_', '_', '_', '_', '_', '_', '_', '_'},
								{'_', '_', '_', '_', '_', '_', '_', '_'},
								{'_', '_', '_', '_', '_', '_', '_', '_'},
				};

				public Game() {
				}

				public void displayBoard() {
								for(int i = 0; i < 8; i++) {
												if (i == 0) {
																System.out.println("   1  2  3  4  5  6  7  8");
												}
												else {
																System.out.println();
												}
												System.out.print((i + 1) + " ");
												for (int j = 0; j < 8; j++) {
																System.out.print(" " + this.board[i][j] + " ");
												}
								}
								System.out.println();
				}

				public int gameResult(Point[] whitePlaceableLocations, Point[] blackPlaceableLocations) {
								if (Reversi.isEmpty(whitePlaceableLocations) && Reversi.isEmpty(blackPlaceableLocations)) {
												int white = 0;
												int black = 0;
												for (int i = 0; i < board.length; i++) {
																for (int j = 0; j < board.length; j++) {
																				if (board[i][j] == 'B') {
																								black++;
																				}
																				else if (board[i][j] == 'W') {
																								white++;
																				}
																}
												}
												if ( black > white )  {
																System.out.println("Black Won!");
																System.out.println("Black: " + black + " White: " + white);
																return -1;
												}
												else if ( white > black) {
																System.out.println("White Won!");
																System.out.println("White: " + white + " Black: " + black);
																return 1;
												}
												else {
																System.out.println("It's a Tie!");
																System.out.println("White: " + white + " Black: " + black);
																return 0;
												}

												
								}
								return 99;
				}

				public Point[] getPlaceableLocations(char player, char opponent) {
								Point[] placeables = new Point[64];

								for (int i = 0; i < board[0].length; i++) {
												for (int j = 0; j < board.length; j++) {
																if (board[i][j] == player) {
																				int temp = i;
																				boolean checking = false;
																				//top Down check
																				while ( ++temp < board.length ) {
																								if (board[temp][j] == opponent) {
																												checking = true;
																								}
																								else if (board[temp][j] == '_' && checking) {
																												placeables[(i*8) + j] = new Point(temp, j);
																												break;
																								}
																								else {
																												break;
																								}
																				}
																				//bottom up check
																				temp = i;
																				checking = false;
																				while ( --temp >= 0 ) {
																								if (board[temp][j] == opponent) {
																												checking = true;
																								}
																								else if (board[temp][j] == '_' && checking) {
																												placeables[(temp*8) + j] = new Point(temp, j);
																												break;
																								}
																								else {
																												break;
																								}
																				}
																				//left to right check
																				temp = j;
																				checking = false;
																				while ( ++temp < board[0].length ) {
																								if (board[i][temp] == opponent) {
																												checking = true;
																								}
																								else if (board[i][temp] == '_' && checking) {
																												placeables[(i*8) + temp] = new Point(i, temp);
																												break;
																								}
																								else {
																												break;
																								}
																				}
																				temp = j;
																				checking = false;
																				//right to left check
																				while ( --temp >= 0 ) {
																								if (board[i][temp] == opponent) {
																												checking = true;
																								}
																								else if (board[i][temp] == '_' && checking) {
																												placeables[(i*8) + temp] = new Point(i, temp);
																												break;
																								}
																								else {
																												break;
																								}
																				}
																				temp = i;
																				int temp2 = j;
																				checking = false;
																				//up/left diagonally check
																				
																				while ( --temp >= 0 && --temp2 >= 0  ) {
																								if (board[temp][temp2] == opponent) {
																												checking = true;
																								}
																								else if (board[temp][temp2] == '_' && checking) {
																												placeables[(temp*8) + temp2] = new Point(temp, temp2);
																												break;
																								}
																								else {
																												break;
																								}
																				}

																				temp = i;
																				temp2 = j;
																				checking = false;

																				// up right diagonally check 
																				while ( --temp >= 0 && ++temp2 < board[0].length) {
																								if (board[temp][temp2] == opponent) {
																												checking = true;
																								}
																								else if (board[temp][temp2] == '_' && checking) {
																												placeables[(temp * 8) + temp2] = new Point(temp, temp2);
																												break;
																								}
																								else {
																												break;
																								}
																				}
																				temp = i;
																				temp2 = j;
																				checking = false;

																				//down left diagonally check
																				while (++temp < board.length && --temp2 >= 0) {
																								if (board[temp][temp2] == opponent) {
																												checking = true;
																								}
																								else if (board[temp][temp2] == '_' && checking) {
																												placeables[(temp * 8) + temp2] = new Point(temp, temp2);
																												break;
																								}
																								else {
																												break;
																								}
																				}
																				temp = i;
																				temp2 = j;
																				checking = false;

																				//down right diagonally check
																				while (++temp < board.length && ++temp2 < board[0].length) {
																								if (board[temp][temp2] == opponent) {
																												checking = true;
																								}
																								else if (board[temp][temp2] == '_' && checking) {
																												placeables[(temp * 8) + temp2] = new Point(temp, temp2);
																												break;
																								}
																								else {
																												break;
																								}
																				}
																}
												}
								}
								return placeables;
				}

				public void showPlaceableLocations(Point[] locations, char player, char opponent) {
								for (Point p : locations) {
											if (p != null) {
															board[p.x][p.y] = '*';
											}
								}
								this.displayBoard();
				}

				public void placeMove(Point p, char player, char opponent) {

								if (board[p.x][p.y] == '*') {
												board[p.x][p.y] = player;
								}
								
								int temp = p.x;
								boolean checking = false;
								int count = 0;
								//top down
								while ( ++temp < board.length ) {
												if (board[temp][p.y] == opponent) {
																checking = true;
																count++;
												}
												//had [temp][i] and wasn't breaking??
												else if (board[temp][p.y] == player && checking) {
																while (count > 0) {
																				board[--temp][p.y] = player;
																				count--;
																}
																break;
												}
												else {
																break;
												}
								}
								//double check this p.y (compare to other method)
								temp = p.x;
								checking = false;
								count = 0;
								//bottom up check
								while ( --temp >= 0 ) {
												if (board[temp][p.y] == opponent) {
																checking = true;
																count++;
												}
												else if (board[temp][p.y] == player && checking) {
																while (count > 0) {
																				board[++temp][p.y] = player;
																				count--;
																}
																break;
												}
												else {
																break;
												}
								}
								temp = p.y;
								checking = false;
								count = 0;
								//left to right
								while ( ++temp < board.length ) {
												if (board[p.x][temp] == opponent) {
																checking = true;
																count++;
												}
												else if (board[p.x][temp] == player && checking) {
																while ( count > 0 ) {
																				board[p.x][--temp] = player;
																				count--;
																}
																break;
												}
												else {
																break;
												}
								}
								temp = p.y;
								checking = false;
								count = 0;
								//right to left check 
								while ( --temp >= 0 ) {
												if (board[p.x][temp] == opponent) {
																checking = true;
																count++;
												}
												else if (board[p.x][temp] == player && checking) {
																while (count > 0) {
																				board[p.x][++temp] = player;
																				count--;
																}
																break;
												}
												else {
																break;
												}
								}
								temp = p.x;
								int temp2 = p.y;
								checking = false;
								count = 0;
								//up left diagonally
								while ( --temp >= 0 && --temp2 >= 0 ) {
												if (board[temp][temp2] == opponent) {
																checking = true;
																count++;
												}
												else if (board[temp][temp2] == player && checking) {
																while (count > 0) {
																				board[++temp][++temp2] = player;
																				count--;
																}
																break;
												}
												else {
																break;
												}
								}
								temp = p.x;
								temp2 = p.y;
								checking = false;
								count = 0;
								
								//up right diagonally
								while ( --temp >= 0 && ++temp2 < board.length ) {
												if (board[temp][temp2] == opponent) {
																checking = true;
																count++;
												}
												else if (board[temp][temp2] == player && checking) {
																while (count > 0) {
																				board[++temp][--temp2] = player;
																				count--;
																}
																break;
												}
												else {
																break;
												}
								}
								temp = p.x;
								temp2 = p.y;
								checking = false;
								count = 0;

								//down right diagonally
								while ( ++temp < board.length && ++temp2 < board.length ) {
												if (board[temp][temp2] == opponent) {
																checking = true;
																count++;
												}
												else if (board[temp][temp2] == player && checking) {
																while (count > 0) {
																				board[--temp][--temp2] = player;
																				count--;
																}
																break;
												}
												else {
																break;
												}
								}
								temp = p.x;
								temp2 = p.y;
								checking = false;
								count = 0;

								//down left diagonally
								while ( ++temp < board.length && --temp2 >= 0 ) {
												if (board[temp][temp2] == opponent) {
																checking = true;
																count++;
												}
												else if (board[temp][temp2] == player && checking) {
																while (count > 0) {
																				board[--temp][++temp2] = player;
																				count--;
																}
																break;
												}
												else {
																break;
												}
								}
								temp = p.x;
								temp2 = p.y;
								checking = false;
								count = 0;


								//clean up left over * spaces
								for (int i = 0; i < board[0].length; i++) {
												for (int j = 0; j < board.length; j++) {
																if (board[i][j] == '*') {
																				board[i][j] = '_';
																}
												}
								}
				}
}
