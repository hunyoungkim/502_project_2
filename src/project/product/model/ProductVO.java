package project.product.model;

public class ProductVO {
private int goods_num;
private String category;
private String goods_name;
private String energy_efcnc;
private String price;
private String liter;
private String image;
private String old_file;
public int getGoods_num() {
	return goods_num;
}
public void setGoods_num(int goods_num) {
	this.goods_num = goods_num;
}
public String getCategory() {
	return category;
}
public void setCategory(String category) {
	this.category = category;
}
public String getGoods_name() {
	return goods_name;
}
public void setGoods_name(String goods_name) {
	this.goods_name = goods_name;
}
public String getEnergy_efcnc() {
	return energy_efcnc;
}
public void setEnergy_efcnc(String energy_efcnc) {
	this.energy_efcnc = energy_efcnc;
}
public String getPrice() {
	return price;
}
public void setPrice(String price) {
	this.price = price;
}
public String getLiter() {
	return liter;
}
public void setLiter(String liter) {
	this.liter = liter;
}
public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public String getOld_file() {
	return old_file;
}
public void setOld_file(String old_file) {
	this.old_file = old_file;
}
@Override
public String toString() {
	return "ProductVO [goods_num=" + goods_num + ", category=" + category + ", goods_name=" + goods_name
			+ ", energy_efcnc=" + energy_efcnc + ", price=" + price + ", liter=" + liter + ", image=" + image
			+ ", old_file=" + old_file + "]";
}

}
