package model;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "model_Product")
@Table(name = "Product")
public class ProductEntity implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer helmet_id;

	private String model;
	private String subCategory;
	private String brand;
	private String name;
	private String description;
	private Double price;

	@ManyToOne
	@JoinColumn(name = "FK_category", foreignKey = @ForeignKey(name = "fkc_product_category"))
	Category category;

	public ProductEntity() {
		super();
	}

	public ProductEntity(Integer helmet_id, String model, String subCategory, String brand, String name,
			String description, Double price, Category category) {
		super();
		this.helmet_id = helmet_id;
		this.model = model;
		this.subCategory = subCategory;
		this.brand = brand;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public Integer getHelmet_id() {
		return helmet_id;
	}

	public void setHelmet_id(Integer helmet_id) {
		this.helmet_id = helmet_id;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
