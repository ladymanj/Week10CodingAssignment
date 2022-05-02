package entity;

public class BoardGame {
	
	private int id;
	private String name;
	private int minPlayerCount;
	private int maxPlayerCount;
	
	public BoardGame(int id, String name, int minPlayerCount, int maxPlayerCount) {
		this.setId(id);
		this.setName(name);
		this.setMinPlayerCount(minPlayerCount);
		this.setMaxPlayerCount(maxPlayerCount);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinPlayerCount() {
		return minPlayerCount;
	}

	public void setMinPlayerCount(int minPlayerCount) {
		this.minPlayerCount = minPlayerCount;
	}

	public int getMaxPlayerCount() {
		return maxPlayerCount;
	}

	public void setMaxPlayerCount(int maxPlayerCount) {
		this.maxPlayerCount = maxPlayerCount;
	}

}
