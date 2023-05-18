import java.util.Stack;

public class EvaluateString {
	public static int evaluate(String expression)
    {
        char[] tokens = expression.toCharArray();
 
        Stack<Integer> values = new
                              Stack<Integer>();
 
        Stack<Character> ops = new
                              Stack<Character>();
 
        for (int i = 0; i < tokens.length; i++)
        {
             
            if (tokens[i] == ' ')
                continue;
 
            if (tokens[i] >= '0' &&
                 tokens[i] <= '9')
            {
                StringBuffer sbuf = new
                            StringBuffer();
                 
                while (i < tokens.length &&
                        tokens[i] >= '0' &&
                          tokens[i] <= '9')
                    sbuf.append(tokens[i++]);
                values.push(Integer.parseInt(sbuf.
                                      toString()));
               
                  i--;
            }
 
            else if (tokens[i] == '(')
                ops.push(tokens[i]);
 
            else if (tokens[i] == ')')
            {
                while (ops.peek() != '(')
                  values.push(applyOp(ops.pop(),
                                   values.pop(),
                                 values.pop()));
                ops.pop();
            }
 
            else if (tokens[i] == '+' ||
                     tokens[i] == '-' ||
                     tokens[i] == '*' ||
                     tokens[i] == 'o' ||
                        tokens[i] == '/')
            {
                while (!ops.empty() &&
                       hasPrecedence(tokens[i],
                                    ops.peek()))
                  values.push(applyOp(ops.pop(),
                                   values.pop(),
                                 values.pop()));
 
                ops.push(tokens[i]);
            }
        }
 
        while (!ops.empty())
            values.push(applyOp(ops.pop(),
                             values.pop(),
                           values.pop()));

        return values.pop();
    }

    public static boolean hasPrecedence(
                           char op1, char op2)
    {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/' || op1 == 'o') &&
            (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;
    }

    public static int applyOp(char op,
                           int b, int a)
    {
        switch (op)
        {
        case '+':
        	if (a == 0 && b == 0) {
        		return 0;
        	} else {
        		return 1;
        	}
        case 'o':
        	if (a == b) {
        		return 0;
        	} else {
        		return 1;
        	}
        case '*':
        	if (a == 1 && b == 1) {
        		return 1;
        	} else {
        		return 0;
        	}
        }
        return 0;
    }
}
