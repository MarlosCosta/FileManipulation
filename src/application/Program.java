package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entities.Product;

/*Programa para criar um arquivo CSV através de um arquivo TXT separado por virgula.
 * ex. do conteudo de arquivo txt com descrição, valor e quantidade:
 * TV LED,1290.00,5
 * SAMSUNG GALAXY,1000.00,2
 * GELADEIRA,3000.00,1 
 * 
 * 
 * 
 * sourceFileStr = arquivo origem
 * sourceFolderStr = caminho do arquivo origem
 * targetFileStr = arquivo destino
 * itemCsv = item gravado no arquivo destino
*/
public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner input = new Scanner(System.in);
		
		List<Product> list = new ArrayList<>();
		
		System.out.println("Enter file path: ");
		String sourceFileStr = input.nextLine();
		
		File sourceFile = new File(sourceFileStr);
		String sourceFoldeStr = sourceFile.getParent();
		
		boolean success = new File(sourceFoldeStr + "\\out").mkdir();
		
		String targetFileStr = sourceFoldeStr + "\\out\\summary.csv";
		
		try (BufferedReader br = new BufferedReader(new FileReader(sourceFileStr))) {
			
			String itemCsv = br.readLine();
			while (itemCsv != null) {
				String[] fields = itemCsv.split(",");
				String name = fields[0];
				double price = Double.parseDouble(fields[1]);
				int quantity = Integer.parseInt(fields[2]);
				
				list.add(new Product(name, price, quantity));
				
				itemCsv = br.readLine();
			}
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(targetFileStr))) {
			
			for (Product item : list) {
				bw.write(item.getName() + "," + String.format("%.2f", item.total()));
				bw.newLine();
			}
			
			System.out.println("CREATED!");
		
		}catch (IOException error) {
			System.out.println("Error reading file: " + error.getMessage());
		}
			
		}catch (IOException error) {
			System.out.println("Error reading file: " + error.getMessage());
		}
		
		
		
		input.close();
	}

}
