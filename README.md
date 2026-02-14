## Authors
Caleb Waggy
Louis Zhu

## Revision Reporter
This java program lets user look up the most recent edits that was made to a Wikipedia article. The user can run the program from the command line and inputs a Wikipedia article name. The program then connect to the API downloads the data then output.

The program displays 15 most recent output from the newest to the oldest, it also handles special cases like an article does not exist or when there's network problems.

## Run
.\gradlew run --args"Insert Wikipedia article here"