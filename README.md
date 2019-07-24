# SiteClearingSimulator
Site Clearing Simulator Tech Challenge

Setup Instructions:

1. Download the source from GitHub into a folder on your machine.
	git clone https://github.com/benciojr/SiteClearingSimulator.git

2. In a command line / terminal, change the directory to the root folder of the application (SiteClearingSimulator).
	cd SiteClearingSimulator

3. Run maven to compile the source, run the unit tests, and package the application into a jar file.
	mvn install

4. After the successful build process, change the directory to the target folder where the jar file has been created.
	cd target

5. Then run the application using the following command format:
	java -jar SiteClearingSimulator-1.0.jar <input_file>

For example:
	java -jar SiteClearingSimulator-1.0.jar ../test-input/TestSiteMap1.txt
	java -jar SiteClearingSimulator-1.0.jar ../test-input/TestSiteMap2.txt

The two files shown in the examples above can be used for testing. Both files are saved in the folder SiteClearingSimulator/test-input

