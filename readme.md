<p>In this project, I tried to implement a system that can represent nested mathematical expressions that include variables, evaluate their values for specific variable assignments, differentiate them, and simplify the results.</p>

<p>In doing so we will work in a recursive framework, see some more examples of polymorphism, and practice the use of inheritance and class hierarchies for sharing of common code.</p>

<p>&nbsp;</p>

<p>Our goal is to represent mathematical expressions such as:</p>

<p>sin(((2x + y) * 4)^x)</p>

<p>Where the&nbsp;<code>^</code>&nbsp;the symbol denotes the &quot;power&quot; operator, and&nbsp;<code>x</code>&nbsp;and&nbsp;<code>y</code>&nbsp;are variables.<br />
Complicated expression is composed of atomic expressions which are either binary or unary, arranged in a tree structure. The expression itself is the root of the tree.</p>

<p style="text-align:center">&nbsp;</p>

<p style="text-align:center"><img alt="expression tree" src="https://github.com/kleinay/biuoop2020/wiki/images/tree.png" /></p>

<p style="text-align:center">&nbsp;</p>

<p>Class Hierarchy:</p>

<p style="text-align:center"><img alt="UML class diagram" src="https://github.com/kleinay/biuoop2020/wiki/images/expes-classes.png" /></p>
