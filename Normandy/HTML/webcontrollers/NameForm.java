package game.webcontrollers;

/**
 * Created by mzwart on 23-11-2016.
 */
public class NameForm {

	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println("Setting name");
	}
}
