# SiteClearingSimulator
Site Clearing Simulator Tech Challenge

SETUP INSTRUCTIONS:

1. Install JDK 1.8, maven, and setup environment variables on your machine.

2. Download the source from GitHub into a folder on your machine.

	git clone https://github.com/benciojr/SiteClearingSimulator.git

3. In a command line / terminal, change the directory to the root folder of the application (SiteClearingSimulator).
	
	cd SiteClearingSimulator

4. Run maven to compile the source, run the unit tests, and package the application into a jar file.
	
	mvn install

5. After the successful build process, change the directory to the target folder where the jar file has been created.
	
	cd target

6. Then run the application using the following command format:
	
	java -jar SiteClearingSimulator-1.0.jar <input_file>

For example:
	
	java -jar SiteClearingSimulator-1.0.jar ../test-input/TestSiteMap1.txt
	java -jar SiteClearingSimulator-1.0.jar ../test-input/TestSiteMap2.txt

The two files shown in the examples above can be used for testing. Both files are saved in the folder SiteClearingSimulator/test-input





DESIGN AND APPROACH

ApplicationMain is the entry point to run the application and runs the main class of the simulator - the SiteClearingSimulator class. It also calls the ArgumentParser class to parse the arguments passed to the application, checks if the file exists, and returns a File object whenever it is valid.

SiteClearingSimulator

The SiteClearingSimulator class is the main class of the simulator. It has methods to read the data from the input text file, to start the actual simulation, and to display the report after the simulation has ended. The loadDataFromFile() method calls the SiteMapLoader that validates the input text file, instantiates the appropriate SiteBlock class, and stores them into a HashMap that represents the site map. The startSimulation() method is where the application asks the user input for commands. The CommandManager is assigned for this task of reading the user's input, validation and execution of the commands.


Square Blocks

A SiteBlock abstract class is created to represent a square block in a given site map. It has implementation classes specifically, the PlainBlock, RockyBlock, TreeBlock, and ProtectedBlock to represent the square blocks - plain, rocky, with trees, and with protected tree(s), respectively.

The SiteBlockFactory is used to create instances of either a PlainBlock, RockyBlock, TreeBlock, or a ProtectedBlock. Each type of SiteBlock is created with a specific fuel consumption set.


Site Map

For the site map, each block is assigned to have a specific row and column index. The first square block, which is located at the north west, has a row index of 0 and a column index of 0. The column index increases as you move to the next column on the right. Likewise, the row index also increases as you move down a row.

Here is an example of the row and column index assignments for a site map with 5 rows and 5 columns:

[0,0], [0,1], [0,2], [0,3], [0,4],

[1,0], [1,1], [1,2], [1,3], [1,4],

[2,0], [2,1], [2,2], [2,3], [2,4],

[3,0], [3,1], [3,2], [3,3], [3,4],

[4,0], [4,1], [4,2], [4,3], [4,4]


The site blocks are stored in a HashMap with the row index and column index as key. Any key not found in the HashMap is considered out of the boundaries of the site map.


Bulldozer

The Bulldozer class is created to represent the clearing machine. It has row and column index fields to determine the current location of the bulldozer on the site map, and also a direction field to determine where it is currently facing. Whenever a Bulldozer is created, it is set with a direction facing east and a location just to the left of the first square block in the site map - with a row and column index of [0, -1]. The Bulldozer class also has methods to turn left, turn right, and advance its coordinates on the site map.


ExpenseManager

The ExpenseManager class keeps track of the expenses and computes the total costs for the simulation.


Exceptions

There are three exceptions created for the application to handle errors encountered during the simulation: AttemptToClearProtectedBlockException, AttemptToNavigateBeyondBoundaryException, and FactoryException.

The exceptions AttemptToClearProtectedBlockException and AttemptToNavigateBeyondBoundaryException, respectively, are thrown whenever the user has tried to enter a site block that is protected, and whenever the user tried to go beyond the site map.

The FactoryException on the other hand, is thrown whenever an error is encountered during instantiation of a Command or a SiteBlock. An example of when the FactoryException might be thrown is when the user input entered is invalid, or the square block type read from the input file is invalid.

