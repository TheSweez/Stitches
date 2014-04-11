//Loc0 Operation Room A = Scalpel
//loc4 Bathroom = Toilet Paper
//loc5 Janitors Closet = Med Supply Closet key
//loc9 Medical Supply Room = Master Key
public class Item {
	private int id;
	private String name; //Item name
	private String desc; //Item description
	private boolean found = false; //Player hasn't taken item yet when false.

	public Item(int num, String name2, boolean b) {
		id = num;
		name = name2;
		b = false;
	}
	public Item(int id){
		this.id=id;
	}
	public String getDesc() {
		return desc;
	}
	public String getName() {
		return name;
	}
	public void setDesc(String desc){
		this.desc = desc;
	}
	public void setName(String name){
		this.name = name;
	}
	public boolean itemFound(){
		return found;
	}
	public void setFound(boolean found){
		this.found = found;
	}
		public String toString(){
		return "name: "+ this.name +" desc: "+ this.desc;
	}
}
