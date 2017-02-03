package cs652.repl;

import java.io.BufferedReader;
import java.util.Stack;

public class NestedReader {
	
	StringBuilder buf;
	BufferedReader input;
	int c;
	
	public NestedReader(BufferedReader input)
	{
		this.input = input;
		buf = new StringBuilder();
	}
	
	public String getNestedString() throws Exception
	{
		if (buf.length() != 0)
			buf = new StringBuilder();
		c = input.read();
		if (c == 10) // \n
		    return '\n' + "";
        Stack<Character> s = new Stack<Character>();
		boolean comment = false;
		while (c != -1)
		{
			if (comment == true)
            {
                while(true)
                {
                    int c1 = input.read();
                    if ((char)c1 == '\n'){
                        c = c1;
                        comment = false;
                        break;
                    }
                }
            }
			switch(c)
			{
			    case '/':
					if (comment == false)
					{
						int c1 = input.read();
						if ((char)c1 != '/')
						{
							buf.append('/');
							c = c1;
						}
						else {
						    c = c1;
                            comment = true;
                        }
						break;
					}
			case '{':
				s.push('}');
				break;
			case '(':
				s.push(')');
				break;
			case '[':
				s.push(']');
				break;
			case '}':
				if (s.pop() != '}')
					return buf.toString();
				break;
			case ']':
				if (s.pop() != ']')
					return buf.toString();
				break;
			case ')':
				if (s.empty() == false && s.pop() != ')')
					return buf.toString();
				break;
			case '\n':
				if (s.empty())
					return buf.toString();
				break;
			}

			consume();
		}
		return null;
	}
	
	public void consume() throws Exception
	{
		if ((char)c != '/')
			buf.append((char)c);
		c = input.read();
	}
}
