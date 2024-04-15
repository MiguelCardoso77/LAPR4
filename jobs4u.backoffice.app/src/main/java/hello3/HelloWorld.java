package hello3;

import authentication.AddUserUI;

public class HelloWorld {
	public static void main(String[] args) {
		Greeter greeter = new Greeter();
		System.out.println(greeter.sayHello());
		AddUserUI addUserUI = new AddUserUI();
		addUserUI.registerUser();
	}
}
