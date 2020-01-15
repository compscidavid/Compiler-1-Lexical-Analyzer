Reads an input file from the command line & writes lexemes and tokens to the screen.

Below is a detailed run-through of the code logic. 

The program will use regex Pattern-matching to identify the largest token at the start of a
string, capture that token, then remove the token from the string and return a substring
without the tokenized portion. It will iterate through this process until the line is fully captured, 
and read line by line from the text file until it is empty.

First, a series of regex Patterns are defined for use with the Matcher.
Then, a while loop is creating that will read the text file line-by-line into a buffered
reader so long as the line isn't empty, and store it into an input String.
If the input String is empty for a given line, it will skip to the next line. Otherwise, if
the input.trimp() is not empty, it will print the entire line as the "INPUT: ".

After this, an outerLoop begins which will iterate so long as the input != null, which will
mark the end of the file upon reaching the null character at the end of the last line.

The first thing it will do to the input is perform input.trim(), which will remove the blank
spaces at the beginning and end of the input String.

Then, if the String is now empty (the case where the line was nothing but whitespace), it will
skip to the next line via break outerLoop;.

Upon reaching line 71, we will begin using our regex Patterns. The first case matches the
ending of a comment block */. This logic will only apply if we already are inside a comment
block (else it will just read */ as two separate symbols). Otherwise, the logic will dictate
that we now flag commentBlock to false, indicating the end of a comment block. Further, we
will create a substring from the input String that excludes the */ out of the front of the
String. This logic will be consistently used for each regex case.

On line 83, we check for the start of a block comment /*. We will flag blockComment to true,
indicating we are now inside a block comment after reading the /*, and then create a new
substring from the input String without the /*, moving the string forwards.

On line 93, we also operate the logic only if already in a block comment. So long as we are
inside a block comment and we are not reading a */ to denote the end of the block comment, we
will check if the entire input string for that line has a */. If it doesn't, we simply skip to
the next line. If it does, we will move forward on the string, ignoring all other tokens until
we reach the */ end of comment block. This is done by creating a substring out of the input
string starting at the */ end of comment block.

The next case to cover for is just // line comments. This simply will run a regex that, upon finding
// at the start of a string, will proceed to skip everything after and move to the next line.

Line 120 begins the logic which covers an ID token whose substring begins with a Keyword but extends
past the keyword with additional letters.

After this, we have 6 logical cases, one for each of the 6 keywords the regex can match. 

Following this is the regex logic that will match any number of characters as an ID [a-zA-Z]+. '+' will
count as any number of letters above 0. 

After this is the regex logic covering tokens belonging to symbols. The symbols are broken up into
symbol1 and symbol2. This distinction is made for the symbols with 2 characters (==, <=, etc) being
read as a single symbol rather than as two symbols. Thus, the two-char symbols are above priority the 
single-char symbols. 

Following this is the regex logic similar to the above for, but with integers instead of letters.

Lastly is a regex covering all non-valid tokens which are to be classified as errors.
This regex simply considers any of the above tokens, including INT, ID, KW, Symbol. If no match is 
found, then it considers the token an error token (whitespace is handled constantly via input.trim()
and need not be concern