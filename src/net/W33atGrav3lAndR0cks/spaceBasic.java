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

public class spaceBasic {
	private static List<List<String>> lists = new ArrayList<List<String>>(); // all your lists
	private static List<String> result = new ArrayList<String>(); // list of your required permutations

	private static Path p = Paths.get("C:\\Users\\Dominik\\Desktop\\spaceBasic.csv");
	private static String header = "Handle,Title,Body (HTML),Vendor,Type,Tags,Published,Option1 Name,Option1 Value,Option2 Name,Option2 Value,Option3 Name,Option3 Value,Variant SKU,Variant Grams,Variant Inventory Tracker,Variant Inventory Qty,Variant Inventory Policy,Variant Fulfillment Service,Variant Price,Variant Compare At Price,Variant Requires Shipping,Variant Taxable,Variant Barcode,Image Src,Image Position,Image Alt Text,Gift Card,SEO Title,SEO Description,Google Shopping / Google Product Category,Google Shopping / Gender,Google Shopping / Age Group,Google Shopping / MPN,Google Shopping / AdWords Grouping,Google Shopping / AdWords Labels,Google Shopping / Condition,Google Shopping / Custom Product,Google Shopping / Custom Label 0,Google Shopping / Custom Label 1,Google Shopping / Custom Label 2,Google Shopping / Custom Label 3,Google Shopping / Custom Label 4,Variant Image,Variant Weight Unit,Variant Tax Code,Cost per item,Status\n";
	private static String t = " ";
	private static String t2 = ",";

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
			String s = handle + current + "\"\",workspace4home,\"\",\"\",true,Title,Default Title,,,,,,0.0,,0,deny,manual,"
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
		int preis = 499;

		if (s.contains("1200 X 800 X 25 Mm")) {
			preis = preis + 0;
		} else if (s.contains("1400 X 700 X 25 Mm")) {
			preis = preis + 19;
		} else if (s.contains("1400 X 800 X 25 Mm")) {
			preis = preis + 19;
		} else if (s.contains("1600 X 800 X 25 Mm")) {
			preis = preis + 29;
		} else if (s.contains("1800 X 800 X 25 Mm")) {
			preis = preis + 39;
		}

		if (s.contains("Steckdose Atom Mit Leiste")) {
			preis = preis + 79 + 19;
		} else if (s.contains("Steckdose Bachmann Mit Leiste")) {
			preis = preis + 99 + 19;
		}

		if (s.contains("Mit Rollen")) {
			preis = preis + 29;
		}
		
		if (s.contains("Mit Abgerundeten Ecken")) {
			preis = preis + 39;
		}

		return preis;
	}

	private static void fillLists() {
		List<String> tischplattengroesse = new ArrayList<String>();
		String s = "Plattengröße: ";
		tischplattengroesse.add(s + "1200 X 650 X 25 Mm" + t); // standard
		tischplattengroesse.add(s + "1200 X 800 X 25 Mm" + t); // 0€
		tischplattengroesse.add(s + "1400 X 700 X 25 Mm" + t); // 19€
		tischplattengroesse.add(s + "1400 X 800 X 25 Mm" + t); // 19€
		tischplattengroesse.add(s + "1600 X 800 X 25 Mm" + t); // 29€
		tischplattengroesse.add(s + "1800 X 800 X 25 Mm" + t); // 39€

		List<String> steckdose = new ArrayList<String>();
		s = "Steckdose: ";
		steckdose.add(s + "Ohne Steckdose" + t); // standard
		steckdose.add(s + "Steckdose Atom Mit Leiste" + t); // 79€ + 19€
		steckdose.add(s + "Steckdose Bachmann Mit Leiste" + t); // 99€ + 19 €

		List<String> rollen = new ArrayList<String>();
		s = "Rollen: ";
		rollen.add(s + "Ohne Rollen" + t); // standard
		rollen.add(s + "Mit Rollen" + t); // 29€
		
		List<String> ecken = new ArrayList<String>();
		s = "Abgerundete Ecken: ";
		ecken.add(s + "Ohne Abgerundete Ecken" + t2); // standard
		ecken.add(s + "Mit Abgerundeten Ecken" + t2); // 39€

		lists.add(tischplattengroesse);
		lists.add(steckdose);
		lists.add(rollen);
		lists.add(ecken);
	}
}
