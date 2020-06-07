public class Reversi {
				public static void main(String[] args) {
								Game g = new Game();
								start(g);
				}

				public static void start(Game g) {
								char player = 'W';
								char opponent = 'B';

								while ( !isEmpty(g.getPlaceableLocations(opponent, player)) || !isEmpty(g.getPlaceableLocations(player, opponent))) {
												/*
												if (isEmpty(g.getPlaceableLocations(opponent, player))) {
																continue;
												}
												*/
												char temp = player;
												player = opponent;
												opponent = temp;

												if (isEmpty(g.getPlaceableLocations(player, opponent))) {
																continue;
												}


												System.out.println("Place move (" + player + "): row then column:");
												java.util.Scanner input = new java.util.Scanner(System.in);
								// error starts here 
												Point[] placeables = g.getPlaceableLocations(player, opponent);
												g.showPlaceableLocations(placeables, player, opponent);

												Point p = new Point(input.nextInt() - 1, input.nextInt() - 1);
												while (!contains(placeables, p)) {
																System.out.println("options: ");
																for (Point zz : placeables) {
																				if (zz != null) {
																								System.out.println((zz.x + 1) +  " " + (zz.y + 1));
																				}
																}
																System.out.println("Entry not valid. Try again: ");
																System.out.println("Enter Row: ");
																p.x = input.nextInt() - 1;
																System.out.println("Enter Column: ");
																p.y = input.nextInt() - 1;
												}
												g.placeMove(p, player, opponent);
												}
								
								//g.showPlaceableLocations(g.getPlaceableLocations(player, opponent), player, opponent);
								System.out.println("shouldnt be any stars on board");
								g.gameResult(g.getPlaceableLocations('W', 'B'), g.getPlaceableLocations('B', 'W'));
								g.displayBoard();
								/*
								Integer coordinates = input.nextInt();
								Point p = new Point();
								p.y = coordinates.toString().charAt(0);
								p.x = coordinates.toString().charAt(1);

								g.placeMove(p, player, opponent);
								g.showPlaceableLocations(g.getPlaceableLocations(player, opponent), player, opponent);
								*/
				}

				public static boolean contains(Point[] points, Point p) {
								for (Point x : points) {
												if (x != null) {
																if ( x.x == p.x && x.y == p.y) {
																				return true;
																}
												}
								}
								return false;
				}

				public static boolean isEmpty(Point[] p) {
								for (Point point : p) {
												if (point != null) {
																return false;
												}
								}
								return true;
				}
}
