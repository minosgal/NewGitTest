import java.util.Arrays;
import java.util.Scanner;
public class Shiritori{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("-- Shiritori --");
        char playAgain = 'n';
        do{
            int i = 0;
            while(!Shiritori.game_over){            
                System.out.print("Type a word: ");
                String word = input.next();
                Shiritori.play(word, i);
                i++;
            }
            Shiritori.getWords(Shiritori.words, i);
            Shiritori.restart(Shiritori.words);
            System.out.print("\n>> Do you want to play again? yes(y)-no(n): ");
            playAgain = input.next().charAt(0);
        }while(playAgain != 'n');        
        input.close();
    }
}

class Shiritori{
    static String[] words = new String[50]; //every round allows 50 words to be entered(probably enough)
    static boolean game_over = false;
    static int reasonExit = 0;  //reasonExit (1) repeated word / (2) last letter != first letter

    public static void play(String word, int index){
        if(index == 0){
            words[index] = word;    //first word is always saved
        }
        else{
            if(word.charAt(0) != words[index - 1].charAt(words[index - 1].length() - 1)){
                //GameOver if last letter != first letter reasonExit remains 0
                game_over = true;
            }
            for(int i = 0; i <= index - 1; i++){
                if(word.equals(words[i])){
                    //GameOver if new word = any previously said word reasonExit gets 1
                    game_over = true;
                    reasonExit = 1;
                }
            }
            if(game_over && reasonExit == 1){
                System.out.println("> "+ word +" has alread been said");
                System.out.println("> Game Over <");
                //checks reason of GameOver                
            }
            else if(game_over && reasonExit == 0){
                System.out.println("> last letter of "+ words[index - 1] +" is not the same as first letter of "+ word);
                System.out.println("> Game Over <");
                //checks reason of GameOver               
            }
            else{
                words[index] = word;
            }
        }
    }

    public static void getWords(String[] words, int index){
        System.out.println("\n -- Words Entered -- ");
        for(int i = 0; i < index - 1; i++){
            System.out.printf("<%d> %s\n", i + 1, words[i]);
            //prints every word of the array
        }
    }

    public static void restart(String[] words){
        Arrays.fill(words, null);
        game_over = false;
        System.out.println("> Game restarted");
        //resets game_over to starting state and puts null in array
    }
}

