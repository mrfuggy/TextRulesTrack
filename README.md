## Text Rules Track
This is a simple app to track your movies, TV shows and anime episodes.

## Description
Program uses text format files.
Each day you add to the file the last watched episode.
With this application you can convert a file into a total info file.
Finite state machine pattern was used to develop this program.
For each file you can select the base length of one episode.
For example, for anime it's 23 minutes, for TV shows is 50 minutes.
If an episode is several times longer than the base length, for example a double episode,
add the amount as an additional time. But you can not use it if you want.

## Input file format
```
Date line = *** <year>-<month>-<day> = <total episodes until the end of the day>
```
```
Title line = <title name> : <last watched episode>
```
```
Completed title line = <title name> : <total episodes> [(<+ or -><addition time>)] - <score><? if doubt> - <tags>
```
Day's segments is separated by empty line.

## Input example
```
*** 2016-09-12 = 12
Clannad: After Story : 12

*** 2016-09-13 = 36
Clannad: After Story : 24 - 10? - drama
Shinsekai yori : 12

*** 2016-09-14 = 56
Shinsekai yori : 25 - 10 - story based on novel, plot
Suzumiya Haruhi no Shoushitsu : 1 (+6) - 9 - opening
```

## Output file format
```
Title line = <title>
```
```
Title Info = start: <start date> end: <end date> eps: <watched episodes> score: <score>
```
```
Tags line = tags: <tags>
```
```
Total line = total: <total episodes> average: <average episodes per day>
```
Title's segment is separated by empty line.

## Output example
```
Clannad: After Story
start: 2016-09-12 end: 2016-09-13 eps: 24 score: 10
tags: drama

Shinsekai yori
start: 2016-09-13 end: 2016-09-14 eps: 25 score: 10
tags: story based on novel, plot

Suzumiya Haruhi no Shoushitsu
start: 2016-09-14 end: 2016-09-14 eps: 1 score: 9
tags: opening


date: 2016-09-12 count: 12
date: 2016-09-13 count: 24
date: 2016-09-14 count: 20
total: 56 average: 18.67
```

## Requirements
Kotlin

## License
This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
