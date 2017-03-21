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
- [ ] Add percentages to the project report


# Testing to see if md output works with Github

# Saved Project Report
## Project Overview
Report saved: 21/03/2017


Project folder: D:\Github\LineCount


Number of Files: 18


### Code Tally
| Code | Comments | Whitespace | Total |
|------|----------|------------|-------|
| 975  |   339    |    237     | 1551  |


## File Overview
|       Filename       | Extension |                           Path                            | Code | Comments | Whitespace | Total |
|----------------------|-----------|-----------------------------------------------------------|------|----------|------------|-------|
|    CodeFile.java     |   java    |     src\LineCount\FileOperations\Files\CodeFile.java      |  78  |    58    |     24     |  160  |
|   ReportFile.java    |   java    |    src\LineCount\FileOperations\Files\ReportFile.java     | 111  |    32    |     22     |  165  |
|    TextFile.java     |   java    |     src\LineCount\FileOperations\Files\TextFile.java      |  37  |    28    |     8      |  73   |
|   FileParser.java    |   java    |   src\LineCount\FileOperations\Parsing\FileParser.java    |  40  |    21    |     11     |  72   |
| FileParserModel.java |   java    | src\LineCount\FileOperations\Parsing\FileParserModel.java |  5   |    3     |     1      |   9   |
|  ParserChooser.java  |   java    |  src\LineCount\FileOperations\Parsing\ParserChooser.java  |  41  |    18    |     11     |  70   |
|   FileFilter.java    |   java    |    src\LineCount\FileOperations\Utils\FileFilter.java     |  62  |    54    |     16     |  132  |
|     FileOps.java     |   java    |      src\LineCount\FileOperations\Utils\FileOps.java      |  50  |    16    |     13     |  79   |
|     BoxHelp.java     |   java    |              src\LineCount\GUI\BoxHelp.java               |  19  |    0     |     4      |  23   |
|   FilePicker.java    |   java    |             src\LineCount\GUI\FilePicker.java             | 146  |    11    |     35     |  192  |
|    FileTable.java    |   java    |             src\LineCount\GUI\FileTable.java              |  62  |    5     |     13     |  80   |
| ProjectManager.java  |   java    |           src\LineCount\GUI\ProjectManager.java           |  93  |    24    |     25     |  142  |
|  ProjectReport.java  |   java    |           src\LineCount\GUI\ProjectReport.java            |  82  |    8     |     21     |  111  |
|      Main.java       |   java    |                  src\LineCount\Main.java                  |  13  |    0     |     5      |  18   |
|     MdHelp.java      |   java    |              src\LineCount\Utils\MdHelp.java              |  51  |    42    |     15     |  108  |
|   StringHelp.java    |   java    |            src\LineCount\Utils\StringHelp.java            |  24  |    15    |     3      |  42   |
|     TxtHelp.java     |   java    |             src\LineCount\Utils\TxtHelp.java              |  38  |    4     |     10     |  52   |
|   fileparsers.yml    |    yml    |                      fileparsers.yml                      |  23  |    0     |     0      |  23   |
|        Total         |    N/A    |                    D:\Github\LineCount                    | 975  |   339    |    237     | 1551  |
