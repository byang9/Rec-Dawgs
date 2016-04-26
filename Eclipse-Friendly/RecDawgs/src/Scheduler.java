
import java.util.*;

public class Scheduler{

    public static void printMatrix(int[][] matrix){
		for (int i = 0; i < matrix.length; i++){
		    for (int j = 0; j < matrix[0].length; j++){
			System.out.print(matrix[i][j] + "   ");
		    }
		    System.out.println();
		}
    }
    public static int getRemaining(int[][]schedules, int i, int j){
		//Check rows and check columns
		//get the lowest value thats not in the column
		HashSet<Integer> invalids = new HashSet<Integer>();
		HashSet<Integer> daysCanPlay = new HashSet<Integer>();
		
		
		for (int row = 0; row < schedules.length; row++){
		    //keep a list of the teams
			daysCanPlay.add(row+1);
			//add in extra days just in case
			daysCanPlay.add((row+1 + schedules.length));
		    for (int col = 0; col < schedules[0].length; col++){
				if (row == i-1){
				    invalids.add(schedules[row][col]);
				} 
				if (col == j-1){
				    invalids.add(schedules[row][col]);
				}

		    }
		}
		//from the set of teams, remove all that have been in the same rows or same columns
		daysCanPlay.removeAll(invalids);
	        Iterator iter = daysCanPlay.iterator();
		int lowest = 99;
		while(iter.hasNext()){
		    int temp = (Integer)iter.next();
		    lowest = temp < lowest ? temp : lowest;
		}
		return lowest;
    }

    public static void createSchedule(int numTeams){
    	int[][]schedule = new int[numTeams][numTeams];	
    	for (int i = 1; i <= schedule.length; i++){
		    for (int j = 1; j <= schedule[0].length; j++){
				if (i == j) schedule[i-1][j-1] = 0;
				else{
				    //newScheduleAndIndex update = getRemaining(schedules, i, j);
				    schedule[i-1][j-1] = getRemaining(schedule, i, j);
				}
			}//for

		}//for
		printMatrix(schedule);
    }

    public static void main(String[]args){

    	if (args.length != 1){
    		System.out.println("Please run the program with the correct arguments: Scheduler <team number>");
    	}
		else{
			int numTeams = Integer.valueOf(args[0]);
			createSchedule(numTeams );
		}


    }


}