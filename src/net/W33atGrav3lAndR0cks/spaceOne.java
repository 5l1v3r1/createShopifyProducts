/*
__          ______ ____        _                       ____  _                 _       ___       _        
\ \        / /___ \___ \      | |                     |___ \| |               | |     / _ \     | |       
 \ \  /\  / /  __) |__) | __ _| |_ __ _ _ __ __ ___   ____) | | __ _ _ __   __| |_ __| | | | ___| | _____ 
  \ \/  \/ /  |__ <|__ < / _` | __/ _` | '__/ _` \ \ / /__ <| |/ _` | '_ \ / _` | '__| | | |/ __| |/ / __|
   \  /\  /   ___) |__) | (_| | || (_| | | | (_| |\ V /___) | | (_| | | | | (_| | |  | |_| | (__|   <\__ \
    \/  \/   |____/____/ \__,_|\__\__, |_|  \__,_| \_/|____/|_|\__,_|_| |_|\__,_|_|   \___/ \___|_|\_\___/
                                   __/ |                                                                  
                                  |___/                                                                   
*/
package net.W33atGrav3lAndR0cks;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class spaceOne {
	
	private static List<List<String>> lists = new ArrayList<List<String>>(); // all your lists
	private static List<String> result = new ArrayList<String>(); // list of your required permutations

	private static Path p = Paths.get("C:\exportDestination\spaceOne.csv");
	private static String header = "Handle,Title,Body (HTML),Vendor,Type,Tags,Published,Option1 Name,Option1 Value,Option2 Name,Option2 Value,Option3 Name,Option3 Value,Variant SKU,Variant Grams,Variant Inventory Tracker,Variant Inventory Qty,Variant Inventory Policy,Variant Fulfillment Service,Variant Price,Variant Compare At Price,Variant Requires Shipping,Variant Taxable,Variant Barcode,Image Src,Image Position,Image Alt Text,Gift Card,SEO Title,SEO Description,Google Shopping / Google Product Category,Google Shopping / Gender,Google Shopping / Age Group,Google Shopping / MPN,Google Shopping / AdWords Grouping,Google Shopping / AdWords Labels,Google Shopping / Condition,Google Shopping / Custom Product,Google Shopping / Custom Label 0,Google Shopping / Custom Label 1,Google Shopping / Custom Label 2,Google Shopping / Custom Label 3,Google Shopping / Custom Label 4,Variant Image,Variant Weight Unit,Variant Tax Code,Cost per item,Status\n";
	private static String t = " "; // you can change this to e.g. ";" or "-" or "_" (NOT ","!)
	private static String t2 = ","; // Shopify uses "," to seperate the columns

	public static void main(String[] args) throws IOException {
		Files.write(p, header.getBytes());
		fillLists();
		generatePermutations(lists, result, 0, "");
		System.out.println("finished!");
	}

	public static void generatePermutations(List<List<String>> lists, List<String> result, int depth, String current)
			throws IOException {
		if (depth == lists.size()) {
			String handle = current.replaceAll(" - ", "-").replaceAll(":", "").replace("ö", "o").replace("ß", "ss").toLowerCase();
			handle = handle.replaceAll(" ", "-");
			String s = handle + current + "\"\",YourShopName,\"\",\"\",true,Title,Default Title,,,,,,0.0,,0,deny,manual,"
					+ calcPrice(current) + ",,true,true,,,,,false,,,,,,,,,,,,,,,,,kg,,,active\n";
			result.add(s);
			Files.write(p, s.getBytes(), StandardOpenOption.APPEND);
			return;
		}
		for (int i = 0; i < lists.get(depth).size(); i++) {
			generatePermutations(lists, result, depth + 1, current + lists.get(depth).get(i));
		}
	}

	private static int calcPrice(String s) {
		int preis = 999;

		if (s.contains("Option 1 1")) {
			preis = preis + 0;
		} else if (s.contains("Option 1 2")) {
			preis = preis + 19;
		}

		if (s.contains("Option 2 1")) {
			preis = preis + 19;
		} else if (s.contains("Option 2 2")) {
			preis = preis + 29;
		} else if (s.contains("Option 2 3")) {
			preis = preis + 49;
		}

		if (s.contains("Option 3 1")) {
			preis = preis + 19;
		} else if (s.contains("3 2")) {
			preis = preis + 19;
		}
		return preis;
	}

	private static void fillLists() {
		List<String> option_one = new ArrayList<String>();
		String s = "Option 1: ";
		option_one.add(s + "Option 1 1" + t); // standard
		option_one.add(s + "Option 1 2" + t); // 0€
		option_one.add(s + "Option 1 3" + t); // 19€

		List<String> option_two = new ArrayList<String>();
		s = "Option 2: ";
		option_two.add(s + "Option 2 1" + t); // standard
		option_two.add(s + "Option 2 2" + t); // 19€
		option_two.add(s + "Option 2 3" + t); // 29€
		option_two.add(s + "Option 2 4" + t); // 49€

		List<String> option_thr = new ArrayList<String>();
		s = "Option 3: ";
		option_thr.add(s + "Option 3 1" + t); // standard
		option_thr.add(s + "Option 3 2" + t); // 19€
		option_thr.add(s + "Option 3 3" + t); // 19 €

		lists.add(option_one);
		lists.add(option_two);
		lists.add(option_thr);
	}
}
