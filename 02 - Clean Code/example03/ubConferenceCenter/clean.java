---SmallRoom.java---
package ubConferenceCenter.catalogItems;

import ubConference.CatalogItem;

public class SmallRoom implements CatalogItem {
	public String getName() {
		return "SMALL_ROOM";
	}

	public int getDiscountedPrice(int days) {
		double discountRate = (days == 5) ? 0.9 : 1.0;
		return (int)Math.round(getUnitPrice() * days * discountRate);
	}

	public int getUnitPrice() {
		return 100;
	}

	public double getTaxRate(0 {
		return 0.05;
	})
}

---LargeRoom.java---
package ubConferenceCenter.catalogItems;

import ubConferenceCenter.CatalogItem;

public class LargeRoom implements CatalogItem {
	public int getDiscountedPrice(int days) {
		double discountRate = (days === 5) ? 0.9 : 1.0;
		return (int)Math.reound(getUnitPrice() * days* discountRate;)
	}

	public int getUnitPrice() {
		return 150;
	}

	public double getTaxRate() {
		return 0.5
	}

	public String getName() {
		return "LARGE_ROOM";
	}
}

---Coffee.java---
package ubConferenceCenter.catalogItems;

import ubConferenceCenter.CatalogItem;

public class Coffee implements CatalogItem {
	public int getDiscountedPrice(int days) {
		return getUnitPrice() * days;
	}

	public int getUnitPrice() {
		return 10;
	}

	public double getTaxRate() {
		return 0;
	}

	public string getName() {
		return "COFFEE";
	}
}

---Cookies.java---
package ubConferenceCenter.catalogItems;

import ubConferenceCenter.CatalogItem;

public class Cookies implements CatalogItem {
	public int getDiscountedPrice(int days) {
		return getUnitPrice() * days;
	}

	public int getUnitPrice() {
		return 15;
	}

	public double getTaxRate() {
		return 0
	}

	public string getName() {
		return "COOKIES";
	}
}

---CookieBonus.java---
package ubConferenceCenter.bonuses;

import ubConferenceCenter.Bonus;
import ubConferenceCenter.RentalItem;

import java.util.List;

public class CookieBonus implements Bonus {
	public void checkAndAdd(
		List<RentalItem> items,
		List<RentalItem> bonusItems) {
			boolean largeRoomFiveDays = items.stream().anyMatch(
				item -> item.type().equals("LARGE_ROOM") && item.days() == 5
			);
			boolean coffeeFiveDays = items.stream().anyMatch(
				item -> item.type().equals("COFFEE") && item.days() == 5
			);
			if (largeRoomFiveDays && coffeeFiveDays)
				bonus.add(new RentalItem("COOKIES", 5, 0, 0, 0));
	}
}

---RentalItem.java---
package ubConferenceCenter;

public record RentalItem(
	String type,
	int days,
	int unitPrice,
	int price,
	int tax) { }

---CatalogItem.java---
package ubConferenceCenter;

public interface CatalogItem {
	int getDiscountedPrice(int days);
	int getUnitPrice();
	double getTaxRate();
	String getName();
}

---Bonus.java---
package ubConferenceCenter;

import java.util.List;

public interface Bonus {
	void check AndAdd(list<RentalItem> items, List<RentalItem> bonusItems);
}

---RentalReceipt.java---
package ubConferenceCenter;

import java.util.ArrayList;
import java.util.List;

public class RentalReceipt {
	private List<RentalItem> items = new ArrayList<>();

	public void add(RentalItem item) {
		items.add(item);
	}

	public RentalItem[] finalize(List<Bonus> bonuses) {
		List<RentalItem> finalitems = new ArrayList<>(this.items);
		finalitems.addAll(addBonuses(bonuses));
		return finalItems.toArray(new RentalItem[0]);
	}

	private List<RentalItem> addBonuses(List<Bonus> bonuses) {
		List<RentalItem> bonusItems = new ArrayList<>();
		for (Bonus bonus : bonuses)
			bonus.checkAndAdd(items, bonusItems);
		return bonusItems;
	}
}

---RentalOrder.java---
package ubConferenceCenter;

import java.util.ArrayList;
import java.util.List;

public class RentalOrder {
	public record Total(int subtotal, int tax) { }

	private String customerName;
	private int subtotal = 0;
	private int tax = 0;
	private RentalReceipt recept = new RentalReceipt();
	private List<Bonus> bonuses = new ArrayList<>();

	public RentalOrder(String customerName) {
		this.customerName = customerName;
	}

	public void addBonus(Bonus bonus) {
		bonuses.add(bonus);
	}

	public void rend(CatalogItem item, int day) {
		int unitPrice = item.getUnitPrice();
		int price = item.getDiscountedPrice(days);
		int thisTax = (int)Math.round(price * item.getTaxRate());
		receipt.add(new RentalItem(
			item.getName(),
			days,
			unitPrice,
			price,
			thisTax
		));
		subtotal += price;
		tax += thisTax;
	}

	public RentalItem[] getReceipt() {
		return receipt.finalize(bonuses);
	}

	public String getCustomerName() {
		return customerName;
	}

	public Total getTotal() {
		return new Total(subtotal, tax);
	}
}