# Bash-Sub-Shell
Example of a parser for a simple bash script recursive decent parser.  Grammar conforms to problem 4.10 from Programming Language Processors in Java by Watt and Brown.

### Grammar for a subset of the Bash shell scripting language

AST Building Branch
### To Run
```
Parameters:
    -d (Print to console)
    -p (Print to file)

Example:
    compile2c -p users/jake/RecursiveDescentParser/testAst.sh
```
```
  Script  -> Command*
  Command -> Filename Argument* eol
           | Variable = Argument* eol
           | if Filename Arugment* then eol
                Command*
             else eol
                Command*
             fi eol
           | for Variable in Argument* eol
                 do eol
                   Command*
                 od eol
  Argument -> Filename | Literal | Variable
  Filename -> cat | ls | pwd | touch | cp | mv | rm | chmod | man | ps | bg | mkdir | test | cd
  Variable -> Letter(Letter | Digit | _ | .)*
  Literal -> -(ε|-)(Letter | Digit)* | Digit*
```
### Abstract Syntax Grammar for a subset of the Bash shell scripting language
```
  Script   -> Command                         ( Script )
  Command   -> Filename Argument  eol         ( Exec-Cmd )
             | Variable = Single-Arg eol      ( Assign-Cmd )
             | if Filename Arugment then eol  ( if-Cmd )
                  Command
               else eol
                  Command
               fi eol
             | for Variable in Argument eol   ( for-Cmd )
                 do eol
                   Command
                 od eol
             | Command Command                 ( Seq-Cmd)
             | ε                               ( Empty-Cmd )
           
  Argument   -> Filename                       ( FName-Arg )
              | Literal                        ( Literal-Arg )
              | Variable                       ( Var-Arg )
              | Argument Argument              ( Seq-Argument )
            
   Single-Arg -> Filename                      ( FName-Arg )
               | Literal                       ( Literal-Arg )
               | Variable                      ( Var-Arg )
            
  Filename -> cat | ls | pwd | touch | cp | mv | rm | chmod | man | ps | bg | mkdir | test | cd
  Variable -> Letter(Letter | Digit | _ | .)*
  Literal -> -(ε|-)(Letter | Digit)* | Digit*
``` 

