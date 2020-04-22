/*
 * Program created by Jay Rana
 * 
 * This program turns a nucleotide sequence from a DNA template strand into its
 * corresponding coding strand, messenger RNA strand, amino acid sequence, and
 * proteins.
 * 
 * You are allowed to reuse this code as long as you mention me in the credits.
 */

package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import obj.DNATranslator;

public class Main {

	public static void main(String[] args) {

		DNATranslator translator = new DNATranslator();
		BufferedReader reader;
		BufferedWriter codingStrandWriter;
		BufferedWriter messengerRNAWriter;
		BufferedWriter aminoAcidWriter;
		BufferedWriter proteinsWriter;
		
		try {
			
			reader = new BufferedReader(new FileReader(new File("src\\main\\template_strand.txt")));
			codingStrandWriter = new BufferedWriter(new FileWriter(new File("src\\main\\coding_strand.txt")));
			messengerRNAWriter = new BufferedWriter(new FileWriter(new File("src\\main\\messenger_RNA.txt")));
			aminoAcidWriter = new BufferedWriter(new FileWriter(new File("src\\main\\amino_acids.txt")));
			proteinsWriter = new BufferedWriter(new FileWriter(new File("src\\main\\proteins.txt")));
			
			String nucleotides = reader.readLine();
			
			translator = new DNATranslator(nucleotides);
			translator.run();
			
			codingStrandWriter.write(translator.getCodingStrand());
			messengerRNAWriter.write(translator.getMessengerRNA());
			aminoAcidWriter.write(DNATranslator.concatenate(translator.getAminoAcidSequence(), "-"));
			
			int count = 0;
			String str = "";
			for(ArrayList<String> subProtein: translator.getProteins()) {
				
				count++;
				str += count + ": [" + DNATranslator.concatenate(subProtein, "-") + "] (" + subProtein.size() + ")" + "\n";
				
			}
			
			proteinsWriter.write(str);
			
			reader.close();
			codingStrandWriter.close();
			messengerRNAWriter.close();
			aminoAcidWriter.close();
			proteinsWriter.close();
			
		} catch (IOException e) {

			e.printStackTrace();
			
		}
		
		

	}

}
