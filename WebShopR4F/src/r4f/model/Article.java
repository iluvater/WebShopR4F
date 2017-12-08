package r4f.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class that represents an article
 * 
 * @author Ture
 *
 */
public class Article {

	private int id;
	private String name;
	private String description;
	private List<Integer> size;
	private double price;
	private String manufacturer;
	private List<String> color;
	private Date entryDate;
	private String category;
	private String sport;
	private int mainImage;
	private List<Integer> images;
	private Date deliveryDate;

	/**
	 * Constructor that initializes all attributes
	 * 
	 * @param id
	 *            id to set
	 * @param name
	 *            name to set
	 * @param description
	 *            description to set
	 * @param size
	 *            size to set
	 * @param price
	 *            price to set
	 * @param manufacturer
	 *            manufacturer to set
	 * @param color
	 *            color to set
	 * @param entryDate
	 *            entryDate to set
	 * @param category
	 *            category to set
	 */
	public Article(int id, String name, String description, List<Integer> size, double price, String manufacturer, List<String> color,
			Date entryDate, String category, String sport, int mainImage, List<Integer> images) {
		this.id = id;
		this.name = name;
		setDescription(description);
		this.size = size;
		this.price = price;
		this.manufacturer = manufacturer;
		this.color = color;
		this.entryDate = entryDate;
		this.category = category;
		this.sport = sport;
		this.mainImage = mainImage;
		this.images = images;
		this.deliveryDate = new Date();
		deliveryDate.setTime(deliveryDate.getTime() + 604800000);
	}

	/**
	 * Constructor that can be used to store all attributes that are requiered
	 * for creating an article in the database
	 * 
	 * @param name
	 *            name to set
	 * @param description
	 *            description to set
	 * @param size
	 *            size to set
	 * @param price
	 *            price to set
	 * @param manufacturer
	 *            manufacturer to set
	 * @param color
	 *            color to set
	 * @param category
	 *            category to set
	 */
	public Article(String name, String description, List<Integer> size, double price, String manufacturer, List<String> color,
			String category, String sport) {
		this.name = name;
		setDescription(description);
		this.size = size;
		this.price = price;
		this.manufacturer = manufacturer;
		this.color = color;
		this.entryDate = new Date();
		this.category = category;
		this.sport = sport;
		this.images = new ArrayList<Integer>();
	}

	public Article() {
		this.name = null;
		this.description = null;
		this.manufacturer = null;
		this.color = null;
		this.entryDate = null;
		this.category = null;
		this.sport = null;
		this.images = new ArrayList<Integer>();
		this.setDeliveryDate(null);
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description.replaceAll("\r\n", "<br>");
	}

	/**
	 * @return the size
	 */
	public List<Integer> getSize() {
		return size;
	}

	/**
	 * @param size
	 *            the size to set
	 */
	public void setSize(List<Integer> size) {
		this.size = size;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the manufacturer
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * @param manufacturer
	 *            the manufacturer to set
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * @return the color
	 */
	public List<String> getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(List<String> color) {
		this.color = color;
	}

	/**
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}

	/**
	 * @param entryDate
	 *            the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * @return the sport
	 */
	public String getSport() {
		return sport;
	}

	/**
	 * @param sport
	 *            the sport to set
	 */
	public void setSport(String sport) {
		this.sport = sport;
	}

	/**
	 * checks whether a string is a sport or not
	 * 
	 * @param sport
	 *            sport to check
	 * @return returns true if the sport to check is a sport
	 */
	public static boolean checkSport(String sport) {
		if (sport.equals("Laufen") || sport.equals("Fussball") || sport.equals("Basketball") || sport.equals("Golf")
				|| sport.equals("Schwimmen") || sport.equals("Fahrrad")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks whether a string is a category or not
	 * 
	 * @param category
	 *            category to check
	 * @return returns true if the category to check is a category
	 */
	public static boolean checkCategory(String category) {
		if (category.equals("Herren") || category.equals("Damen") || category.equals("Kinder")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks whether a string is a manufacturer or not
	 * 
	 * @param manufacturer
	 *            manufacturer to check
	 * @return returns true if the manufacturer to check is a manufacturer
	 */
	public static boolean checkManufacturer(String manufacturer) {
		if (manufacturer.equals("Nike") || manufacturer.equals("Asics") || manufacturer.equals("Kempa")
				|| manufacturer.equals("Hummel") || manufacturer.equals("Adidas") || manufacturer.equals("Puma")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks whether a String is a color or not
	 * 
	 * @param color
	 *            color to check
	 * @return returns true if the string is a color otherwise it will return
	 *         false
	 */
	public static boolean checkColor(String color) {
		if (color.equals("blau") || color.equals("gelb") || color.equals("grün") || color.equals("orange")
				|| color.equals("pink") || color.equals("rot") || color.equals("schwarz") || color.equals("weiß")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return the mainImage
	 */
	public int getMainImage() {
		return mainImage;
	}

	/**
	 * @param mainImage
	 *            the mainImage to set
	 */
	public void setMainImage(int image) {
		this.mainImage = image;
	}

	/**
	 * @return the images
	 */
	public List<Integer> getImages() {
		return images;
	}

	/**
	 * @param images the images to set
	 */
	public void setImages(List<Integer> images) {
		this.images = images;
	}

	/**
	 * @return the deliveryDate
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

}
