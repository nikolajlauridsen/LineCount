# LineCount
#### How many lines is that?

Ever wondered how many lines of your über new code codebase is? LineCount will tell ya'

Made in cofee... I mean Java

## Roadmap
- [x] Filter out uninmportant files
- [x] Save code reports
- [x] Improve codefile parsing
- [x] Add config with regex for parsing more languages
- [ ] Make the GUI pretty (add padding)
- [ ] Add menu for adding user defined regexes to the config for languages not included
- [ ] Create menu for ignoring files outside gitignore (maybe create a seperate LineCountIgnore file)
- [ ] Add percentages to the project report


# Testing to see if md output works with Github

# Saved Project Report
## Project Overview
Report saved: 16/03/2017


Project folder: D:\Github\LineCount


Number of Files: 16


### Code Tally
| Code | Comments | Whitespace | Total |
|------|----------|------------|-------|
| 931  |   338    |    236     | 1505  |


## File Overview
|       Filename       | Extension |                           Path                            | Code | Comments | Whitespace | Total |
|----------------------|-----------|-----------------------------------------------------------|------|----------|------------|-------|
|    CodeFile.java     |   java    |     src\LineCount\FileOperations\Files\CodeFile.java      |  78  |    58    |     27     |  163  |
|   ReportFile.java    |   java    |    src\LineCount\FileOperations\Files\ReportFile.java     | 111  |    32    |     22     |  165  |
|    TextFile.java     |   java    |     src\LineCount\FileOperations\Files\TextFile.java      |  37  |    28    |     8      |  73   |
|   FileParser.java    |   java    |   src\LineCount\FileOperations\Parsing\FileParser.java    |  38  |    21    |     11     |  70   |
| FileParserModel.java |   java    | src\LineCount\FileOperations\Parsing\FileParserModel.java |  5   |    3     |     1      |   9   |
|  ParserChooser.java  |   java    |  src\LineCount\FileOperations\Parsing\ParserChooser.java  |  41  |    17    |     11     |  69   |
|   FileFilter.java    |   java    |    src\LineCount\FileOperations\Utils\FileFilter.java     |  62  |    54    |     16     |  132  |
|     FileOps.java     |   java    |      src\LineCount\FileOperations\Utils\FileOps.java      |  50  |    16    |     13     |  79   |
|   FilePicker.java    |   java    |             src\LineCount\GUI\FilePicker.java             | 136  |    11    |     37     |  184  |
|    FileTable.java    |   java    |             src\LineCount\GUI\FileTable.java              |  63  |    5     |     13     |  81   |
|  ProjectPanel.java   |   java    |            src\LineCount\GUI\ProjectPanel.java            |  95  |    24    |     22     |  141  |
|  ProjectReport.java  |   java    |           src\LineCount\GUI\ProjectReport.java            |  88  |    8     |     22     |  118  |
|      Main.java       |   java    |                  src\LineCount\Main.java                  |  14  |    0     |     5      |  19   |
|     MdHelp.java      |   java    |              src\LineCount\Utils\MdHelp.java              |  51  |    42    |     15     |  108  |
|   StringHelp.java    |   java    |            src\LineCount\Utils\StringHelp.java            |  24  |    15    |     3      |  42   |
|     TxtHelp.java     |   java    |             src\LineCount\Utils\TxtHelp.java              |  38  |    4     |     10     |  52   |
|        Total         |    N/A    |                    D:\Github\LineCount                    | 931  |   338    |    236     | 1505  |
