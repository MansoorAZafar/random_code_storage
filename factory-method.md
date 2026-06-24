# Facotry Method
A creational design pattern where you delegate the creation of a related classes to a 'factory'. Why a facotry? Because it's whole purpose will be to create said classes. 
There are 2.5 types of factories:
  1. Simple Factory (0.5th of a design pattern)
  2. Factory Method
  3. Abstract Factory

### Simple Factory
  - All concrete products must implement the same interface or abstract product
  - Not a full fledged pattern
  - The first step for understanding the Factory Method Design Pattern
  - Follows Single Responsibility Pattern

#### Why it's not a full fledged Design pattern
  - It's still open for modification
    - To extend, we will have to 'open' the method
      - We would have to add more case statements.    

#### UML:

![image](https://github.com/user-attachments/assets/12b41217-b2fa-499f-9959-93ff12f35d01)

#### Code:
```java
public class Main {
	public static void main(String[] args) {
		OSMaker creator = new OSMaker();
		creator.MakeOS(OSType.ANDROID);
		creator.MakeOS(OSType.WINDOWS);
	}
}

interface OS {
	void spec();
}

class Android implements OS {
	@Override
	public void spec() {
		System.out.println("The best OS");
	}
}

class Windows implements OS {
	@Override
	public void spec() {
		System.out.println("The most bloated OS");
	}
}

class Apple implements OS {
	@Override
	public void spec() {
		System.out.println("The most secure OS");
	}
}

enum OSType {
	ANDROID,
	WINDOWS,
	APPLE
}

class OSFactory {
	private OS operatingSystem;
	
	public OS GetInstance(OSType type) {
		switch(type) {
			case OSType.WINDOWS: 
				return new Windows();
			case OSType.APPLE:
				return new Apple();
			default:
				return new Android();
		}
	}
}

class OSMaker {
	public OS MakeOS(OSType type) {
		OSFactory factory = new OSFactory();
		OS desiredOS = factory.GetInstance(type);
		desiredOS.spec();
		
		return desiredOS;
	}
}
```

### Factory Method  
  - Creational Design Pattern
  - loosens coupling
    - separates the construction code from the code that uses this product
  - Relies heavily on inheritance
  - Delegates objects creations to subclasses that implement the factory method
    - Creator subclasses will decide what to instantiate   
