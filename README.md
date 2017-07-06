# SitaTask Details

##



Input Files are at location D:/Saurabh/Projects/SITA_TEST_TASK/InputFeeds.

This appliaction works as per below algo:
1. .txt extension files will be picked.
2. Files are read from the input directory (D:/Saurabh/Projects/SITA_TEST_TASK/InputFeeds ) to check if any new files has arrived in every 5 seconds.
3. If a new file is found, then the application sums all the numbers in the file and create a new file containing the resulting value in the directory (D:/Saurabh/Projects/SITA_TEST_TASK/OutPut). 

4. Post this input file is moved to (D:/Saurabh/Projects/SITA_TEST_TASK/ProcessedFeed) directory with .PROCESSED extension. 

5. In case of any invalid files(If File contain other than number), file is moved to (D:/Saurabh/Projects/SITA_TEST_TASK/ErorFeeds) directory. 

6. After processing the files from inputFeed , files should be removed and dirctory should be empty.


## To build the application
 ##
1. From the command prompt go to project folder and run command mvn clean install.



## Testing the application.

1. Before running the application, place the input files in the input directory configured under application.properties.

2. Deploy the war SpringIntegrationTask.war and it will start processing from input folder.

