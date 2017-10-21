package facebook;


/**
Prettify JSON: 输入[1,2,3, {"id": 1, "name": "wang", "tag":[1,"home",2], "price":234}]
 */
public class PrintJSON {

	public static void main(String[] args) {
		String str = "[1,2,3, {\"id\": 1, \"name\": \"wang\", \"tag\":[1,\"home\",2], \"price\":234}]";
		new PrintJSON().print(str);
	}

	public void print(String str) {
		String indent = "";
		boolean isChangeLine = false;
		for (char c : str.toCharArray()) {
			if (c == ']' || c == '}') {
				indent = indent.substring(0, indent.length()-2);
				isChangeLine = true;
			}
			if (isChangeLine && c == ' ') continue;
			if (isChangeLine) {
				System.out.println("");
				System.out.printf(indent);
				isChangeLine = false;
			}
			System.out.printf("%s", c);
			if (c == '[' || c == '{' || c == ',') {
				isChangeLine = true;
				if (c == '[' || c == '{')
					indent += "  ";
			}
			
		}
	}

	public void printRecurse(String str) {
		helper(str.trim(), "");
	}

	private void helper(String obj, String indent) {
		if (obj.length() == 0) return;
		if (obj.charAt(0) == '[' || obj.charAt(0) == '{') {
			int last  = obj.length()-1;
			if (obj.charAt(last) ==  ',') last--;
			System.out.println(String.format("%s%s", indent, obj.charAt(0)));
			System.out.print(indent + "  ");
			helper(obj.substring(1, last), indent + "  ");
			System.out.println();
			System.out.printf("%s%s", indent, obj.charAt(last));
			helper(obj.substring(last+1), indent);
		} else if (obj.charAt(0) == ',') {
			System.out.println(",");
			System.out.print(indent);
			helper(obj.substring(1), indent);
		} else {
			System.out.printf("%s", obj.charAt(0));
		}
	}
}
