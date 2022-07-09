public class impasse {


    public static void main(String[] args) {

        String[] argArray = new String[args.length]; //store all arguments as an array
        for (int i = 0; i < args.length; i++) {
            argArray[i] = args[i];
        }


        //check if too many or too few arguments

        if (args.length < 4) {
            StdOut.println("Not enough arguments.");
            System.exit(0);
        }
        if (args.length > 4) {
            StdOut.println("Too many arguments.");
            System.exit(0);
        }


        int gmode = Integer.parseInt(argArray[0]); //game mode
        int dmode = Integer.parseInt(argArray[1]); //display mode
        int n = Integer.parseInt(argArray[2]); //number of different colours
        int k = Integer.parseInt(argArray[3]); //lenght of blockades
        int m = 0;



        if (gmode == 2) {
            StdOut.println("Functionality currently not supported.");
            System.exit(0);
        }
        if (gmode > 2) {
            gmode = 0;
            StdOut.println("First input reset to default");
        }
        if (gmode < 0) {
            gmode = 0;
            StdOut.println("First input reset to default");
        }


        //validate display mode



        if (dmode > 1) {
            dmode = 0;
            StdOut.println("Second input reset to default.");
        }
        if (dmode < 0) {
            dmode = 0;
            StdOut.println("Second input reset to default.");
        }


        //validate number of colours
        if (n == 4) {
            StdOut.println("Functionality currently not supported.");
            System.exit(0);
        }

        if (n > 4) {
            n = 2;
            StdOut.println("Third input reset to default.");
        }
        if (n < 2) {
            n = 2;
            StdOut.println("Third input reset to default.");
        }

        //determine m
        if (n == 2) {
            m = 8;

        }
        if (n == 3) {
            m = 30;
        }

        if (n == 4) {
            m = 128;
        }

        //validate length of blockades

        if (k > m) {
            k = 3;
            StdOut.println("Fourth input reset to default.");
        }
        if (k < 3) {
            k = 3;
            StdOut.println("Fourth input reset to default.");
        }


        StdOut.println("The dimension of your board is: " + m + "x" + m);
        StdOut.println("The length of a blockade is: " + k);


        String[][] a = new String[m][m];        //initialise 2d array to store board
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++)
                a[i][j] = "*";                    //fill board with stars
        }

        int i, j;
        for (i = 0; i < m; i++) {                      //make first column open
            a[i][0] = ".";
        }



        int move = 0;                   //declare variables to be used in the game loop
        int rownum = 0;
        int columnnum = 0;
        int color = 0;
        String color1 = null;
        int numberOfMoves = 0;
        int edge = m - 1;
        int moveisvalid = 0;


        boolean gameIsRunning = true;


        while (gameIsRunning) { //game loop starts here
            if (dmode == 0) {
                textMode(move, rownum, columnnum, color, color1, numberOfMoves, edge, moveisvalid, a, m, n, gmode, k);
            }else guiMode(a);
            // gameIsRunning = true;
        }
    }

    //textMode function
    static void textMode(int move, int rownum, int columnnum, int color, String color1, int numberOfMoves, int edge, int moveIsValid, String [][] a, int m, int n, int gmode, int k) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                StdOut.print(a[i][j] + "");            //print whole board
            }
            StdOut.println();
        }



        while (moveIsValid == 0) {
            StdOut.println(" ");
            StdOut.println("Move: ");

            move = StdIn.readInt();

            int counter1 = 0;
            int counter2 = 0;


            if (move == 2) {
                //stop the game
                //end game and print stats



                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < m; j++) {
                            if (a[i][j] == ".") {
                                counter1++;
                            }
                        }
                    }



                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < m; j++) {
                            if (a[i][j] == "*") {
                                counter2++;
                            }
                        }
                    }


                StdOut.println("");
                StdOut.println("Termination: User terminated game!");
                double calcscore = counter1 + counter2;
                double calcscore1 = m * m;
                double calcscore2 = calcscore / calcscore1;
                calcscore2 = 1 - calcscore2;
                double calcscore3 = calcscore2 * 100;
                StdOut.println("Score: " + Math.round(calcscore3) + "%");
                StdOut.println("Moves: " + numberOfMoves);
                StdOut.println("Game ended!");
                System.exit(0);


            }
            if (move < 0) {
                StdOut.println("Unknown move!");
                break;
            }
            if (move > 2) {
                StdOut.println("Unknown move!");
                break;
            }

            if (move == 0) {
                rowDeletion(a, m);
            }
            if (move == 2) {
                StdOut.println("");
                StdOut.println("Termination: User terminated game!");

                double gridSize = (m * m);
                double blocksUsed = counter1 + counter2;
                double score = 100 - (blocksUsed / gridSize) * 100;

                StdOut.println("Score: " + Math.round(score) + "%");
                StdOut.println("Moves: " + numberOfMoves);
                StdOut.println("Game ended!");
                System.exit(0);

            }
            if (move == 1) {

                StdOut.println("Row Number: ");
                rownum = StdIn.readInt();


                StdOut.println("Column Number: ");
                columnnum = StdIn.readInt();


                StdOut.println("Color: ");              //choose colour
                color = StdIn.readInt();

                if (color == 0) {
                    color1 = "G";
                }
                if (color == 1) {
                    color1 = "Y";
                }
                if (color == 2) {
                    color1 = "R";
                }
                if (color == 3) {
                    color1 = "B";
                }
                if (color == 4) {
                    color1 = "P";
                }

                //check position if position is out of bounds
                if (rownum < 0) {
                    StdOut.println("Outside of board!");
                    break;
                }
                if (rownum > 7) {
                    StdOut.println("Outside of board!");
                    break;
                }
                if (columnnum < 0) {
                    StdOut.println("Outside of board!");
                    break;
                }
                if (columnnum > 7) {
                    StdOut.println("Outside of board!");
                    break;
                }
                //check if color is valid

                if (color > n) {
                    StdOut.println("Unknown color!");    //colour is out of bounds
                    break;
                }
                //check if cell is open
                if (a[rownum][columnnum] == "*") {
                    StdOut.println("Cell is not open!");
                    break;
                }
                if (a[rownum][columnnum] == "G") {
                    StdOut.println("Cell is not open!");
                    break;
                }
                if (a[rownum][columnnum] == "Y") {
                    StdOut.println("Cell is not open!");
                    break;
                }
                if (a[rownum][columnnum] == "R") {
                    StdOut.println("Cell is not open!");
                    break;
                }
                if (a[rownum][columnnum] == "B") {
                    StdOut.println("Cell is not open!");
                    break;
                }
                if (a[rownum][columnnum] == "P") {
                    StdOut.println("Cell is not open!");
                    break;
                }

                a[rownum][columnnum] = color1;
                if (columnnum < edge) {
                    a[rownum][columnnum + 1] = "."; //open up cell to the right
                }

                //print a blank line
                StdOut.println(" ");
                for (int i = 0; i < m; i++) {
                    for (int j = 0; j < m; j++) {
                        StdOut.print(a[i][j] + "");            //print whole board
                    }
                    StdOut.println();

                }
            }
            numberOfMoves++;


            //detect wins


            edge = m - 1;

            if (gmode != 0) { //only run when not gamemode 0
                detectSplitsLooper(a, m, numberOfMoves);
            }

            detectWins(m, a, numberOfMoves);

            String colorCheck = "G";

            for (int columnNum = 0; columnNum < edge; columnNum++) {

                colorCheck = "G";
                blockadeChecker(columnNum, m, k, colorCheck, a, numberOfMoves);
                colorCheck = "Y";
                blockadeChecker(columnNum, m, k, colorCheck, a, numberOfMoves);
                colorCheck = "R";
                blockadeChecker(columnNum, m, k, colorCheck, a, numberOfMoves);
                colorCheck = "B";
                blockadeChecker(columnNum, m, k, colorCheck, a, numberOfMoves);
                colorCheck = "P";
                blockadeChecker(columnNum, m, k, colorCheck, a, numberOfMoves);

            }


        }
    }




    //blockade check function

    static void blockadeChecker(int columnNum, int m, int k, String colorCheck, String[][] a, int numberOfMoves) {
        int count = 0;


        for (int i = 0; i < m; i++) {
            if (a[i][columnNum] == colorCheck) count++;
            else count = 0;
            if (count >= k) {
                // call calcAndPrintScore
            calcAndPrintScore(m, a, numberOfMoves);
            }

        }

    }

    //detect wins function

    static int detectWins(int m, String[][] a, int numberOfMoves) {
        int counter = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == ".") {
                    counter++;
                }
            }
        }
        if (counter == 0) {
            StdOut.println("");
            StdOut.println("Termination: You have won!");
            StdOut.println("Score: 100%");
            StdOut.println("Moves: " + numberOfMoves);
            StdOut.println("Game ended!");
            System.exit(0);
        }
        return counter;

    }

    //calculate score and print score if blockade

    static void calcAndPrintScore(int m, String [][] a, int numberOfMoves) {
    int counter1 = 0;
    int counter2 = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == ".") {
                    counter1++;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == "*") {
                    counter2++;
                }
            }
        }

        StdOut.println("");
        StdOut.println("Termination: Blockade!");
        double gridSize = (m * m);
        double blocksUsed = counter1 + counter2;
        double score = 100 - (blocksUsed / gridSize) * 100;

        StdOut.println("Score: "+Math.round(score)+"%");
        StdOut.println("Moves: "+numberOfMoves);
        StdOut.println("Game ended!");
        System.exit(0);
    }


    static void rowDeletion(String [][] a, int m){
        int rownum;
        int columnnum;



        StdOut.println("Row Number: ");
        rownum = StdIn.readInt();


        StdOut.println("Column Number: ");
        columnnum = StdIn.readInt();

        int columnNum2 = columnnum;


        //check position if position is out of bounds
        if (rownum < 0) {
            StdOut.println("Outside of board!");
            return;
        }
        if (rownum > 7) {
            StdOut.println("Outside of board!");
            return;
        }
        if (columnnum < 0) {
            StdOut.println("Outside of board!");
            return;
        }
        if (columnnum > 7) {
            StdOut.println("Outside of board!");
            return;
        }


        //check if deletion is effective
        int count = 0;
        if (columnnum > m){
            StdOut.println("Outside of board!");
            return;
        }
        if (rownum > m){
            StdOut.println("Outside of board!");
            return;
        }
        while (columnnum < m){
            if (a[rownum][columnnum] == "*") {
                count++;

            }
            columnnum++;
        }


            if (count > 0) {
                //execute deletion

                a[rownum][columnNum2 + 1] = ".";
                int columnNum3 = columnNum2 + 2;
                while (columnNum3 < m) {
                    a[rownum][columnNum3] = "*";

                    columnNum3++;
                }




            } else StdOut.println("Nothing to delete!");


        //print a blank line
        StdOut.println(" ");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                StdOut.print(a[i][j] + "");            //print whole board
            }
            StdOut.println();

        }
    }

    static void detectSplitsLooper(String [][] a, int m, int numberOfMoves){
        int r = 0;
        while ( r < m){
            detectArraySplits(a, r, m, numberOfMoves);
            r++;
        }
    }




    static void detectArraySplits(String a[][], int r, int m, int numberOfMoves) {
        if (numberOfMoves < m) return;
        int count = 0;
        int columnNum = 0;
        while (columnNum < m) {

            for (int i = 0; i < m; i++) {
                if (a[i][columnNum] == ".") count++;

                if (count >= m) {
                    for (int k = 0; k < m; k++) {
                        if (a[k].length != a[r].length) {
                            splitCalcScore(a, m, numberOfMoves);
                        }
                        for (int l = 0; k < a[i].length; k++)
                            if (!a[l].equals(a[r])) {
                                splitCalcScore(a, m, numberOfMoves);
                            }





                    }

                }
            }columnNum++;
        }
    }

    static void splitCalcScore(String a [][], int m, int numberOfMoves){
        int counter1 = 0;
        int counter2 = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == ".") {
                    counter1++;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (a[i][j] == "*") {
                    counter2++;
                }
            }
        }
        StdOut.println("");
        StdOut.println("Termination: You have caused a split!");
        double gridSize = (m * m);
        double blocksUsed = counter1 + counter2;
        double score = 100 - (blocksUsed / gridSize) * 100;
        StdOut.println("Score: "+Math.round(score)+"%");
        StdOut.println("Moves: "+numberOfMoves);
        StdOut.println("Game ended!");
        System.exit(0);
    }

    // gui code from here onwards
    static void guiMode(String [][] a) {

        // set up values to capture which cell the user is currently highlighting.
        int currCol = 0;
        int currRow = 0;
        // StdAudio.playInBackground("toaster.wav");

        // call the canvas setup function
        initCanvas();

        // lets draw the highlight around the cell 0,0
        drawHighlight(currRow, currCol);

        boolean gameIsOver = false;

        while (!gameIsOver) {


            while (!StdDraw.hasNextKeyTyped());


            char pressed =  StdDraw.nextKeyTyped();

            StdDraw.pause(100);

            switch (pressed) {

                case 'w':
                    // W pressed implies moving up.
                    if (currRow > 0) currRow--;
                    // StdAudio.playInBackground("toaster.wav");
                    break;
                case 'a':
                    // A pressed involves moving left.
                    if (currCol > 0) currCol--;
                   // StdAudio.playInBackground("toaster.wav");
                    break;
                case 's':
                    // S pressed implies moving down.
                    if (currRow < 1) currRow++;
                    // StdAudio.playInBackground("toaster.wav");
                    break;
                case 'd':
                    // D pressed implies moving right.
                    if (currCol < 1) currCol++;
                    // StdAudio.playInBackground("toaster.wav");
                    break;
                case 'q':
                    //quits game
                    System.exit(0);


                // How would you implement quitting?

                // if they didnt press 'w'  'a'  's'  'd' keys, the default condition will be called
                default:
                    System.out.printf("The key: %s is not recognized\n", pressed);
                    break;
            }

            // once currRow and currCol have been updated accordingly, draw the highlight.
            drawHighlight(currRow, currCol);

        }

    }


    public static void initCanvas() {

        // Double buffering only displays the canvas when a call to StdDraw.show() is made.
        // Enabling this allows for smoother canvas rendering.
        StdDraw.enableDoubleBuffering();

        StdDraw.setCanvasSize(800,800);
        StdDraw.setXscale(0, 800);
        StdDraw.setYscale(800,0);



        drawBackground();

        // This function call will display the canvas when double buffering is enabled.
        StdDraw.show();

    }


    public static void drawBackground() {

        StdDraw.setPenColor(0,0,0);

        StdDraw.filledRectangle(400, 400, 400, 400);



        // color of the inner squares
        StdDraw.setPenColor(220,220,220);

        // The inner-square centers are (200,200) ... (200,600) ... (600,200) ... (600,600)
        StdDraw.filledRectangle(200, 200, 150, 150);
        StdDraw.filledRectangle(600, 200, 150, 150);
        StdDraw.filledRectangle(200, 600, 150, 150);
        StdDraw.filledRectangle(600, 600, 150, 150);

        StdDraw.show();
    }


    public static void drawHighlight(int row, int col) {
        // Arguments out of bounds?  quit the function.
        if (row < 0 || row > 1 || col < 0 || col > 1) {
            System.out.println("Highlight is out of range");
            return;
        }


        drawBackground();


        int centerRowCoord;
        int centerColCoord;
        if (row == 0) {
            // row is 0
            centerRowCoord = 200;
        } else {
            // implies row is 1
            centerRowCoord = 600;
        }
        if (col == 0) {
            // col is 0
            centerColCoord = 200;
        } else {
            // implies col is 1
            centerColCoord = 600;
        }



        // This integer will represent how much the highlight will extend beyond the perimeter of the cell.
        int halfGlowWidth = 10;

        // Set the pen color to the glow-color
        StdDraw.setPenColor(StdDraw.YELLOW);

        // Draw the rectangle for the glow.. draw it the same size as the cell itself, with the glow width added additionally
        StdDraw.filledRectangle(centerColCoord, centerRowCoord, 150 + halfGlowWidth, 150 + halfGlowWidth);



        // color of the inner squares
        StdDraw.setPenColor(220,220,220);
        // location of the inner cell
        StdDraw.filledRectangle(centerColCoord, centerRowCoord, 150, 150);

        StdDraw.show();

    }





}

