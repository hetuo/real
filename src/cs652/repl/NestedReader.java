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
		if (c == 10)
		    return '\n' + "";
		//System.out.println(c + "xxxxxxxxxx");
        Stack<Character> s = new Stack<Character>();
		
		while (c != -1)
		{
			//System.out.print((char)c + " ");
			switch(c)
			{
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
		buf.append((char)c);
		//System.out.println("####" + buf.toString());
		c = input.read();
		//System.out.print((char)c + " ");
	}
}
