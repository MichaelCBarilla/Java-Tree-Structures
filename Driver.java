
public class Driver {

	public static void main(String[] args) {
		PhoneListing mike = new PhoneListing("Mike", "1252 Isadora ct.", "112");
		PhoneListing tom = new PhoneListing("Tom", "525 Adelphia rd.", "115");
		PhoneListing darian = new PhoneListing("Darian", "17 Taunton rd.", "114");
		PhoneListing diane = new PhoneListing("Diane", "9 Orange dr.", "117");
		PhoneListing kerry = new PhoneListing("Kerry", "111 Get dr.", "113");
		PhoneListing zac = new PhoneListing("Zac", "1252 Isadora ct.", "111");
		PhoneListing vaughn = new PhoneListing("Vaughn", "1252 Isadora ct.", "120");
		PhoneListing victor = new PhoneListing("Victor", "1252 Isadora ct.", "120");
		PhoneListing zong = new PhoneListing("Zong", "525 Adelphia rd.", "118");
		PhoneListing jeff = new PhoneListing("Jeff", "17 Taunton rd.", "119");
		PhoneListing abby = new PhoneListing("Abby", "9 Orange dr.", "116");
		PhoneListing dummy = new PhoneListing();

		MyBinaryTree<PhoneListing> bt = new MyBinaryTree<PhoneListing>();
		bt.insert(mike);
		bt.insert(tom);
		bt.insert(darian);
		bt.insert(diane);
		bt.insert(kerry);
		bt.insert(zac);
		bt.insert(zong);
		bt.insert(jeff);
		bt.insert(vaughn);
		bt.insert(victor);
		
		System.out.println(bt.fetch("Jeff"));
		System.out.println(bt.fetch("Zong"));
		System.out.println(bt.fetch("Jaff"));
		
		bt.delete("Zong");
		
		bt.update("Vaughn", abby);
		
		System.out.println("==============================================");
		System.out.println("==============================================");
		System.out.println("==============================================");
		bt.showAll();
		System.out.println("==============================================");
		System.out.println("==============================================");
		System.out.println("==============================================");
		bt.showAll();
		System.out.println("==============================================");
		System.out.println("==============================================");
		System.out.println("==============================================");
		bt.showAll();
	}

}
