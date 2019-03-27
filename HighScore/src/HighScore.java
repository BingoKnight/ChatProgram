public class HighScore {
    public static void main(String[] args) {
        displayHighScores("David", calculateHighScorePos(1500));
        displayHighScores("Mark", calculateHighScorePos(400));
        displayHighScores("John", calculateHighScorePos(900));
        displayHighScores("Mike", calculateHighScorePos(50));
    }
    public static void displayHighScores(String name, int position){
        System.out.println(name + " got position " + position);
    }
    public static int calculateHighScorePos(int score){
        if(score > 1000){ return 1;}
        else if(score > 500){return 2;}
        else if(score > 100){return 3;}
        else{return 4;}
    }
}
