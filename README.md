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
Report saved: 17/04/2017


Project folder: D:\Github\LineCount


Number of Files: 21


### Code Tally
|     Code     |  Comments   | Whitespace  | Total |
|--------------|-------------|-------------|-------|
| 1083 (57,0%) | 514 (27,0%) | 304 (16,0%) | 1901  |


## File Overview
|       Filename        | Extension |                           Path                            |     Code     |  Comments   | Whitespace  | Total |
|-----------------------|-----------|-----------------------------------------------------------|--------------|-------------|-------------|-------|
|     CodeFile.java     |   java    |     src\LineCount\FileOperations\Files\CodeFile.java      |  79 (44,9%)  | 62 (35,2%)  | 35 (19,9%)  |  176  |
|    ReportFile.java    |   java    |    src\LineCount\FileOperations\Files\ReportFile.java     | 112 (65,1%)  | 33 (19,2%)  | 27 (15,7%)  |  172  |
|     TextFile.java     |   java    |     src\LineCount\FileOperations\Files\TextFile.java      |  41 (46,1%)  | 36 (40,4%)  | 12 (13,5%)  |  89   |
|    FileParser.java    |   java    |   src\LineCount\FileOperations\Parsing\FileParser.java    |  37 (51,4%)  | 24 (33,3%)  | 11 (15,3%)  |  72   |
| FileParserModel.java  |   java    | src\LineCount\FileOperations\Parsing\FileParserModel.java |  5 (55,6%)   |  3 (33,3%)  |  1 (11,1%)  |   9   |
|  ParserChooser.java   |   java    |  src\LineCount\FileOperations\Parsing\ParserChooser.java  |  37 (53,6%)  | 21 (30,4%)  | 11 (15,9%)  |  69   |
|    FileFilter.java    |   java    |    src\LineCount\FileOperations\Utils\FileFilter.java     |  68 (49,6%)  | 52 (38,0%)  | 17 (12,4%)  |  137  |
|     FileHelp.java     |   java    |     src\LineCount\FileOperations\Utils\FileHelp.java      |  37 (59,7%)  | 13 (21,0%)  | 12 (19,4%)  |  62   |
| InvalidGlobError.java |   java    | src\LineCount\FileOperations\Utils\InvalidGlobError.java  |  6 (54,5%)   |  4 (36,4%)  |  1 (9,1%)   |  11   |
|     BoxHelp.java      |   java    |              src\LineCount\GUI\BoxHelp.java               |  26 (53,1%)  | 17 (34,7%)  |  6 (12,2%)  |  49   |
|    ErrorPanel.java    |   java    |        src\LineCount\GUI\Elements\ErrorPanel.java         |  45 (63,4%)  | 12 (16,9%)  | 14 (19,7%)  |  71   |
|    FilePicker.java    |   java    |        src\LineCount\GUI\Elements\FilePicker.java         | 146 (63,2%)  | 43 (18,6%)  | 42 (18,2%)  |  231  |
|    FileTable.java     |   java    |         src\LineCount\GUI\Elements\FileTable.java         |  63 (67,0%)  | 16 (17,0%)  | 15 (16,0%)  |  94   |
|    ErrorFrame.java    |   java    |         src\LineCount\GUI\Windows\ErrorFrame.java         |  21 (50,0%)  | 15 (35,7%)  |  6 (14,3%)  |  42   |
|  ProjectManager.java  |   java    |       src\LineCount\GUI\Windows\ProjectManager.java       | 116 (64,1%)  | 36 (19,9%)  | 29 (16,0%)  |  181  |
|  ProjectReport.java   |   java    |       src\LineCount\GUI\Windows\ProjectReport.java        |  87 (57,2%)  | 41 (27,0%)  | 24 (15,8%)  |  152  |
|       Main.java       |   java    |                  src\LineCount\Main.java                  |  7 (63,6%)   |  1 (9,1%)   |  3 (27,3%)  |  11   |
|      Config.java      |   java    |              src\LineCount\Utils\Config.java              |  7 (63,6%)   |  3 (27,3%)  |  1 (9,1%)   |  11   |
|      MdHelp.java      |   java    |              src\LineCount\Utils\MdHelp.java              |  48 (48,0%)  | 36 (36,0%)  | 16 (16,0%)  |  100  |
|    StringHelp.java    |   java    |            src\LineCount\Utils\StringHelp.java            |  68 (50,4%)  | 46 (34,1%)  | 21 (15,6%)  |  135  |
|    fileparsers.yml    |    yml    |                      fileparsers.yml                      | 27 (100,0%)  |  0 (0,0%)   |  0 (0,0%)   |  27   |
|         Total         |    N/A    |                    D:\Github\LineCount                    | 1083 (57,0%) | 514 (27,0%) | 304 (16,0%) | 1901  |
