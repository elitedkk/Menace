package menace;


public class Tree_UI {
    /*
     * Tree that produces more trees that produces more trees that produces...
     *
     *
     */
    //public static int win=0;
    //tracking the field
    char[][] field = new char[3][3];
    Tree_UI[][] children = new Tree_UI[3][3];

    int value;
    boolean isMin;
    TTT_UI ttt;

    public Tree_UI(char[][] field, boolean isMin, TTT_UI ttt) {
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


    private int retScore(boolean isMin) {
        if(isMin) return 1;
        else return -1;
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
                    children[i][j] = new Tree_UI(childField, !isMin, ttt);

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
        for (int i = 0; i < children.length; i++) {
            for (int j = 0; j < children[i].length; j++) {
                if (children[i][j] != null && children[i][j].value == value) {
                    return new int[] {i,j};
                }
            }
        }
        return null;
    }

}

