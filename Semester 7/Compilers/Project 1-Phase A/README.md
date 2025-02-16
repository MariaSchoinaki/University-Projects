# **MiniPython Compiler - Lexical & Syntax Analysis**

## **Overview**
This project focuses on **lexical and syntax analysis** for the **MiniPython language**, implemented using **SableCC**. It consists of two main phases:
1. **Lexical Analysis** - Tokenizing the input source code.
2. **Syntax Analysis** - Parsing tokens based on a defined grammar.

The project aims to **construct a compiler front-end**, ensuring proper **tokenization, parsing, and AST construction**.

## **Key Components**
- **Lexical Analysis**
  - Tokenization using **regular expressions**.
  - Identifying **keywords, identifiers, operators, and literals**.
  - Using `LexerTest1.java` for testing token recognition.
- **Syntax Analysis**
  - Defining grammar rules for MiniPython.
  - Resolving **ambiguities and precedence conflicts**.
  - Using `ParserTest1.java` for parsing validation.

## **Project Breakdown**
### **1. Lexical Analysis (Lexer)**
- Implemented using **SableCC**, defining:
  - `Helpers` - Character-level token structure.
  - `States` - State transitions for lexing.
  - `Tokens` - Defined **keywords, operators, identifiers, and literals**.
- **Execution:**
  ```bash
  sablecc minipython.grammar
  javac LexerTest1.java
  java LexerTest1 minipythonexample.py
  ```

### **2. Syntax Analysis (Parser)**
- Grammar extended with **Productions**:
  - Defines **MiniPython's syntactic rules**.
  - Resolves **shift/reduce and reduce/reduce conflicts**.
  - Uses **ignored tokens for whitespace and comments**.
- **Execution:**
  ```bash
  sablecc minipython.grammar
  javac ParserTest1.java
  java ParserTest1 minipythonexample.py
  ```

---

## **Tools & Technologies Used**
- **SableCC** for compiler construction.
- **Java (LexerTest1 & ParserTest1)** for validation.
- **Linux/Windows (Command Line Execution).**

---

## **Project Team**
- **Giorgos Andritsos**
- **Christos Stamoulos**
- **Anthippi Fatsea**
- **Maria Schoinaki**