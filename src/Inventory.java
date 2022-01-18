
public class Inventory {

	private String name;
	private String description;
	private String quantity;	
	private String location;
	
	public Inventory() {
		
	}
	
	public Inventory(String n, String d, String q, String l) {
		name = n;
		description = d;
		quantity = q;
		location = l;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String toString() {
		return name + "," + description + "," + quantity + "," + location;
	}
}
