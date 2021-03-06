package menace;

import java.util.ArrayList;
import java.util.List;


public class Tree {
	/*
	 * Tree that produces more trees that produces more trees that produces...
	 * 
	 * 
	 */
	//tracking the field
	public static int win=0;
	char[][] field = new char[3][3];
	Tree[][] children = new Tree[3][3];

	int value;
	boolean isMin;
	TTT_Main ttt;

	public Tree(char[][] field, boolean isMin, TTT_Main ttt) {
		this.field = field;
		this.isMin=isMin;
		this.ttt = ttt;
	}


	public char[][] clone() {
		
		char[][] clone = new char[3][3];
		for (int i = 0; i < clone.length; i++) {
			for (int j = 0; j < clone[i].length; j++) {
				clone[i][j] = field[i][j];
			}
		}
		return clone;
	}

	
	public int retScore(boolean isMin) {
		if(isMin) {
			win++;
			return 1;
		}
		else {
			return -1;
		}
	}
	public int createTree() {
		if (ttt.CheckIfWin(field, this.ttt)) {
			value = retScore(isMin);
			//win++;
			return value;
		} else if (ttt.getTotal(field)) {
			value = 0;
			return value;
		}

		value = retScore(isMin);

		for (int i = 0; i < children.length; i++) {
			for (int j = 0; j < children[i].length; j++) {

				if (field[i][j] == 0) {
					char[][] childField = clone();

					char input;
					//If mini, put x mark, else o mark
					if (isMin) input = ttt.x;
					else input = ttt.o;
					childField[i][j] = input;

					//Create children - The opposite player, hence isMin=~isMin
					children[i][j] = new Tree(childField, !isMin, ttt);

					//If mini
					if(isMin) {
						value = Math.min(value, children[i][j].createTree());
					}
					//If maxi
					else {
						value = Math.max(value, children[i][j].createTree());
					}

				}
			}
		}

		return value;
	}


	
	public int[] getChildWithValue() {
		//List<int[]> li = new ArrayList<int[]>();
		for (int i = 0; i < children.length; i++) {
			for (int j = 0; j < children[i].length; j++) {
				if (children[i][j] != null && children[i][j].value == value) {
					//li.add(new int[] {i,j});
					return new int[] {i,j};
				}
			}
		}
		return null;
		//int size = li.size();
		//int rand = new Menace().getRandomNum(0, size-1);
		//return li.get(rand);
	}

}
