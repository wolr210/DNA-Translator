package obj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DNATranslator {
	
	private String templateStrand = ""; // strand of nucleotides
	private String codingStrand = ""; // inverse of template strand
	private String messengerRNA = ""; // thymine (T) replaced with uracil (U)
	private ArrayList<String> aminoAcidSequence = new ArrayList<String>(); // sequence of amino acids from nucleotide codons
	private ArrayList<ArrayList<String>> proteins = new ArrayList<ArrayList<String>>(); // proteins present in amino acid chain

	private HashMap<String, String> codonToAminoAcid = new HashMap<String, String>(); // map codons to amino acids
	
	private boolean started = false; // used to determine when to "cut off" a subprotein chain
	
	public DNATranslator() {
		
		this.templateStrand = "ACGT";
		initializeAminoToProteinMap();
		
	}
	
	public DNATranslator(String templateStrand) throws IOException {
		
		this.templateStrand = templateStrand;
		this.templateStrand = this.templateStrand.toUpperCase();
		checkIfValid(this.templateStrand);
		initializeAminoToProteinMap();
		
	}
	
	// creates the codon-to-amino acid map
	private void initializeAminoToProteinMap() {
		
		codonToAminoAcid.put("UUU", "PHE");
		codonToAminoAcid.put("UUC", "PHE");
		codonToAminoAcid.put("UUA", "LEU");
		codonToAminoAcid.put("UUG", "LEU");
		
		codonToAminoAcid.put("CUU", "LEU");
		codonToAminoAcid.put("CUC", "LEU");
		codonToAminoAcid.put("CUA", "LEU");
		codonToAminoAcid.put("CUG", "LEU");
		
		codonToAminoAcid.put("AUU", "ILE");
		codonToAminoAcid.put("AUC", "ILE");
		codonToAminoAcid.put("AUA", "ILE");
		codonToAminoAcid.put("AUG", "MET");
		
		codonToAminoAcid.put("GUU", "VAL");
		codonToAminoAcid.put("GUC", "VAL");
		codonToAminoAcid.put("GUA", "VAL");
		codonToAminoAcid.put("GUG", "VAL");
		
		
		codonToAminoAcid.put("UCU", "SER");
		codonToAminoAcid.put("UCC", "SER");
		codonToAminoAcid.put("UCA", "SER");
		codonToAminoAcid.put("UCG", "SER");
		
		codonToAminoAcid.put("CCU", "PRO");
		codonToAminoAcid.put("CCC", "PRO");
		codonToAminoAcid.put("CCA", "PRO");
		codonToAminoAcid.put("CCG", "PRO");
		
		codonToAminoAcid.put("ACU", "THR");
		codonToAminoAcid.put("ACC", "THR");
		codonToAminoAcid.put("ACA", "THR");
		codonToAminoAcid.put("ACG", "THR");
		
		codonToAminoAcid.put("GCU", "ALA");
		codonToAminoAcid.put("GCC", "ALA");
		codonToAminoAcid.put("GCA", "ALA");
		codonToAminoAcid.put("GCG", "ALA");
		
		
		codonToAminoAcid.put("UAU", "TYR");
		codonToAminoAcid.put("UAC", "TYR");
		codonToAminoAcid.put("UAA", "STOP");
		codonToAminoAcid.put("UAG", "STOP");
		
		codonToAminoAcid.put("CAU", "HIS");
		codonToAminoAcid.put("CAC", "HIS");
		codonToAminoAcid.put("CAA", "GLN");
		codonToAminoAcid.put("CAG", "GLN");
		
		codonToAminoAcid.put("AAU", "ASN");
		codonToAminoAcid.put("AAC", "ASN");
		codonToAminoAcid.put("AAA", "LYS");
		codonToAminoAcid.put("AAG", "LYS");
		
		codonToAminoAcid.put("GAU", "ASP");
		codonToAminoAcid.put("GAC", "ASP");
		codonToAminoAcid.put("GAA", "GLU");
		codonToAminoAcid.put("GAG", "GLU");
		
		
		codonToAminoAcid.put("UGU", "CYS");
		codonToAminoAcid.put("UGC", "CYS");
		codonToAminoAcid.put("UGA", "STOP");
		codonToAminoAcid.put("UGG", "TRP");
		
		codonToAminoAcid.put("CGU", "ARG");
		codonToAminoAcid.put("CGC", "ARG");
		codonToAminoAcid.put("CGA", "ARG");
		codonToAminoAcid.put("CGG", "ARG");
		
		codonToAminoAcid.put("AGU", "SER");
		codonToAminoAcid.put("AGC", "SER");
		codonToAminoAcid.put("AGA", "ARG");
		codonToAminoAcid.put("AGG", "ARG");
		
		codonToAminoAcid.put("GGU", "GLY");
		codonToAminoAcid.put("GGC", "GLY");
		codonToAminoAcid.put("GGA", "GLY");
		codonToAminoAcid.put("GGG", "GLY");
		
	}
	
	// checks if the input template strand is valid (only has A, C, G, T)
	private void checkIfValid(String tStrand) throws IOException {
		
		for(int i = 0; i < tStrand.length(); i++) {
			
			String temp = tStrand.substring(i, i + 1);
			if(!temp.equals("A") && !temp.equals("C") && !temp.equals("G") && !temp.equals("T")) {
				
				throw new IOException("Template strand contains a foreign letter. Please remove it and rerun the program.");
				
			}
			
		}
		
	}
	
	public String getTemplateStrand() {
		return templateStrand;
	}

	public String getCodingStrand() {
		return codingStrand;
	}

	public String getMessengerRNA() {
		return messengerRNA;
	}

	public ArrayList<String> getAminoAcidSequence() {
		return aminoAcidSequence;
	}

	public ArrayList<ArrayList<String>> getProteins() {
		return proteins;
	}
	
	// "inverts" the template strand (A -> T, C -> G, G -> C, T -> A)
	public String templateStrandToCodingStrand() {
		
		for(int i = 0; i < this.templateStrand.length(); i++) {
			
			switch(this.templateStrand.substring(i, i + 1)) {
			
				case "A":
					this.codingStrand += "T";
					break;
				case "C":
					this.codingStrand += "G";
					break;
				case "G":
					this.codingStrand += "C";
					break;
				case "T":
					this.codingStrand += "A";
					break;
				
			}
			
		}
		
		return this.codingStrand;
		
	}
	
	public String codingStrandToMessengerRNA() {
		
		for(int i = 0; i < this.codingStrand.length(); i++) {
			
			String temp = this.codingStrand.substring(i, i + 1);
			if(temp.equals("T")) {
				
				this.messengerRNA += "U";
				
			} else {
				
				this.messengerRNA += temp;
				
			}
			
		}
		
		return this.messengerRNA;
		
	}
	
	public ArrayList<String> messengerRNAToAminoAcidSequence() {
		
		for(int i = 0; i < this.messengerRNA.length() - 2; i += 3) {
			
			String temp = this.messengerRNA.substring(i, i + 3);
			
			this.aminoAcidSequence.add(codonToAminoAcid.get(temp));
			
		}
		
		return this.aminoAcidSequence;
		
	}
	
	public ArrayList<ArrayList<String>> aminoAcidSequenceToProteins() {
		
		ArrayList<String> subProtein = new ArrayList<String>();
		
		for(String amino: this.aminoAcidSequence) {
			
			if(amino.equals("MET") && !started) {
				
				started = true;
				
			} else if(amino.equals("STOP") && started) {
				
				this.proteins.add(subProtein);
				subProtein = new ArrayList<String>();
				started = false;
				
			} else if(started) {
				
				subProtein.add(amino);
				
			}
				
		}
		
		return this.proteins;
		
	}
	
	public ArrayList<ArrayList<String>> run() {
		
		this.templateStrandToCodingStrand();
		this.codingStrandToMessengerRNA();
		this.messengerRNAToAminoAcidSequence();
		return this.aminoAcidSequenceToProteins();
		
	}
	
	public static String concatenate(ArrayList<String> arrayList, String delimiter) {
		
		String concat = "";
		
		for(String str: arrayList) {
			
			concat += str + delimiter;
			
		}
		
		//System.out.println(concat.length());
		
		if(concat.length() > 0) {
			
			return concat.substring(0, concat.length() - 1);
			
		} else {
			
			return concat;
			
		}
		
	}
	
	public String toString() {
		
		String str = "";
		run();
		
		str += "Template Strand: " + this.templateStrand;
		str += "\nCoding Strand: " + this.codingStrand;
		str += "\nMessenger RNA: " + this.messengerRNA;
		str += "\nAmino Acid Sequence: " + concatenate(this.aminoAcidSequence, "-");
		str += "\nProteins:";
		
		int count = 0;
		for(ArrayList<String> subProtein: proteins) {
			
			count++;
			str += "\n\t" + count + ": [" + concatenate(subProtein, "-") + "] (" + subProtein.size() + ")";
			
		}
		
		return str;
		
	}

}
