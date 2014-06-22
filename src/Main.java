import java.util.ArrayList;
import java.util.Arrays;

/**
 * Calculates dimensions (in servers) of perfect cubes in varying sizes made of
 * 19-inch rack servers.
 * 
 * This is the underpinning of the Hypermegacube project, intended to provide
 * every child in the world with their own emulated Nintendo Gamecube console,
 * hosted on an emulator inside a huge, monumental structure made of commodity
 * server equipment.
 * 
 *  * This assumes that all servers are square (19 x 19 inches) and does not
 * account for cooling. Your cubage may vary. If you do not keep up repayments
 * on your cube your cube may be repossessed.
 * 
 * @author Josef Kenny
 */
public class Main 
{

	static int heightLimit = 2000;
	static int widthLimit = 100;
	static int rackUnits = 1;
	
	public static void main(String[] args) 
	{					
		parseArgs(args);
		
		ArrayList<String> solutions = new ArrayList<String>();
		
		for(int ServersPerLayerEdge = 1; ServersPerLayerEdge < widthLimit; ServersPerLayerEdge++)
		{
			for(int Height_Servers = 1; Height_Servers < heightLimit; Height_Servers++)
			{
				int ServersPerLayer = ServersPerLayerEdge^2;

				double TotalServersUsed = (ServersPerLayer/1.75) * (ServersPerLayerEdge * 19);

				double Height_Inches = (Height_Servers * 1.75 * rackUnits);

				double EdgeLength_Inches = ServersPerLayerEdge * 19;
				
				if( Height_Inches == EdgeLength_Inches && isInteger(TotalServersUsed) )
				{
					System.out.println("Solution: Using " + (int) TotalServersUsed + " " + rackUnits + "U rack units.");
					
					System.out.println("Each layer: " + (int) ServersPerLayerEdge + " x " + (int) ServersPerLayerEdge + " servers.");
					System.out.println("With a height of " + (Height_Servers * rackUnits) + " rack units (" + Height_Inches + " inches).");
					System.out.println("Total size of cube: " + (int) EdgeLength_Inches + " inches wide, " + (int) EdgeLength_Inches + " inches deep and " + (int) Height_Inches + " inches tall.");
					System.out.println((int) EdgeLength_Inches / 12 + " cubic feet, using " + (int) TotalServersUsed + " servers.");
					System.out.println("");
					
					String solutionString = (int) ServersPerLayerEdge + " x " + (int) ServersPerLayerEdge + " x " + (int) Height_Servers;
					solutions.add(solutionString);
				}
			}
		}
		
		System.out.println("Done searching.");
		
		System.out.println("Solutions found: ");
		int count = 1;
		for(String solution : solutions)
		{	
			System.out.println(count + ". " + solution);
			count++;
		}
		
	}
	
	private static void parseArgs(String[] args)
	{
		String[] helpOptions = {"-h", "--h", "help", "--help", "-help"};
		if( contentsInList(args, helpOptions))
		{
			System.out.println("Calculates the possible sizes (in servers)");
			System.out.println("");
			System.out.println("Usage:");
			System.out.println("servercube [height limit] [width limit] [server size in units]");
			System.out.println("");
			System.out.println("e.g. 	servercube 2000 100 1");
			System.out.println("");
			System.out.println("For a height limit of 2000, width limit of 100 ");
		    System.out.println("and 1U rack height for each server.");
			System.exit(0);
		}
		else if(args.length != 2)
		{
			System.out.println("No valid arguments specified. Using defaults:");
			System.out.println("Height: " + heightLimit + ", Width: " + widthLimit + ", 1U rack height.");
		}
		else if(args.length == 2)
		{
			heightLimit = Integer.parseInt(args[0]);
			widthLimit = Integer.parseInt(args[1]);
			System.out.println("Searching up to a height limit of " + heightLimit + " and a width limit of " + widthLimit);
			System.out.println("Assuming 1U rack height.");
		}
		else if(args.length == 3)
		{
			heightLimit = Integer.parseInt(args[0]);
			widthLimit = Integer.parseInt(args[1]);
			rackUnits = Integer.parseInt(args[2]);

			System.out.println("Searching up to a height limit of " + heightLimit + " and a width limit of " + widthLimit);
			System.out.println("Using a rack height of " + rackUnits + "U.");
		}
		
	}
	
	/**
	 * Checks whether the input value is an integer.
	 * 
	 * @param input		Input as a double.
	 * @return			True if integer, false if not.
	 */
	private static boolean isInteger(double input)
	{
		if ((input == Math.floor(input)) && !Double.isInfinite(input)) 
		{
		    return true;
		}
		return false;
	}


	/**
	 * Checks whether the specified array contains any of the items
	 * in the specified list of matches.
	 * 
	 * @param input		The input array to check.
	 * @param list		The list of matches to check against.
	 * @return			True if there is a match.
	 */
	private static boolean contentsInList(String[] input, String[] list)
	{
		for(String str : input)
		{
			for(String match : list)
			{
				if( str.equals(match) )
				{
					return true;
				}
			}
		}
		return false;
	}

}
