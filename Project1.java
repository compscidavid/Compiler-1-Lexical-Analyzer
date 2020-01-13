import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Project1 {
	public static void main(String[] args) throws Exception {
		//command line argument for fileName
		//String fileName = args[0];
		//File file = new File(fileName);
		File file = new File("C:\\Users\\David\\eclipse-workspace\\Project1\\src\\test_fn.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String input;
		Boolean blockComment = false;
		// ArrayList to store tokens for Parser in sequential order (token = type of
		// token and name of token)
		ArrayList<Token> arrList = new ArrayList<Token>();
		// KEYWORDS
		// else if int return void while
		// SYMBOLS
		// + - * / < <= > >= == != = ; , ( ) [ ] { } /* */
		// ESCAPE CHARS
		// [ ] ( ) { } . * + ? ^ $ \ |

		// PATTERNS
		Pattern commentBlockEnd = Pattern.compile("^\\*/");
		Pattern commentBlockEndSameLine = Pattern.compile("\\*/");
		Pattern commentBlockStart = Pattern.compile("^/\\*");
		Pattern commentLine = Pattern.compile("^//");
		Pattern returnKW = Pattern.compile("^return");
		Pattern whileKW = Pattern.compile("^while");
		Pattern voidKW = Pattern.compile("^void");
		Pattern elseKW = Pattern.compile("^else");
		Pattern intKW = Pattern.compile("^int");
		Pattern ifKW = Pattern.compile("^if");
		Pattern symbol1 = Pattern.compile("^==|^<=|^>=|^!=");
		Pattern symbol2 = Pattern.compile("^\\+|^\\-|^\\*|^/|^=|^<|^>|^;|^,|^\\(|^\\)|^\\[|^\\]|^\\{|^\\}");
		Pattern id = Pattern.compile("(^[a-zA-Z]+)");
		Pattern error = Pattern.compile(
				"^([a-zA-Z]|[\\d]|\\+|^\\-|^\\*|^/|^=|^<|^>|^;|^,|^\\(|^\\)|^\\[|^\\]|^\\{|^\\}|==|^<=|^>=|^!=)");
		Pattern integer = Pattern.compile("^(\\d+)");
		Pattern kwID = Pattern.compile(
				"^return[a-zA-Z]*[\\d]+|^return[\\d]*[a-zA-Z]+|^while[a-zA-Z]*[\\d]+|^while[\\d]*[a-zA-Z]+|^void[a-zA-Z]*[\\d]+|^void[\\d]*[a-zA-Z]+|^else[a-zA-Z]*[\\d]+|^else[\\d]*[a-zA-Z]+|^int[a-zA-Z]*[\\d]+|^int[\\d]*[a-zA-Z]+|^if[a-zA-Z]*[\\d]+|^if[\\d]*[a-zA-Z]+");
		// matcher for checking regex
		Matcher check;
		// index for modifying substrings
		int matchPos;

		while ((input = br.readLine()) != null) {
			// iterates to next line if there is nothing but blank space on a Line.
			if (input.isEmpty()) {
				continue;
			}

			// only prints line input if there is an input on the line besides blank space
			if (!input.trim().isEmpty()) {
				System.out.println("INPUT: " + input);
			}
			outerLoop: while (input != null) {
				// trims whitespace from front and back of string
				input = input.trim();

				// iterates to next line if there is nothing but blank space on a Line.
				if (input.isEmpty()) {
					break outerLoop;
				}

				// checks for end of comment block */
				if (blockComment == true) {
					check = commentBlockEnd.matcher(input);
					if (check.find()) {
						// upon finding */, toggles blockComment to false and moves string forwards
						blockComment = false;
						matchPos = check.end();
						input = input.substring(matchPos);
						continue;
					}
				}

				// checks for start of comment block /*
				check = commentBlockStart.matcher(input);
				if (check.find()) {
					// upon finding /*, toggles blockComment to true and moves string forwards
					blockComment = true;
					matchPos = check.end();
					input = input.substring(matchPos);
					continue;
				}

				// if inside BlockComment, logic to try to find another */ on the line
				if (blockComment == true) {
					// check if the next term ends the comment block */
					check = commentBlockEnd.matcher(input);
					// while the next term is not the end comment block,
					while (!check.find()) {
						// check if the entire line contains an end of comment block
						check = commentBlockEndSameLine.matcher(input);
						// if no */ exists on the entire line, the entire line is a comment
						if (!check.find()) {
							break outerLoop;
						}
						// if */ does exist on the line, it will ignore the string up to that point
						matchPos = check.start();
						input = input.substring(matchPos);
						// then check again for the end of comment block
						check = commentBlockEnd.matcher(input);
					}
					continue;
				}

				// checks if the string begins with //, then the entire line is a comment
				check = commentLine.matcher(input);
				if (check.find()) {
					break outerLoop;
				}

				// regex for ID starting with a KW
				check = kwID.matcher(input);
				if (check.find()) {
					matchPos = check.end();
					System.out.println("ID: " + input.substring(check.start(), matchPos));
					input = input.substring(matchPos);
					input = input.trim();
					check = kwID.matcher(input);
					continue;
				}

				// KW regexes
				check = returnKW.matcher(input);
				if (check.find()) {
					matchPos = check.end();
					System.out.println("KW: " + input.substring(check.start(), matchPos));
					input = input.substring(matchPos);
					input = input.trim();
					check = returnKW.matcher(input);
					continue;
				}

				check = whileKW.matcher(input);
				if (check.find()) {
					matchPos = check.end();
					System.out.println("KW: " + input.substring(check.start(), matchPos));
					input = input.substring(matchPos);
					input = input.trim();
					check = whileKW.matcher(input);
					continue;
				}

				check = voidKW.matcher(input);
				if (check.find()) {
					matchPos = check.end();
					System.out.println("KW: " + input.substring(check.start(), matchPos));
					input = input.substring(matchPos);
					input = input.trim();
					check = voidKW.matcher(input);
					continue;
				}

				check = elseKW.matcher(input);
				if (check.find()) {
					matchPos = check.end();
					System.out.println("KW: " + input.substring(check.start(), matchPos));
					input = input.substring(matchPos);
					input = input.trim();
					check = elseKW.matcher(input);
					continue;
				}

				check = intKW.matcher(input);
				if (check.find()) {
					matchPos = check.end();
					System.out.println("KW: " + input.substring(check.start(), matchPos));
					input = input.substring(matchPos);
					input = input.trim();
					check = intKW.matcher(input);
					continue;
				}

				check = ifKW.matcher(input);
				if (check.find()) {
					matchPos = check.end();
					System.out.println("KW: " + input.substring(check.start(), matchPos));
					input = input.substring(matchPos);
					input = input.trim();
					check = ifKW.matcher(input);
					continue;
				}

				// ID regexes
				check = id.matcher(input);
				if (check.find()) {
					matchPos = check.end();
					System.out.println("ID: " + input.substring(check.start(), matchPos));
					input = input.substring(matchPos);
					input = input.trim();
					check = id.matcher(input);
					continue;
				}

				// 2-length symbol regexes
				check = symbol1.matcher(input);
				if (check.find()) {
					matchPos = check.end();
					System.out.println(input.substring(check.start(), matchPos));
					input = input.substring(matchPos);
					input = input.trim();
					check = symbol1.matcher(input);
					continue;
				}

				// 1-length symbol regexes
				check = symbol2.matcher(input);
				if (check.find()) {
					matchPos = check.end();
					System.out.println(input.substring(check.start(), matchPos));
					input = input.substring(matchPos);
					input = input.trim();
					check = symbol2.matcher(input);
					continue;
				}

				// int regexes
				check = integer.matcher(input);
				if (check.find()) {
					matchPos = check.end();
					System.out.println("INT: " + input.substring(check.start(), matchPos));
					input = input.substring(matchPos);
					input = input.trim();
					check = integer.matcher(input);
					continue;
				}

				check = error.matcher(input);
				if (!check.find()) {
					// matchPos = check.end();
					System.out.println("ERROR: " + input.substring(0, 1));
					input = input.substring(1);
					input = input.trim();
					check = error.matcher(input);
					continue;
				}

				continue;

			} // end of while- line empty
		} ///////////////////// end of while- no more lines to read
	} //////////////////////////// end of main

} /// main class

// "(\d*\.\d+E\+\d+|\d*\.\d+E-\d+)|(<=)|(>=)|(==)|(!=)|(-)|(\*)|(/)|(<)|(>)|(=)|(;)|(,)|(\+)|(\()|(\))|(\[)|(])|({)|(})(\d+)|(\d*\.\d+)|([A-Za-z]+)"