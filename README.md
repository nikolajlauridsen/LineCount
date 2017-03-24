# LineCount
#### How many lines is that?

Ever wondered how many lines of your über new code codebase is? LineCount will tell ya'

Made in cofee... I mean Java

## Roadmap
- [x] Filter out uninmportant files
- [x] Save code reports
- [x] Improve codefile parsing
- [x] Add config with regex for parsing more languages
- [x] Make the GUI pretty (add padding)
- [ ] Add menu for adding user defined regexes to the config for languages not included
- [ ] Create menu for ignoring files outside gitignore (maybe create a seperate LineCountIgnore file)
- [x] Add percentages to the project report


# Testing to see if md output works with Github

# Saved Project Report
## Project Overview
Report saved: 24/03/2017


Project folder: D:\Github\LineCount


Number of Files: 18


### Code Tally
|    Code     |  Comments   | Whitespace  | Total |
|-------------|-------------|-------------|-------|
| 971 (63,3%) | 327 (21,3%) | 237 (15,4%) | 1535  |


## File Overview
|       Filename       | Extension |                           Path                            |    Code     |  Comments   | Whitespace  | Total |
|----------------------|-----------|-----------------------------------------------------------|-------------|-------------|-------------|-------|
|    CodeFile.java     |   java    |     src\LineCount\FileOperations\Files\CodeFile.java      | 78 (48,8%)  | 58 (36,3%)  | 24 (15,0%)  |  160  |
|   ReportFile.java    |   java    |    src\LineCount\FileOperations\Files\ReportFile.java     | 112 (67,1%) | 32 (19,2%)  | 23 (13,8%)  |  167  |
|    TextFile.java     |   java    |     src\LineCount\FileOperations\Files\TextFile.java      | 37 (50,0%)  | 28 (37,8%)  |  9 (12,2%)  |  74   |
|   FileParser.java    |   java    |   src\LineCount\FileOperations\Parsing\FileParser.java    | 40 (55,6%)  | 21 (29,2%)  | 11 (15,3%)  |  72   |
| FileParserModel.java |   java    | src\LineCount\FileOperations\Parsing\FileParserModel.java |  5 (55,6%)  |  3 (33,3%)  |  1 (11,1%)  |   9   |
|  ParserChooser.java  |   java    |  src\LineCount\FileOperations\Parsing\ParserChooser.java  | 41 (58,6%)  | 18 (25,7%)  | 11 (15,7%)  |  70   |
|   FileFilter.java    |   java    |    src\LineCount\FileOperations\Utils\FileFilter.java     | 59 (47,6%)  | 49 (39,5%)  | 16 (12,9%)  |  124  |
|     FileOps.java     |   java    |      src\LineCount\FileOperations\Utils\FileOps.java      | 37 (62,7%)  | 11 (18,6%)  | 11 (18,6%)  |  59   |
|     BoxHelp.java     |   java    |              src\LineCount\GUI\BoxHelp.java               | 19 (79,2%)  |  0 (0,0%)   |  5 (20,8%)  |  24   |
|   FilePicker.java    |   java    |             src\LineCount\GUI\FilePicker.java             | 146 (76,0%) |  11 (5,7%)  | 35 (18,2%)  |  192  |
|    FileTable.java    |   java    |             src\LineCount\GUI\FileTable.java              | 65 (78,3%)  |  5 (6,0%)   | 13 (15,7%)  |  83   |
| ProjectManager.java  |   java    |           src\LineCount\GUI\ProjectManager.java           | 106 (67,9%) | 24 (15,4%)  | 26 (16,7%)  |  156  |
|  ProjectReport.java  |   java    |           src\LineCount\GUI\ProjectReport.java            | 85 (73,9%)  |  8 (7,0%)   | 22 (19,1%)  |  115  |
|      Main.java       |   java    |                  src\LineCount\Main.java                  |  7 (70,0%)  |  0 (0,0%)   |  3 (30,0%)  |  10   |
|     Config.java      |   java    |              src\LineCount\Utils\Config.java              |  4 (50,0%)  |  3 (37,5%)  |  1 (12,5%)  |   8   |
|     MdHelp.java      |   java    |              src\LineCount\Utils\MdHelp.java              | 48 (48,0%)  | 37 (37,0%)  | 15 (15,0%)  |  100  |
|   StringHelp.java    |   java    |            src\LineCount\Utils\StringHelp.java            | 59 (66,3%)  | 19 (21,3%)  | 11 (12,4%)  |  89   |
|   fileparsers.yml    |    yml    |                      fileparsers.yml                      | 23 (100,0%) |  0 (0,0%)   |  0 (0,0%)   |  23   |
|        Total         |    N/A    |                    D:\Github\LineCount                    | 971 (63,3%) | 327 (21,3%) | 237 (15,4%) | 1535  |
